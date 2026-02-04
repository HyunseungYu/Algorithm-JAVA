import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d3_6808_규영이와인영이의카드게임_서울_8반_유현승 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++)
		{
			String solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int[] opponents;
	static int[] mines;
	static boolean[] visited;
	static int winCount;
	static int loseCount;

	static String solution() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		winCount = 0;
		loseCount = 0;

		opponents = new int[9];
		mines = new int[9];
		visited = new boolean[19]; // 1 ~ 18까지 사용
		for (int i = 0; i < 9; i++) {
			opponents[i] = Integer.parseInt(st.nextToken());
			visited[opponents[i]] = true;
		}

		perm(0);

		return loseCount + " " + winCount;
	}

	static void perm(int count) {
		if(count == 9) {
			compare();
			return;
		}

		for(int i=1; i<=18; i++) {
			if(visited[i])
				continue;

			visited[i] = true;
			mines[count] = i;
			perm(count + 1);
			visited[i] = false;
		}
	}

	/**
	 * 규영(opponents)와 인영(mines) 비교해서 승패 결정
	 */
	static void compare() {
		int myScore = 0;
		int opponentScore = 0;

		for (int i = 0; i < 9; i++) {
			int myCard = mines[i];
			int opponentCard = opponents[i];

			if(opponentCard < myCard)
				myScore += myCard + opponentCard;
			else
				opponentScore += myCard + opponentCard;
		}

		if(opponentScore < myScore)
			winCount++;
		else if(myScore < opponentScore)
			loseCount++;
	}
}
