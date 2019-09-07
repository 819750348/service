package com.snake.web.boot;

import com.snake.web.boot.module.system.model.Role;

/**
 * Created by HP on 2018/7/3.
 */
public class Constants {

    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_ERROR = "error";
    private static final String RESULT_FAILURE = "failure";

    public static final Object success() {
        return RESULT_SUCCESS;
    }

    public static final Object error() {
        return RESULT_ERROR;
    }

    public static final Object failure() {
        return RESULT_FAILURE;
    }

    public static final String REQUEST_METHOD_ALL = "ALL";

    private static final String ROLE_SUPER_ADMIN_CODE = "super_root_admin";
    public static final Role ROLE_SUPER_ADMIN = new Role(0l,ROLE_SUPER_ADMIN_CODE);

    public static boolean isSuperAdmin(String code) {
        return ROLE_SUPER_ADMIN.getCode().equals(code);
    }

}
