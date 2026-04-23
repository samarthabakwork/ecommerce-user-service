package com.ecommerce.UserService.services.auth;

import com.ecommerce.UserService.dto.request.LoginRequestDTO;
import com.ecommerce.UserService.dto.request.RegisterRequestDTO;
import com.ecommerce.UserService.dto.response.LoginResponseDTO;
import com.ecommerce.UserService.dto.response.RegisterResponseDTO;
import com.ecommerce.UserService.entities.Role;
import com.ecommerce.UserService.entities.User;
import com.ecommerce.UserService.exception.DuplicateResourceException;
import com.ecommerce.UserService.exception.InvalidCredentialsException;
import com.ecommerce.UserService.repositories.UserRepository;
import com.ecommerce.UserService.security.JwtUtil;
import com.ecommerce.UserService.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    ModelMapper modelMapper=new ModelMapper();

    //register
    public RegisterResponseDTO register(RegisterRequestDTO dto){
        if(userRepo.existsByEmail(dto.getEmail())){
            throw new DuplicateResourceException("email already in use: "+dto.getEmail());
        }

        User user=modelMapper.map(dto,User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        User savedUser=userRepo.save(user);
        return modelMapper.map(savedUser,RegisterResponseDTO.class);
    }


    //login
    public LoginResponseDTO login(LoginRequestDTO dto){
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );

            UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal();
            String token=jwtUtil.generateToken(userPrincipal.getUsername());
            LoginResponseDTO response=new LoginResponseDTO();
            response.setName(userPrincipal.getName());
            response.setRole(userPrincipal.getRole());
            response.setToken(token);

            return response;
        }
        catch(BadCredentialsException ex){
            throw new InvalidCredentialsException("Invalid email or password");
        }



    }
}
