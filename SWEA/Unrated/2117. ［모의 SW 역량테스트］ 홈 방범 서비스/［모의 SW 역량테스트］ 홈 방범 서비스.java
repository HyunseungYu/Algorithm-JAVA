import java.util.Scanner;

public class Solution {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int T = sc.nextInt();

        for(int t = 1; t <= T; t++)
        {
            int solution = solve();
            sb.append("#" + t + " " + solution + "\n");
        }

        System.out.println(sb.toString());

    }

    static int N, M;
    static int[][] map;

    private static int solve() {
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int home = sc.nextInt();
                map[i][j] = home;
            }
        }

        int maxHomeCount = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int homeCount = getHomeCount(i, j);

                maxHomeCount = Math.max(maxHomeCount, homeCount);
            }
        }

        return maxHomeCount;
    }

    private static int getHomeCount(int i, int j) {
        int maxHomeCount = Integer.MIN_VALUE;

        for (int r = 1; r <= 2 * N - 1; r++) {
            int cost = r * r + (r-1) * (r-1);
            int homeCount = getHome(i, j, r);
            int benefit = homeCount * M;

            if(cost <= benefit)
                maxHomeCount = Math.max(maxHomeCount, homeCount);
        }

        return maxHomeCount;
    }

    private static int getHome(int i, int j, int r) {
        int home = 0;

        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                if(map[k][l] == 0)
                    continue;

                int distance = Math.abs(k - i) + Math.abs(l - j);

                if(distance <= r-1)
                    home++;
            }
        }

        return home;
    }
}
