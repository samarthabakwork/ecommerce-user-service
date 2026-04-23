package com.ecommerce.UserService.services.admin;

import com.ecommerce.UserService.dto.response.UserResponseDTO;
import com.ecommerce.UserService.entities.User;
import com.ecommerce.UserService.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    private User getUser(Long id){
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public UserResponseDTO activateUser(Long id) {
        User user=getUser(id);
        user.setActive(true);
        User savedUser=userRepo.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO deactivateUser(Long id) {
        User user=getUser(id);
        user.setActive(false);
        User savedUser=userRepo.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user->modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user=getUser(id);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
    User user=getUser(id);
    userRepo.delete(user);
    }
}
