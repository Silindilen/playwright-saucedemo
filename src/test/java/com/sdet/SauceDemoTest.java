package com.sdet;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterEach
    void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    void testLoginAndVerifyProductsPage() {
        // Navigate to Sauce Demo
        page.navigate("https://www.saucedemo.com/v1/");

        // Fill in login credentials
        page.fill("input[id='user-name']", "standard_user");
        page.fill("input[id='password']", "secret_sauce");

        // Click login button
        page.click("input[id='login-button']");

        // Wait for the products page to load
        page.waitForLoadState();

        // Assert that the Products page is visible
        assertTrue(page.isVisible(".inventory_list"), "Products page should be visible after login");
    }
     
    @Test
    void testAddItemToCart() {
        page.navigate("https://www.saucedemo.com/v1/");
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
        
        page.click("#add-to-cart-sauce-labs-backpack");
        assertTrue(page.isVisible(".shopping_cart_badge"));
        assertEquals("1", page.locator(".shopping_cart_badge").textContent());
    }

    @Test
    void testLogout() {
        page.navigate("https://www.saucedemo.com/v1/");
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
        
        page.click("#react-burger-menu-btn");
        page.click("#logout_sidebar_link");
        assertTrue(page.isVisible("#login-button"));
    }

}

