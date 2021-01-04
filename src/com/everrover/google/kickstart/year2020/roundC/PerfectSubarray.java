package com.everrover.google.kickstart.year2020.roundC;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PerfectSubarray {
    private static int prefix[], perfectSq[], offsets[];
    private static int solution(int []arr){
        Map<Integer, Integer> map = new HashMap<>();
        int res[] = new int[arr.length];

        for(int j=0; j<prefix.length; j++){
            int index = prefix[j]-offsets[j];
            for(int i=0; i<perfectSq.length; i++){
                int sqIndex = index-perfectSq[i];
                if( sqIndex < 0 ) break;
                if(map.containsKey(sqIndex)){
                    res[j] += map.get(sqIndex);
                }
            }
            if(!map.containsKey(index)){
                map.put(index, 1);
            } else {
                map.put(index, map.get(index) + 1);
            }
        }

        int ans = 0;
        for(int r: res){
            ans+=r;
        }
        return ans;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            final int n = scanner.nextInt();
            final int arr[] = new int[n+1];
            offsets = new int[n+1];
            prefix = new int[n+1];

            int max_A = Integer.MIN_VALUE;
            for(int i=1; i<=n; i++){
                arr[i] = scanner.nextInt();
                prefix[i] = prefix[i-1]+arr[i];
                max_A = Integer.max(max_A, prefix[i]);
                offsets[i] = Integer.min(offsets[i-1], prefix[i]);
            }
            perfectSq = new int[(int)Math.ceil(Math.sqrt(prefix[prefix.length-1]))+1];
            for(int i=0; i<perfectSq.length; i++){
                perfectSq[i] = i*i;
            }
//            solution(matrix, num, 0, 0);
            System.out.println("Case #"+(k-t)+": "+solution(arr));
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
