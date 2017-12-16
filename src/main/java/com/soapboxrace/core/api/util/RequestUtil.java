package com.soapboxrace.core.api.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil
{
    public static String getIp(HttpServletRequest request)
    {
        return request.getRemoteAddr();
    }
}
