package com.javarush.service;

import com.javarush.dao.LanguageDao;
import com.javarush.entity.Language;
import com.javarush.exception.NotFoundException;

public class LanguageService {
    private final LanguageDao languageDao;

    public LanguageService() {
        this.languageDao = new LanguageDao();
    }

    public Language getLanguageByName(String name) {
        return languageDao.getLanguageByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Language with name %s not found", name)));
    }
}
