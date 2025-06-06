package com.ll;

public class Calc {
    public static int run(String input) {
        // 공백 먼저 제거 (또는 trim 처리로도 가능)
        input = input.replace(" ", "");

        // -를 부호로 바꾸기
        input=input.replaceAll("-","+-");

        // + 기준으로 분리
        String[] exprBites=input.split("\\+"); // +는 정규표현식에서 특별한 문자이므로, 이스케이프 문자로 \\+ 써야 함.

        int num1=Integer.parseInt(exprBites[0].trim());
        int num2=Integer.parseInt(exprBites[1].trim());

        return num1+num2;
    }
}
