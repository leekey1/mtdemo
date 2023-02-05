package cn.com.hyun.common;




import cn.com.hyun.common.auth.CurrentEmployee;
import cn.com.hyun.common.auth.CurrentEmployeeService;
import cn.com.hyun.framework.utils.AssertUtils;

import cn.com.hyun.framework.web.util.HttpContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 * Created by zhg on 2016/3/26.
 */
@Service
public abstract class BaseService {

    @Autowired
    CurrentEmployeeService currentEmployeeService;


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //变量 10000
    protected  final static BigDecimal yiwan = new BigDecimal("10000");

    protected Map<String, Object> failState(String... str) {
        String message = "操作失败";
        if (str.length > 0) {
            message = str[0];
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", message);
        return map;
    }


    protected Map<String, Object> successState() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "操作成功");
        return map;
    }

    protected CurrentEmployee getEmp() {
        CurrentEmployee emp = currentEmployeeService.getCurrentEmployee(HttpContextUtils.getRequest());
        AssertUtils.notNull(emp, "token过期");
        return emp;
    }

    //生成 32位 UUID
    protected String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    protected static Date StringToDate(String dateStr) {

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            date = sf.parse(dateStr);
            //打印Date
//            /System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date;
    }


    protected static String StringToDate(Date dateStr) {

        String date = "";

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            if(dateStr != null) {
                date = sf.format(dateStr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return date;
    }


    protected static BigDecimal StringToBigDecimal(String bigDecimal){
        BigDecimal bd=new BigDecimal(bigDecimal);
        return bd;
    }

    protected static String BigDecimalToString(BigDecimal bigDecimalStr){
        return  bigDecimalStr.stripTrailingZeros().toPlainString();

    }






}
