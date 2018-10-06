package com.howie.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 1:28
 */
public interface Resource {
    InputStream getInputStream() throws IOException;

    String getDescription();
}
