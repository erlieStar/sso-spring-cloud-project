package com.javashitang.autoconfigure.sso;

import com.javashitang.tool.JsonConvert;
import com.javashitang.tool.OperStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lilimin
 * @since 2020-05-30
 */
public class ResponseWrite {

    private static final Logger log = LoggerFactory.getLogger(ResponseWrite.class);

    public static void writeResult(HttpServletResponse response, OperStatus operStatus) {
        try {
            response.reset();
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JsonConvert.obj2Str(operStatus));
        } catch (Exception e) {
            log.error("writeResult error", e);
        }
    }
}
