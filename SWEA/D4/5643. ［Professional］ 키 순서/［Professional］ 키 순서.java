

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

    static int N, M, cnt;
    static boolean[][] adj;

    static int solve() throws Exception {
        cnt = 0;
        N = sc.nextInt();
        M = sc.nextInt();

        adj = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            int smaller = sc.nextInt();
            int bigger = sc.nextInt();

            adj[smaller][bigger] = true;
        }

        for (int i = 1; i <= N; i++) {
            List<Integer> up = makeUp(i);
            List<Integer> down = makeDown(i);

            boolean[] visited = new boolean[N+1];
            for (int j = 0; j < up.size(); j++) {
                visited[up.get(j)] = true;
            }
            for (int j = 0; j < down.size(); j++) {
                visited[down.get(j)] = true;
            }
            int visitedCnt = 0;
            for (int j = 1; j <= N; j++) {
                if(visited[j])
                    visitedCnt++;
            }
            if(visitedCnt == N-1)
                cnt++;
        }

        return cnt;


    }

    static List<Integer> makeUp(int n) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(n);

        boolean[] visited = new boolean[N+1];
        List<Integer> ret = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();

            if(visited[node])
                continue;
            visited[node] = true;

            for (int i = 1; i <= N; i++) {
                if(adj[i][node]) {
                    q.offer(i);
                    ret.add(i);
                }
            }
        }

        return ret;
    }



    static List<Integer> makeDown(int n) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(n);

        List<Integer> ret = new ArrayList<>();

        boolean[] visited = new boolean[N+1];
        while (!q.isEmpty()) {
            int node = q.poll();

            if(visited[node])
                continue;

            visited[node] = true;

            for (int i = 1; i <= N; i++) {
                if(adj[node][i]) {
                    q.offer(i);
                    ret.add(i);
                }
            }
        }

        return ret;
    }


}
