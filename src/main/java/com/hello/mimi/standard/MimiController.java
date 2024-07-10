package com.hello.mimi.standard;

import com.hello.mimi.util.language.Language;
import com.hello.mimi.util.language.LanguageDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MimiController {

    @Autowired
    Language language;



    // 어떻게는 모르겠고, 언어를 바꾸라는 요청이 들어옴.
    @ResponseBody
    @PostMapping("/changeLanguage")
    public void changeLanguage(@ModelAttribute LanguageDTO languageDTO, HttpSession session, HttpServletRequest request){
        language.changeLanguage(languageDTO, session, request);
    }


}
