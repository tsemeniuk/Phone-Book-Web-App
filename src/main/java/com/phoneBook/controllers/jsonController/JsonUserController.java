package com.phoneBook.controllers.jsonController;

import com.phoneBook.models.Authorities;
import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import com.phoneBook.service.jsonImpl.JsonAuthoritiesServiceImpl;
import com.phoneBook.service.jsonImpl.JsonContactServiceImpl;
import com.phoneBook.service.jsonImpl.JsonUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Profile("json")
@Controller
public class JsonUserController {
    @Autowired
    private JsonUserServiceImpl userService;
    @Autowired
    private JsonContactServiceImpl contactService;
    @Autowired
    private JsonAuthoritiesServiceImpl authoritiesService;
    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;


    @RequestMapping("/")
    public String welcome(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "welcome";
        } else {
            List<Contact> contacts = contactService.findAllByUsername(principal.getName());

            model.addAttribute("user", user);
            model.addAttribute("contacts", contacts);
            return "welcome";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Неправельный логин и/или пароль.");
        }
        if (logout != null) {
            model.addAttribute("msg", "Выход из учетной записи успешно.");
        }
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSuccess() {
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, HttpServletRequest request,
                                 @RequestParam(value = "error", required = false) String error) {
        if (result.hasErrors()) {
            return "register";
        } else {
            //Добавляем по умолчанию статус - активный
            user.setEnabled(true);
            userService.save(user);

            //Добавляем по умолчанию роль - пользователь
            Authorities authorities = new Authorities();
            authorities.setUsername(user.getUsername());
            authorities.setAuthority("ROLE_USER");
            authoritiesService.save(authorities);


            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication auth = authenticationManager.authenticate(token);
            // Place the new Authentication object in the security context.
            SecurityContextHolder.getContext().setAuthentication(auth);

            //this step is import, otherwise the new login is not in session which is required by Spring Security
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            model.addAttribute("user", user);
            return "redirect:/";
        }
    }
}