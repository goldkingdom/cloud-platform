package cn.xj.project.controller;

import cn.xj.common.config.BaseConfig;
import cn.xj.common.model.Result;
import cn.xj.project.model.Emp;
import cn.xj.project.service.EmpService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private BaseConfig baseConfig;

    @Autowired
    private EmpService empService;

    @RequestMapping("/queryEmpListByPage")
    public Object queryEmpListByPage() {
        Map params = Maps.newHashMap();
        params.put("name", "jack");
        List<Emp> list = null;
        try {
            list = empService.queryEmpListByPage(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result = new Result(baseConfig.getVersion(), Thread.currentThread().getStackTrace()[1].getMethodName(), list);
        return result;
    }

}
