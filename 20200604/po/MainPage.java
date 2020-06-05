package com.po;

import com.utils.FindElementUtils;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 首页
 * <android.widget.RelativeLayout resource-id="com.xueqiu.android:id/home_container">
 *
 * @author 冷枫红舞
 */
@Data
@Slf4j
public class MainPage {

    public static final String tv_agree_id = "com.xueqiu.android:id/tv_agree";

    /**
     * 通过 “首页” -> 跳转 “详情” 页
     */
    public static final String portfolio_xpath = "//*[@text='行情']";

    public static final String home_search_id = "com.xueqiu.android:id/home_search";

    /**
     * 点击 app 启动后的 “同意” 弹框
     *
     * @param driver
     * @param id
     */
//    public void doClickAgree(AndroidDriver driver, String id) {
//        new FindElementUtils().findEleByIdAndClick(driver, id);
//    }

    /**
     * 获取 “搜索” 页面（首页顶部搜索栏）
     *
     * @param driver
     * @param id
     * @return
     */
    public HomeSearchPage getHomeSearchPageById(AndroidDriver driver, String id) {
//        log.info("调用 [{}] {}() 方法，返回 [HomeSearchPage] 对象.",
//                Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());

        new FindElementUtils().findEleByIdAndClick(driver, id);
        return new HomeSearchPage();
    }

    /**
     * 获取 “行情” 页
     *
     * @param driver
     * @param xpath
     * @return
     */
    public QuotationPage getQuotationPageByXpath(AndroidDriver driver, String xpath) {
//        log.info("调用 [{}] {}() 方法，返回 [QuotationPage] 对象.",
//                Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());

        new FindElementUtils().findEleByXpathAndClick(driver, xpath);
        return new QuotationPage();
    }

}
