import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int t = 1; t <= T; t++)
		{
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int N, M;
	static int[] roots;

	static int solution() throws Exception {
		N = sc.nextInt();
		M = sc.nextInt();

		roots = new int[N + 1];
		for (int i = 1; i <= N; i++)
			roots[i] = -1;

		for (int i = 0; i < M; i++) {
			int left = sc.nextInt();
			int right = sc.nextInt();

			union(left, right);
		}

		int count = 0;
		for (int i = 1; i <= N; i++) {
			if(roots[i] == -1)
				count++;
		}

		return count;
	}

	static void union(int left, int right) {
		int leftRoot = find(left);
		int rightRoot = find(right);

		// 둘 다 루트일 경우
		if(leftRoot == left && rightRoot == right) {
			roots[right] = left; // 오른쪽의 루트를 왼쪽으로 지정
			return;
		}

		// left가 루트일 경우이고, right는 루트가 있을 경우
		if(leftRoot == left && rightRoot != right) {
			// 오른쪽의 루트가 왼쪽일 경우 그냥 지나감~~
			if(rightRoot == left)
				return;

			roots[left] = rightRoot; // left의 루트에 오른쪽 루트를 지정
		}

		// 왼쪽에 루트가 있고, 오른쪽이 루트일 경우
		if(leftRoot != left && rightRoot == right) {
			// 왼쪽의 루트가 오른쪽일 경우 이미 지정되어 있으니 팻흐
			if(leftRoot == right)
				return;

			roots[right] = leftRoot;
		}

		// 서로 다른 집단에 속해있는 경우 -> 합치기
		if(leftRoot != left && rightRoot != right) {
			if(leftRoot != rightRoot)
				roots[leftRoot] = rightRoot; // 왼쪽 루트를 오른쪽 루트로 지정: 왼쪽 -> 오른쪽으로 합치기

		}

		// 루트가 같은 경우 패스~~
		if(leftRoot == rightRoot)
			return;

	}

	static int find(int number) {
		if(roots[number] == -1)
			return number;

		return find(roots[number]);
	}
}
