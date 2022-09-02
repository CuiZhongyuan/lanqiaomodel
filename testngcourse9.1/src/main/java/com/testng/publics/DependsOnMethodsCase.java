package com.testng.publics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

/**
 * 9.1.2 方法依赖场景示例
 */
public class DependsOnMethodsCase {
    public WebDriver driver;
    private String search = "selenium";
    List<String> getCourseName = new ArrayList<>();
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
    /**
     * 学习tab页搜索
     */
    @Test
    public void searchBoxOne(){
        driver.findElement(By.linkText("学习")).click();
        RemoteWebElement webElement = (RemoteWebElement) driver.findElement(By.linkText("学习"));
        System.out.println(webElement);
        driver.findElement(By.id("__BVID__21")).click();
        driver.findElement(By.id("__BVID__21")).sendKeys(search);
        driver.findElement(By.cssSelector(".search-icon")).click();
        //获取学习tab页下的搜索课程结果
        getSearchCourseNameResults("学习",search);
    }
    /**
     * 蓝桥杯tab页搜索
     */
    @Test
    public void searchBoxTwo(){
        driver.findElement(By.linkText("蓝桥杯")).click();
        RemoteWebElement webElement = (RemoteWebElement) driver.findElement(By.linkText("蓝桥杯"));
        driver.findElement(By.id("search-input")).click();
        driver.findElement(By.id("search-input")).sendKeys(search);
        driver.findElement(By.cssSelector(".search-icon")).click();
        //获取蓝桥杯tab页下的搜索课程结果
        getSearchCourseNameResults("蓝桥杯",search);

    }
    /**
     * 讨论区tab页搜索
     */
    @Test
    public void searchBoxThree(){
        driver.findElement(By.linkText("讨论区")).click();
        RemoteWebElement webElement = (RemoteWebElement) driver.findElement(By.linkText("讨论区"));
        driver.findElement(By.id("__BVID__21")).click();
        driver.findElement(By.id("__BVID__21")).sendKeys(search);
        driver.findElement(By.cssSelector(".search-icon")).click();
        //获取讨论区tab页下的搜索课程结果
        getSearchCourseNameResults("讨论区",search);
    }

    /**
     * 聚合每个tab页的搜索结果
     */
    @Test(dependsOnMethods = { "searchBoxOne" ,"searchBoxTwo","searchBoxThree"})
    public void test() {
        for (String s: getCourseName){
            System.out.println(s);
        }
        System.out.println("测试结果如上：因为三个tab页搜索关键字是selenium都指向学习tab页");
    }

    /**
     * 依据搜索结果->获取该查询的课程名称
     * @return
     */
    public List<String> getSearchCourseNameResults(String tab,String searchName){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.lanqiao.cn/search/?type=course&search="+searchName+"&page_size=15").get();
            Elements elements = doc.getElementsByTag("h6");
            if (elements.size() == 0){
                return null;
            }
            for (Element element : elements){
                if (element == null|| "".equals(element.text())){
                    return null;
                }
                getCourseName.add(tab+"页搜索下的"+search+"课程名称："+element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getCourseName;
    }

}
