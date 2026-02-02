package SWEA;
import java.io.*;
import java.util.*;

class Solution_d3_1230_암호문3_서울_8반_유현승 {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
//		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=10; t++) {
			String solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}
		System.out.println(sb);
		
		br.close();
	}
	
	static int N, M;
	static List<Integer> list;
	
	private static String solution() throws Exception {
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>(N);
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()," ");
		while(st.hasMoreTokens()) {
			String command = st.nextToken();
			
			// 삽입 x위치에 y개 암호문 삽입
			if(command.equals("I")) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				// 붙일 암호문 입력
				for(int i=0; i<y; i++) {
					int add = Integer.parseInt(st.nextToken());
					list.add(x+i, add);
				}
			} else if(command.equals("D")) {
				// 삭제, x번째 암호문 바로 다음부터 y개 삭제
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				for(int i=0; i<y; i++) {
					list.remove(x);
				}
				
			} else if(command.equals("A")) {
				// 추가, 맨 뒤에 y개 암호문 추가
				int y = Integer.parseInt(st.nextToken());
				
				for(int i=0; i<y; i++) {
					int crypto = Integer.parseInt(st.nextToken());
					list.add(crypto);
				}
			}
		}
		
		StringBuilder sb2 = new StringBuilder();
		for(int i=0; i<10; i++) {
			sb2.append(list.get(i) + " ");
		}
		
		return sb2.toString().trim();
	}
	
	

}
