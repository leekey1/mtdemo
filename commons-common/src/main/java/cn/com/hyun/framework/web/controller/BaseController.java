package cn.com.hyun.framework.web.controller;

import cn.com.hyun.framework.utils.JsonUtils;
import cn.com.hyun.framework.web.exception.ForbiddenException;
import cn.com.hyun.framework.web.exception.MultipartUploadException;
import cn.com.hyun.framework.web.exception.OptimisticLockException;
import cn.com.hyun.framework.web.exception.UnAuthorizedException;
import cn.com.hyun.framework.web.response.JsonResponse;
import cn.com.hyun.framework.web.util.HttpContextUtils;
import cn.com.hyun.framework.web.util.HttpCookieUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hyunwoo
 */
public abstract class BaseController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResponse throwableHandler(Exception exception) {
        HttpServletRequest request = HttpContextUtils.getRequest();
        log.error("运行时异常, URL:{}, Content-Type:{}, Params:{}, Method:{}, Protocol:{}, IP:{},  Cookie:{}, UserAgent:{}, exceptionStackTrace:{}",
                request.getRequestURL(), request.getContentType(), request.getQueryString(), request.getMethod(), request.getProtocol(),
                HttpContextUtils.getRequestIp(), HttpCookieUtils.getCookieValues(request), request.getHeader(HttpHeaders.USER_AGENT), ExceptionUtils.getStackTrace(exception));
        return new JsonResponse(false, "接口错误");
    }

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public JsonResponse unAuthorizedExceptionHandler(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, exception.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public JsonResponse forbiddenExceptionHandler(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, exception.getMessage());
    }

    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
    public JsonResponse optimisticLockExceptionHandler(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public JsonResponse illegalArgumentHandler(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, exception.getMessage());
    }

    @ExceptionHandler(MultipartUploadException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public String multipartUploadException(Exception exception) {
        log.warn(exception.getMessage());
        JsonResponse jsonResponse = new JsonResponse(false, exception.getMessage());
        return JsonUtils.toJson(jsonResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public JsonResponse missingServletRequestParameterException(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public JsonResponse methodArgumentTypeMismatchException(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, "参数类型错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResponse httpMessageNotReadableHandler(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, "请传入json格式body参数");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResponse httpMediaTypeNotSupportedExceptionHandler(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, "请设置Content-Type:application/json;charset=UTF-8");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public JsonResponse httpRequestMethodNotSupportedHandler(Exception exception) {
        log.warn(exception.getMessage());
        return new JsonResponse(false, "请求方式错误,请确认get/post/put/delete/option/head");
    }
    protected JsonResponse jsonData(Object data) {
        return JsonResponse.newInstance().setData(data);
    }
}
