package cn.xj.common.util;

import cn.xj.common.model.BaseBean;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.cglib.beans.BeanMap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/4/26.
 */
public class ConvertUtil {

    public static String objectToStr(Object val) {
        String s = Objects.equal(val, null) ? null : String.valueOf(val);
        return s;
    }

    public static Integer objectToInt(Object val) {
        Integer i = Objects.equal(val, null) ? null : Integer.valueOf(String.valueOf(val));
        return i;
    }

    public static Long objectToLong(Object val) {
        Long l = Objects.equal(val, null) ? null : Long.valueOf(String.valueOf(val));
        return l;
    }

    public static Double objectToDouble(Object val) {
        Double d = Objects.equal(val, null) ? null : Double.parseDouble(val.toString());
        return d;
    }

    public static Float objectToFloat(Object val) {
        Float f = Objects.equal(val, null) ? null : Float.parseFloat(String.valueOf(val));
        return f;
    }

    public static BigDecimal objToBigDecimal(Object val) {
        BigDecimal b = Objects.equal(val, null) ? null : new BigDecimal(String.valueOf(val));
        return b;
    }

    public static Boolean objectToBool(Object val) {
        String s = ConvertUtil.objectToStr(val);
        if ("0".equals(s) || "false".equals(s)) {
            return false;
        } else if ("1".equals(s) || "true".equals(s)) {
            return true;
        } else {
            return null;
        }
    }

    public static Date objectToDate(Object val) {
        if (val instanceof Date) {
            return (Date) val;
        }
        return null;
    }

    public static Object convertByType(Object val, String type) {
        if ("String".equals(type.trim())) {
            return ConvertUtil.objectToStr(val);
        } else if ("Integer".equals(type.trim())) {
            return ConvertUtil.objectToInt(val);
        } else if ("Long".equals(type.trim())) {
            return ConvertUtil.objectToLong(val);
        } else if ("BigDecimal".equals(type.trim())) {
            return ConvertUtil.objToBigDecimal(val);
        } else if ("Date".equals(type.trim())) {
            return ConvertUtil.objectToDate(val);
        } else if ("Double".equals(type.trim())) {
            return ConvertUtil.objectToDouble(val);
        } else if ("Float".equals(type.trim())) {
            return ConvertUtil.objectToFloat(val);
        } else if ("Boolean".equals(type.trim())) {
            return ConvertUtil.objectToBool(val);
        }
        return null;
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map转换为bean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将map转换为bean对象
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T bean = ConvertUtil.mapToBean(map, clazz.newInstance());
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param beans
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beans) throws JsonMappingException, JsonGenerationException {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (beans != null && beans.size() > 0) {
            Map<String, Object> map;
            T bean;
            for (int i = 0, size = beans.size(); i < size; i++) {
                bean = beans.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param clazz
     * @param <T>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T extends BaseBean> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            for (Map<String, Object> map : maps) {
                T bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 将json字符串转成map
     *
     * @param json
     * @return
     */
    public static Map jsonToMap(String json) {
        Gson gson = new Gson();
        Map map = gson.fromJson(json, new TypeToken<Map>() {
        }.getType());
        return map;
    }

    /**
     * 将json字符串转成list
     *
     * @param json
     * @return
     */
    public static List jsonToList(String json) {
        Gson gson = new Gson();
        List list = gson.fromJson(json, new TypeToken<List>() {
        }.getType());
        return list;
    }

    /**
     * 将json字符串转成bean
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T extends BaseBean> T jsonToBean(String json, Class<T> clazz) {
        Gson gson = new Gson();
        T bean = gson.fromJson(json, clazz);
        return bean;
    }

    /**
     * 将map转成json字符串
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map map) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(map);
    }

    public static void main(String[] args) {
    }

}
