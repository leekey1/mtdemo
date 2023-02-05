package cn.com.hyun.common;

import cn.com.hyun.framework.page.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by zhg on 2017/3/8.
 */
public class DataPageInfo<T, D> extends PageInfo<T> {
    private D data;

    public DataPageInfo() {
        super();
    }

    public DataPageInfo(List<T> list, D data) {
        super(list);
        this.data = data;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

}
