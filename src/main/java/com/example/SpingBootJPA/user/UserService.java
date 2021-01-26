package com.example.SpingBootJPA.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final  UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser(){
       return userRepository.findAll();
    }

    public void addUser(User user){
        Optional<User> studentByEmail = userRepository
                .findStudentByEmail(user.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new IllegalStateException("User id" + userId + "does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId,String name,String email){

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("User id" + userId + "does not exist")
        );
        if (name != null){
            user.setName(name);
        }
        if(email != null){
            user.setEmail(email);
        }

    }
}
