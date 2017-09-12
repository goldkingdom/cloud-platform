package cn.xj.common.api;

import cn.xj.common.model.BaseBean;
import cn.xj.common.model.BaseInfo;
import cn.xj.common.model.BatchInfo;
import cn.xj.common.model.PageInfo;
import org.springframework.cloud.bus.ConditionalOnBusEnabled;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("${provider.mybatis}")
@ConditionalOnBusEnabled
public interface ClientApi {

    @RequestMapping("/feign/query")
    List query(@RequestBody BaseInfo info);

    @RequestMapping("/feign/queryByPage")
    List queryByPage(@RequestBody PageInfo info);

    @RequestMapping("/feign/queryOnes")
    List queryOnes(@RequestBody BaseInfo info);

    @RequestMapping("/feign/queryOne")
    Object queryOne(@RequestBody BaseInfo info);

    @RequestMapping("/feign/save")
    boolean save(@RequestBody BaseInfo info) throws Exception;

    @RequestMapping("/feign/update")
    boolean update(@RequestBody BaseInfo info) throws Exception;

    @RequestMapping("/feign/remove")
    boolean remove(@RequestBody BaseInfo info) throws Exception;

    @RequestMapping("/feign/queryBeans")
    <T> List<T> queryBeans(@RequestBody BaseInfo info) throws Exception;

    @RequestMapping("/feign/queryBeansByPage")
    <T> List<T> queryBeansByPage(@RequestBody PageInfo info) throws Exception;

    @RequestMapping("/feign/saveBean")
    <T> boolean saveBean(@RequestBody T bean) throws Exception;

    @RequestMapping("/feign/batchSaveBySql")
    boolean batchSaveBySql(@RequestBody BatchInfo info) throws Exception;

    @RequestMapping("/feign/batchSaveByClass")
    boolean batchSaveByClass(@RequestBody BatchInfo info) throws Exception;

    @RequestMapping("/feign/batchSaveBeans")
    boolean batchSaveBeans(@RequestBody BatchInfo info) throws Exception;

    @RequestMapping("/feign/batchSaveDistinctOne")
    boolean batchSaveDistinctOne(@RequestBody BatchInfo info) throws Exception;

    @RequestMapping("/feign/batchSaveDistinctOnes")
    boolean batchSaveDistinctOnes(@RequestBody BatchInfo info) throws Exception;

    @RequestMapping("/feign/updateBean")
    <T> boolean updateBean(@RequestBody T bean) throws Exception;

    @RequestMapping("/feign/removeBean")
    <T> boolean removeBean(@RequestBody T bean) throws Exception;

}
