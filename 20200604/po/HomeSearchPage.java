package com.po;

import com.utils.FindElementUtils;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;


/**
 * 搜索页
 * <android.widget.RelativeLayout>
 * ---- <android.widget.EditText resource-id="com.xueqiu.android:id/search_input_text">
 *
 * @author 冷枫红舞
 */
@Data
@Slf4j
public class HomeSearchPage {

    public static final String search_input_text_id = "com.xueqiu.android:id/search_input_text";

    public static final String text_follow_btn_xpath = "//*[@text='加自选']";

    /**
     * 向 “搜索框” 中输入 keyword，该控件同步列出检索结果。
     *
     * @param keyword
     * @return
     */
    public HomeSearchPage doSearchById(AndroidDriver driver, String id, String keyword) {
//        log.info("调用 [{}] {}() 方法，返回 [HomeSearchPage] 对象.",
//                Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());

        new FindElementUtils().findEleByIdAndSendKey(driver, id, keyword);
        return new HomeSearchPage();
    }

    /**
     * 点击搜索结果中的内容
     *
     * @param driver
     * @param xpath
     * @return
     */
    public HomeSearchPage doClickByXpath(AndroidDriver driver, String xpath) {
//        log.info("调用 [{}] {}() 方法，返回 [HomeSearchPage] 对象.",
//                Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());

        new FindElementUtils().findEleByXpathAndClick(driver, xpath);
        return new HomeSearchPage();
    }

    /**
     * 获取股票对应价格
     *
     * @return
     */
    public String getPrice(AndroidDriver driver) {
//        MobileElement element = (MobileElement) driver.findElementByXPath("//*[@text='218.61']");
        By by = By.id("阿里巴巴");
        driver.findElement(by).click();
        String price = driver.findElement(By.id("current_price")).getText();

        return price;
    }

}
