package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dao.IAuthenticationFacade;
import com.bezkoder.springjwt.models.Profile;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.MessageRepository;
import com.bezkoder.springjwt.repository.ProfileRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @RequestMapping("/foo")
    public Object foo(Principal principal) {
        Authentication authentication = (Authentication) principal;
        Object user = authentication.getPrincipal();

        return user;
    }

    @GetMapping("/findProfile")
    public Profile findProfile(Principal principal) {
      User user = userRepository.findByUsername(principal.getName()).get();

      Profile profile = profileRepository.findProfileByUser(user);

      return profile;
    }

    @GetMapping("/user")
    private User findUserPrincipal(Principal principal){

       return userRepository.findByUsername(principal.getName()).get();
    }


    //Получаем имя роли , который авторизован в springSecurity
    @RequestMapping(value = "/usernamez", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal){

        return principal.getName();
        }

    //Получаем название роли, которая зашла в профиль с помощью Authentication
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
}

