import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			String solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

    static int V, E;
    static int n1, n2;
    static Node[] nodes;
    static int subTreeCnt;

	static String solution() throws Exception {

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());

        nodes = new Node[V+1];
        for (int i = 0; i < V+1; i++) {
            nodes[i] = new Node(i);
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < E; i++) {
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            if(nodes[parent].left == null) {
                nodes[parent].left = nodes[child];
                nodes[child].parent = nodes[parent];
            } else {
                nodes[parent].right = nodes[child];
                nodes[child].parent = nodes[parent];
            }
        }

        Node find1 = nodes[n1];
        Node find2 = nodes[n2];

        Node cur = find1;
        List<Integer> find1Parents = new ArrayList<>();
        while (cur != null) {
            Node parent = cur.parent;
            if(parent != null)
                find1Parents.add(parent.number);

            cur = cur.parent;
        }


        cur = find2;
        List<Integer> find2Parents = new ArrayList<>();
        while (cur != null) {
            Node parent = cur.parent;
            if(parent != null)
                find2Parents.add(parent.number);

            cur = cur.parent;
        }

        int findParent = 0;
        for (int i = 0; i < find1Parents.size(); i++) {
            int find1Parent = find1Parents.get(i);
            boolean findSuccess = false;

            for (int j = 0; j < find2Parents.size(); j++) {
                int find2Parent = find2Parents.get(j);

                if(find1Parent == find2Parent) {
                    findSuccess = true;
                }

            }

            if(findSuccess) {
                findParent = find1Parent;
                break;
            }

        }

        // 서브트리 노드 수 찾기
        subTreeCnt = 0;
        dfs(nodes[findParent]);


        return findParent + " " + subTreeCnt;
    }

    static void dfs(Node cur) {
        if(cur == null)
            return;

        subTreeCnt++;
        dfs(cur.left);
        dfs(cur.right);

    }

    public static class Node {
        int number;
        Node parent;
        Node left, right;

        Node(int number){
            this.number = number;
            parent = null;
            left = right = null;
        }
    }
}
