package com.hello.mimi.standard;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Controller
public class MimiController {

    @GetMapping("/chkMessage")
    public String mimiIndex(@RequestParam(defaultValue = "ko") String lang, HttpSession session){
        System.out.println("lang----" + lang);

        return "chkMessage";
    }

    @PostMapping("/changeLanguage")
    public String changeLanguage(@RequestParam(defaultValue = "ko") String lang,
                                 @RequestParam String currentLoc,
                                 HttpSession session){
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));

        return "redirect:"+currentLoc;
    }

}
