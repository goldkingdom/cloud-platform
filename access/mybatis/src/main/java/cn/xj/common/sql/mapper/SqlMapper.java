package cn.xj.common.sql.mapper;

import cn.xj.common.model.BaseInfo;

import java.util.List;

/**
 * Created by Welink on 2017/1/24.
 */
public interface SqlMapper {

    public List query(BaseInfo info);

    public List queryOnes(BaseInfo info);

    public Object queryOne(BaseInfo info);

    public Integer save(BaseInfo info);

    public Integer update(BaseInfo info);

    public Integer remove(BaseInfo info);

}
