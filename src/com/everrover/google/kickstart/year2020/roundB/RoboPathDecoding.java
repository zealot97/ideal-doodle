package com.everrover.google.kickstart.year2020.roundB;

import java.util.Scanner;
import java.util.Stack;

public class RoboPathDecoding {
    static final int RANGE_L = 1, RANGE_R = 1000000000;

    private static int getEffectiveCood(int x){
        return x>=0? RANGE_L+x: RANGE_R+x;
    }

    private static void calcRC(char ch, int []cood){
        if(ch == 'N'){
            cood[0]--;
        }else if(ch == 'S'){
            cood[0]++;
        }else if(ch == 'E'){
            cood[1]++;
        }else if(ch == 'W'){
            cood[1]--;
        }
    }

    private static int[] solution(String str){
        int []ans = new int[2];
        Stack<Integer> st = new Stack<>();
        Stack<Integer> multipliers = new Stack<>();
        int i=0;
        while(i<str.length()){
            char ch = str.charAt(i);
            if(Character.isDigit(ch)){
                int r=i+1;
                ch = str.charAt(r);
                while(Character.isDigit(ch)){
                    ch = str.charAt(r);
                    r++;
                }
                multipliers.push(Integer.parseInt(str.substring(i, r)));
                i = r;
            }else if(ch == ')'){
                int []co = new int[2];
                while(str.charAt(st.peek()) != '('){
                    calcRC(str.charAt(st.pop()), co);
                }
                st.pop(); // take out '('
                int multiplier = multipliers.pop();
                ans[0] += co[0]*multiplier;
                ans[1] += co[1]*multiplier;
                i++;
            }else{// if(ch == '(' || ch is 'char'){
                st.push(i++);
            }
        }
        while(!st.isEmpty()){
            calcRC(str.charAt(st.pop()), ans);
        }
        return new int[]{getEffectiveCood(ans[0]), getEffectiveCood(ans[1])};
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            int []ans = solution(scanner.next());
            System.out.println("Case #"+(k-t)+": "+ans[1]+" "+ans[0]);
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
