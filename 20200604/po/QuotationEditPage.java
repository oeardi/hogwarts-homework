package com.po;

import com.utils.FindElementUtils;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 行情 - 管理页
 *
 * @author 冷枫红舞
 */
@Data
@Slf4j
public class QuotationEditPage {

    /**
     * 1. “自选股管理” 页面，通过 “行情” 页面右侧 “管理” 按钮进入 “管理页”
     */
    public static final String edit_group_id = "com.xueqiu.android:id/edit_group";
    /**
     * 2. 全选按钮
     */
    public static final String check_all_id = "com.xueqiu.android:id/check_all";
    /**
     * 3. 删除自选按钮
     */
    public static final String cancel_follow_id = "com.xueqiu.android:id/cancel_follow";
    /**
     * 4. 删除自选股的弹框确认（点击确定）
     */
    public static final String tv_rigth_id = "com.xueqiu.android:id/tv_right";

    /**
     * 5. 返回 “行情” 页
     * 1）“搜索框” 右侧的 “取消” 按钮，text = 取消
     * 2）“自选股” 管理页面的 “完成” 按钮，text = 完成
     */
    public static final String action_close_id = "com.xueqiu.android:id/action_close";

    /**
     * 点击 “完成” 或 “取消” 按钮，返回 “行情” 页
     *
     * @param driver
     * @param id
     * @return
     */
    public QuotationPage returnQuotationPage(AndroidDriver driver, String id) {
//        log.info("调用 [{}] {}() 方法，返回 [QuotationPage] 对象.",
//                Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());

        new FindElementUtils().findEleByIdAndClick(driver, id);
        return new QuotationPage();
    }

}
