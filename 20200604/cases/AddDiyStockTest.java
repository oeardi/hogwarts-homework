package com.cases;

import com.po.HomeSearchPage;
import com.po.MainPage;
import com.po.QuotationEditPage;
import com.po.QuotationPage;
import com.utils.AndroidDriverUtils;
import com.utils.FindElementUtils;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.po.HomeSearchPage.search_input_text_id;
import static com.po.HomeSearchPage.text_follow_btn_xpath;
import static com.po.MainPage.portfolio_xpath;
import static com.po.MainPage.tv_agree_id;
import static com.po.QuotationEditPage.*;
import static com.po.QuotationPage.action_search_id;
import static com.po.QuotationPage.tv_left_id;

/**
 * 实现流程：
 * 1. 首页 -> 行情页
 * ---- 如已有 “自选” 股票，则删除
 * 2. 添加三只 “自选” 股票
 *
 * @author 冷枫红舞
 */
@Slf4j
public class AddDiyStockTest {

    private AndroidDriver driver;

    private MainPage mainPage;
    private QuotationPage quotationPage;
    private HomeSearchPage homeSearchPage;

    @BeforeClass
    public void beforeClass() {
        driver = AndroidDriverUtils.getInitDriver();
    }

    /**
     * 行情页面中有内容，删除
     */
    @Test
    public void addDiyStock() {
        /**
         * 0. 创建 MainPage 对象
         */
        mainPage = new MainPage();
        log.info("[Test Case] [测试用例开始执行] 创建 [MainPage] 对象.");

        /**
         * 1. 启动 App 后，点击弹框的 “同意” 按钮，关闭弹框。（未登录状态下，每次启动后弹出）
         */
        if (new FindElementUtils().elementExistById(driver, tv_agree_id)) {
            new FindElementUtils().findEleByIdAndClick(driver, tv_agree_id);
            log.info("[Test Case] [发现弹框] 点击元素 [{}] 关闭弹框.", tv_agree_id);
        }

        /**
         * 2. 根据 MainPage 对象，获取 QuotationPage 对象（行情页）
         */
        quotationPage = mainPage.getQuotationPageByXpath(driver, portfolio_xpath);
        log.info("[Test Case] 初始化 [QuotationPage] 对象.");

        /**
         * 3. 进入 “行情页” 后，如果用户已经添加过 “自选股”，则当前页面存在 portfolio_stockName 元素，
         * 所以先判断 portfolio_stockName 元素是否存在：
         * 1）不存在，表示未添加过 “自选股”，我们将手动添加三只自选股票；
         * 2）存在 portfolio_stockName 元素，则删除全部已添加的股票。
         */
        log.info("[Test Case] 首先查看用户是否已添加（关注）了 “自选股”.");
        String portfolio_stockName_id = "portfolio_stockName";
        List<String> list = new ArrayList<>();
        if (new FindElementUtils().elementExistById(driver, portfolio_stockName_id)) {
            list = new FindElementUtils().getSearchResultList(driver, portfolio_stockName_id);
            if (list.size() > 0) {
                log.info("[Test Case] 用户已添加过 “自选股” 脚本即将对已添加（关注）的自选股票执行删除操作 ...");
            }

            /**
             * 4. 已添加过自选股，需要将自选股全部删除，涉及如下操作：
             * 1）点击 “管理” 按钮（行情页上右方），进入管理页
             * 2）点击 “管理页” 中的 “全选” 按钮（button）
             * 3）点击 “删除自选” 按钮
             * 4）弹框 “是否取消关注”，点击 “确定” 删除并关闭弹框
             * 5）点击 “完成” 按钮，返回 “行情页”，同时返回 QuotationPage 对象，便于进一步添加 “自选股” 操作
             */
            FindElementUtils elementUtils = new FindElementUtils();
            elementUtils.findEleByIdAndClick(driver, edit_group_id);
            elementUtils.findEleByIdAndClick(driver, check_all_id);
            elementUtils.findEleByIdAndClick(driver, cancel_follow_id);
            elementUtils.findEleByIdAndClick(driver, tv_rigth_id);
            quotationPage = new QuotationEditPage().returnQuotationPage(driver, action_close_id);
        } else {
            log.info("[Test Case] 用户还未添加 “自选股”，根据需求定义，测试用例将添加 3 只股票 ...");
        }

        /**
         * 添加三只股票：拼多多、贵州茅台、五菱汽车
         */
        Map<String, String> map = new HashMap<>(16);
        map.put("pdd", "PDD");
        map.put("maotai", "SH600519");
        map.put("wuling", "00305");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            /**
             * 4. 点击 “搜索” 控件，进入 “搜索页”
             */
            homeSearchPage = quotationPage.getSearchPage(driver, action_search_id);
            /**
             * 5. 输入 keyword 搜索股票
             */
            String keyword = entry.getKey();
            homeSearchPage.doSearchById(driver, search_input_text_id, keyword);
            /**
             * 6. 选中一个搜索结果
             */
            String value = entry.getValue();
            // 拼接 xpath（写着玩的）也可以将 xpath 直接存入 Map 中，这里就可以直接使用 value，而不需要用 value 拼接。
            String text_xpath = "//*[@text='" + value + "']";
            homeSearchPage.doClickByXpath(driver, text_xpath);
            log.info("[Test Case][调试信息] 输入数据 keyword = {}, 搜索结果 text = {}", keyword, value);

            /**
             * 7. 点击 “搜索结果” 对应的 “加自选” 按钮（加关注）
             */
            homeSearchPage.doClickByXpath(driver, text_follow_btn_xpath);
            if (keyword.equals("pdd")) {
                log.info("[Test Case][调试信息] 成功添加 [拼多多] 为自选股.");
            } else if (keyword.equals("maotai")) {
                log.info("[Test Case][调试信息] 成功添加 [贵州茅台] 为自选股.");
            } else if (keyword.equals("wuling")) {
                log.info("[Test Case][调试信息] 成功添加 [五菱汽车] 为自选股.");
            }

            /**
             * 8. 关闭 “评价” 弹框，点击 “下次再说”
             * 注：每次启动 App 首次添加 “自选股” 时弹出此弹框，引导用户去应用市场评价。多次添加自选股不会再次弹框。
             * （所以对关闭弹框元素做存在判断）
             */
            if (new FindElementUtils().elementExistById(driver, tv_left_id)) {
                new FindElementUtils().findEleByIdAndClick(driver, tv_left_id);
                log.info("[Test Case] 点击元素 {} 关闭弹框.", tv_left_id);
            }

            /**
             * 9. 点击 “管理页” 中的 “取消” 按钮，返回 “行情” 页，同时也会返回 QuotationPage 对象。
             */
            quotationPage = new QuotationEditPage().returnQuotationPage(driver, action_close_id);
            log.info("[Test Case] {} 只股票添加完毕，客户端停留在 [行情] 页面。", list.size());

        }

    }

    @AfterClass
    public void afterClass() {
        log.info("[Test Case] 测试完毕.");
    }

}
