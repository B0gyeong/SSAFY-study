import java.io.*;
import java.util.*;

public class S2383_점심식사시간 {

    static int N;
    static int[][] grid;
    static List<Point> people;
    static List<Stair> stairs;
    static int ans;
    static int[] selectedStair;

    static class Point {
        int r;
        int c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static class Stair {
        int r,c,k;

        public Stair(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }

    public static int calTime(List<Integer> arriveTimes, int K) {
        if(arriveTimes.isEmpty()) return 0;

        Collections.sort(arriveTimes);

        Queue<Integer> waitQ = new LinkedList<>(); // 계단 입구에서 줄 서 있는 사람들
        for (int t : arriveTimes) waitQ.add(t);

        Queue<Integer> stairQ = new LinkedList<>();

        int time = 0;
        int finishedCount = 0;
        int totalPeople = arriveTimes.size();

        while (finishedCount < totalPeople) {
            time++;

            // 1. 계단 위에 있는 사람들 처리 (내려가기)
            int size = stairQ.size();
            for (int i = 0; i < size; i++) {
                int remain = stairQ.poll();
                remain--; // 1분 경과
                if (remain > 0) stairQ.add(remain); // 아직 덜 내려갔으면 다시 넣음
                else finishedCount++; // 다 내려갔으면 탈출 처리
            }

            // 2. 계단 입구에 도착한 사람들 중 진입 가능한 사람 처리
            // 주의: 도착 후 1분 뒤부터 진입 가능하므로 (time > arrivalTime) 체크
            while (stairQ.size() < 3 && !waitQ.isEmpty() && waitQ.peek() < time) {
                waitQ.poll();
                stairQ.add(K); // 계단 길이(K)만큼 내려가야 함
            }
        }

        return time;
    }
    public static int startSimulation() {
        List<Integer> group0 = new ArrayList<>();
        List<Integer> group1 = new ArrayList<>();

        for(int i=0; i< people.size(); i++) {
            int sidx = selectedStair[i];
            Stair s = stairs.get(sidx);
            Point p = people.get(i);

            int dist = Math.abs(p.r - s.r) + Math.abs(p.c - s.c);

            if(sidx==0) group0.add(dist);
            else group1.add(dist);
        }

        int time0 = calTime(group0, stairs.get(0).k);
        int time1 = calTime(group1, stairs.get(0).k);

        return Math.max(time0, time1);
    }

    public static void dfs(int idx) {
        if(idx == people.size()) {
            int result =  startSimulation();
            ans = Math.min(ans, result);
            return;
        }

        selectedStair[idx] = 0;
        dfs(idx+1);

        selectedStair[idx] = 1;
        dfs(idx+1);
    }

    public static void solve() {

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(grid[i][j]==1) {
                    people.add(new Point(i,j));
                } else if (grid[i][j]>1) {
                    stairs.add(new Stair(i, j, grid[i][j]));
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            people = new ArrayList<>();
            stairs = new ArrayList<>();


            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve();
            selectedStair = new int[people.size()];
            ans = Integer.MAX_VALUE;
            dfs(0);
            System.out.println("#"+t+" "+ans);
        }
    }
}
