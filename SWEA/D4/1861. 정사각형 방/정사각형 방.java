

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
            String solution = solve();
            sb.append("#" + t + " " + solution + "\n");
        }

        System.out.println(sb.toString());
    }

    static int N;
    static int[][] map;
    static int maxRoom, maxNumber;

    static String solve() throws Exception {
        maxRoom = Integer.MIN_VALUE;
        maxNumber = Integer.MIN_VALUE;


        N = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j, 1);
            }
        }

        return maxNumber + " " + maxRoom;
    }

    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    static void dfs(int i, int j, int cnt) {
        if(!isAvail(i, j))
            return;

        if(maxRoom < cnt) {
            maxRoom = cnt;
            maxNumber = map[i][j];
        } else if(maxRoom == cnt) {
            maxNumber = Math.min(maxNumber, map[i][j]);
        }


        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            if(isAvail(ni, nj) && map[ni][nj] == map[i][j] - 1)
                dfs(ni, nj, cnt + 1);
        }

    }

    static boolean isAvail(int i, int j) {
        if(i < 0 || N <= i || j < 0 || N <= j)
            return false;

        return true;
    }


}
