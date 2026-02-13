import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Scanner sc;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		sc = new Scanner(System.in);
//		br = new BufferedReader(new InputStreamReader(System.in));

//		int T = Integer.parseInt(br.readLine().trim());
		int T = sc.nextInt();

		for(int t = 1; t <= T; t++) {
			int solution = solve();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
//		br.close();
	}

	static int N, M;
	static Node[] nodes;

	static int solve() throws Exception {
//		N = Integer.parseInt(br.readLine().trim());
//		M = Integer.parseInt(br.readLine().trim());
		N = sc.nextInt();
		M = sc.nextInt();
		nodes = new Node[N+1];
		for (int i = 1; i < N+1; i++) {
			nodes[i] = new Node(i);
		}

		// 노드 입력
		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			int child = Integer.parseInt(st.nextToken());
//			int parent = Integer.parseInt(st.nextToken());
			int child = sc.nextInt();
			int parent = sc.nextInt();
			nodes[parent].addChild(nodes[child]);
		}

		int count = 0;
		// for문 돌면서 노드별로 up & down 배열 만들기 1이면 up, 2이면 down, 0이면 도달하지 못했으니 알 수 없음.
		for (int i = 1; i < N + 1; i++) {
			int[] upAndDown = new int[N+1];
			visit(nodes[i], upAndDown);

			boolean isAllVisit = true;
			for (int j = 1; j < N + 1; j++) {
				if(upAndDown[j] == 0)
					isAllVisit = false;
			}

			if(isAllVisit)
				count++;
		}

		return count;
	}

	static void visit(Node node, int[] upAndDown) {
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(node);
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			upAndDown[cur.number] = 1;

			for(Node p : cur.parents) {
				if( upAndDown[p.number] == 0) {
					upAndDown[p.number] = 1;
					queue.add(p);
				}
			}
		}

		queue.clear();
		queue.add(node);
		while(!queue.isEmpty()) {
			Node child = queue.poll();
			upAndDown[child.number] = 2;

			for(Node c : child.children) {
				if( upAndDown[c.number] == 0) {
					upAndDown[c.number] = 2;
					queue.add(c);
				}
			}
		}

	}

	static class Node {
		int number;
		List<Node> parents;
		List<Node> children;

		Node(int number) {
			this.number = number;
			parents = new ArrayList<>();
			children = new ArrayList<>();
		}

		public void addChild(Node child) {
			children.add(child);
			child.parents.add(this);
		}
	}
}

// jS3lRY0gN6g