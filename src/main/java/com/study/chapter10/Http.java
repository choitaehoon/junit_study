package com.study.chapter10;

import java.io.*;

public interface Http {
    String get(String url) throws IOException;
}
