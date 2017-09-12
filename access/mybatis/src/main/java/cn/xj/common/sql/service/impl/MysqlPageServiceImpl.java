package cn.xj.common.sql.service.impl;

import cn.xj.common.model.BaseInfo;
import cn.xj.common.model.PageInfo;
import cn.xj.common.sql.service.PageService;
import cn.xj.common.sql.service.SqlService;
import cn.xj.common.util.ConvertUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MysqlPageServiceImpl implements PageService {

    @Autowired
    private SqlService sqlService;

    @Override
    public List query(PageInfo info) {
        if (info == null) {
            throw new RuntimeException("请输入分页信息");
        }
        String str = info.getInstruction().toString();
        str = str.toLowerCase().indexOf("order by") > 0 ? str.substring(0, str.toLowerCase().indexOf("order by") - 1) : str;
        //获取分页信息
        String countSql = StringUtils.isEmpty(info.getCountColumn()) ? str.replace(str.substring(0, str.toLowerCase().indexOf("from")), "select count(*) COUNT ")
                : str.replace(str.substring(0, str.toLowerCase().indexOf("from")), "select count(" + info.getCountColumn() + ") COUNT ");
        List<Map> countList = sqlService.query(new BaseInfo(new StringBuffer(countSql), info.getParams()));
        Integer totalItems;
        if (str.toLowerCase().indexOf("group by") > 0) {
            totalItems = countList == null || countList.size() == 0 ? 0 : countList.size();
        } else {
            totalItems = countList == null || countList.size() == 0 ? 0 : ConvertUtil.objectToInt(countList.get(0).get("COUNT"));
        }
        countSql = null;
        info.setTotalItems(totalItems);
        info.setTotalPages(totalItems % ConvertUtil.objectToInt(info.getItemsPerPage()) > 0 ? totalItems / ConvertUtil.objectToInt(info.getItemsPerPage()) + 1 : totalItems / ConvertUtil.objectToInt(info.getItemsPerPage()));
        //根据分页信息进行分页查询
        StringBuffer pageSql = info.getInstruction().append(" limit {offset,Integer},{pageSize,Integer}");
        if (info.getParams() == null) {
            info.setParams(Maps.newHashMap());
        }
        info.getParams().put("offset", (ConvertUtil.objectToInt(info.getCurrentPage()) - 1) * ConvertUtil.objectToInt(info.getItemsPerPage()));
        info.getParams().put("pageSize", info.getItemsPerPage());
        info.setInstruction(pageSql);
        List<Map> list = sqlService.query(info);
        pageSql = null;
        str = null;
        return list;
    }

}
