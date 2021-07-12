package com.bb.recruitment.pages;

import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.WebDriverRunner;

public abstract class PageObject<T extends PageObject<T>> {

    protected PageObject() {
        waitToBeLoaded();
        page(returnThis());
    }

    protected abstract void waitToBeLoaded();

    public String getCurrentURL() {
        return WebDriverRunner.url();
    }

    public abstract T returnThis();
}
