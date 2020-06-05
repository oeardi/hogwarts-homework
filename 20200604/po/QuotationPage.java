package com.po;

import com.utils.FindElementUtils;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 行情页
 *
 * @author 冷枫红舞
 */
@Data
@Slf4j
public class QuotationPage {

    /**
     * 行情页搜索控件（放大镜）
     */
    public static final String action_search_id = "com.xueqiu.android:id/action_search";

    public static final String tv_left_id = "com.xueqiu.android:id/tv_left";

    /**
     * 获取 “搜索” 页面（行情页搜索）
     *
     * @param driver
     * @param id
     * @return
     */
    public HomeSearchPage getSearchPage(AndroidDriver driver, String id) {
//        log.info("调用 [{}] {}() 方法，返回 [HomeSearchPage] 对象.",
//                Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());

        new FindElementUtils().findEleByIdAndClick(driver, id);
        return new HomeSearchPage();
    }

}
