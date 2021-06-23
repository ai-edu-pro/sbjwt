package org.zerock.sbjwt.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.sbjwt.entity.ERole;
import org.zerock.sbjwt.entity.Role;
import org.zerock.sbjwt.entity.User;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {

        //$2a$10$Pl8wTMHcTFQ2XJVFTdCa/Oit0TRG.LRBA57m89t05rDJZ9pFmv/Iy
        System.out.println(passwordEncoder.encode("1111"));

    }

    @Test
    public void testInsertOne() {

        User user = User.builder()
                .email("user00@aaa.com")
                .username("USER00")
                .password(passwordEncoder.encode("1111"))
                .build();

        user.addRole(Role.builder().name(ERole.ROLE_ADMIN).build());
        user.addRole(Role.builder().name(ERole.ROLE_USER).build());
        user.addRole(Role.builder().name(ERole.ROLE_MODERATOR).build());
        userRepository.save(user);
    }

    @Test
    public void testInsertDummies() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            User user = User.builder()
                    .email("user"+i+"@aaa.com")
                    .username("USER" + i)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            if(i >= 90){
                user.addRole(Role.builder().name(ERole.ROLE_ADMIN).build());
            }

            if(i >= 80){
                user.addRole(Role.builder().name(ERole.ROLE_MODERATOR).build());
            }

            user.addRole(Role.builder().name(ERole.ROLE_USER).build());

            userRepository.save(user);

        });
    }
    @Test
    public void testRead(){

        String email = "user00@aaa.com";


        User user = userRepository.login(email);

        System.out.println(user);

    }
}
