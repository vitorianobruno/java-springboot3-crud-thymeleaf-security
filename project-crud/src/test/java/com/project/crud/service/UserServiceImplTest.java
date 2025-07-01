package com.project.crud.service;

import com.project.crud.model.User;
import com.project.crud.repository.UserRepository;
import com.project.crud.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testFindAll() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(1L, userService.findById(1L).getId());
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("Test");
        when(userRepository.save(any(User.class))).thenReturn(user);
        assertEquals("Test", userService.save(user).getName());
    }

    @Test
    public void testDeleteById() {
        userService.deleteById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}