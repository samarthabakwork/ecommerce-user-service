package com.ecommerce.UserService.services.auth;

import com.ecommerce.UserService.dto.request.LoginRequestDTO;
import com.ecommerce.UserService.dto.request.RegisterRequestDTO;
import com.ecommerce.UserService.dto.response.LoginResponseDTO;
import com.ecommerce.UserService.dto.response.RegisterResponseDTO;
import com.ecommerce.UserService.entities.User;
import com.ecommerce.UserService.exception.DuplicateResourceException;
import com.ecommerce.UserService.exception.InvalidCredentialsException;
import com.ecommerce.UserService.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    ModelMapper modelMapper=new ModelMapper();

    //register
    public RegisterResponseDTO register(RegisterRequestDTO dto){
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new DuplicateResourceException("email already in use: "+dto.getEmail());
        }

        User user=modelMapper.map(dto,User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser=userRepo.save(user);
        return modelMapper.map(savedUser,RegisterResponseDTO.class);
    }


    //login
    public LoginResponseDTO login(LoginRequestDTO dto){
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email"));

        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("invalid password");
        }

        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),null, Collections.emptyList());

        return modelMapper.map(user,LoginResponseDTO.class);
    }
}
