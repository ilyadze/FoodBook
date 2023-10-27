package com.example.foodbook.sevices;

import com.example.foodbook.dto.PersonDTO;
import com.example.foodbook.dto.RegistrationUserDTO;
import com.example.foodbook.exceptions.AppError;
import com.example.foodbook.models.Person;
import com.example.foodbook.response.JwtRequest;
import com.example.foodbook.response.JwtResponse;
import com.example.foodbook.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PersonService personService;
    private final JWTUtil jwtUtil;
    private  final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        System.out.println("Какого хера");
        /*try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
        } catch(BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),"Неправильный логин или пароль"),HttpStatus.UNAUTHORIZED);
        }*/

        System.out.println("Есть пробитие");

        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        System.out.println(userDetails);
        String token = jwtUtil.generateToken(userDetails);
        System.out.println("token:" + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewPerson(@RequestBody RegistrationUserDTO registrationUserDTO){
        if(!registrationUserDTO.getPassword().equals(registrationUserDTO.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"),HttpStatus.BAD_REQUEST);
        }
        if(personService.findByUsername(registrationUserDTO.getUsername()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такой пользователь уже существует"),HttpStatus.BAD_REQUEST);
        }

        Person person = personService.createPerson(registrationUserDTO);
        return ResponseEntity.ok(new PersonDTO(person.getId(), person.getUsername(), person.getEmail()));
    }
}
