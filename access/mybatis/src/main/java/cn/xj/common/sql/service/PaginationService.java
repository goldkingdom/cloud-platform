package cn.xj.common.sql.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/2/8.
 */
public interface PaginationService {

    public List query(StringBuffer sql, Map params, Map<String, Integer> pagination, String countColumn);

}
