package com.ll;

import java.util.Arrays;

public class Calc {
    public static int run(String input) {
        // 공백 먼저 제거 (또는 trim 처리로도 가능)
        input = input.replace(" ", "");

        input=removeUnnecessaryBrackets(input);

        if(input.contains("*") && input.contains("+")){
            String[] exprBites=input.split("\\+");

            int sum=Arrays.stream(exprBites)
                    .map(Calc::run)
                    .reduce((a,b)->a+b)// 누적 덧셈
                    .orElse(0); // 빈 배열이면 기본값 0
            return sum;

        }
        if(input.contains("*")){

            String[] expr = input.split("\\*");

            int sum=Arrays.stream(expr)
                    .map(Integer::parseInt) // 문자열 → 정수 변환
                    .reduce((a,b)->a*b)// 누적 곱셈
                    .orElse(0); // 빈 배열이면 기본값 0
            return sum;

        }
        // -를 부호로 바꾸기
        input=input.replaceAll("-","+-");

        // + 기준으로 분리
        String[] exprBites=input.split("\\+"); // +는 정규표현식에서 특별한 문자이므로, 이스케이프 문자로 \\+ 써야 함.

        // 배열 스트림으로 변환 → 각 문자열을 정수로 파싱 → 모두 더하기
        int sum=Arrays.stream(exprBites)
                .map(Integer::parseInt) // 문자열 → 정수 변환
                .reduce((a,b)->a+b)// 누적 덧셈
                .orElse(0); // 빈 배열이면 기본값 0
        return sum;
    }

    private static String removeUnnecessaryBrackets(String expr) {
        if(expr.startsWith("(") && expr.endsWith(")")){
            return expr.substring(1, expr.length()-1);
        }
        return expr;
    }
}
