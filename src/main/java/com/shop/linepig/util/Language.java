package com.shop.linepig.util;

import lombok.Getter;

import java.util.Locale;
@Getter
public enum Language {

    BASIC("한국어", Locale.KOREA),
    ENGLISH("English", Locale.ENGLISH),
    CHINA("中文 (简体)",Locale.CHINESE),
    JAPANESE("日本",Locale.JAPANESE);

    private String lang;
    private Locale locale;

    Language(String lang, Locale locale) {
        this.lang = lang;
        this.locale = locale;
    }

}
