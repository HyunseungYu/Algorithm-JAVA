package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_d3_1244_최대상금_서울_8반_유현승 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++)
		{
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N, max, length;
	static int[] numbers;
	static Set<Integer>[] visited;

	static int solution() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		String numStr = st.nextToken();
		N = Integer.parseInt(st.nextToken());
		length = numStr.length();
		max = 0;

		visited = new HashSet[N];
		for (int i = 0; i < N; i++)
			visited[i] = new HashSet<>();

		numbers = new int[length];
		for (int i = 0; i < length; i++)
			numbers[i] = numStr.charAt(i) - '0';

		dfs(0);

		return max;
	}

	static void dfs(int count) {

		int number = makeNumber();

		if(count == N) {
			max = Math.max(max, number);
			return;
		}

		if (visited[count].contains(number))
			return;

		visited[count].add(number);

		for (int i = 0; i < length; i++) {
			for (int j = i+1; j < length; j++) {
				swap(i, j);
				dfs(count + 1);
				swap(i, j);
			}

		}
	}

	static int makeNumber(){
		int number = 0;

		for (int i = 0; i < length; i++) {
			number *= 10;
			number += numbers[i];
		}

		return number;
	}

	static void swap(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}


}
