# Section 2: Web Application Test Automation

## Assignment Requirement
Automate https://www.saucedemo.com using Playwright. Implement smoke tests for Login, Add to Cart, and Logout functionality.

## Tool: Playwright + Java + JUnit 5

### Why Playwright
1. **Auto-wait mechanism**: Eliminates flaky tests caused by timing issues
2. **Fast execution**: Headless by default, runs ~2x faster than Selenium
3. **Modern web support**: Handles SPAs, shadow DOM, API testing
4. **Debugging**: Built-in trace viewer and video recording on failure

## Test Cases Implemented
| ID | Test Case | Locators Used | Assertion |
| --- | --- | --- | --- |
| **TC01** | Valid Login | `#user-name`, `#password`, `#login-button` | URL contains `/inventory.html` |
| **TC02** | Add to Cart | `text=Add to cart`, `.shopping_cart_badge` | Cart badge shows `1` |
| **TC03** | Logout | `#react-burger-menu-btn`, `#logout_sidebar_link` | URL returns to `/` |

## Best Practices Applied
1. **Browser Context Isolation**: Each test uses `browser.newContext()` for clean state
2. **Playwright Assertions**: `assertThat()` with built-in retry logic
3. **Stable Locators**: Prioritized IDs over XPath for speed
4. **No Thread.sleep()**: Relied on Playwright auto-waiting

## How to Run
```bash
# Install browsers once
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"

# Run tests
mvn test