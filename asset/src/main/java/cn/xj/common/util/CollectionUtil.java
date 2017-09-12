package cn.xj.common.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Welink on 2017/4/26.
 */
public class CollectionUtil {

    /**
     * 检查是否重复
     *
     * @param map
     * @param sets
     * @param fields
     * @return
     */
    public static boolean checkRepeatOrNot(Map<String, Object> map, Set[] sets, String[] fields) {
        for (int i = 0; i < fields.length; i++) {
            if (!sets[i].add(map.get(fields[i]))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据某个字段对list去重
     *
     * @param beans
     * @param field
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> distinctList(List<T> beans, String field) throws JsonMappingException, JsonGenerationException {
        List<Map<String, Object>> maps = ConvertUtil.beansToMaps(beans);
        List<Map<String, Object>> list = Lists.newArrayList();
        Set set = Sets.newHashSet();
        list.addAll(maps.stream().filter(m -> set.add(m.get(field))).collect(Collectors.toList()));
        return list;
    }

    /**
     * 根据某几个字段对list去重
     *
     * @param beans
     * @param fields
     * @param <T>
     * @return
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    public static <T> List<Map<String, Object>> distinctList(List<T> beans, String[] fields) throws JsonMappingException, JsonGenerationException {
        List<Map<String, Object>> maps = ConvertUtil.beansToMaps(beans);
        List<Map<String, Object>> list = Lists.newArrayList();
        Set[] sets = new Set[fields.length];
        for (Map map : maps) {
            boolean flag = CollectionUtil.checkRepeatOrNot(map, sets, fields);
            if (flag) {
                list.add(map);
            }
        }
        return list;
    }

}
