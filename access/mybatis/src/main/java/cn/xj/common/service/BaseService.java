package cn.xj.common.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/4/26.
 */
public interface BaseService {

    public List query(StringBuffer instruction, Map params);

    public List query(StringBuffer instruction, Map params, Map pager, String countColumn);

    public List queryOnes(StringBuffer instruction, Map params);

    public Object queryOne(StringBuffer instruction, Map params);

    public boolean save(StringBuffer instruction, Map params) throws Exception;

    public boolean update(StringBuffer instruction, Map params) throws Exception;

    public boolean remove(StringBuffer instruction, Map params) throws Exception;

}
