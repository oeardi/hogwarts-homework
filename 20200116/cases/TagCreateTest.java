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

import static com.hogwarts.service.commons.BaseField.*;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * @author 冷枫红舞
 */
public class TagCreateTest {

    @BeforeClass(description = "初始化 token")
    public void beforeClass() {
        TokenManagement.getInstance().getAccessToken();
        OutputUtils.outputBeforeClass();
    }

    @Test(description = "创建标签 - 成功用例")
    public void tagCreate() {
        tagName = "草帽海贼团 - 后勤保障部" + randomNumeric(2);
        tagId = Integer.valueOf(randomNumeric(2));

        Map<String, Object> params = new HashMap<>(16);
        params.put(TAG_NAME_KEY, tagName);
        params.put(TAG_ID_KEY, tagId);

        Response response = TagManagement.getInstance().tagCreate(params);
        AssertUtils.assertEqualsCreated(response);
        AssertUtils.assertEquals(response, TAG_ID_KEY, tagId);
    }
}
