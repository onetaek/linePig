package com.shop.linepig.common.test;


import com.shop.linepig.common.util.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GlobalTestController {

    private final LocaleResolver localeResolver;

    @GetMapping("/test/globalMessage")
    public String home(@ModelAttribute LocaleDto localeDto, Model model) {

        model.addAttribute("languages", Language.values());

        return "/test/globalMessage";
    }

    @PostMapping("/test/globalMessage")
    public String changeLocale(@ModelAttribute LocaleDto localeDto,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        Locale locale = localeDto.getLocale();
        localeResolver.setLocale(request, response, locale);

        log.info("locale = {}",locale);

        return "redirect:/test/globalMessage";
    }

    @GetMapping("/test/globalTest")
    public String testPage() {
        return "/test/globalTest";
    }

}
