package com.phoneBook.controllers;

import com.phoneBook.models.Authorities;
import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import com.phoneBook.repository.AuthoritiesRepository;
import com.phoneBook.repository.ContactRepository;
import com.phoneBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @RequestMapping("/")
    public String welcome(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Contact> contacts = contactRepository.findAllByUsername(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("contacts", contacts);
        return "welcome";
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


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
//            request.getSession().invalidate();
        }*/
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSuccess(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute("user") User user, Model model, HttpServletRequest request,
                                 @RequestParam(value = "error", required = false) String error) {

        if (error != null) {
            model.addAttribute("error", "Неправельный логин и/или пароль.");
            return "register";
        }
        user.setEnabled(true);
        userRepository.save(user);

        Authorities authorities = new Authorities();
        authorities.setUsername(user.getUsername());
        authorities.setAuthority("ROLE_USER");
        authoritiesRepository.save(authorities);

//        request.login(user.getUsername(), user.getPassword());


        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        // Place the new Authentication object in the security context.
        SecurityContextHolder.getContext().setAuthentication(auth);

        //this step is import, otherwise the new login is not in session which is required by Spring Security
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());


        model.addAttribute("user", user);
        return "redirect:/";
    }
//        return "redirect:/";


}