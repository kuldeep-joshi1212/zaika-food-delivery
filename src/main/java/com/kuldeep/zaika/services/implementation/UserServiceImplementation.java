package com.kuldeep.zaika.services.implementation;

import com.kuldeep.zaika.ZaikaConfigProperties;
import com.kuldeep.zaika.enities.Token;
import com.kuldeep.zaika.enities.User;
import com.kuldeep.zaika.enities.dto.UserLoginDto;
import com.kuldeep.zaika.enums.UserType;
import com.kuldeep.zaika.exceptions.UserException;
import com.kuldeep.zaika.repositories.TokenRepository;
import com.kuldeep.zaika.repositories.UserRepository;
import com.kuldeep.zaika.security.jwt.JwtTokenService;
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
    private final JwtTokenService jwtTokenService;
    private final TokenRepository tokenRepository;
    private final ZaikaConfigProperties zaikaConfigProperties;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtTokenService jwtTokenService,
                                     TokenRepository tokenRepository,
                                     ZaikaConfigProperties zaikaConfigProperties){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.tokenRepository = tokenRepository;
        this.zaikaConfigProperties = zaikaConfigProperties;
    }
    public User signUp(User user) throws UserException {
        if(Boolean.FALSE.equals(validateUser(user))){
            throw new UserException("invalid user object");
        }
        User usernameDuplicate=userRepository.findByUsername(user.getUsername());
        if(Objects.nonNull(usernameDuplicate)){
            throw new UserException("username already exist");
        }
        User emailDuplicate=userRepository.findByEmail(user.getEmail());
        if(Objects.nonNull(emailDuplicate)){
            throw new UserException("email already exist");// make email and username unique instead to fetching from database multiple times
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
    public String login(UserLoginDto userLoginDto) throws UserException {
        if(Objects.isNull(userLoginDto)){
            throw new UserException("credentials ");
        }
        if(Boolean.FALSE.equals(StringUtils.hasText(userLoginDto.getUsername()))){
            throw new UserException("username");
        }
        if(Boolean.FALSE.equals(StringUtils.hasText(userLoginDto.getPassword()))){
            throw new UserException("password");
        }
        User loginUser=userRepository.findByUsername(userLoginDto.getUsername());
        if(Objects.isNull(loginUser)){
            throw new UserException("user not found");
        }
        Token token=new Token();
        token.setUserID(loginUser.getId());
        token.setToken(jwtTokenService.createToken(loginUser.getUsername()));
        Token savedToken=tokenRepository.save(token);
        if(passwordEncoder.matches(userLoginDto.getPassword(), loginUser.getPassword())){
            return savedToken.getToken();
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