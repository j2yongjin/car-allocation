package com.allocation.support.util;

import java.util.Random;

public class RandomUtil {

    public static String getRandomString(int size){
        int start = 65;
        int end = 90;
        Random random = new Random();
        return random.ints(start,end)
                .limit(size)
                .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }
}
