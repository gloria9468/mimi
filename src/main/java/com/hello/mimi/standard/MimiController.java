package com.hello.mimi.standard;

import com.hello.mimi.util.Language;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
public class MimiController {
    @Autowired
    Language language;

    @GetMapping("/chkMessage")
    public String mimiIndex(HttpSession session, Model model){
        model.addAttribute("changeLang", language);
        return "chkMessage";
    }

    // 어떻게는 모르겠고, 언어를 바꾸라는 요청이 들어옴.
    @ResponseBody
    @PostMapping("/changeLanguage")
    public void changeLanguage(@ModelAttribute Language changeLang, HttpSession session){
        language.changeLanguage(changeLang, session);
    }

}
