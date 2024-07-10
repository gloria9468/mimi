package com.hello.mimi.util.language;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Getter @Setter
public class LanguageDTO {
    private String lang;

    public LanguageDTO(String lang) {
        this.lang = lang;
    }
}
