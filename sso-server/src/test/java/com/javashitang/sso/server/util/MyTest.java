package com.javashitang.sso.server.util;

import com.javashitang.tool.MD5Util;
import org.junit.Test;

/**
 * @author lilimin
 * @since 2020-06-01
 */
public class MyTest {


    @Test
    public void test() {
        String str = MD5Util.getMd5Str("root");
        System.out.println(str);
    }
}
