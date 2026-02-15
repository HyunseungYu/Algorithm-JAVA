import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			int solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dj = {-1, 0, +1, -1, +1, -1, 0, +1};
    static int count = 0;

	static int solution() throws Exception {
        count = 0;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (c[j] == '*') {
                    board[i][j] = -1;
                }
            }
        }

        // board에 주변 지뢰 개수 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == -1)
                    continue;

                for (int k = 0; k < 8; k++) {
                    int ni = i + di[k];
                    int nj = j + dj[k];

                    if(!isAvailable(ni, nj))
                        continue;

                    if(board[ni][nj] == -1)
                        board[i][j]++;
                }
            }
        }

        count = 0;
        // 지뢰 개수가 0인 지점부터 클릭
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 0 && !visited[i][j]) {
                    click(i, j);
                    count++;
                }
            }
        }

        // 나머지 부분 클릭
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j] && board[i][j] != -1) {
                    click(i, j);
                    count++;
                }
            }
        }

        return count;
    }

    static void click(int i, int j) {
        if(!isAvailable(i, j))
            return;

        if(visited[i][j])
            return;

        visited[i][j] = true;

        if(board[i][j] != 0)
            return;

        // 클릭한 지점이 0이라면 주변 전파
        for (int k = 0; k < 8; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            click(ni, nj);
        }
    }


    static boolean isAvailable(int i, int j) {
        if(i < 0 || N <= i || j < 0 || N <= j)
            return false;

        return true;
    }
}
