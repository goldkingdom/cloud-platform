package cn.xj.common.service;

import cn.xj.common.model.BaseInfo;
import cn.xj.common.model.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/4/26.
 */
public interface BaseService {

    public List query(BaseInfo info);

    public List queryByPage(PageInfo info);

    public List queryOnes(BaseInfo info);

    public Object queryOne(BaseInfo info);

    public boolean save(BaseInfo info) throws Exception;

    public boolean update(BaseInfo info) throws Exception;

    public boolean remove(BaseInfo info) throws Exception;

}
