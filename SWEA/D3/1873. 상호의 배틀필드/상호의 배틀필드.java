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
			String solution = solution();
			sb.append("#" + t + " " + solution);
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int H, W, N;
	static char[][] field;
	static int tankI, tankJ;
	static int direction;
	static final char[] tank = {' ', '^', 'v', '<', '>' };
	static final int[] dI = {0, -1, 1, 0, 0};
	static final int[] dJ = {0, 0, 0, -1, 1};


	static String solution() throws Exception {
		tankI = 0;
		tankJ = 0;
		direction = 0;

		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		field = new char[H][W];
		for (int i = 0; i < H; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				field[i][j] = line[j];

				if(line[j] == '^') {
					tankI = i;
					tankJ = j;
					direction = 1;
				}
				if(line[j] == 'v') {
					tankI = i;
					tankJ = j;
					direction = 2;
				}
				if(line[j] == '<') {
					tankI = i;
					tankJ = j;
					direction = 3;
				}
				if(line[j] == '>') {
					tankI = i;
					tankJ = j;
					direction = 4;
				}
			}
		}

		N = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		for (char c : line) {
			switch (c) {
				case 'U':
					moveUp();
					break;
				case 'D':
					moveDown();
					break;
				case 'L':
					moveLeft();
					break;
				case 'R':
					moveRight();
					break;
				case 'S':
					shoot();
					break;
			}
		}

//		if(direction == 1) {
//			field[tankI][tankJ] = '^';
//		}
//
//		if(direction == 2) {
//			field[tankI][tankJ] = 'v';
//		}
//
//		if(direction == 3) {
//			field[tankI][tankJ] = '<';
//		}
//
//		if(direction == 4) {
//			field[tankI][tankJ] = '>';
//		}

		field[tankI][tankJ] = tank[direction];

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < H; i++) {
			answer.append(field[i]);
			answer.append("\n");
		}

		return answer.toString();
	}

	static void moveUp() {
		direction = 1;

		if(!canGo(tankI-1, tankJ))
			return;

		field[tankI][tankJ] = '.';
		tankI--;
	}
	static boolean canGo(int i, int j) {
		if(!isAvailable(i, j))
			return false;

		if(field[i][j] == '.')
			return true;

		return false;
	}

	static boolean isAvailable(int i, int j) {
		if(i < 0 || H <= i || j < 0 || W <= j)
			return false;

		return true;
	}

	static void moveDown() {
		direction = 2;

		if(!canGo(tankI+1, tankJ))
			return;

		field[tankI][tankJ] = '.';
		tankI++;
	}
	static void moveLeft() {
		direction = 3;

		if(!canGo(tankI, tankJ-1))
			return;

		field[tankI][tankJ] = '.';
		tankJ--;

	}
	static void moveRight(){
		direction = 4;

		if(!canGo(tankI, tankJ+1))
			return;

		field[tankI][tankJ] = '.';
		tankJ++;
	}
	static void shoot(){
		int curI = tankI, curJ = tankJ;
		while(true) {
			curI = curI + dI[direction];
			curJ = curJ + dJ[direction];

			if(!isAvailable(curI, curJ))
				return;

			if(field[curI][curJ] == '*') {
				field[curI][curJ] = '.';
				return;
			}

			if(field[curI][curJ] == '#') {
				return;
			}
		}
	}


}
