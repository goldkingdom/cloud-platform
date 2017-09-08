package cn.xj.common.sql.service.impl;

import cn.xj.common.sql.service.PaginationService;
import cn.xj.common.sql.service.SqlService;
import cn.xj.common.util.ConvertUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Welink on 2017/2/8.
 */
@Service
public class OrclPaginationServiceImpl implements PaginationService {

    @Autowired
    private SqlService sqlService;

    @Override
    public List query(StringBuffer sql, Map params, Map pagination, String countColumn) {
        if (pagination == null) {
            throw new RuntimeException("请输入分页信息");
        }
        String str = sql.toString();
        str = str.toLowerCase().indexOf("order by") > 0 ? str.substring(0, str.toLowerCase().indexOf("order by") - 1) : str;
        //获取分页信息
        String countSql = StringUtils.isEmpty(countColumn) ? str.replace(str.substring(0, str.toLowerCase().indexOf("from")), "select count(*) COUNT ")
                : str.replace(str.substring(0, str.toLowerCase().indexOf("from")), "select count(" + countColumn + ") COUNT ");
        List<Map> countList = sqlService.query(new StringBuffer(countSql), params);
        Integer totalItems;
        if (str.toLowerCase().indexOf("group by") > 0) {
            totalItems = countList == null || countList.size() == 0 ? 0 : countList.size();
        } else {
            totalItems = countList == null || countList.size() == 0 ? 0 : ConvertUtil.objectToInt(countList.get(0).get("COUNT"));
        }
        countSql = null;
        pagination.put("totalItems", totalItems);
        pagination.put("totalPages", totalItems % ConvertUtil.objectToInt(pagination.get("itemsPerPage")) > 0 ? totalItems / ConvertUtil.objectToInt(pagination.get("itemsPerPage")) + 1 : totalItems / ConvertUtil.objectToInt(pagination.get("itemsPerPage")));
        //根据分页信息进行分页查询
        String paginationSql = "SELECT pagination1.* FROM (SELECT pagination2.*,rownum AS rowno FROM (" + sql.toString() + ") pagination2 where rownum <= {itemsEnd,Integer}) pagination1 where pagination1.rowno > {itemsStart,Integer}";
        params = params == null ? Maps.newHashMap() : params;
        params.put("itemsEnd", ConvertUtil.objectToInt(pagination.get("currentPage")) * ConvertUtil.objectToInt(pagination.get("itemsPerPage")));
        params.put("itemsStart", (ConvertUtil.objectToInt(pagination.get("currentPage")) - 1) * ConvertUtil.objectToInt(pagination.get("itemsPerPage")));
        List<Map> list = sqlService.query(new StringBuffer(paginationSql), params);
        paginationSql = null;
        str = null;
        return list;
    }

}
