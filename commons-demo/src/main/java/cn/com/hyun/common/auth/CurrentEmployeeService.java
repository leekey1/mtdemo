package cn.com.hyun.common.auth;

import cn.com.hyun.framework.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhg
 */
@Service
public class CurrentEmployeeService {
    @Autowired
    private StringCacheSignNodeDao stringCacheDao;

    public CurrentEmployee getCurrentEmployee(String token) {
        String value = stringCacheDao.get(AuthConstants.SESSION_PERFIX + token);
        return JsonUtils.toBean(value, CurrentEmployee.class);
    }

    public CurrentEmployee getCurrentEmployee(HttpServletRequest request) {
        String token = request.getParameter("token");
        String value = stringCacheDao.get(AuthConstants.SESSION_PERFIX + token);
        return JsonUtils.toBean(value, CurrentEmployee.class);
    }

    public String getCurrentEmployeeAccount(String token) {
        CurrentEmployee currentEmployee = this.getCurrentEmployee(token);
        return currentEmployee.getEmpAccount();
    }

    public String getCurrentEmployeeAccount(HttpServletRequest request) {
        CurrentEmployee currentEmployee = this.getCurrentEmployee(request);
        return currentEmployee.getEmpAccount();
    }
}
