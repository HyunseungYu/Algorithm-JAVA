
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


    static int solve() throws Exception {
        int N = sc.nextInt();
        int L = sc.nextInt();

        int[] scores = new int[N+1];
        int[] calories = new int[N+1];

        for (int i = 1; i <= N; i++) {
            scores[i] = sc.nextInt();
            calories[i] = sc.nextInt();
        }

        int[][] dp = new int[N+1][L+1];
        for (int i = 1; i <= N; i++) {
            for (int c = 0; c <= L; c++) {
                dp[i][c] = dp[i-1][c];
                if(c >= calories[i]) {
                    dp[i][c] = Math.max(dp[i][c], dp[i-1][c-calories[i]] + scores[i]);
                }
                
            }

        }


        return dp[N][L];


    }

}
