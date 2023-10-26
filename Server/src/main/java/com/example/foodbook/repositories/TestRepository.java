/*
package com.example.foodbook.repositories;

import com.example.foodbook.models.Person;
import com.example.foodbook.models.Role;
import lombok.Data;
import org.springframework.context.support.BeanDefinitionDsl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TestRepository {
    private List<Person> personList = new ArrayList<>();
    {
        Person person = new Person();
        person.setId(1L);
        person.setEmail("email@mail.ru");
        person.setDescription("Some description");
        person.setPassword("12345678");
        person.setUsername("user");
        Set<Role> roleSet= new HashSet<>();
        */
/*roleSet.add(Role);*//*

        person.setRoles(roleSet);

        Person person1 = new Person();
        person1.setId(2L);
        person1.setEmail("gmail@mail.ru");
        person1.setDescription("Some description 2");
        person1.setPassword("12345678");
        person1.setUsername("admin");
        Set<Role> roleSet2= new HashSet<>();
        */
/*roleSet2.add(Role.ADMIN);*//*

        person.setRoles(roleSet2);

    }

}
*/
