package cn.com.hyun.framework.page.pagehelper;

public class SqlUtilConfig {
    //方言
    private String dialect;
    //RowBounds参数offset作为PageNum使用 - 默认不使用
    private boolean offsetAsPageNum = false;
    //RowBounds是否进行count查询 - 默认不查询
    private boolean rowBoundsWithCount = false;
    //当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
    private boolean pageSizeZero = false;
    //分页合理化
    private boolean reasonable = false;
    //是否支持接口参数来传递分页参数，默认false
    private boolean supportMethodsArguments = false;
    //参数别名
    private String params;
    //运行时自动获取dialect
    private boolean autoRuntimeDialect;

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public boolean isOffsetAsPageNum() {
        return offsetAsPageNum;
    }

    public void setOffsetAsPageNum(boolean offsetAsPageNum) {
        this.offsetAsPageNum = offsetAsPageNum;
    }

    public boolean isRowBoundsWithCount() {
        return rowBoundsWithCount;
    }

    public void setRowBoundsWithCount(boolean rowBoundsWithCount) {
        this.rowBoundsWithCount = rowBoundsWithCount;
    }

    public boolean isPageSizeZero() {
        return pageSizeZero;
    }

    public void setPageSizeZero(boolean pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
    }

    public boolean isReasonable() {
        return reasonable;
    }

    public void setReasonable(boolean reasonable) {
        this.reasonable = reasonable;
    }

    public boolean isSupportMethodsArguments() {
        return supportMethodsArguments;
    }

    public void setSupportMethodsArguments(boolean supportMethodsArguments) {
        this.supportMethodsArguments = supportMethodsArguments;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public boolean isAutoRuntimeDialect() {
        return autoRuntimeDialect;
    }

    public void setAutoRuntimeDialect(boolean autoRuntimeDialect) {
        this.autoRuntimeDialect = autoRuntimeDialect;
    }
}
