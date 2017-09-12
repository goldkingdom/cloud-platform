package cn.xj.project.service.impl;

import cn.xj.common.api.ClientApi;
import cn.xj.common.model.PageInfo;
import cn.xj.project.model.Emp;
import cn.xj.project.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private ClientApi clientApi;

    @Override
    public List<Emp> queryEmpListByPage(Map params) throws Exception {
        StringBuffer sql = new StringBuffer("select * from emp where name = {name,String}");
        PageInfo info = new PageInfo(sql, params, Emp.class, 1, 10);
        List<Emp> list = clientApi.queryBeansByPage(info);
        return list;
    }

}
