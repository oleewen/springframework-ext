package org.springframework.ext.common.helper;

import com.google.common.collect.Maps;
import org.hamcrest.CoreMatchers;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertThat;

/**
 * Created by only on 2017/7/11.
 */
public class JsonHelperTest {
    @org.junit.Test
    public void toJson() throws Exception {
        Store store = new Store();
        store.onlineTime = new Date(1514474781575L);
        store.status = 3L;

        assertThat(JsonHelper.toJson(store), CoreMatchers.is("{\"online_time\":\"Dec 28, 2017 11:26:21 PM\",\"status\":3}"));
    }

    @org.junit.Test
    public void fromJson() throws Exception {
        Store store = JsonHelper.fromJson("{\"online_time\":\"1970-01-01 08:00:30\", \"status\":4938430150972220247}", Store.class);

        assertThat(store.status, CoreMatchers.is(4938430150972220247L));
        assertThat(store.onlineTime.getTime(), CoreMatchers.is(30000L));
    }

    @org.junit.Test
    public void fromJsonDouble() throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("k", "v");
        map.put("k1", 111111);
        map.put("k2", 4938430150972220247L);

        String json = JsonHelper.toJson(map);
        assertThat(json, CoreMatchers.is("{\"k1\":111111,\"k2\":4938430150972220247,\"k\":\"v\"}"));


        Map<String, Object> result = JsonHelper.fromJsonMap(json);

        assertThat(result.size(), CoreMatchers.is(3));
        assertThat(JsonHelper.toJson(result), CoreMatchers.is(json));
    }

    @org.junit.Test
    public void fromJsonMap() throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("k", "v");
        map.put("k1", 111111);
        map.put("k2", 999999.73);

        String json = JsonHelper.toJson(map);
        assertThat(json, CoreMatchers.is("{\"k1\":111111,\"k2\":999999.73,\"k\":\"v\"}"));


        Map<String, Object> result = JsonHelper.fromJsonMap(json);

        assertThat(result.size(), CoreMatchers.is(3));
        assertThat(JsonHelper.toJson(result), CoreMatchers.is(json));
    }

    @org.junit.Test
    public void fromJsonMapPage() throws Exception {
        String json = "{\"p61designer_image_url\":\"https://img.alicdn.com/imgextra/i4/263662065/TB2lYiJwH8kpuFjy0FcXXaUhpXa_!!263662065-0-shop_design.jpg\",\"p61designer_image_url_backUrl\":\"https://img.alicdn.com/imgextra/i4/263662065/TB2lYiJwH8kpuFjy0FcXXaUhpXa_!!263662065-0-shop_design.jpg\",\"p61image_widget_id\":444444444444,\"pageId\":8888888888,\"userId\":2222222222,\"p61designer_image_url_splitUrls\":\"//img.alicdn.com/imgextra/i3/263662065/TB2GGiwwHRkpuFjSspmXXc.9XXa_!!263662065-0-shop_design.jpg;480|//img.alicdn.com/imgextra/i2/263662065/TB2cImJwH8kpuFjy0FcXXaUhpXa_!!263662065-0-shop_design.jpg;480|//img.alicdn.com/imgextra/i1/263662065/TB2vJOtwKJ8puFjy1XbXXagqVXa_!!263662065-0-shop_design.jpg;480|//img.alicdn.com/imgextra/i2/263662065/TB2pD02wHtlpuFjSspoXXbcDpXa_!!263662065-0-shop_design.jpg;480|//img.alicdn.com/imgextra/i4/263662065/TB2xJOtwKJ8puFjy1XbXXagqVXa_!!263662065-0-shop_design.jpg;236\"}";

        Map<String, Object> result = JsonHelper.fromJsonMap(json);

        assertThat(result.size(), CoreMatchers.is(6));
        String actual = JsonHelper.toJson(result);
        assertThat(actual, CoreMatchers.is(json));
    }

    @org.junit.Test
    public void fromJsonList() throws Exception {

    }

    class Store {
        private Date onlineTime;
        private Long status;
    }

}