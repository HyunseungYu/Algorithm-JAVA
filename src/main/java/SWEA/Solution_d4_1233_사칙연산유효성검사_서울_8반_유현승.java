package SWEA;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_d4_1233_사칙연산유효성검사_서울_8반_유현승 {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
//		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= 10; t++)
		{
            boolean status = solution();
            int solve = (status) ? 1 : 0;
            sb.append("#" + t + " " + solve + "\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int N;
	
	private static boolean solution() throws Exception {
		String command = br.readLine();
		N = Integer.parseInt(command);
		
		boolean isRight = true;

		for(int i=0; i<N; i++) {
			String line = br.readLine();
			String[] split = line.split(" ");
			if(split.length == 4) {
				if(split[1].equals("-") || split[1].equals("+") || split[1].equals("/") || split[1].equals("*") ) {
					continue;
				} else {
					isRight = false;
				}
			} else if(split.length == 2) {
				if(split[1].equals("-") || split[1].equals("+") || split[1].equals("/") || split[1].equals("*") ) {
					isRight = false;
				}
			}
		}
		
		return isRight;
	}

}
