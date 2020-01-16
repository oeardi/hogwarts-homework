package com.hogwarts.service.testcase.single.tag;

import com.hogwarts.service.apis.TagManagement;
import com.hogwarts.service.apis.TokenManagement;
import com.hogwarts.service.utils.AssertUtils;
import com.hogwarts.service.utils.OutputUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.hogwarts.service.commons.BaseField.TAG_ID_KEY;
import static com.hogwarts.service.commons.BaseField.tagId;

/**
 * @author 冷枫红舞
 */
public class TagDeleteTest {

    @BeforeClass(description = "初始化 token")
    public void beforeClass() {
        TokenManagement.getInstance().getAccessToken();
        OutputUtils.outputBeforeClass();
    }

    @Test(priority = 1, description = "创建标签")
    public void tagCreate() {
        TagCreateTest tagCreateTest = new TagCreateTest();
        tagCreateTest.tagCreate();
    }

    @Test(priority = 2, description = "删除标签 - 成功用例")
    public void tagDelete() {
        Map<String, Object> params = new HashMap<>(16);
        params.put(TAG_ID_KEY, tagId);

        Response response = TagManagement.getInstance().tagDelete(params);
        AssertUtils.assertEqualsDeleted(response);
    }
}
