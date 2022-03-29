package com.test;

import com.zc.gui.LoginView;
import org.junit.jupiter.api.Test;

public class DemoTest {
    LoginView v;
    int i;

    @Test
    public void test() {
        char n = '\127';
        int w = 0;
        System.out.println(i);
        System.out.println(w);
        System.out.println(v);
    }

    @Test
    public void test1() {
        int n = 9;
        int i = n >> 1;
        System.out.println(i);
    }

    @Test
    public void test2() {
        byte b = 2;
        System.out.println(Integer.toBinaryString(-127));
    }

    @Test
    public void test3() {
        //String str = "";        //我们想赋值这样一个字符，假设我输入法打不出来

        //但我知道它的Unicode是0x1D11E
        //String str = "\u1D11E";  //这样写不会识别

        //于是通过计算得到其UTF-16编码 D834 DD1E
//        String str = "\uD834\uDD1E"; //然后这么写
        String str = "\uD834"; //然后这么写
        String str1 = "\uDD1E"; //然后这么写
        String str2 = "\uD834\uD834\uDD1E\uDD1E你好"; //然后这么写


        System.out.println(str);     //成功输出了""
        System.out.println(str1);     //成功输出了""
        System.out.println(str2);     //成功输出了""
    }

    @Test
    public void test4() {
//        String str1 = "hi𝕆";
        String str1 = "\ud83d\ude02";
        System.out.println(str1);

        String s = String.valueOf(Character.toChars(0x1F602));
        char[] chars = s.toCharArray();
        for (char c : chars) {
            System.out.format("%x", (short) c);
        }


    }


}
