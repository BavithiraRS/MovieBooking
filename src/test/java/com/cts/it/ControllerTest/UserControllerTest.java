package com.cts.it.ControllerTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.it.controller.UserController;
import com.cts.it.exception.HousefullException;
import com.cts.it.exception.IdNotFoundException;
import com.cts.it.model.ErrorDetail;
import com.cts.it.model.Theatre;
import com.cts.it.model.User;
import com.cts.it.repository.UserRepository;
import com.cts.it.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;



    @Test
    void testGetAllUsers_Positive() {
       
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        when(userService.getAllUsers()).thenReturn(expectedUsers);

       
        List<User> actualUsers = userController.getAllUsers();

       
        assertEquals(expectedUsers, actualUsers);
    }
    @Test
    void testGetAllUsers_Negative() {
     
        when(userService.getAllUsers()).thenReturn(null);

        List<User> actualUsers = userController.getAllUsers();
        
     
        assertNull(actualUsers);
    }

}
