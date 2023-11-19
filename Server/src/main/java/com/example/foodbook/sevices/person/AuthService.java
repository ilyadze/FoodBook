package com.example.foodbook.sevices.person;

import com.example.foodbook.dto.person.PersonDTO;
import com.example.foodbook.dto.person.RegistrationUserDTO;
import com.example.foodbook.exceptions.AppError;
import com.example.foodbook.exceptions.LocalException;
import com.example.foodbook.models.person.Person;
import com.example.foodbook.requests.jwt.JwtRequest;
import com.example.foodbook.response.jwt.JwtResponse;
import com.example.foodbook.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PersonService personService;
    private final JWTUtil jwtUtil;
    private  final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
        } catch(BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),"Неправильный логин или пароль"),HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);
        String refreshToken= jwtUtil.generateRefreshToken(userDetails);
        refreshStorage.put(userDetails.getUsername(), refreshToken);
        return ResponseEntity.ok(new JwtResponse(token,refreshToken));
    }

    public ResponseEntity<PersonDTO> createNewPerson(@RequestBody RegistrationUserDTO registrationUserDTO)  {
        if(!registrationUserDTO.getPassword().equals(registrationUserDTO.getConfirmPassword())){
            throw new LocalException(HttpStatus.BAD_REQUEST,"ПАроли не совпадают");
           /* tphoneNUmbehrow  new AppError(HttpStatus.BAD_REQUEST.value(), "ароли не совпадают");*/
            /*return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"),HttpStatus.BAD_REQUEST);
       */ }
        try{
            personService.findByUsername(registrationUserDTO.getUsername());
            throw new LocalException(HttpStatus.BAD_REQUEST,"Такое имя пользователя уже зарегистрировано");
        }
        catch (UsernameNotFoundException e){
            Person person = personService.createPerson(registrationUserDTO);
            return ResponseEntity.ok(new PersonDTO(person.getId(), person.getUsername(), person.getEmail()));
        }

       //todo проверку почты
       }
    public JwtResponse getAccessToken( String refreshToken) {
        if (jwtUtil.validateRefreshToken(refreshToken)) {

            final String login = jwtUtil.getUsernameFromRefreshToken(refreshToken);
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final UserDetails user = personService.loadUserByUsername(login);
                final String accessToken = jwtUtil.generateToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }
    public JwtResponse refresh( String refreshToken) {
        if (jwtUtil.validateRefreshToken(refreshToken)) {

            final String login = jwtUtil.getUsernameFromRefreshToken(refreshToken);
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final UserDetails user = personService.loadUserByUsername(login);
                final String accessToken = jwtUtil.generateToken(user);
                final String newRefreshToken = jwtUtil.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        return new JwtResponse(null, null);
    }
    /*public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }*/

}
