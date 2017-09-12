package cn.xj.common.sql.service;

import cn.xj.common.model.BaseBean;
import cn.xj.common.model.BaseInfo;
import cn.xj.common.model.BatchInfo;
import cn.xj.common.model.PageInfo;
import cn.xj.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/2/6.
 */
public interface SqlService extends BaseService {

    /**
     * 指定类型查询
     *
     * @param info
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> List<T> queryBeans(BaseInfo info) throws Exception;

    /**
     * 指定类型分页查询
     *
     * @param info
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> List<T> queryBeansByPage(PageInfo info) throws Exception;

    /**
     * 单表插入
     *
     * @param bean
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean saveBean(T bean) throws Exception;

    /**
     * 指定类型指定sql带校验插入数据
     *
     * @param info
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean batchSaveBySql(BatchInfo info) throws Exception;

    /**
     * 指定类型不指定sql带校验批量插入数据
     *
     * @param info
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean batchSaveByClass(BatchInfo info) throws Exception;

    /**
     * 指定类型不指定sql带校验批量插入数据
     *
     * @param info
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean batchSaveByBeans(BatchInfo info) throws Exception;

    /**
     * 指定类型并按指定字段去重批量插入数据
     *
     * @param info
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean batchSaveDistinctOne(BatchInfo info) throws Exception;

    /**
     * 指定类型并按指定字段组去重批量插入数据
     *
     * @param info
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean batchSaveDistinctOnes(BatchInfo info) throws Exception;

    /**
     * 指定类型修改
     *
     * @param bean
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean updateBean(T bean) throws Exception;

    /**
     * 指定类型删除
     *
     * @param bean
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseBean> boolean removeBean(T bean) throws Exception;

}
