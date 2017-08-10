package com.lib.lzlin.api.utils.gsonFormat.helper;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.lib.lzlin.api.utils.gsonFormat.adapter.CollectionTypeAdapterFactory;
import com.lib.lzlin.api.utils.gsonFormat.adapter.DoubleDefault0Adapter;
import com.lib.lzlin.api.utils.gsonFormat.adapter.IntegerDefault0Adapter;
import com.lib.lzlin.api.utils.gsonFormat.adapter.LongDefault0Adapter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 自定义TypeAdapter，将其放入facotries中，并且gson在解析json时使用对应的TypeAdapter来的，而我们手动添加的TypeAdapter会优先于预设的TypeAdapter被使用。
 * 创建人: lz - Administrator
 * 创建时间:  2017/7/21 15:26
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class GsonHelper {
    private static GsonHelper instance = null;
    private static Gson gsonInstance = null;
    // GsonFormat 格式化异常时, 默认显示的字段
    private static Integer defaultInteger = 0; // int 默认值
    private static Double defaultDouble = 0.00; // double 默认值
    private static Long defaultLong = 0L; // long 默认值
    private static String defaultString = ""; // string 默认值
    private static Boolean isDebug = false; // 是否是调试模式

    private GsonHelper() {}

    static {
        if (instance == null) {
            instance = new GsonHelper();
        }
    }

    /**
     * 自定义TypeAdapter ,null对象将被解析成空字符串
     */
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        public String read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return getDefaultString();//原先是返回Null，这里改为返回空字符串
                }
                return reader.nextString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getDefaultString();
        }

        public void write(JsonWriter writer, String value) {
            try {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 获取单实例
     * @return
     */
    public static Gson getGsonInstance() {
        if (gsonInstance == null) {
            GsonBuilder gsonBulder = new GsonBuilder();
            // 解决思路: 修改Integer/Double/Long 适配器, 序列化和反序列化时修改内容
            gsonBulder
                    .generateNonExecutableJson()
                    .serializeNulls()
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter(getDefaultInteger(), isDebug()))
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter(getDefaultInteger(), isDebug()))
                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter(getDefaultDouble(), isDebug()))
                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter(getDefaultDouble(), isDebug()))
                    .registerTypeAdapter(Long.class, new LongDefault0Adapter(getDefaultLong(), isDebug()))
                    .registerTypeAdapter(long.class, new LongDefault0Adapter(getDefaultLong(), isDebug()))
                    .registerTypeAdapter(String.class, STRING) ;
            // 通过反射获取instanceCreators属性
            try {
                Class builder = (Class) gsonBulder.getClass();
                Field f = builder.getDeclaredField("instanceCreators");
                f.setAccessible(true);
                Map<Type, InstanceCreator<?>> val = (Map<Type, InstanceCreator<?>>) f.get(gsonBulder);//得到此属性的值
                // 注册数组的处理器
                gsonBulder.registerTypeAdapterFactory(new CollectionTypeAdapterFactory(new ConstructorConstructor(val)));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            gsonInstance = gsonBulder.create();
        }
        return gsonInstance;
    }

    /**
     * 判断元素是否为空
     * @return
     */
    public static boolean isNull(JsonElement json) {
        if (TextUtils.isEmpty(json.getAsString().trim()) || json.getAsString().trim().equalsIgnoreCase(NULL)) {// 后台返回""或者null
            return true;
        }
        return false;
    }

    /**
     * 提供给外部, 供修改的显示默认值
     */
    public static GsonHelper setDefaultInteger(Integer defaultInteger) {
        GsonHelper.defaultInteger = defaultInteger;
        return instance;
    }
    public static GsonHelper setDefaultDouble(Double defaultDouble) {
        GsonHelper.defaultDouble = defaultDouble;
        return instance;
    }
    public static GsonHelper setDefaultLong(Long defaultLong) {
        GsonHelper.defaultLong = defaultLong;
        return instance;
    }
    public static GsonHelper setDefaultString(String defaultString) {
        GsonHelper.defaultString = defaultString;
        return instance;
    }
    public static GsonHelper setIsDebug(Boolean isDebug) {
        GsonHelper.isDebug = isDebug;
        return instance;
    }
    public static Integer getDefaultInteger() {
        return defaultInteger;
    }
    public static Double getDefaultDouble() {
        return defaultDouble;
    }
    public static Long getDefaultLong() {
        return defaultLong;
    }
    public static String getDefaultString() {
        return defaultString;
    }
    public static Boolean isDebug() {
        return isDebug;
    }
}
