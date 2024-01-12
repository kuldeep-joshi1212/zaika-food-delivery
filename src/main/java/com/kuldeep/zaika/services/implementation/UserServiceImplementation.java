package com.kuldeep.zaika.services.implementation;

import com.kuldeep.zaika.enities.User;
import com.kuldeep.zaika.enums.UserType;
import com.kuldeep.zaika.exceptions.UserException;
import com.kuldeep.zaika.repositories.UserRepository;
import com.kuldeep.zaika.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User signUp(User user) throws UserException {
        if(Boolean.FALSE.equals(validateUser(user))){
            throw new UserException("invalid user object");
        }
        User usernameDuplicate=userRepository.findByUsername(user.getUsername());
        if(Objects.nonNull(usernameDuplicate)){
            return usernameDuplicate;
        }
        User emailDuplicate=userRepository.findByEmail(user.getEmail());
        if(Objects.nonNull(emailDuplicate)){
            return emailDuplicate;
        }
        if(Objects.isNull(user.getUsertype())){
            user.setUsertype(UserType.CUSTOMER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser=userRepository.save(user);
        return savedUser;
    }

    public User getUserById(Long id) throws UserException {
        if (id == null ||
        Boolean.FALSE.equals(userRepository.existsById(id))){
            throw new UserException("user not found");
        }
        User user=userRepository.findUserByid(id);
        return user;

    }
    public User login(User user) throws UserException {
        if(Objects.isNull(user)){
            throw new UserException("credentials ");
        }
        if(Boolean.FALSE.equals(StringUtils.hasText(user.getUsername()))){
            throw new UserException("username");
        }
        if(Boolean.FALSE.equals(StringUtils.hasText(user.getPassword()))){
            throw new UserException("password");
        }
        User loginUser=userRepository.findByUsername(user.getUsername());
        if(Objects.isNull(loginUser)){
            throw new UserException("user not found");
        }
        if(passwordEncoder.matches(user.getPassword(), loginUser.getPassword())){
            return user;
        }else{
            throw new UserException("wrong password");
        }

    }

    private boolean validateUser(User user) throws UserException{
        if(Objects.isNull(user)
                || Boolean.FALSE.equals(StringUtils.hasText(user.getUsername()))
                || Boolean.FALSE.equals((StringUtils.hasText(user.getEmail())))
                || Boolean.FALSE.equals(StringUtils.hasText(user.getPassword()))
                || Boolean.FALSE.equals(StringUtils.hasText(user.getFirstname()))
                || Boolean.FALSE.equals(StringUtils.hasText(user.getLastname()))){
            throw new UserException("invalid user object");
        }
        return Boolean.TRUE;
    }

}