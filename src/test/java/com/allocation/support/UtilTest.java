package com.allocation.support;

import org.junit.Test;

import java.util.Random;

public class UtilTest {

    @Test
    public void whenRandomStringValue(){

        int start = 65;
        int end = 90;
        int randomValueSize = 10;
        Random random = new Random();
        String randomValue = random.ints(start,end)
                .limit(randomValueSize)
                .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();

        System.out.println("randomValue : " + randomValue);

    }

}
