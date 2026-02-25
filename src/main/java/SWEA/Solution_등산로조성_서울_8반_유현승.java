package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution_등산로조성_서울_8반_유현승 {
    static int arr[][];
    static int n, k;
    static ArrayList<int[]> al = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            al.clear();

            int max = 0;
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < n; l++) {
                    arr[j][l] = Integer.parseInt(st.nextToken());
                    if(max < arr[j][l]) max = arr[j][l];
                }
            }

            // 가장 높은 봉우리 저장
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if(max == arr[j][l]) al.add(new int[]{j, l});
                }
            }

            result = 0;
            for(int[] xy : al) {
                visited = new boolean[n][n];
                visited[xy[0]][xy[1]] = true;
                dfs(xy[0], xy[1], false, 1);
            }

            sb.append("#" + (i + 1) + " " + result + "\n");
        }
        System.out.println(sb);
    }

    static int rx[] = {0, 0, -1, 1};
    static int ry[] = {-1, 1, 0, 0};
    static boolean visited[][];
    static int result = 0;
    static void dfs(int x, int y, boolean flag, int cnt) {
        if(result < cnt) result = cnt;
        for (int i = 0; i < 4; i++) {
            int nx = x + rx[i];
            int ny = y + ry[i];
            // 범위 초과
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            // 이미 방문한 봉우리
            if(visited[nx][ny]) continue;
            // 이미 깎은 경우
            if(arr[x][y] <= arr[nx][ny] && flag) continue;

            if(arr[x][y] <= arr[nx][ny]) {
                // 깎아도 못 가는 경우
                if(arr[x][y] <= arr[nx][ny] - k) continue;

                int cur = arr[nx][ny];
                arr[nx][ny] = arr[x][y] - 1; // 깎기

                visited[nx][ny] = true;
                dfs(nx, ny, !flag, cnt + 1);
                visited[nx][ny] = false;

                arr[nx][ny] = cur; // 원복
            }
            else {
                visited[nx][ny] = true;
                dfs(nx, ny, flag, cnt + 1);
                visited[nx][ny] = false;
            }
        }
    }
}