package SWEA;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_d3_2805_농작물수확하기_서울_8반_유현승 {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_d3_2805.txt"));

		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++)
		{
            int solve = solution();
            sb.append("#" + t + " " + solve + "\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	private static int N;
	private static int[][] field;
	private static int sum;
	
	private static int solution() throws Exception {
		N = Integer.parseInt(br.readLine());
		field = new int[N][N];
		sum = 0;
		
		for(int i=0; i<N; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=0; j<N; j++) 
				field[i][j] = Integer.valueOf(input[j] - '0');
		}
		
		/**
		 * 맨해튼 거리 방식으로 풀기
		 */
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int distance = calculateDistance(i, j);
				if(distance <= N/2)
					sum += field[i][j];
			}
		}
		
		return sum;
		
	}
	
	private static int calculateDistance(int i, int j) {
		int height = N/2 - i;
		int width = N/2 - j;
		
		return Math.abs(height) + Math.abs(width);
	}

}
