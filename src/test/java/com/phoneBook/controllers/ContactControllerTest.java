package com.phoneBook.controllers;

import com.phoneBook.controllers.mysqlContrller.ContactController;
import com.phoneBook.models.Contact;
import com.phoneBook.service.mysqlImpl.ContactServiceImpl;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ContactControllerTest {
    @Mock
    private ContactServiceImpl contactService;
    @InjectMocks
    private ContactController contactController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testAddPage() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    @Test
    public void testAdd() throws Exception {
        //prepare
        BindingResult result = mock(BindingResult.class);

        //when
        when(result.hasErrors()).thenReturn(false);

        //then
        mockMvc.perform(post("/add").principal(new UserPrincipal("user")).requestAttr("contact", String.class)
                .param("firstName", "")
                .param("secondName", "secondName")
                .param("lastName", "lastName")
                .param("phoneMobile", "(067)171-19-18")
                .param("phoneHome", "AAA")
                .param("address", "")
                .param("email", ""))

                .andDo(print())
                .andExpect(view().name("add"))
                .andExpect(model().attributeHasFieldErrors("contact", "firstName"))
                .andExpect(model().attributeHasFieldErrors("contact", "phoneHome"))

                .andExpect(status().isOk())
                .andExpect(model().attributeExists("contact"));
    }

    @Test
    public void testEdit() throws Exception {
        //prepare
        Contact contact = new Contact();
        int Id = 1;

        //when
        Mockito.when(contactService.findOne(Id)).thenReturn(contact);

        //then
        mockMvc.perform(get("/edit/{id}", Id))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("contact", instanceOf(Contact.class)));
        //verify
        verify(contactService, times(1)).findOne(Id);
    }

    @Test
    public void testEdit1() throws Exception {
        //prepare
        Contact contact = new Contact();
        int Id = 1;

        //then
        mockMvc.perform(post("/edit/{id}", Id).principal(new UserPrincipal("user")).requestAttr("contact", String.class)
                .param("firstName", "")
                .param("secondName", "secondName")
                .param("lastName", "lastName")
                .param("phoneMobile", "(067)171-19-18")
                .param("phoneHome", "AAA")
                .param("address", "")
                .param("email", ""))

                .andExpect(status().isOk())
                .andExpect(model().attributeExists("contact"));
    }

    @Test
    public void testDelete() throws Exception {
        //prepare
        int id = 1;

        //then
        mockMvc.perform(post("/delete/{id}", id).param("id", String.valueOf(id)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}