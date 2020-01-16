package com.hogwarts.service.apis;

import com.hogwarts.service.utils.RequestUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.hogwarts.service.commons.WorkApiUrl.*;

/**
 * 标签管理
 *
 * @author 冷枫红舞
 */
@Slf4j
public class TagManagement {

    private volatile static TagManagement tagManagement = null;

    private TagManagement() {
    }

    public static TagManagement getInstance() {
        if (null == tagManagement) {
            synchronized (TagManagement.class) {
                if (null == tagManagement) {
                    tagManagement = new TagManagement();
                    log.info("[TagManagement] 创建 TagManagement 实例.");
                }
            }
        }
        return tagManagement;
    }

    /**
     * 创建标签
     * 请求方式：POST（HTTPS）
     * https://qyapi.weixin.qq.com/cgi-bin/tag/create
     *
     * @param params
     * @return
     */
    public Response tagCreate(Map<String, Object> params) {
        return RequestUtils.getInstance().doPostWithBody(TAG_CREATE_URL, params);
    }

    /**
     * 更新标签名字
     * 请求方式：POST（HTTPS）
     * https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=ACCESS_TOKEN
     *
     * @param
     * @return
     */
    public Response tagUpdate(Map<String, Object> params) {
        return RequestUtils.getInstance().doPostWithBody(TAG_UPDATE_URL, params);
    }

    /**
     * 删除标签
     * 请求方式：GET（HTTPS）
     * https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=ACCESS_TOKEN&tagid=TAGID
     *
     * @param params
     * @return
     */
    public Response tagDelete(Map<String, Object> params) {
        return RequestUtils.getInstance().doGetWithQueryParams(TAG_DELETE_URL, params);
    }

    /**
     * 获取标签列表
     * 请求方式：GET（HTTPS）
     * https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=ACCESS_TOKEN
     *
     * @return
     */
    public Response tagList() {
        return RequestUtils.getInstance().doGet(TAG_LIST_URL);
    }
}
