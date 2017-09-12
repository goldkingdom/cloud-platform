package cn.xj.common.api;

import cn.xj.common.model.BaseBean;
import cn.xj.common.model.BaseInfo;
import cn.xj.common.model.BatchInfo;
import cn.xj.common.model.PageInfo;
import cn.xj.common.sql.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feign")
public class ServerApi {

    @Autowired
    private SqlService sqlService;

    @RequestMapping("/query")
    List query(@RequestBody BaseInfo info) {
        return sqlService.query(info);
    }

    @RequestMapping("/queryByPage")
    List queryByPage(@RequestBody PageInfo info) {
        return sqlService.queryByPage(info);
    }

    @RequestMapping("/queryOnes")
    List queryOnes(@RequestBody BaseInfo info) {
        return sqlService.queryOnes(info);
    }

    @RequestMapping("/queryOne")
    Object queryOne(@RequestBody BaseInfo info) {
        return sqlService.queryOne(info);
    }

    @RequestMapping("/save")
    boolean save(@RequestBody BaseInfo info) throws Exception {
        return sqlService.save(info);
    }

    @RequestMapping("/update")
    boolean update(@RequestBody BaseInfo info) throws Exception {
        return sqlService.update(info);
    }

    @RequestMapping("/remove")
    boolean remove(@RequestBody BaseInfo info) throws Exception {
        return sqlService.remove(info);
    }

    @RequestMapping("/queryBeans")
    <T extends BaseBean> List<T> queryBeans(@RequestBody BaseInfo info) throws Exception {
        List<T> list = sqlService.queryBeans(info);
        return list;
    }

    @RequestMapping("/queryBeansByPage")
    <T extends BaseBean> List<T> queryBeansByPage(@RequestBody PageInfo info) throws Exception {
        List<T> list = sqlService.queryBeansByPage(info);
        return list;
    }

    @RequestMapping("/saveBean")
    <T extends BaseBean> boolean saveBean(@RequestBody T bean) throws Exception {
        return sqlService.saveBean(bean);
    }

    @RequestMapping("/batchSaveBySql")
    <T extends BaseBean> boolean batchSaveBySql(@RequestBody BatchInfo info) throws Exception {
        return sqlService.batchSaveBySql(info);
    }

    @RequestMapping("/batchSaveByClass")
    <T extends BaseBean> boolean batchSaveByClass(@RequestBody BatchInfo info) throws Exception {
        return sqlService.batchSaveByClass(info);
    }

    @RequestMapping("/batchSaveBeans")
    <T extends BaseBean> boolean batchSaveBeans(@RequestBody BatchInfo info) throws Exception {
        return sqlService.batchSaveByBeans(info);
    }

    @RequestMapping("/batchSaveDistinctOne")
    <T extends BaseBean> boolean batchSaveDistinctOne(@RequestBody BatchInfo info) throws Exception {
        return sqlService.batchSaveDistinctOne(info);
    }

    @RequestMapping("/batchSaveDistinctOnes")
    <T extends BaseBean> boolean batchSaveDistinctOnes(@RequestBody BatchInfo info) throws Exception {
        return sqlService.batchSaveDistinctOnes(info);
    }

    @RequestMapping("/updateBean")
    <T extends BaseBean> boolean updateBean(@RequestBody T bean) throws Exception {
        return sqlService.updateBean(bean);
    }

    @RequestMapping("/removeBean")
    <T extends BaseBean> boolean removeBean(@RequestBody T bean) throws Exception {
        return sqlService.removeBean(bean);
    }

}
