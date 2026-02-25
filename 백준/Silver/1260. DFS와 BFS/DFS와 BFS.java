import java.io.BufferedReader;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);

//        int T = sc.nextInt();

//        for(int t = 1; t <= T; t++) {
            String solution = solution();
//            sb.append("#" + t + " " + solution + "\n");
//        }

//        System.out.println(sb.toString());
        System.out.println(solution);
        sc.close();
    }

    static int V, E, root;
    static Node[] nodes;

    static String solution() throws Exception {
        V = sc.nextInt();
        E = sc.nextInt();
        root = sc.nextInt();

        nodes = new Node[V+1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < E; i++) {
            int p = sc.nextInt();
            int c = sc.nextInt();
            nodes[p].adjForDFS.add(c);
            nodes[c].adjForDFS.add(p);

            nodes[p].adjForBFS.add(c);
            nodes[c].adjForBFS.add(p);
        }


        // DFS
        StringBuilder sbForDFS = new StringBuilder();
        dfs(root, new boolean[V+1], sbForDFS);

        // BFS
        StringBuilder sbForBFS = new StringBuilder();
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(root);
        boolean[] visited = new boolean[V+1];
        while (!q.isEmpty()) {
            int n = q.poll();
            if(visited[n])
                continue;

            visited[n] = true;
            sbForBFS.append(n + " ");


            Node node = nodes[n];

            while (!node.adjForBFS.isEmpty()) {
                q.offer(node.adjForBFS.poll());
            }
        }

        return sbForDFS.toString().trim() + "\n" + sbForBFS.toString().trim();
    }

    static void dfs(int n, boolean[] visited, StringBuilder sbForDFS) {
        Node node = nodes[n];
        if(visited[n])
            return;

        visited[n] = true;
        sbForDFS.append(n + " ");

        while (!node.adjForDFS.isEmpty()) {
            int child = node.adjForDFS.poll();
            dfs(child, visited, sbForDFS);
        }
    }


}

class Node {
    int no;
    PriorityQueue<Integer> adjForDFS;
    PriorityQueue<Integer> adjForBFS;

    public Node(int no) {
        this.no = no;
        adjForDFS = new PriorityQueue<>();
        adjForBFS = new PriorityQueue<>();
    }
}