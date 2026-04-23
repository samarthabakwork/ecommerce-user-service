package com.ecommerce.UserService.services.user;

import com.ecommerce.UserService.dto.request.UpdateRequestDTO;
import com.ecommerce.UserService.dto.response.UserResponseDTO;
import com.ecommerce.UserService.entities.User;
import com.ecommerce.UserService.exception.UserNotFoundException;
import com.ecommerce.UserService.repositories.UserRepository;
import com.ecommerce.UserService.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal();
        return userRepo.findById(userPrincipal.getUser().getId()).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public UserResponseDTO viewProfile() {
        User user=getCurrentUser();
        return modelMapper.map(user, UserResponseDTO.class);
    }


    @Override
    public UserResponseDTO updateProfile(UpdateRequestDTO dto) {
        User user=getCurrentUser();
        user.setName(dto.getName());
        if(dto.getEmail()!=null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }
        User updated=userRepo.save(user);
        return modelMapper.map(updated, UserResponseDTO.class);
    }

    @Override
    public void deleteAccount() {
        User user=getCurrentUser();
        userRepo.delete(user);
    }
}
