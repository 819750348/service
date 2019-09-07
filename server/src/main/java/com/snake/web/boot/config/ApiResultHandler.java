package com.snake.web.boot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@ControllerAdvice(annotations = RestController.class)
public class ApiResultHandler implements ResponseBodyAdvice {

    private static ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };

    public static Object encode(@Nullable Object body){
        Object out;
        ObjectMapper mapper = mapperThreadLocal.get();
        if (null == body) {
            out = ApiResult.SUCCESSFUL;
        } else if (body instanceof ApiResult) {
            out = body;
        } else if (body instanceof ApiResultType) {
            ApiResultType apiResultType = (ApiResultType) body;
            out = new ApiResult(apiResultType.getCode(), apiResultType.getMsg());
        } else if (body instanceof String) {
            ApiResult result = ApiResult.success((String) body);
            try {
                //因为是String类型，我们要返回Json字符串，否则SpringBoot框架会转换出错
                out = mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                out = ApiResult.error(ApiResultType.JSON_PARSE_ERROR, e.getMessage());
            }
//            out = result;
        } else {
            out = ApiResult.success(body);
        }
        return out;
    }

    /**
     * 对所有RestController的接口方法进行拦截
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResponseEntity){
            return body;
        }else{
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return encode(body);
        }
    }

}
