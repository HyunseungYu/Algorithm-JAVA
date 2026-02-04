package Baekjoon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class BJ_2477 {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	private static StringBuilder sb; 
	
	static int K;
	static int count;
	static int area;
	static int maxHeight = 0;
	static int maxHeightIndex = 0;
	static int maxWidth = 0;
	static int maxWidthIndex = 0;
	static int maxLineDirection = 0;
	static int[][] lines;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		lines = new int[6][2];

		K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int direction = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			if(direction == 1 || direction == 2) {
				if(maxWidth < length) {
					maxWidth = length;
					maxWidthIndex = i;
					maxLineDirection = direction;
				}
			} else {
				maxHeight = Math.max(maxHeight, length);
				maxHeightIndex = i;
			}
			
			lines[i] = new int[] {direction, length};
		}
		
		area = maxWidth * maxHeight;
		int minusArea = 0;
		
		if(maxLineDirection == 1) { // ㄴ 모양
			int left = getRoundIndex(maxWidthIndex, -1);
			int right = getRoundIndex(maxWidthIndex, 1);
			
			if(lines[left][1] < lines[right][1]) {
				int minusHeight = lines[right][1] - lines[left][1];
				int minusWidth = lines[getRoundIndex(left, -1)][1];
				minusArea = minusHeight * minusWidth;
			} else { // ㄴ 모양
				int minusHeight = lines[left][1] - lines[right][1];
				int minusWidth = lines[getRoundIndex(right, +1)][1];
				minusArea = minusHeight * minusWidth;
			}
			
		} else {
			int left = getRoundIndex(maxWidthIndex, +1);
			int right = getRoundIndex(maxWidthIndex, -1);
			
			if(lines[left][1] < lines[right][1]) { // ㄱ 모양
				int minusHeight = lines[right][1] - lines[left][1];
				int minusWidth = lines[getRoundIndex(left, 1)][1];
				minusArea = minusHeight * minusWidth;
			} else { // ㄴ 모양
				int minusHeight = lines[left][1] - lines[right][1];
				int minusWidth = lines[getRoundIndex(right, -1)][1];
				minusArea = minusHeight * minusWidth;
			}
			
		}
		
		
		
		
		area = area - minusArea;
		count = area * K;
		sb.append(count);
		
//		System.out.println(sb.toString());
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int getRoundIndex(int i, int range) {
		return (i + 6 + range) % 6;
	}

}
