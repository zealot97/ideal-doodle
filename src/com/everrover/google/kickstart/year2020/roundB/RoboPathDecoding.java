package com.everrover.google.kickstart.year2020.roundB;


import java.util.Scanner;
import java.util.Stack;

public class RoboPathDecoding {
    public static class Block{
        long cood[];
        char ch;

        public Block(char ch) {
            cood = new long[2];
            this.ch = ch;
        }

        public Block(long[] cood, char ch) {
            this.cood = cood;
            this.ch = ch;
        }
    }
    static final int RANGE_L = 1, RANGE_R = 1000000000;

    private static long getEffectiveCood(long x){
        x %= RANGE_R;
        return x>=0? x+RANGE_L: RANGE_R+x+1;
    }

    private static void calcRC(Block block, long []cood){
        if(block.ch == 'N'){
            cood[0]--;
        }else if(block.ch == 'S'){
            cood[0]++;
        }else if(block.ch == 'E'){
            cood[1]++;
        }else if(block.ch == 'W'){
            cood[1]--;
        }else if(block.ch == '*'){
            cood[0] = cood[0] + block.cood[0];
            cood[1] = cood[1] + block.cood[1];
        }
    }

    private static long[] solution(String str){
        long []ans = new long[2];
        Stack<Block> st = new Stack<>();
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
                long []co = new long[2];
                while(st.peek().ch != '('){
                    calcRC(st.pop(), co);
                }
                st.pop(); // take out '('
                int multiplier = multipliers.pop();
                co[0] = co[0]*multiplier;
                co[1] = co[1]*multiplier;
                st.push(new Block(co, '*'));
                i++;

            }else{// if(ch == '(' || ch is 'char'){
                st.push(new Block(str.charAt(i++)));
            }
        }
        while(!st.isEmpty()){
            Block block = st.pop();
            calcRC(block, ans);
        }
        ans[0] = getEffectiveCood(ans[0]);
        ans[1] = getEffectiveCood(ans[1]);
        return ans;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            long []ans = solution(scanner.next());
            System.out.println("Case #"+(k-t)+": "+ans[1]+" "+ans[0]);
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
