package com.hogwarts.service.testcase.example;

import com.alibaba.fastjson.JSONObject;
import com.hogwarts.service.apis.TokenManagement;
import com.hogwarts.service.apis.UserManagement;
import com.hogwarts.service.utils.AssertUtils;
import com.hogwarts.service.utils.InitParamsUtils;
import com.hogwarts.service.utils.OutputUtils;
import com.hogwarts.service.utils.TemplateUtils;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

import static com.hogwarts.service.commons.BaseField.USER_LIST_KEY;
import static com.hogwarts.service.commons.BaseField.departmentId;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * 接口自动化测试框架 - TestNg.xml 方式 - 综合练习：
 * 1. 使用 TestNg.xml + @Parameters 运行测试用例
 * 2. 对 xml 参数做 init 初始化处理
 * 3. 从 mustache.json 模板读取 json 数据，并对根据实际情况，对 {{}} 格式的数据进行修改
 * |- 可选：使用 JsonPath 功能对 json 报文中的字段重新赋值
 * 4）调用接口，发送请求
 * <p>
 * 成员管理 - 创建成员
 * https://work.weixin.qq.com/api/doc/90002/90151/90814
 *
 * @author 冷枫红舞
 */
public class ExampleTestNgXmlTest {

    @BeforeClass(description = "获取 access_token")
    public void beforeClass() {
        TokenManagement.getInstance().getAccessToken();
        OutputUtils.outputBeforeClass();
    }

    /**
     * 使用模板技术，从模板读取请求报文。其中：
     * 1. mobile 和 userid 两个参数从 xml 获取，并替换模板中的参数；
     * 2. name 参数，通过 json-path 方式替换模板中的参数。
     */
    @Parameters({"mobile", "userid"})
    @Test(description = "创建成员")
    public void userCreate(String mobile, String userId) {

        String[] arrays = {"mobile", "userid"};
        OutputUtils.outputTest("arrays", Arrays.toString(arrays));
        // 初始化参数，将多个 String 参数整合为 Map，其中：arrays 存 key，变参 String 存 value
        // 函数原型：public Map<String, Object> getParams(String[] arrays, String... strings)
        Map<String, Object> params = new InitParamsUtils().getParams(arrays, mobile, userId);

        // 获取模板数据并替换参数（也可以将模板文件 path 定义在 BaseUrl 类）
        String fileName = "user_create.json";
        String path = "/template_file_json/user_management/" + fileName;
        // 函数原型：public String getJsonFromTemplateFile(String templateFilePath, Map<String, Object> params)
        String jsonStringFromTemplate = TemplateUtils.getInstance().getJsonFromTemplateFile(path, params);
        OutputUtils.outputTest("jsonStringFromTemplate", jsonStringFromTemplate);

        // 使用 JsonPath 替换从模板中获取的 name 参数
        DocumentContext documentContext = JsonPath.parse(jsonStringFromTemplate);
        // 设置 name 字段的值（通过 documentContext.put() 方法替换）
        String name = "罗小黑的小嘿咻 No." + randomNumeric(2);
        /**
         * public DocumentContext put(String path, String key, Object value, Predicate... filters) {
         *     return this.put(JsonPath.compile(path, filters), key, value);
         * }
         */
        documentContext.put("$", "name", name);
        String jsonString = documentContext.jsonString();
        OutputUtils.outputTest("jsonString", jsonString);

        // 发送请求
        Response response = UserManagement.getInstance().userCreate(JSONObject.parseObject(jsonString));
        AssertUtils.assertEqualsCreated(response);
    }

    @AfterClass(description = "获取部门成员 - 新增结果的验证查询")
    public void afterClass() {
        Map<String, Object> params = new HashMap<>();
        params.put("department_id", departmentId);

        Response response = UserManagement.getInstance().userSimpleList(params);
        AssertUtils.assertEqualsBase(response);

        List<String> list = response.getBody().path(USER_LIST_KEY);

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("[Test Case] next = " + iterator.next());
        }
    }
}
