import java.io.*;
import java.util.*;

public class S2383_점심식사시간 {

    static int N;
    static int[][] grid;
    static int min;
    static List<Person> people;
    static List<Stair> stairs;
    static int[] sel;

    static class Person {
        int r;
        int c;
        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Stair {
        int r;
        int c;
        int x;
        public Stair(int r, int c, int x) {
            this.r = r;
            this.c = c;
            this.x = x;
        }
    }

    // num번 계단 group
    public static int calTime(List<Integer> group, int num) {

        if(group.isEmpty()) return 0;

        Stair s = stairs.get(num);
        List<Integer> arrivalTime = new ArrayList<>();

        for(int i=0; i<group.size(); i++) {
            Person person = people.get(group.get(i));
            int d = Math.abs(person.r-s.r)+Math.abs(person.c-s.c);
            arrivalTime.add(d);
        }

        Collections.sort(arrivalTime);

        Queue<Integer> stair = new LinkedList<>();
        int time = 0;
        for(int arrive:arrivalTime) {
            int canEnter = arrive + 1;

            if(stair.size()==3) {
                int firstout = stair.poll();
                canEnter = Math.max(canEnter, firstout);
                int exitTime = canEnter + s.x;
                stair.add(exitTime);
            } else {
                int exitTime = canEnter + s.x;
                stair.add(exitTime);
            }
        }

        int lastTime = 0;
        while(!stair.isEmpty()) {
            lastTime = stair.poll();
        }

        return lastTime;

    }

    public static int simulation() {
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();

        for(int i=0; i<sel.length; i++) {
            if(sel[i]==0) {
                group1.add(i); // 0번 계단 그룹
            } else {
                group2.add(i); // 1번 계단 그룹
            }
        }

        int time1 = calTime(group1, 0);
        int time2 = calTime(group2, 1);

        return Math.max(time1, time2);
    }

    public static void dfs(int idx) {
        if(idx==people.size()) {
            // 시뮬레이션
            int ans = simulation();
            // min 업데이트
            min = Math.min(ans, min);
            return;
        }

        sel[idx] = 0;
        dfs(idx+1);

        sel[idx] = 1;
        dfs(idx+1);

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            people = new ArrayList<>();
            stairs = new ArrayList<>();

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(grid[i][j]==1) {
                        people.add(new Person(i, j));
                    } else if(grid[i][j]>1) {
                        stairs.add(new Stair(i,j,grid[i][j]));
                    }
                }
            }

            sel = new int[people.size()];

            dfs(0);

            System.out.println("#"+t+" "+min);

        }
    }
}