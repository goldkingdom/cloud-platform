package cn.xj.common.sql.service;

import cn.xj.common.model.BaseBean;
import cn.xj.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/2/6.
 */
public interface SqlService extends BaseService {

    /**
     * 根据需要查询的字段以及表的级联关系查询
     *
     * @param jql
     * @param params
     * @param assets
     * @return
     * @throws Exception
     */
    public List select(Map<String, String> assets, StringBuffer jql, Map params) throws Exception;

    /**
     * 不指定类型分页级联查询，并把查询结果按级联关系封装到返回的对象中
     *
     * @param assets
     * @param jql
     * @param params
     * @param pager
     * @param countColumn
     * @return
     * @throws Exception
     */
    public List select(Map<String, String> assets, StringBuffer jql, Map params, Map pager, String countColumn) throws Exception;

    /**
     * 指定类型查询
     *
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> List<T> select(StringBuffer sql, Map params, Class<T> clazz) throws Exception;

    /**
     * 指定类型分页查询
     *
     * @param sql
     * @param params
     * @param pager
     * @param countColumn
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> List<T> select(StringBuffer sql, Map params, Map pager, String countColumn, Class<T> clazz) throws Exception;

    /**
     * 指定类型级联查询，并把查询结果按级联关系封装到返回的对象中
     *
     * @param jql
     * @param params
     * @param assets
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> List<T> cascadeSelect(Map<String, String> assets, StringBuffer jql, Map params) throws Exception;

    /**
     * 指定类型分页级联查询，并把查询结果按级联关系封装到返回的对象中
     *
     * @param assets
     * @param jql
     * @param params
     * @param pager
     * @param countColumn
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> List<T> cascadeSelect(Map<String, String> assets, StringBuffer jql, Map params, Map pager, String countColumn) throws Exception;

    /**
     * 单表插入
     *
     * @param bean
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean insert(T bean) throws Exception;

    /**
     * 无类型指定sql带校验插入数据
     *
     * @param sql
     * @param maps
     * @param batchNum
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean insert(StringBuffer sql, List<Map<String, Object>> maps, int batchNum, Class<T> clazz) throws Exception;

    /**
     * 无类型不指定sql带校验批量插入数据
     *
     * @param maps
     * @param batchNum
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean insert(List<Map<String, Object>> maps, int batchNum, Class<T> clazz) throws Exception;

    /**
     * 指定类型不指定sql带校验批量插入数据
     *
     * @param beans
     * @param batchNum
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean insert(List<T> beans, int batchNum) throws Exception;

    /**
     * 指定类型并按指定字段去重批量插入数据
     *
     * @param beans
     * @param batchNum
     * @param field
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean insert(List<T> beans, int batchNum, String field) throws Exception;

    /**
     * 指定类型并按指定字段组去重批量插入数据
     *
     * @param beans
     * @param batchNum
     * @param fields
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean insert(List<T> beans, int batchNum, String[] fields) throws Exception;

    /**
     * 从多张表中抽取数据插入一张表
     *
     * @param assets
     * @param jql
     * @param params
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean insertSelect(Map<String, String> assets, StringBuffer jql, Map params, Class<T> clazz) throws Exception;

    /**
     * 指定类型修改
     *
     * @param bean
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean update(T bean) throws Exception;

    /**
     * 指定类型删除
     *
     * @param bean
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean delete(T bean) throws Exception;

}
