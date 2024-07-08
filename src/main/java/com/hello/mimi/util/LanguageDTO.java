package com.hello.mimi.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@Getter @Setter
public class LanguageDTO {
    private String lang = SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

}
