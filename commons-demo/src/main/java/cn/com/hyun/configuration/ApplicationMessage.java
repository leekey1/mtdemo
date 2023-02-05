package cn.com.hyun.configuration;




import cn.com.hyun.framework.utils.AssertUtils;
import cn.com.hyun.framework.utils.RegexUtils;

import java.text.MessageFormat;

/**
 * Created by helloworld on 2016/1/8
 */
public final class ApplicationMessage {


    public static final Integer PAGE_SIZE = 10;

    private static final String ADD_LOG = "添加{0},{1}";
    private static final String GET_BY_LOG = "查询{0}详细, {1}={2}";
    private static final String GET_PRODUCT_RULE_LOG = "获取产品规则, productId={0}, repaymentMethod={1}, raiseMethod={2}";
    private static final String GET_LOG = "查询全部{0}";
    private static final String GET_PAGE_LOG = "分页查询{0}";
    private static final String SUBMIT_AUDIT_LOG = "提交项目审核, projectId={0}";
    private static final String SUBMIT_AUDIT_SUCCESS = "提交项目审核成功";
    private static final String UPDATE_SUCCESS = "更新成功";
    private static final String STOP_SUCCESS = "终止成功";

    private static final String NOT_EXIST_ERROR = "{0}不存在";
    private static final String ACCEPT_STATUS_ERROR = "该贷款申请已受理或已拒绝受理，不能重复处理";
    private static final String DUPLICATE_ERROR = "{0}已存在";
    private static final String SUBMIT_AUDIT_ERROR = "贷款项目提交审核失败";


    public static void  lenghtValidator(Object object, String name, Integer num){
        if (num!=null){
            AssertUtils.isFalse(object.toString().length() > num, lenghtErro(name,num));
        }
    }

    public static void draftValidator(Object object, String message,String regex,Integer num) {
        if(object != null&&!"".equals(object.toString())) {
            lenghtValidator(object, message, num);
            if (regex!=null){
                AssertUtils.isTrue(RegexUtils.isValidateExpression(object.toString(), regex), getRegex(message));
            }
        }
    }

    public static final String VERSION_ERROR = " 此进件已进行过更新操作请刷新重试";

    //长度message
    private static final String LENGTH_ERROR = "{0}最大长度为{1}!";

    private static final String NOT_NULL = "{0}不能为空!";

    private static final String REGEX = "请输入正确的{0}!";


    //匹配年月日日期格式
    public static final String DATE_REGEXP="^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$";

    //次数校验正则，正整数，大于等0，小于100
    public static final String FREQUENCY_REGEXP="^99$|^(\\d|[1-9]\\d)$";

    //正整数，大于0小于等于100
    public static  final String MONTH_NUM_REGEXP="^([1-9]\\d?|100)$";

    //字母，数字，汉字
    public static final String CHINESE_NUMBER_ENGLISH_REGEXP="^[\\u4E00-\\u9FA5A-Za-z0-9]+$";

    //大写字母，数字
    public static final String UPWARD_LETTER_NUMBER_REGEXP="^[A-Z0-9]+$";

    //比率，未换算前，小数点后4位，最大1
    public static final String RATE_REGEXP="^[0](\\.[0-9]{1,4})?$";

    //比率，换算后，小数点后2位，最大100
    public static final String RATE_CHANGE_REGEXP="^100$|^(\\d|[1-9]\\d)(\\.[0-9]{1,2})?$";

    //正整数，大于等于0，小于10000000，人数

    public static final String  PEOPLE_NUMBER_REGEXP="^(\\d|[0-9]\\d{0,6})$";

    //手机号码校验
    public static final String  PHONE_REGEXP="^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\d{8}$";


    //金额，面积，正数，大于等于0，小于10000000，小数点后两位
    public static final String  MONEY_REGEXP="^(\\d|[0-9]\\d{0,6})(\\.\\d{0,2})*$";

    //收入 6位小数
    public static final String  TON_INCOME="^(\\d|[0-9]\\d{0,6})(\\.\\d{0,4})*$";

    //汉字
    public static final String CHINESE_REGEX="^[\\u4e00-\\u9fa5]+$";

    //正整数，大于0，小于等于10000000
    public static final String BIG_INT_NON_ZERO="^10000000$|^(\\d|[1-9]\\d{0,6})$";

    public static String lenghtErro(String name,Integer num) {
        return MessageFormat.format(LENGTH_ERROR, name, num);
    }

    public static String notNull(String objectName) {
        return MessageFormat.format(NOT_NULL, objectName);
    }

    public static String getRegex(String objectName) {
        return MessageFormat.format(REGEX, objectName);
    }


    /* 普通LOG */
    public static String addLog(Object object) {
        return MessageFormat.format(ADD_LOG, object.getClass().getSimpleName(), object.toString());
    }

    public static String getByLog(String objectName, String key, Object value) {
        return MessageFormat.format(GET_BY_LOG, objectName, key, value);
    }

    public static String getProductRuleLog(Integer productId, String repaymentMethod, String raiseMethod) {
        return MessageFormat.format(GET_PRODUCT_RULE_LOG, productId, repaymentMethod, raiseMethod);
    }

    public static String getPageLog(String objectName) {
        return MessageFormat.format(GET_PAGE_LOG, objectName);
    }

    public static String submitAuditLog(Integer projectId) {
        return MessageFormat.format(SUBMIT_AUDIT_LOG, projectId);
    }

    /* 操作成功LOG */

    public static String submitAuditSuccess(Integer projectId) {
        return SUBMIT_AUDIT_SUCCESS;
    }

    public static String updateSuccess(Integer id) {
        return UPDATE_SUCCESS;
    }

    public static String stopSuccess(Integer id) {
        return STOP_SUCCESS;
    }

    /* 异常LOG */
    public static String notExistError(String objectName, String key, Object value) {
        return MessageFormat.format(NOT_EXIST_ERROR, objectName);
    }

    public static String acceptStatusError() {
        return ACCEPT_STATUS_ERROR;
    }

    public static String duplicateError(String message) {
        return MessageFormat.format(DUPLICATE_ERROR, message);
    }

    public static String submitAuditError(Integer id) {
        return SUBMIT_AUDIT_ERROR;
    }

    private static final String GOBACK_SUCCESS = "退回调整成功";
    private static final String APPROVAL_SUCCESS = "批准成功";
    private static final String REJECT_SUCCESS = "拒绝成功";
    private static final String UPDATE_FAILURE = "更新失败";
    private static final String GET_BY_PRODUCT_ID_LOG = "查询产品规则,productId:{0}";
    private static final String ADD_SUCCESS = "添加成功";

    private static final String TIMEOUT_ERROR = "连接超时,请稍后重试";
    private static final String PARAMETER_ERROR = "{0}错误";
    private static final String UPDATE_LOG = "修改{0}, {1}";

    public static String addSuccess(Integer id) {
        return ADD_SUCCESS;
    }

    public static String gobackSuccess(Integer id) {
        return GOBACK_SUCCESS;
    }

    public static String approvalSuccess(Integer id) {
        return APPROVAL_SUCCESS;
    }

    public static String rejectSuccess(Integer id) {
        return REJECT_SUCCESS;
    }


    public static String timeout() {
        return TIMEOUT_ERROR;
    }

    public static String parameterError(String parameter) {
        return MessageFormat.format(PARAMETER_ERROR, parameter);
    }

    public static String updateFailure(Integer id) {
        return UPDATE_FAILURE;
    }


    public static String updateLog(Object object) {
        return MessageFormat.format(UPDATE_LOG, object.getClass().getSimpleName(), object.toString());
    }

    public static String getLog(String objectName) {
        return MessageFormat.format(GET_LOG, objectName);
    }

    public static String getByProductIdLog(Integer productId) {
        return MessageFormat.format(GET_BY_PRODUCT_ID_LOG, productId);
    }

}
