package cn.com.hyun.framework.web.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

/**
 * Created by hyunwoo
 */
public class CustomerJsonMapperConverter extends MappingJackson2HttpMessageConverter {

    public CustomerJsonMapperConverter() {
        this(true);
    }

    public CustomerJsonMapperConverter(boolean hiddenNull) {
        ObjectMapper jsonMapper = this.getObjectMapper();
        if (hiddenNull) {
            jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        } else {
            jsonMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        }
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        jsonMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

//        SimpleModule module = new SimpleModule();
//        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
//        objectMapper.registerModule(module);
    }
}
