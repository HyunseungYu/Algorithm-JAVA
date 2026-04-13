import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    static Scanner sc;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
//        br = new BufferedReader(new InputStreamReader(System.in));
        sc = new Scanner(System.in);

//        int T = Integer.parseInt(br.readLine());
        int T = sc.nextInt();

        for(int t = 1; t <= T; t++)
        {
            int solution = solve();
            sb.append("#" + t + " " + solution + "\n");
        }

        System.out.println(sb.toString());
    }

    static int N, K;
    static int[] weight, value;

    static int solve() throws Exception {
        N = sc.nextInt();
        K = sc.nextInt();

        weight = new int[N+1];
        value = new int[N+1];

        for (int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

//        Arrays.sort(bags, (b1, b2) -> b1.v - b2.v);

        int[][] dp = new int[N+1][K+1]; // dp[i][w]는 i번째 물건을 넣을 때 최대 무게 w
//        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int w = 0; w <= K; w++) {
                // i번째 배낭만 검색
                // 1. 현재 물건을 안 넣는 경우
                dp[i][w] = dp[i-1][w];

                // 2. 현재 물건을 넣는 경우
                if(weight[i] <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weight[i]] + value[i]);
                }
            }
        }


        return dp[N][K];
    }
}