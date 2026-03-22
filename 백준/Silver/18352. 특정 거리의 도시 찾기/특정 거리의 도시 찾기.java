import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v); // 단방향 간선
        }

        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);
        distance[X] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (distance[next] == -1) { // 방문한 노드와 방문하지 않는 노드를 체크하는 용도로 사용
                    distance[next] = distance[current] + 1; // 전 노드까지의 거리 + 1
                    queue.add(next);
                }
            }
        }

        boolean found = false;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) { // 거리가 K인것만 출력
                System.out.println(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println(-1);
        }
    }
}