import java.io.BufferedReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc;

    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
//        br = new BufferedReader(new InputStreamReader(System.in));
        sc = new Scanner(System.in);

//        int T = Integer.parseInt(br.readLine());
        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            int solution = solution();
            sb.append("#" + t + " " + solution + "\n");
        }

        System.out.println(sb.toString());
//        br.close();
        sc.close();
    }

    // 0: 상
    // 1: 하
    // 2: 좌
    // 3: 우
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int top;
    static int max;


    static int solution() throws Exception {
        N = sc.nextInt();
        K = sc.nextInt();

        map = new int[N][N];
        top = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                top = Math.max(top, map[i][j]);
            }
        }

        max = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == top) {
                    visited[i][j] = true;
                    goDown(1, i, j, map[i][j], false);
                    visited[i][j] = false;
                }
            }
        }

        return max;
    }

    static void goDown(int depth, int i, int j, int current, boolean cutUsed) {
        max = Math.max(max, depth);

        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            // 다음 칸이 맵을 벗어난다면 pass~
            if(!isOk(ni, nj))
                continue;

            if(visited[ni][nj])
                continue;

            int next = map[ni][nj];

            // 다음 칸이 나보다 작으면 go
            if(next < current) {
                visited[i][j] = true;
                goDown(depth + 1, ni, nj, next, cutUsed);
                visited[i][j] = false;
            }
            // 다음 칸이 나보다 작지 않은데 깎아낼 수 있다면(cutUsed = false) 깎고 다시 go!
            else if(!cutUsed) {
                int needToCut = next - (current - 1); // current보다 1 작게 만드는 수

                if(1 <= needToCut && needToCut <= K) {
                    visited[i][j] = true;
                    goDown(depth + 1, ni, nj, current - 1, true);
                    visited[i][j] = false;
                }
            }
        }
    }

    private static boolean isOk(int i, int j) {
        if (i < 0 || N <= i || j < 0 || N <= j)
            return false;

        return true;
    }

}
