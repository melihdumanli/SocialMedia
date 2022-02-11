package com.bilgeadam.language;

public class LanguageCreator {

    public static Language language;
    public static ALanguage getLanguage(Language language){
        switch (language){
            case TR: return new Tr();
            case FR: return new Fr();
            case DE: return new De();
            case EN: return new En();
            default:return new En();
        }
    }
}
