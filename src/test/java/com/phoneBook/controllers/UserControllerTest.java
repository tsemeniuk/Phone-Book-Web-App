package com.phoneBook.controllers;

import com.phoneBook.controllers.mysqlContrller.UserController;
import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import com.phoneBook.service.mysqlImpl.AuthoritiesServiceImpl;
import com.phoneBook.service.mysqlImpl.ContactServiceImpl;
import com.phoneBook.service.mysqlImpl.UserServiceImpl;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    @Mock
    private ContactServiceImpl contactService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private AuthoritiesServiceImpl authoritiesService;
    @Mock
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;

    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testWelcome() throws Exception {
        //prepare
        User user = new User();
        user.setUsername("user");
        user.setEnabled(true);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());
        contacts.add(new Contact());

        //when
        Mockito.when(userService.findByUsername(user.getUsername())).thenReturn((User) user);
        Mockito.when(contactService.findAllByUsername(user.getUsername())).thenReturn((List<Contact>) contacts);

        //then
        mockMvc.perform(get("/").principal(new UserPrincipal("user")))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("user", instanceOf(User.class)))
                .andExpect(model().attribute("contacts", hasSize(3)));
        //verify
        verify(userService, times(1)).findByUsername(user.getUsername());
        verify(contactService, times(1)).findAllByUsername(user.getUsername());
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login").param("error", "error").param("logout", "logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", instanceOf(String.class)))
                .andExpect(model().attribute("msg", instanceOf(String.class)));
        ;
    }

    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testRegisterSubmit() throws Exception {
        //prepare
        BindingResult result = mock(BindingResult.class);
        User user = new User("user","pass","firstName","secondName","lastName");
        user.setEnabled(true);

        //when
        Mockito.when(result.hasErrors()).thenReturn(false);

        //then
        mockMvc.perform(post("/register").requestAttr("user", instanceOf(String.class)).param("error", "error"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", instanceOf(User.class)));
    }
}