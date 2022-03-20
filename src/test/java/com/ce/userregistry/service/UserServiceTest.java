package com.ce.userregistry.service;

import com.ce.userregistry.entity.User;
import com.ce.userregistry.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser() {
        User user = mock(User.class);

        userService.addUser(user);

        verify(userRepository).save(user);
    }

    @Test
    void findUsersBySurname() {
        String surname = "Something";

        when(userRepository.find(surname)).thenReturn(List.of(mock(User.class)));

        List<User> users = userService.findUsersBySurname(surname);

        assertNotNull(users);
        assertEquals(users.size(), 1);
        verify(userRepository).find(surname);
    }

    @Test
    void throwsAnExceptionIfNullSurnameProvided() {
        assertThrows(IllegalArgumentException.class, () -> userService.findUsersBySurname(null));
    }
}
