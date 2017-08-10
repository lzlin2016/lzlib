package com.lib.lzlin.api.utils.gsonFormat.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.lib.lzlin.api.utils.gsonFormat.helper.GsonHelper;

import java.lang.reflect.Type;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 定义为 Integer 类型, 如果后台返回""或者null,则返回0
 *  我们写一个针对 Integer 值的类型转换器,需要实现Gson的 JsonSerializer<T> 接口和 JsonDeserializer<T> ,即序列化和反序列化接口
 * 创建人: lz - Administrator
 * 创建时间:  2017/7/21 15:34
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class IntegerDefault0Adapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
    private int defaultInteger;
    private boolean isDebug;

    public IntegerDefault0Adapter(int defaultInteger, boolean isDebug) {
        this.defaultInteger = defaultInteger;
        this.isDebug = isDebug;
    }

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (GsonHelper.isNull(json)) { // 定义为 Integer 类型, 如果后台返回""或者null,则返回0
                return defaultInteger;
            }
        } catch (Exception ignore) {
        }
        try {
            return (int)json.getAsDouble();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (isDebug) {
                throw new JsonSyntaxException(e);
            }
        }
        return defaultInteger;
    }

    @Override
    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
