package com.hogwarts.service.testcase.single.tag;

import com.hogwarts.service.apis.TagManagement;
import com.hogwarts.service.apis.TokenManagement;
import com.hogwarts.service.utils.AssertUtils;
import com.hogwarts.service.utils.OutputUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.hogwarts.service.commons.BaseField.*;

/**
 * @author 冷枫红舞
 */
public class TagListTest {

    @BeforeClass(description = "初始化 token")
    public void beforeClass() {
        TokenManagement.getInstance().getAccessToken();
        OutputUtils.outputBeforeClass();
    }

    @Test(description = "获取标签列表 - 成功用例")
    public void tagList_success() {
        Response response = TagManagement.getInstance().tagList();
        AssertUtils.assertEqualsBase(response);
    }

    //    @Test(description = "获取标签列表 - 失败用例 - accessToken 为空")
    public void tagList_error() {
        accessToken = null;
        Response response = TagManagement.getInstance().tagList();
        AssertUtils.assertEquals(response, ERR_CODE_KEY, ERR_CODE_VALUE_41001);
    }
}
