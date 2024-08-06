package com.hello.mimi.util.language;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.io.IOException;
import java.util.Locale;

@ControllerAdvice
public class Language {

    private final SessionLocaleResolver localeResolver;

    public Language(SessionLocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @ModelAttribute("changeLanguage")
    public LanguageDTO changeLanguage(LanguageDTO languageDTO, HttpSession session, HttpServletRequest request){

        LanguageDTO rtnLanguageDTO = (LanguageDTO) session.getAttribute("language");

        String lang = null;

        if (rtnLanguageDTO == null) {
            lang = localeResolver.resolveLocale(request).toString();
            rtnLanguageDTO = new LanguageDTO(lang);
        } else if (languageDTO != null && languageDTO.getLang() != null) {
            lang = languageDTO.getLang();
            localeResolver.setDefaultLocale(new Locale(lang));
            rtnLanguageDTO = languageDTO;
        }else {
            lang = rtnLanguageDTO.getLang();
        }


        String test = request.getRequestURI();
        if(lang == null) {
            System.out.println("else---" + rtnLanguageDTO.getLang());
            throw new IllegalArgumentException("lang == null: " + test);
        }
        System.out.println("request.getRequestURI() --- " + test);

        session.setAttribute("language", rtnLanguageDTO);
        return rtnLanguageDTO;
    }

    @ModelAttribute("languageEnum")
    public LanguageEnum[] languageEnum() {
        //System.out.println("languageEnum----");
        return LanguageEnum.values();
    }



}
