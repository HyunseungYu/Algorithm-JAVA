import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static List<Pos> people;
    static Pos[] stairs;
    static int[] stairLen;

    static int P;
    static int[][] dist;   // dist[i][0 or 1]
    static int[] select;   // 각 사람의 계단 선택
    static int answer;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = fs.nextInt();

            people = new ArrayList<>();
            stairs = new Pos[2];
            stairLen = new int[2];

            int stairIdx = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int v = fs.nextInt();
                    if (v == 1) {
                        people.add(new Pos(i, j));
                    } else if (v >= 2) {
                        stairs[stairIdx] = new Pos(i, j);
                        stairLen[stairIdx] = v;
                        stairIdx++;
                    }
                }
            }

            P = people.size();
            dist = new int[P][2];
            select = new int[P];

            for (int i = 0; i < P; i++) {
                Pos p = people.get(i);
                for (int s = 0; s < 2; s++) {
                    dist[i][s] = Math.abs(p.r - stairs[s].r) + Math.abs(p.c - stairs[s].c);
                }
            }

            answer = Integer.MAX_VALUE;
            dfs(0);

            sb.append("#").append(tc).append(" ").append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int idx) {
        if (idx == P) {
            answer = Math.min(answer, simulate());
            return;
        }

        select[idx] = 0;
        dfs(idx + 1);

        select[idx] = 1;
        dfs(idx + 1);
    }

    static int simulate() {
        int[][] arrivals = new int[2][P];
        int[] cnt = new int[2];

        // 각 계단으로 가는 사람들의 도착 시간 수집
        for (int i = 0; i < P; i++) {
            int s = select[i];
            arrivals[s][cnt[s]++] = dist[i][s];
        }

        int totalTime = 0;

        for (int s = 0; s < 2; s++) {
            int m = cnt[s];
            if (m == 0) continue;

            Arrays.sort(arrivals[s], 0, m);

            int[] finish = new int[m];

            for (int i = 0; i < m; i++) {
                int start = arrivals[s][i] + 1; // 도착 후 1분 뒤 계단 진입 가능

                // 이미 3명이 계단에 있다면, i-3번째 사람이 끝날 때까지 기다림
                if (i >= 3) {
                    start = Math.max(start, finish[i - 3]);
                }

                finish[i] = start + stairLen[s];
            }

            totalTime = Math.max(totalTime, finish[m - 1]);

            // 현재 최적값보다 이미 크거나 같으면 더 볼 필요 없음
            if (totalTime >= answer) return totalTime;
        }

        return totalTime;
    }
}