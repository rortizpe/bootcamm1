package com.example.example2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CustomLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

  //configura la lista de idiomas -> por defecto sera Epañol
  List<Locale> LOCALES = Arrays.asList(
          new Locale("es"),
          new Locale("en"));

  //verifica si en el header Accept-Language esta presente
  //configuracion regional
  @Override
  public Locale resolveLocale(HttpServletRequest request) {
    String headerLang = request.getHeader("Accept-Language");
    return headerLang == null || headerLang.isEmpty()
            ? Locale.getDefault()
            : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
  }

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
    rs.setBasename("messages");
    rs.setDefaultEncoding("UTF-8");
    rs.setUseCodeAsDefaultMessage(true);
    return rs;
  }
}