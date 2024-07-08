package com.hello.mimi.util;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Component
@Getter
public class Language {
    private String lang = SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;



    // 바꿀 내용이 담겨있는 객체를 가져와서, session에 로케일 변경해서 언어를 변경하는 과정을 함.
    public void changeLanguage(LanguageDTO languageDTO, HttpSession session){
        lang = languageDTO.getLang();
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));
    }




}
