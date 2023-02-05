package cn.com.hyun.common;





import cn.com.hyun.framework.utils.AssertUtils;
import cn.com.hyun.framework.web.controller.BaseController;
import cn.com.hyun.framework.web.util.HttpContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * Created by zhg on 2017/11/6.
 */
@RequestMapping(value = "/d")
public class HBaseController  extends BaseController {

//    @Autowired
//    CurrentEmployeeService currentEmployeeService;
//
//    protected CurrentEmployee getEmp() {
//        CurrentEmployee emp = currentEmployeeService.getCurrentEmployee(HttpContextUtils.getRequest());
//        AssertUtils.notNull(emp, "token过期");
//        return emp;
//    }

    //生成 32位 UUID
    protected String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
