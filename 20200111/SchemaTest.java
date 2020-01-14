package com.hogwarts.schema;

import com.hogwarts.assured.filter.Base64Utils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.hogwarts.commons.BaseUrl.lotto_get_url;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * https://jsonschema.net/
 * JsonSchemaValidator 使用实例：
 * 1. 如果响应报文的字段数量 > schema.json 文件中的字段数量，运行结果不会报错（因为 schema 没有这个字段的校验规则）；
 * 2. 如果响应报文的字段数量 < schema.json 文件中的字段数量，运行结果会校验不通过。
 *
 * @author 冷枫红舞
 */
@Slf4j
public class SchemaTest {

    // 没有使用公司项目接口
    private String lotto_schema_json_file = "schema/lotto_schema.json";

    private String vipInfoKey = "lotto.vipInfo";
    private String payNoKey = "lotto.payNo";

    @Test(description = "json schema 练习")
    public void testJsonSchemaValidator() {
        // 文件读取校验
        JsonSchemaValidator jsonSchemaValidator = matchesJsonSchemaInClasspath(lotto_schema_json_file);
        if (null == jsonSchemaValidator) {
            System.out.println("[Test Case] jsonSchemaValidator is null.");
            return;
        } else {
            System.out.println("[Test Case] Case Running...");
        }

        Response response = given().log().all()
                .when().get(lotto_get_url)
                .then().log().all()
                .body(jsonSchemaValidator)
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().response();
        // 获取 vipInfo 和 payNo 字段值
        String vipInfoValue = response.body().path(vipInfoKey);
        log.info("[Test Case] vipInfoValue: {}", vipInfoValue);
        String payNoValue = response.body().path(payNoKey);
        log.info("[Test Case] payNoValue: {}", payNoValue);
        // 解密 vipInfo
        String decodeString = Base64Utils.decode(vipInfoValue);
        log.info("[Test Case] decodeString: {}", decodeString);
    }
}
