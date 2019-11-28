package com.jmy.test;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test01 {

    @Test
    public void test01(){
        System.out.println(new Date());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(new Date()));
    }
}
