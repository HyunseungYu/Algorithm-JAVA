import java.io.BufferedReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc;

    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
//        br = new BufferedReader(new InputStreamReader(System.in));
        sc = new Scanner(System.in);

//        int T = Integer.parseInt(br.readLine());
//        int T = sc.nextInt();

//        for(int t = 1; t <= T; t++) {
        while (true) {
            W = sc.nextInt();
            H = sc.nextInt();
            if(W == 0 && H == 0)
                break;
            int solution = solution();
//            sb.append("#" + t + " " + solution + "\n");
            sb.append(solution + "\n");
//        }
        }

        System.out.println(sb.toString());
//        br.close();
        sc.close();
    }

    static int W, H;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, -1, -1, 0, 0, 1, 1, 1,};
    static int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int solution() throws Exception {

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int cnt = 0;
        visited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void dfs(int i, int j) {
        if(!isOk(i, j))
            return;

        if(map[i][j] != 1)
            return;

        if(visited[i][j])
            return;

        visited[i][j] = true;

        for (int k = 0; k < 8; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            dfs(ni, nj);
        }

    }

    static boolean isOk(int i, int j) {
        if (i < 0|| H <= i || j < 0 || W <= j)
            return false;

        return true;
    }

}
