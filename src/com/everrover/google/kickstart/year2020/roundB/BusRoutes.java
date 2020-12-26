package com.everrover.google.kickstart.year2020.roundB;

import java.util.Scanner;

public class BusRoutes {
    /***
     * Google notes on solving the problem.
     *
     * Analysis
     * We need to take the buses in order from 1 to N. Let the buses be B1, B2, ...., BN. Also, let us define a good starting day to be any day Y in the range [1..D] such that it is possible to start the journey on day Y and take all buses in the order from 1 to N before the end of day D. Note that we do not require Y to be a multiple of X1, so there may be some waiting time involved in the beginning of the journey.
     *
     * For a fixed day Y, let us see if it is a good starting day or not. The best strategy would be to take bus B1 as early as possible on or after day Y. This is because it would give us more days to take subsequent buses. Let us say we took bus B1 on day D1. Now the best strategy would be to take bus B2 as early as possible on or after day D1. Thus, if we took bus Bi on day Di, it would be optimal to take bus Bi+1 as early as possible on or after day Di.
     *
     * Now we need to find out what is the earliest possible day for Bucket to take bus Bi on or after a particular day K. Since bus Bi only runs on days that are multiples of Xi, we need to find the smallest multiple of Xi greater than or equal to K. This can be calculated using the formula ⌈ K / Xi ⌉ * Xi. Thus if bus Bi is taken on day Di, then it would be optimal to take bus Bi+1 on day Di+1 = ⌈ Di / Xi+1 ⌉ * Xi+1. Thus, day Y is a good starting day if DN ≤ D, and this question can be answered in O(N) time.
     *
     * Test set 1
     * D can be at most 100, so we can find the latest good starting day by using the above approach for each day Y in the range [1..D]. The time complexity of this naive algorithm is O(DN).
     *
     * Test set 2
     * Now D can be at most 1012, so the naive algorithm would time out. Consider the largest good starting day P. Obviously, any day before P would be good as well because we can take the buses on the same days as if we started the journey on day P. Because of this observation, we can binary search on the range from 1 to D to find the largest good starting day P. The time complexity of the solution is O(N log D). Note that we can reduce the time complexity to O(N log(D/X1)) by restricting the search to multiples of X1 only.
     *
     * Alternate solution
     * It is possible to solve the problem in linear time by working out the solution backwards. If we want to start our journey as late as possible, we should try to take the last bus BN as late a possible, namely, on day DN, which is the largest multiple of XN, less than or equal to day D. Similarly, in order to be on time for the last bus on day DN, we have to take bus BN-1 no later than on day DN-1, which is the largest multiple of XN-1, less than or equal to DN. In general, bus Bi should be taken no later than on day Di, which is the largest multiple of Xi, less than or equal to Di+1. The last calculated value D1 is the answer to the problem.
     *
     * Note that the largest multiple of Xi that occurs before a day L can be calculated in constant time as L - L mod Xi. Therefore, the overall time complexity of this solution is O(N).
     */
    private static long solution(long arr[], long d){
        long multiple;
        for(int i=arr.length-1; i>=0; i--){
            if(d%arr[i] != 0){
                multiple = d/arr[i];
                d = arr[i]*multiple;
            }
        }
        return d;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            final int n = scanner.nextInt();
            final long d = scanner.nextLong();
            long []arr = new long[n];
            for(int i=0; i<arr.length; i++){
                arr[i] = scanner.nextLong();
            }
//            solution(matrix, num, 0, 0);
            System.out.println("Case #"+(k-t)+": "+solution(arr, d));
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
