import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n, k;
    static int[] departure, destination;
    static Bus[] buses;

    static void input() {
        Reader scanner = new Reader();

        m = scanner.nextInt();
        n = scanner.nextInt();
        k = scanner.nextInt();
        buses = new Bus[k + 1];

        for(int bus = 1; bus <= k; bus++) {
            int busNum = scanner.nextInt();
            int startX = scanner.nextInt(), startY = scanner.nextInt();
            int endX = scanner.nextInt(), endY = scanner.nextInt();

            if(startX == endX)
                buses[busNum] = new Bus(new int[] {startX, Math.min(startY, endY)}, new int[] {endX, Math.max(startY, endY)}, true);
            else if(startY == endY)
                buses[busNum] = new Bus(new int[] {Math.min(startX, endX), startY}, new int[] {Math.max(startX, endX), endY}, false);
        }

        int startX = scanner.nextInt(), startY = scanner.nextInt();
        departure = new int[] {startX, startY};

        int endX = scanner.nextInt(), endY = scanner.nextInt();
        destination = new int[] {endX, endY};
    }

    static void solution() {
        System.out.println(bfs());
    }

    static int bfs() {
        // 출발지에서 탈 수 있는 버스 목록을 얻어온다
        List<Loc> startBuses = findStartBuses(departure);

        PriorityQueue<Loc> queue = new PriorityQueue<>();
        boolean[] visitedBus = new boolean[k + 1]; // 각 버스를 방문하였는지 여부를 나타낸다
        for(Loc startBus : startBuses) {
            queue.offer(startBus);
            visitedBus[startBus.busNum] = true;
        }

        while(!queue.isEmpty()) {
            Loc cur = queue.poll();
            Bus bus = buses[cur.busNum];
            // 만약 현재 타고 있는 버스로 목적지에 도착할 수 있다면 그때까지 이용한 버스의 수를 반환한다
            if(bus.isVertical && destination[0] == bus.start[0] &&
                    (bus.start[1] <= destination[1] && destination[1] <= bus.end[1]))
                return cur.usedBusNum;
            if(!bus.isVertical && destination[1] == bus.start[1] &&
                    (bus.start[0] <= destination[0] && destination[0] <= bus.end[0]))
                return cur.usedBusNum;

            // 첫 번째 버스부터 마지막 버스까지 순회하며 아직 타지 않았고 현재 타고 있는 버스를 통해 갈아탈 수 있는 버스를 찾는다
            // 그러한 버스를 Queue에 넣어 이후 탐색에 사용한다
            for(int busIdx = 1; busIdx <= k; busIdx++) {
                if(!visitedBus[busIdx] && isCross(buses[cur.busNum], buses[busIdx])) {
                    visitedBus[busIdx] = true;
                    queue.offer(new Loc(busIdx, cur.usedBusNum + 1));
                }
            }
        }

        return -1;
    }

    static List<Loc> findStartBuses(int[] departure) {
        List<Loc> startBuses = new ArrayList<>(); // 출발지에서 탈 수 있는 버스 목록
        // 첫 번째 버스부터 마지막 버스까지 순회하며 출발지에서 탈 수 있는 버스를 찾는다
        for(int busIdx = 1; busIdx <= k; busIdx++) {
            Bus bus = buses[busIdx];

            // 버스의 방향이 수직이고 x좌표가 같으며 y좌표가 버스가 다니는 y좌표 사이에 존재한다면
            // 해당 버스를 탈 수 있으니 startBuses에 해당 버스 번호를 넣는다
            if(bus.isVertical && departure[0] == bus.start[0] &&
                    (bus.start[1] <= departure[1] && departure[1] <= bus.end[1])) {
                startBuses.add(new Loc(busIdx, 1));
            }
            // 버스의 방향이 수평이고 y좌표가 같으며 x좌표가 버스가 다니는 x좌표 사이에 존재한다면
            // 해당 버스를 탈 수 있으니 startBuses에 해당 버스 번호를 넣는다
            else if(!bus.isVertical && departure[1] == bus.start[1] &&
                    (bus.start[0] <= departure[0] && departure[0] <= bus.end[0])) {
                startBuses.add(new Loc(busIdx, 1));
            }
        }

        return startBuses;
    }

    // 두 버스가 교차되는지, 그래서 한 버스에서 다른 버스로 갈아탈 수 있는지 확인하는 함수
    static boolean isCross(Bus bus1, Bus bus2) {
        // 두 버스 방향 모두 수직일 때,
        // 두 버스의 x좌표가 다르거나, 한 버스의 시작점이 다른 버스의 도착점보다 크거나 한 버스의 도착점이 다른 버스의 시작점보다 작다면
        // 두 버스는 교차하지 않으니 false를 반환
        if(bus1.isVertical && bus2.isVertical) {
            if(bus1.start[0] != bus2.start[0] || bus1.start[1] > bus2.end[1] || bus1.end[1] < bus2.start[1])
                return false;
            else
                return true;
        }
        // 하나의 버스가 수직, 하나의 버스가 수평일 때,
        // 수직인 버스의 x좌표가 수평인 버스의 x좌표 사이에 있고, 수평인 버스의 y좌표가 수직인 수직인 버스의 y좌표 사이에 있다면
        // 두 버스는 교차하니 true를 반환
        else if(bus1.isVertical && !bus2.isVertical) {
            if(bus1.start[0] >= bus2.start[0] && bus1.start[0] <= bus2.end[0] && bus2.start[1] >= bus1.start[1] && bus2.start[1] <= bus1.end[1])
                return true;
            else
                return false;
        } else if(!bus1.isVertical && bus2.isVertical) {
            if(bus2.start[0] >= bus1.start[0] && bus2.start[0] <= bus1.end[0] && bus1.start[1] >= bus2.start[1] && bus1.start[1] <= bus2.end[1])
                return true;
            else
                return false;
        }
        // 두 버스 방향 모두 수평일 때,
        // 두 버스의 y좌표가 다르거나, 한 버스의 시작점이 다른 버스의 도착점보다 크거나 한 버스의 도착점이 다른 버스의 시작점보다 작다면
        // 두 버스는 교차하지 않으니 false를 반환
        else {
            if(bus1.start[1] != bus2.start[1] || bus1.start[0] > bus2.end[0] || bus1.end[0] < bus2.start[0])
                return false;
            else
                return true;
        }
    }

    static class Loc implements Comparable<Loc> {
        int busNum, usedBusNum;

        public Loc(int busNum, int usedBusNum) {
            this.busNum = busNum;
            this.usedBusNum = usedBusNum;
        }

        @Override
        public int compareTo(Loc o) {
            return usedBusNum - o.usedBusNum;
        }
    }

    static class Bus {
        int[] start, end;
        boolean isVertical; // 해당 버스의 방향이 수직이면 true, 수평이면 false

        public Bus(int[] start, int[] end, boolean isVertical) {
            this.start = start;
            this.end = end;
            this.isVertical = isVertical;
        }
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}