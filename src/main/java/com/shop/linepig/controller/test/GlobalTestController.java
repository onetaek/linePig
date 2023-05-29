package com.shop.linepig.controller.test;


import com.shop.linepig.dto.LocaleDto;
import com.shop.linepig.util.Language;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GlobalTestController {

    private final LocaleResolver localeResolver;

    @GetMapping("/test/globalMessage")
    public String home(@ModelAttribute LocaleDto localeDto, Model model) {
        model.addAttribute("localeDto",localeDto);
        model.addAttribute("languages", Language.values());



        return "/test/globalMessage";
    }

    @PostMapping("/test/globalMessage")
    public String changeLocale(@ModelAttribute LocaleDto localeDto,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        Locale locale = localeDto.getLocale();
        localeResolver.setLocale(request, response, locale);

        return "redirect:/test/globalMessage";
    }

    @GetMapping("/test/globalTest")
    public String testPage() {
        return "/test/globalTest";
    }

}
