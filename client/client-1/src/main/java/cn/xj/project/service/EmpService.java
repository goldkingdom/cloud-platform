package cn.xj.project.service;

import cn.xj.project.model.Emp;

import java.util.List;
import java.util.Map;

public interface EmpService {

    public List<Emp> queryEmpListByPage(Map params) throws Exception;

}
