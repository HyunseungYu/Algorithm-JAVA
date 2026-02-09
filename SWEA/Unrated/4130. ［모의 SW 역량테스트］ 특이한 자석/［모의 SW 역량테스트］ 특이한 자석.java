import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
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

	static int K;
	static final int leftSawIndex = 6, rightSawIndex = 2;
	static Gear[] gears;
	static int[] rotateDirections;

	static int solution() throws Exception {
		K = Integer.parseInt(br.readLine());
		gears = new Gear[4];

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			gears[i] = new Gear();

			for (int j = 0; j < 8; j++) {
				gears[i].saws[j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gearNo = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());

			rotateDirections = new int[4];
			rotateDirections[gearNo] = direction;

			compare(gearNo);
			for (int j = 0; j < 4; j++) {
				switch (rotateDirections[j]) {
					case 1:
						gears[j].rotateRight();
						break;

					case -1:
						gears[j].rotateLeft();
						break;
				}
			}
		}

		int score = getScore();
		return score;
	}

	static int getScore() {
		int score = 0;
		for (int i = 3; 0<=i; i--) {
			score *= 2;
			score += gears[i].saws[0];
		}
		return score;
	}

	static void compare(int startIndex) {

		// 오른쪽으로 비교
		for (int i = startIndex + 1; i < 4; i++) {
			Gear leftGear = gears[i-1]; // 이전 기어
			Gear cur = gears[i];

			if(leftGear.saws[rightSawIndex] != cur.saws[leftSawIndex])
				rotateDirections[i] = -rotateDirections[i-1];
		}

		// 왼쪽으로 비교
		for (int i = startIndex - 1; 0<=i; i--){
			Gear rightGear = gears[i+1]; // 이전 기어
			Gear cur = gears[i];

			if(rightGear.saws[leftSawIndex] != cur.saws[rightSawIndex])
				rotateDirections[i] = -rotateDirections[i+1];
		}

	}

	static class Gear {
		int[] saws = new int[8];

		public void rotateRight() {
			int right = saws[7];
			for (int i = 7; 1<=i; i--) {
				saws[i] = saws[i-1];
			}
			saws[0] = right;
		}

		public void rotateLeft() {
			int left = saws[0];
			for (int i = 0; i < 7; i++) {
				saws[i] = saws[i+1];
			}
			saws[7] = left;
		}
	}
}
