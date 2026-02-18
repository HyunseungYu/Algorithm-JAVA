

import java.io.*;
import java.util.*;

public class Solution {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc;
    static BufferedReader br;

    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
        sc = new Scanner(System.in);
//        br = new BufferedReader(new InputStreamReader(System.in));

//        int T = Integer.parseInt(br.readLine());
        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            int solution = solve();
            sb.append("#" + t + " " + solution + "\n");
        }

        System.out.println(sb.toString());
    }

    static int N, B;
    static int[] heights;
    static int min;


    static int solve() throws Exception {
        N = sc.nextInt();
        B = sc.nextInt();

        heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = sc.nextInt();
        }

        Arrays.sort(heights);

        min = Integer.MAX_VALUE;
        choose(0, 0);

        return min;
    }

    static void choose(int depth, int sum) {
        if(depth == N) {
            if(B<=sum){

                int diff = Math.abs(sum - B);
                min = Math.min(min, diff);

            }
            return;
        }

        if(B < sum) {

            int diff = Math.abs(sum - B);
            min = Math.min(min, diff);
            return;
        }

        choose(depth + 1, sum);
        choose(depth + 1, sum + heights[depth]);
    }


}
