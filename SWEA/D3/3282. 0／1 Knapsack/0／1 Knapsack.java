import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                // 같은 물건을 여러 번 쓰지 않도록 뒤에서부터 갱신
                for (int w = K; w >= v; w--) {
                    dp[w] = Math.max(dp[w], dp[w - v] + c);
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[K]).append("\n");
        }

        System.out.print(sb);
    }
}