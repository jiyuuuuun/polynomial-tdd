package com.ll;

public class Calc {
    public static int run(String input) {
        if(input.contains("+")) {
            // 문자열을 "+" 기준으로 나눔
            String[] parts = input.split("\\+");

            int sum = 0;
            for (String part : parts) {
                sum += Integer.parseInt(part.trim());
            }
            return sum;
        }else{
            // 문자열을 "-" 기준으로 나눠 배열 생성
            String[] parts = input.split("-");

            // 결과는 배열의 첫 번째 숫자부터 시작
            int result = Integer.parseInt(parts[0].trim());

            // 배열의 두 번째 요소부터 반복하며 뺄셈 수행
            for (int i = 1; i < parts.length; i++) {
                result -= Integer.parseInt(parts[i].trim());
            }
            return result;
        }
    }
}
