import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>(); // 2차원 ArrayList 로 graph 를 구현(2차원 배열처럼)
    public static boolean[] visited;
    public static int[] d = new int[50001];
    public static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];

        //ArrayList 초기화
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 노드
            int b = Integer.parseInt(st.nextToken()); // 끝 노드
            int dist = Integer.parseInt(st.nextToken()); // 비용

            // 양방향 간선
            graph.get(a).add(new Node(b, dist)); // a 에서 b 까지의 가중치 dist
            graph.get(b).add(new Node(a, dist)); // b 에서 a 까지의 가중치 dist
        }

        //1부터 N 까지의 최소거리 구하기
        dijkstra(1);

        System.out.println(d[N]);
    }

    public static void dijkstra(int start) {
        Arrays.fill(d, Integer.MAX_VALUE); // d 배열의 모든 요소를 Integer.MAX_VALUE 로 채워줌

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0)); // 시작노드에서 시작노드로 가기위한 최단 경로는 0
        d[start] = 0; // 시작노드에서 시작노드까지의 가중치는 제자리 이므로 0

        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            int nodeB = temp.nodeB; // 현재 노드번호
            int distance = temp.distance; // 현재 노드의 가중치

            if(d[nodeB] < distance) continue; // //현재 노드가 이미 처리된적이 있는 노드라면 무시 ( 거리가 더 작다는것은 이미 더 효율적인 방안으로 처리된 것 )
            for(int i=0;i<graph.get(nodeB).size();i++) { //현재 노드와 연결된 다른 인접한 노드들을 확인
                int cost = d[nodeB] + graph.get(nodeB).get(i).distance; // cost = 현재노드의 가중치 + 현재노드와 다음 노드까지의 간선의 가중치
                if( cost < d[graph.get(nodeB).get(i).nodeB]) { // 새롭게 측정한 비용이 다음 노드가 원래 가지고 있던 가중치보다 적을 경우
                    d[graph.get(nodeB).get(i).nodeB] = cost; // 더 적은 값으로 갱신해준다
                    pq.offer(new Node( graph.get(nodeB).get(i).nodeB, cost)); // 계속 탐색을 해봐야 모든 노드들의 최솟값을 알 수 있기 때문에 현재의 가중치를 가지고 계속 탐색
                }
            }
        }
    }

}


// 그래프의 간선을 표현하기 위한 클래스
class Node implements Comparable<Node>{
    int nodeB; // 이 간선이 연결하는 목적지 노드의 번호
    int distance; // 시작노드에서 nodeB 까지의 가중치
    public Node(int nodeB, int distance) {
        this.nodeB = nodeB;
        this.distance = distance;
    }

    // Node 인스턴스 끼리 비교할 때 사용
    // 우선순위 큐에 삽입되었을 때 distance 값이 작은 노드(가장 가까운 노드) 부터 우선순위가 높게 설정되어 먼저 처리된다
    @Override
    public int compareTo(Node other) {
        if(this.distance > other.distance) {
            return 1;
        }else if(this.distance == other.distance) {
            return 0;
        }else {
            return -1;
        }
    }
}
