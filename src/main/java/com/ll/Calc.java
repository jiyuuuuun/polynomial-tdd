package com.ll;

public class Calc {
    public static int run(String input) {
        // 문자열을 "+" 기준으로 나눔
        String[] parts = input.split("\\+");

        int sum = 0;
        for (String part : parts) {
            sum += Integer.parseInt(part.trim());
        }

        System.out.println("총합: " + sum);
        return sum;
    }
}
