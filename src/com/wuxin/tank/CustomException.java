package com.wuxin.tank;

/**
 * @Author: wuxin001
 * @Date: 2022/05/30/21:06
 * @Description: 自定义异常信息
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
