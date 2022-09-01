package com.testng.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PubicClickSearchBox {

    /**
     * 抽取公共方法-搜索框点击操作
     * @param driver
     */
    public static void searchBoxClick(WebDriver driver,String linkTextName){

        switch (linkTextName){
            case "学习" :
                driver.findElement(By.id("__BVID__21")).click();
                driver.findElement(By.id("__BVID__21")).sendKeys("123");
                break;
            case "蓝桥杯" :
                driver.findElement(By.id("search-input")).click();
                driver.findElement(By.id("search-input")).sendKeys("456");
                break;
            case "讨论区" :
                driver.findElement(By.id("__BVID__21")).click();
                driver.findElement(By.id("__BVID__21")).sendKeys("789");
                break;
            default :
                System.out.println("未知linkTextName");
                break;
        }

    }
}
