package cn.com.hyun.common;

import cn.com.hyun.framework.page.pagehelper.Page;
import cn.com.hyun.framework.page.pagehelper.PageHelper;
import cn.com.hyun.framework.page.pagehelper.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by zhg on 2017/2/20.
 */
public class PageUtils {

    /**
     * 分页查询
     * @param supplier 查询列表方法
     * @param pageNum 页码数
     * @param <T> 列表类型
     * @return PageInfo
     */
    public static <T> PageInfo<T> page(Supplier<List<T>> supplier, Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        return new PageInfo<>(supplier.get());
    }

    /**
     * 分页查询
     * 构造 DataPageInfo(扩展了PageInfo,添加自定义的数据字段data)
     * @param listSupplier 查询列表方法
     * @param dataSupplier data字段提供方法
     * @param pageNum 页码数
     * @param <T> 列表类型
     * @param <D> data类型
     * @return DataPageInfo
     */
    public static <T, D> DataPageInfo<T, D> page(Supplier<List<T>> listSupplier, Supplier<D> dataSupplier, Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        return new DataPageInfo<>(listSupplier.get(), dataSupplier.get());
    }

    /**
     * 数据转换
     * @param originList 原始数组
     * @param mapper 转换方法
     * @param <T> 原始类型
     * @param <R> 目标类型
     * @return List
     */
    public static <T, R> List<R> map(List<T> originList, Function<? super T, R> mapper) {
        List<R> list = originList.stream().map(mapper).collect(Collectors.toList());
        if (originList instanceof Page) {
            Page tempPage = (Page) originList;
            Page<R> page = new Page<>(tempPage.getPageNum(), tempPage.getPageSize());
            page.setTotal(tempPage.getTotal());
            page.addAll(list);
            return page;
        }
        return list;
    }

    /**
     * 根据查询条件获取第一条数据
     * @param supplier 查询方法
     * @param <T> 返回类型
     * @return Optional
     */
    public static <T> Optional<T> getFirst(Supplier<List<T>> supplier){
        PageHelper.startPage(1, 1);
        List<T> list = supplier.get();
        if(list == null || list.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(list.get(0));
        }
    }
}
