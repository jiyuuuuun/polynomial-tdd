package com.ll;

import java.util.Arrays;

public class Calc {
    public static int run(String input) {
        // 공백 먼저 제거
        input = input.trim();

        input=removeUnnecessaryBrackets(input);
        
        input=transformMinusOuterBracket(input);

        // 빼기를 바꾸기
        input = input.replaceAll(" - ", " + -");

        if(input.contains("(")){
            String[] exprBits = splitTwoPartsBy(input, '+');

            if (exprBits != null) {
                return run(exprBits[0]) + run(exprBits[1]);
            }

            exprBits = splitTwoPartsBy(input, '*');

            if (exprBits != null) {
                return run(exprBits[0]) * run(exprBits[1]);

            }

            throw new IllegalArgumentException("Invalid expression: " + input);

        }

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

            return Arrays.stream(expr)
                    .map(String::trim)
                    .map(Integer::parseInt) // 문자열 → 정수 변환
                    .reduce((a,b)->a*b)// 누적 곱셈
                    .orElse(0); // 빈 배열이면 기본값 0
        }

        // + 기준으로 분리
        String[] exprBites=input.split("\\+"); // +는 정규표현식에서 특별한 문자이므로, 이스케이프 문자로 \\+ 써야 함.

        return Arrays.stream(exprBites)
                .map(String::trim)
                .map(Integer::parseInt) // 문자열 → 정수 변환
                .reduce((a,b)->a+b)// 누적 덧셈
                .orElse(0); // 빈 배열이면 기본값 0
    }

    private static String transformMinusOuterBracket(String input) {
        if(input.startsWith("-(") && input.endsWith(")")){
            return input.substring(1, input.length()) + "*-1";
        }
        return input;
    }



    private static String[] splitTwoPartsBy(String input, char splitChar) {
        int bracketDepth=0;

        for(int i=0;i<input.length();i++){
            char c=input.charAt(i);

            if(bracketDepth == 0 && c==splitChar){
                return new String[]{ input.substring(0,i),input.substring(i+1) };
            } else if(c == '(') {
                bracketDepth++;
            } else if(c == ')'){
                bracketDepth--;
            }
        }
        return null;
    }

    private static String removeUnnecessaryBrackets(String expr) {
        if(isOneBracketed(expr)){
            return removeUnnecessaryBrackets( expr.substring(1, expr.length()-1));
        }
        return expr;
    }

    private static boolean isOneBracketed(String expr) {
        if (!expr.startsWith("(") || !expr.endsWith(")")) return false; //문자열이 (로 시작하지 않거나 )로 끝나지 않으면 → 애초에 괄호로 감싸진 게 아니니까 false 반환.

        int bracketDepth = 0; // 현재 괄호가 몇 단계로 열려 있는지를 추적
        //괄호를 열면 +1, 닫으면 -1
        //괄호가 딱 닫혀서 bracketDepth == 0이 되는데, 그게 문자열의 끝이 아니면 → 중간에서 괄호가 닫혔다는 뜻이라서 false 반환

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == '(') {
                bracketDepth++;
            } else if (c == ')') {
                bracketDepth--;
            }

            if (bracketDepth == 0 && i != expr.length() - 1) return false;
        }

        return true; //위 조건을 모두 통과했다면 → 진짜로 전체가 한 쌍의 괄호로 감싸져 있으니까 true
    }
}
