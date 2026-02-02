package SWEA;
import java.io.*;
import java.util.*;

class Solution_d4_1226_미로1_서울_8반_유현승 {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
//		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=10; t++) {
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}
		System.out.println(sb);
		
		br.close();
	}
	
	static char[][] maze;
	static boolean[][] visited;
	static boolean isReachable;
	
	
	private static int solution() throws Exception {
		br.readLine();
		
		maze = new char[16][16];
		for(int i=0; i<16; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<16; j++)
				maze[i][j] = line[j];
		}
		
		visited = new boolean[16][16];
		isReachable = false;
		dfs(1, 1);
		
		if(isReachable)
			return 1;
		else
			return 0;
	}
	
	private static void dfs(int i, int j) {
		if(i < 0 || 16 <= i || j < 0 || 16 <= j)
			return;
		
		if(maze[i][j] == '3') {
			isReachable = true;
			return;
		}
		
		if(visited[i][j])
			return;
		
		if(maze[i][j] == '1')
			return;
		
		visited[i][j] = true;
		
		dfs(i-1, j);
		dfs(i+1, j);
		dfs(i, j-1);
		dfs(i, j+1);
	}
	
	

}
