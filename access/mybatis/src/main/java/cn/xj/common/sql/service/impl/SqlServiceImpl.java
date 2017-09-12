package cn.xj.common.sql.service.impl;

import cn.xj.common.annotation.Meta;
import cn.xj.common.container.ContextContainer;
import cn.xj.common.model.*;
import cn.xj.common.sql.mapper.SqlMapper;
import cn.xj.common.sql.service.PageService;
import cn.xj.common.sql.service.SqlService;
import cn.xj.common.util.CollectionUtil;
import cn.xj.common.util.ConvertUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/2/6.
 */
@Service
public class SqlServiceImpl implements SqlService {

    @Autowired
    private ContextContainer container;

    @Autowired
    private SqlMapper sqlMapper;

    @Autowired
    private PageService pageService;

    //保存时的校验
    private void saveCheck(Map<String, Object> params, String className) {
        Map<String, Meta> metaMap = container.getMetaContainer().get(className);
        for (Map.Entry<String, Meta> entry : metaMap.entrySet()) {
            if (params.get(entry.getKey()) == null && entry.getValue() != null) {
                if (!entry.getValue().nullable()) {
                    throw new RuntimeException(entry.getKey() + "不能为空");
                }
                if ("".equals(entry.getValue().defaultValue())) {
                    if (entry.getValue().pKey() && "".equals(entry.getValue().generator())) {
                        throw new RuntimeException("主键不能为空");
                    }
                } else {
                    params.put(entry.getKey(), entry.getValue().defaultValue());
                }
            }
        }
    }

    //查询时的默认检查
    private StringBuffer queryCheck(String alias, String className) {
        StringBuffer check = new StringBuffer();
        Map<String, Meta> metaMap = container.getMetaContainer().get(className);
        metaMap.entrySet().stream().filter(entry -> entry.getValue() != null && !"".equals(entry.getValue().defaultCheck())).forEach(entry -> {
            check.append(" and " + alias + "." + entry.getValue().defaultCheck());
        });
        return check;
    }

//    //读取连接字符串
//    private String readJoinType(String str) {
//        if ("--".equals(str.substring(0, 2))) {
//            return " join ";
//        } else if ("->".equals(str.substring(0, 2))) {
//            return " left join ";
//        } else if ("<-".equals(str.substring(0, 2))) {
//            return " right join ";
//        } else if ("==".equals(str.substring(0, 2))) {
//            return " full join ";
//        } else {
//            throw new RuntimeException("缺少连接字符");
//        }
//    }

//    //获取joinTo
//    private String readJoinTo(String str) {
//        if (str.indexOf("[") > -1 && str.indexOf("]") > -1) {
//            return str.substring(2, str.indexOf("[")).trim();
//        } else if (str.indexOf("|") > -1) {
//            return str.substring(2, str.indexOf("[")).trim();
//        } else {
//            throw new RuntimeException("关联信息错误");
//        }
//    }

//    //获取joinFrom
//    private String[] readJoinFroms(String str) {
//        if (str.indexOf("[") > -1 && str.indexOf("]") > -1) {
//            return str.substring(str.indexOf("[") + 1, str.indexOf("]")).trim().split(",");
//        }
//        return null;
//    }

//    //读取别名
//    private String readAlias(String str) {
//        return str.substring(str.indexOf(":") + 1);
//    }

//    //读取className
//    private String readClassName(String str) throws Exception {
//        return str.substring(0, str.indexOf(":"));
//    }

//    //解析assets
//    private StringBuffer resolve(Map<String, String> assets, Class<?> clazz) throws Exception {
//        String alias, className;
//        String[] array;
//        if (clazz == null) {
//            Map<String, String> mappingMap;
//            StringBuffer buffer = new StringBuffer();
//            for (Map.Entry<String, String> entry : assets.entrySet()) {
//                alias = this.readAlias(entry.getKey());
//                className = this.readClassName(entry.getKey());
//                mappingMap = container.getMappingContainer().get(className);
//                if ("*".equals(entry.getValue())) {
//                    for (String mapping : mappingMap.values()) {
//                        buffer.append("," + alias + "." + mapping.substring(0, mapping.indexOf("=")) + " \"" + alias + "." + mapping.substring(0, mapping.indexOf("=")) + "\"");
//                    }
//                    continue;
//                }
//                array = entry.getValue().split(";");
//                for (String str : array) {
//                    if (str.indexOf("@") > -1) {
//                        buffer.append("," + str.replace("@", alias + "."));
//                    } else {
//                        buffer.append("," + str);
//                    }
//                }
//            }
//            return buffer;
//        } else {
//            String valueFrom, valueTo, field;
//            Map<String, String> mappingFrom;
//            StringBuffer prefix = new StringBuffer(), suffix = new StringBuffer();
//            for (Map.Entry<String, String> entry : assets.entrySet()) {
//                alias = this.readAlias(entry.getKey());
//                className = this.readClassName(entry.getKey());
//                mappingFrom = container.getMappingContainer().get(className);
//                array = entry.getValue().split(";");
//                for (String str : array) {
//                    valueFrom = str.substring(0, str.indexOf("->")).trim();
//                    valueTo = str.substring(str.indexOf("->") + "->".length()).trim();
//                    if (valueFrom.indexOf("@") > -1) {
//                        if (valueFrom.indexOf(")") > -1) {
//                            field = valueFrom.substring(valueFrom.indexOf("@") + 1, valueFrom.indexOf(")"));
//                        } else {
//                            field = valueFrom.substring(valueFrom.indexOf("@") + 1);
//                        }
//                        suffix.append("," + valueFrom.replace("@" + field, alias + "." + mappingFrom.get(field).substring(0, mappingFrom.get(field).indexOf("="))));
//                    } else {
//                        suffix.append("," + valueFrom);
//                    }
//                    prefix.append("," + valueTo);
//                }
//            }
//            return new StringBuffer(" " + container.getModelContainer().get(clazz.getSimpleName()).table() + " (" + prefix.substring(1) + ") select " + suffix.substring(1));
//        }
//    }

//    //解析jql
//    private StringBuffer resolve(StringBuffer jql) throws Exception {
//        if (jql.indexOf("[") > -1 && jql.indexOf("]") > -1) {
//            String joinType, joinTo, aliasTo, aliasFrom, classNameTo, classNameFrom;
//            String[] joinFroms;
//            StringBuffer join = new StringBuffer(), check = new StringBuffer();
//            String[] array = jql.substring(jql.indexOf("[") + 1, jql.lastIndexOf("]")).split(";");
//            for (int i = 0; i < array.length; i++) {
//                joinType = this.readJoinType(array[i]);
//                joinTo = this.readJoinTo(array[i]);
//                aliasTo = this.readAlias(joinTo);
//                classNameTo = this.readClassName(joinTo);
//                join.append(joinType + container.getModelContainer().get(classNameTo).table() + " " + aliasTo + " on ");
//                joinFroms = this.readJoinFroms(array[i]);
//                if (joinFroms == null) {
//                    if (array[i].indexOf("|") < 0) {
//                        throw new RuntimeException("缺失过滤条件");
//                    }
//                    join.append(" on " + array[i].substring(array[i].indexOf("|") + 1));
//                    continue;
//                }
//                for (String joinFrom : joinFroms) {
//                    aliasFrom = this.readAlias(joinFrom);
//                    classNameFrom = this.readClassName(joinFrom);
//                    jql.replace(jql.indexOf("["), jql.indexOf("[") + 1, container.getModelContainer().get(classNameFrom).table() + " " + aliasFrom + " [");
//                    join.append(aliasTo + "." + container.getJoinerContainer().get(classNameTo).get(classNameFrom).key() + " = "
//                            + aliasFrom + "." + container.getJoinerContainer().get(classNameTo).get(classNameFrom).refKey());
//                    if (array[i].indexOf("|") > -1) {
//                        join.append(" and " + array[i].substring(array[i].indexOf("|") + 1));
//                    }
//                    check.append(this.queryCheck(aliasFrom, classNameFrom));
//                }
//                join.append(check).append(this.queryCheck(aliasTo, classNameTo));
//            }
//            jql.replace(jql.indexOf("["), jql.lastIndexOf("]") + 1, join.toString());
//        }
//        return jql;
//    }

//    //解析
//    private StringBuffer resolve(Map<String, String> assets, StringBuffer jql, Class<?> clazz) throws Exception {
//        StringBuffer buffer = this.resolve(assets, clazz);
//        jql = this.resolve(jql);
//        jql.replace(jql.indexOf("..."), jql.indexOf("...") + "...".length(), buffer.substring(1));
//        return jql;
//    }

    //******************************************************************************************************************

    @Override
    public List query(BaseInfo info) {
        info.load();
        return sqlMapper.query(info);
    }

    @Override
    public List queryByPage(PageInfo info) {
        return pageService.query(info);
    }

    @Override
    public List queryOnes(BaseInfo info) {
        info.load();
        return sqlMapper.queryOnes(info);
    }

    @Override
    public Object queryOne(BaseInfo info) {
        info.load();
        return sqlMapper.queryOne(info);
    }

    @Override
    public boolean save(BaseInfo info) throws Exception {
        info.load();
        int i = sqlMapper.save(info);
        return i > 0 ? true : false;
    }

    @Override
    public boolean update(BaseInfo info) throws Exception {
        info.load();
        int i = sqlMapper.update(info);
        return i > 0 ? true : false;
    }

    @Override
    public boolean remove(BaseInfo info) throws Exception {
        info.load();
        int i = sqlMapper.remove(info);
        return i > 0 ? true : false;
    }

    //******************************************************************************************************************

    @Override
    public <T extends BaseBean> List<T> queryBeans(BaseInfo info) throws Exception {
        List<Map<String, Object>> maps = this.query(info);
        List<T> list = ConvertUtil.mapsToBeans(maps, info.getClazz());
        return list;
    }

    @Override
    public <T extends BaseBean> List<T> queryBeansByPage(PageInfo info) throws Exception {
        List<Map<String, Object>> maps = this.query(info);
        List<T> list = ConvertUtil.mapsToBeans(maps, info.getClazz());
        return list;
    }

    @Override
    public <T extends BaseBean> boolean saveBean(T bean) throws Exception {
        if (bean == null) {
            throw new RuntimeException("对象为空");
        }
        Map<String, Object> params = ConvertUtil.beanToMap(bean);
        String key = bean.getClass().getSimpleName();
        this.saveCheck(params, key);
        StringBuffer sql = container.getInstructionContainer().get(key).get("insert");
        boolean flag = this.save(new BaseInfo(sql, params));
        return flag;
    }

    @Override
    public <T extends BaseBean> boolean batchSaveBySql(BatchInfo info) throws Exception {
        if (info.getBatchList() == null || info.getBatchList().size() == 0) {
            throw new RuntimeException("列表为空");
        }
        String key = info.getClazz().getSimpleName();
        StringBuffer values = new StringBuffer("," + info.getInstruction().substring(info.getInstruction().toString().toLowerCase().indexOf("values") + "values".length()));
        List<List<Map<String, Object>>> lists = Lists.partition(info.getBatchList(), info.getBatchNum());
        List<InstructionBuilder> instructionBuilders;
        Map<String, Object> params;
        BaseInfo baseInfo;
        for (List<Map<String, Object>> list : lists) {
            instructionBuilders = Lists.newArrayList();
            for (int i = 0; i < list.size(); i++) {
                params = list.get(i);
                this.saveCheck(params, key);
                baseInfo = i > 0 ? new BaseInfo(values, params) : new BaseInfo(info.getInstruction(), params);
                baseInfo.load();
                instructionBuilders.addAll(baseInfo.getInstructionBuilders());
            }
            baseInfo = new BaseInfo(instructionBuilders);
            int i = sqlMapper.save(baseInfo);
            if (i <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <T extends BaseBean> boolean batchSaveByClass(BatchInfo info) throws Exception {
        if (info.getBatchList() == null || info.getBatchList().size() == 0) {
            throw new RuntimeException("列表为空");
        }
        String key = info.getClazz().getSimpleName();
        StringBuffer sql = container.getInstructionContainer().get(key).get("insert");
        StringBuffer values = new StringBuffer("," + sql.substring(sql.indexOf("values") + "values".length()));
        List<List<Map<String, Object>>> lists = Lists.partition(info.getBatchList(), info.getBatchNum());
        List<InstructionBuilder> instructionBuilders;
        Map<String, Object> params;
        BaseInfo baseInfo;
        for (List<Map<String, Object>> list : lists) {
            instructionBuilders = Lists.newArrayList();
            for (int i = 0; i < list.size(); i++) {
                params = list.get(i);
                this.saveCheck(params, key);
                baseInfo = i > 0 ? new BaseInfo(values, params) : new BaseInfo(info.getInstruction(), params);
                baseInfo.load();
                instructionBuilders.addAll(info.getInstructionBuilders());
            }
            baseInfo = new BaseInfo(instructionBuilders);
            int i = sqlMapper.save(baseInfo);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <T extends BaseBean> boolean batchSaveByBeans(BatchInfo info) throws Exception {
        if (info.getBeans() == null || info.getBeans().size() == 0) {
            throw new RuntimeException("列表为空");
        }
        String key = info.getBeans().get(0).getClass().getSimpleName();
        StringBuffer sql = container.getInstructionContainer().get(key).get("insert");
        StringBuffer values = new StringBuffer("," + sql.substring(sql.indexOf("values") + "values".length()));
        List<List<T>> lists = Lists.partition(info.getBeans(), info.getBatchNum());
        Map<String, Object> params;
        List<InstructionBuilder> instructionBuilders;
        BaseInfo baseInfo;
        for (List<T> list : lists) {
            instructionBuilders = Lists.newArrayList();
            for (int i = 0; i < list.size(); i++) {
                params = ConvertUtil.beanToMap(list.get(i));
                this.saveCheck(params, key);
                baseInfo = i > 0 ? new BaseInfo(values, params) : new BaseInfo(info.getInstruction(), params);
                baseInfo.load();
                instructionBuilders.addAll(info.getInstructionBuilders());
            }
            baseInfo = new BaseInfo(instructionBuilders);
            int i = sqlMapper.save(baseInfo);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <T extends BaseBean> boolean batchSaveDistinctOne(BatchInfo info) throws Exception {
        if (info.getBeans() == null || info.getBeans().size() == 0) {
            throw new RuntimeException("列表为空");
        }
        List<Map<String, Object>> maps = CollectionUtil.distinctList(info.getBeans(), info.getFields()[0]);
        info.setBatchList(maps);
        info.setClazz(info.getBeans().get(0).getClass());
        boolean flag = this.batchSaveByClass(info);
        return flag;
    }

    @Override
    public <T extends BaseBean> boolean batchSaveDistinctOnes(BatchInfo info) throws Exception {
        if (info.getBeans() == null || info.getBeans().size() == 0) {
            throw new RuntimeException("列表为空");
        }
        List<Map<String, Object>> maps = CollectionUtil.distinctList(info.getBeans(), info.getFields());
        info.setBatchList(maps);
        info.setClazz(info.getBeans().get(0).getClass());
        boolean flag = this.batchSaveByClass(info);
        return flag;
    }

    @Override
    public <T extends BaseBean> boolean updateBean(T bean) throws Exception {
        if (bean == null) {
            throw new RuntimeException("对象不存在");
        }
        Map<String, Object> params = ConvertUtil.beanToMap(bean);
        String key = bean.getClass().getSimpleName();
        Map<String, String> mapping = container.getMappingContainer().get(key);
        String sql = container.getInstructionContainer().get(key).get("update").toString();
        String set = "";
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                set += "," + mapping.get(entry.getKey());
            }
        }
        sql = sql.replace("@Update", set.substring(1));
        boolean flag = this.update(new BaseInfo(new StringBuffer(sql), params));
        return flag;
    }

    @Override
    public <T extends BaseBean> boolean removeBean(T bean) throws Exception {
        if (bean == null) {
            throw new RuntimeException("对象为空");
        }
        Map params = ConvertUtil.beanToMap(bean);
        String key = bean.getClass().getSimpleName();
        StringBuffer sql = container.getInstructionContainer().get(key).get("delete");
        boolean flag = this.remove(new BaseInfo(sql, params));
        return flag;
    }

}
