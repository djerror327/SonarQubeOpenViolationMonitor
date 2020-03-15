package com.dinusha.soft.utill;

import org.springframework.stereotype.Component;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

@Component
public class MethodType {
    private MethodType() {
    }

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";
    public static final String PUT = "PUT";
}
