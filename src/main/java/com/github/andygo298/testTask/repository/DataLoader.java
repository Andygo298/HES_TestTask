package com.github.andygo298.testTask.repository;

import com.github.andygo298.testTask.model.User;
import com.github.andygo298.testTask.model.enums.Role;
import com.github.andygo298.testTask.model.enums.Status;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(
                User.builder()
                        .username("admin")
                        .password("admin")
                        .firstName("Andrei")
                        .lastName("Lazouski")
                        .isActive(Status.ACTIVE)
                        .createdAt(LocalDateTime.now().toLocalDate())
                        .roles(new HashSet<Role>() {{ add(Role.ADMIN); }})
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("user")
                        .password("user")
                        .firstName("Test_user")
                        .lastName("Userov")
                        .isActive(Status.ACTIVE)
                        .createdAt(LocalDateTime.now().toLocalDate())
                        .roles(new HashSet<Role>() {{ add(Role.USER); }})
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("ivanTest")
                        .password("ivan")
                        .firstName("ivan")
                        .lastName("ivanov")
                        .isActive(Status.ACTIVE)
                        .createdAt(LocalDateTime.now().toLocalDate())
                        .roles(new HashSet<Role>() {{ add(Role.USER); }})
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("petr")
                        .password("petr")
                        .firstName("petrTest")
                        .lastName("petrov")
                        .isActive(Status.INACTIVE)
                        .createdAt(LocalDateTime.now().toLocalDate())
                        .roles(new HashSet<Role>() {{ add(Role.USER); }})
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("alex")
                        .password("alex")
                        .firstName("alexTest")
                        .lastName("alexandrov")
                        .isActive(Status.INACTIVE)
                        .createdAt(LocalDateTime.now().toLocalDate())
                        .roles(new HashSet<Role>() {{ add(Role.USER); }})
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("oleg")
                        .password("oleg")
                        .firstName("olegTest")
                        .lastName("olegov")
                        .isActive(Status.ACTIVE)
                        .createdAt(LocalDateTime.now().toLocalDate())
                        .roles(new HashSet<Role>() {{ add(Role.USER); }})
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("maria")
                        .password("maria")
                        .firstName("mariaTest")
                        .lastName("petrova")
                        .isActive(Status.INACTIVE)
                        .createdAt(LocalDateTime.now().toLocalDate())
                        .roles(new HashSet<Role>() {{ add(Role.USER); }})
                        .build()
        );
    }
}
