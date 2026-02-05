package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d4_1210_Ladder1_서울_8반_유현승 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

//		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= 10; t++)
		{
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int[][] field;

	static int solution() throws Exception {
		br.readLine();

		field = new int[100][100];
		for (int i = 0; i < 100; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 100; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 100; i++) {
			if(field[0][i] == 1 && start(i))
				return i;
		}

		return -1;
	}

	static boolean[][] visited;

	static boolean start(int x){
		int i = 0;
		int j = x;
		visited = new boolean[100][100];

		while(i < 100) {
			if(!isAvailable(i, j))
				return false;

			if(field[i][j] == 2)
				return true;

			if(visited[i][j])
				i++;

			visited[i][j] = true;

			if(isLeftOpen(i, j)) {
				j--;
			} else if(isRightOpen(i, j)) {
				j++;
			} else {
				i++;
			}
		}

		return false;
	}

	static boolean isLeftOpen(int i, int j) {
		if(!isAvailable(i, j-1))
			return false;

		if(field[i][j-1] == 1 && !visited[i][j-1])
			return true;

		return false;
	}

	static boolean isRightOpen(int i, int j) {
		if(!isAvailable(i, j+1))
			return false;

		if(field[i][j+1] == 1 && !visited[i][j+1])
			return true;

		return false;
	}

	static boolean isAvailable(int i, int j) {
		if(i < 0 || 100 <= i || j < 0 || 100 <= j)
			return false;

		return true;
	}
}
