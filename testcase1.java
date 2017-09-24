package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Testcase1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.n11.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCase1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Giriş Yap")).click();
    driver.findElement(By.xpath("//form[@id='loginForm']/div[4]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | Facebook | 5000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectPopUp | Facebook | ]]
    driver.findElement(By.xpath("//input[@id='email']")).clear();
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("cansuoner12@gmail.com");
    driver.findElement(By.xpath("//input[@id='pass']")).clear();
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("şifre1");
    driver.findElement(By.id("u_0_0")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | Giriş Yap - n11.com | ]]
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("n11.com - Alışverişin Uğurlu Adresi".equals(driver.getTitle())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("(//a[contains(text(),'Kitap, Müzik, Film, Oyun')])[2]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("(//a[contains(text(),'Kitap, Müzik, Film, Oyun')])[2]")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Kitap')])[5]")).click();
    driver.findElement(By.linkText("Yazarlar")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("authorsList"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    Number listCount = driver.findElements(By.xpath("//div[@id='authorsList']//div//ul/li")).size();
    // ERROR: Caught exception [ERROR: Unsupported command [getEval |  ${listCount} <= 80 ? true : false   | ]]
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
