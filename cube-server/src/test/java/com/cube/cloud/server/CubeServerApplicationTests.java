package com.cube.cloud.server;

import com.cube.cloud.server.permission.entity.Permission;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;


@SpringBootTest
class CubeServerApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(CubeServerApplicationTests.class);

    @Test
    public void contextLoads() {
        // 正序排序
        //childList.stream().sorted(Comparator.comparing(Permission::getLevel)).collect(Collectors.toList())
        // 倒序排序
        // childList.stream().sorted(Comparator.comparing(Permission::getLevel).reversed()).collect(Collectors.toList());
        System.out.println("==================================测试开始==================================");
        logger.info("这是一个测试");
        System.out.println("==================================测试开始==================================");
    }

    /**
     * 判断101-200之间有多少个素数
     * 思路：用一个数分别去除以2到这个数，如果能被整除，则表明此数不是素数，反之则为素数
     */
    @Test
    public void getPrimeNumber() {
        System.out.println("==================================测试开始==================================");
        System.out.println("101-200的素数为：");
        int i, j;
        // 素数总数
        int count = 0;
        for (i = 101; i <= 200; i++) {
            for (j = 2; j < i; j++) {
                // 如果除的尽则不是素数，跳出循环
                if (i % j == 0) break;
            }
            // 比较如果相对即数的本身，则为素数，否则不为素数
            if (i == j) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println("101-200之间一共有：" + count + "个素数");
        System.out.println("==================================测试开始==================================");
    }

    /**
     * 输出100-999所有的水仙花数。水仙花数是指一个 3 位数，它的每个位上的数字的3次幂之和等于它本身，例如：1^3 + 5^3+ 3^3 = 153
     * 思路：把从100到999的每一数字的个十百位提取出来，然后加以比较，看看是否符合条件，如果符合条件则打印，否则则继续循环，直到将100到999的每个数判断完为止
     */
    @Test
    public void getDaffodilsNumber() {
        System.out.println("==================================测试开始==================================");
        System.out.println("100-999的水仙花数为：");
        int count = 0;
        for (int i = 1000; i <= 9999; i++) {
            // 个位
            int a = i % 10;
            // 十位
            int b = i / 10 % 10;
            // 百位
            int c = i / 100;
            if (i == Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3)) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println("100-999之间一共有：" + count + "个水仙花数");
        System.out.println("==================================测试开始==================================");
    }

    /**
     * 将字符串"a-b-c-d-f"按照"-"切割，并找到"c"字符将其替换为大写，然后倒序输出"f-d-C-b-a"
     */
    @Test
    public void getString() {
        System.out.println("==================================测试开始==================================");
        String s = "a-b-c-d-f";
        // 按照"-"切割，将字符串转换为数组
        String[] str = s.split("-");
        for (int i = 0; i < str.length; i++) {
            // 找到"c"字符将其替换为大写
            if (str[i].equals("c")) {
                str[i] = str[i].toUpperCase();
            }
        }

        StringBuilder temp = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--) {
            temp.append(str[i]);
            if (i != 0) {
                temp.append("-");
            }
        }
        System.out.println(temp);
        System.out.println("==================================测试结束==================================");
    }

    /**
     * 将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5
     */
    @Test
    public void getPrimeFactor() {
        System.out.println("==================================测试开始==================================");
        int number = 90;
        System.out.print(number +"的正解质因数为：");
        for (int i = 2; i <= number; i++) {
            while (number % i == 0 && i != number) {
                number = number/i;
                System.out.print(i + "*");
            }
            if (i == number) {
                System.out.println(i);
            }
        }
        System.out.println("==================================测试结束==================================");
    }

    @Test
    public void getRandomList() {
        System.out.println("==================================测试开始==================================");
        int[] arr = new int[100];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 + random.nextInt(99);
        }
        System.out.println(Arrays.asList(arr));
        System.out.println("==================================测试结束==================================");
    }

}
