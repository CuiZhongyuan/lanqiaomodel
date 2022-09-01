package com.testng.publics;

import com.testng.common.PubicClickSearchBox;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class DependsOnMethodsCase {
    public WebDriver driver;
    List<RemoteWebElement> webElementList = new ArrayList();
    @BeforeClass
    public void setUp() {
        /**
         * 前置操作加载Chrome驱动
         */
        System.setProperty("webdriver.chrome.driver", this.getClass().getClassLoader().getResource("chromedriver/chromedriver.exe").getPath());
        driver = new ChromeDriver();
        driver.get("https://www.lanqiao.cn/");
        /* 窗口最大化 */
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        /**
         * 后置操作关闭浏览器
         */
        driver.quit();
    }

    @Test
    public void searchBoxOne(){
        driver.findElement(By.linkText("学习")).click();
        RemoteWebElement webElement = (RemoteWebElement) driver.findElement(By.linkText("学习"));
        webElementList.add(webElement);
        System.out.println(webElement);
        driver.findElement(By.id("__BVID__21")).click();
        driver.findElement(By.id("__BVID__21")).sendKeys("selenium");
        driver.findElement(By.cssSelector(".search-icon")).click();
    }

    @Test
    public void searchBoxTwo(){
        driver.findElement(By.linkText("蓝桥杯")).click();
        RemoteWebElement webElement = (RemoteWebElement) driver.findElement(By.linkText("蓝桥杯"));
        webElementList.add(webElement);
        driver.findElement(By.id("search-input")).click();
        driver.findElement(By.id("search-input")).sendKeys("456");
        driver.findElement(By.cssSelector(".search-icon")).click();
    }

    @Test
    public void searchBoxThree(){
        driver.findElement(By.linkText("讨论区")).click();
        RemoteWebElement webElement = (RemoteWebElement) driver.findElement(By.linkText("讨论区"));
        webElementList.add(webElement);
        driver.findElement(By.id("__BVID__21")).click();
        driver.findElement(By.id("__BVID__21")).sendKeys("789");
        driver.findElement(By.cssSelector(".search-icon")).click();
    }

    @Test(dependsOnMethods = { "searchBoxOne" ,"searchBoxTwo","searchBoxThree"})
    public void test() {
        for (RemoteWebElement webElement: webElementList){
            //打印每个linkText对应的id
            System.out.println(webElement.getId());
        }
        System.out.println("测试结束");
    }
}
