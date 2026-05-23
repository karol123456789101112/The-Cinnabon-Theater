package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.Dto.AppUserDto;
import org.example.service.AppUserService;
import org.example.service.EmailService;
import org.example.service.ReCaptchaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.example.domain.AppUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AppUserController {

    private static final Logger log = LoggerFactory.getLogger(AppUserService.class);

    private final AppUserService appUserService;
    private final ReCaptchaService reCaptchaService;

    public AppUserController(AppUserService appUserService, ReCaptchaService reCaptchaService) {
        this.appUserService = appUserService;
        this.reCaptchaService = reCaptchaService;
    }

    @RequestMapping(value = "/appUsers")
    public ModelAndView showAppUsers() {
        log.info("brln");
        return new ModelAndView("appUser", "appUserDto", new AppUserDto());
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@ModelAttribute("appUserDto") AppUserDto appUserDto, HttpServletRequest request) {

        log.info("KROK cos - saved ufffser");

        if(reCaptchaService.verify(request.getParameter("g-recaptcha-response"))){
            appUserService.addAppUser(appUserDto);
        }
        return "redirect:appUsers";
    }

}


