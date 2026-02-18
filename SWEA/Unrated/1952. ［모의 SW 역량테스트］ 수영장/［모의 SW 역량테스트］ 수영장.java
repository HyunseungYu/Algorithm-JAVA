

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

    static int[] fee;
    static int[] month;
    static int[] comb;
    static int min;

    static int solve() throws Exception {
        fee = new int[4];
        for (int i = 0; i < 4; i++) {
            fee[i] = sc.nextInt();
        }

        month = new int[12];
        for (int i = 0; i < 12; i++) {
            month[i] = sc.nextInt();
        }

        comb = new int[12];
        min = fee[3];
        dfs(0, 0);

        return min;
    }

    static void dfs(int depth, int sum) {
        if(12 <= depth) {
            min = Math.min(min, sum);

            return;
        }

        int dailyFee = month[depth] * fee[0];
        int monthFee = fee[1];
        int threeMonthFee = fee[2];

        if(dailyFee < monthFee)
            dfs(depth + 1, sum + dailyFee);
        else
            dfs(depth + 1, sum + monthFee);
        dfs(depth + 3, sum + threeMonthFee);
    }


}
