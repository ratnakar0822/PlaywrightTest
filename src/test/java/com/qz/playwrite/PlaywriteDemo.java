package com.qz.playwrite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywriteDemo {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.google.com/");
            System.out.println(page.title());
            assertThat(page).hasTitle(Pattern.compile("Google"));
            //  page.locator("input[id=\"input\"]").fill("Playwright");
            //  page.locator("input[value=\"Google Search\"]").click();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}