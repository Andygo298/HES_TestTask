package com.github.andygo298.testTask.service;

import com.github.andygo298.testTask.controller.request.EditUserRq;
import com.github.andygo298.testTask.model.User;
import com.github.andygo298.testTask.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user;
        }
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByName(String name){
        return userRepository.findByUsername(name);
    }

    @Transactional
    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (Objects.nonNull(userFromDb)) {
            return false;
        }

        User userToSave = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getRoles())
                .isActive(user.getIsActive())
                .createdAt(LocalDateTime.now().toLocalDate())
                .build();

        userRepository.save(userToSave);
        return true;
    }

    @Transactional
    public boolean editUser(EditUserRq editUser, Integer userId){
        User userFromDb = userRepository.findById(userId).orElse(null);
        if (Objects.nonNull(userFromDb)){
            userFromDb.setUsername(editUser.getUsername());
            userFromDb.setFirstName(editUser.getFirstName());
            userFromDb.setLastName(editUser.getLastName());
            userFromDb.setRoles(editUser.getRoles());
            userFromDb.setIsActive(editUser.getIsActive());
            userRepository.save(userFromDb);
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    public Optional<User> findById(Integer userId){
       return userRepository.findById(userId);
    }

    }
