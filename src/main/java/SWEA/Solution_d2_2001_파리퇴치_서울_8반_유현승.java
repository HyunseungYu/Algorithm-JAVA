package SWEA;
import java.io.*;
import java.util.*;

class Solution_d2_2001_파리퇴치_서울_8반_유현승 {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_2001.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}
		System.out.println(sb);
		
		br.close();
	}
	
	static int N, M;
	static int[][] board;
	static int maxKills;
	
	private static int solution() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maxKills = 0;
		
		board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int first = Integer.parseInt(st.nextToken());
			board[i][0] = first;
			for(int j=1; j<N; j++) {
				board[i][j] = board[i][j-1] + Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int kills = getKills(i, j);
				maxKills = Math.max(maxKills, kills);
			}
		}
		
		
		return maxKills;
		
	}
	
	private static int getKills(int i, int j) {
		int sum = 0;

		for(int k=0; k<M; k++) {
			if(N <= i + k || N <= j + M - 1)
				return -1;
			
			int width = 0;
			if(j - 1 < 0) {
				width = board[i + k][j + M - 1] - 0;
			} else {
				width = board[i + k][j + M - 1] - board[i + k][j-1];
			}
			
			sum += width;
		}
		
		return sum;
	}
	
	

}
