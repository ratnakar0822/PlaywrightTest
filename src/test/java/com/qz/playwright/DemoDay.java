package com.qz.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import java.awt.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DemoDay {
    private static final boolean HEADLESS = false;
    private final static String APP_URL = "http://demosite.senseit360eval.com/";
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @Test
    void testUntitledTestCase() throws InterruptedException {
        page.navigate(APP_URL);
        System.out.println("Page title - " + page.title());
        assertThat(page).hasTitle(Pattern.compile("Dashboard"));
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Tasks")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("List")).click();
        page.locator("#kanbanModal").click();
        Thread.sleep(2000);
        page.locator("#dropDownButtonForList").click();
        page.locator("#person1").click();
        page.locator("#summary").type("Website for demo day");
        page.locator("#desc").type("website for demo day");
        Thread.sleep(3000);
        page.locator("#modalSave").click();
        Thread.sleep(3000);
    }

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setTimeout(60000).setHeadless(HEADLESS));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
