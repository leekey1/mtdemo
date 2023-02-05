package cn.com.hyun.framework.web.converter;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;

/**
 * Created by hyunwoo
 */
public class CustomerDateConverter extends CustomDateEditor {

    public CustomerDateConverter() {
        super(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true);
    }
}
