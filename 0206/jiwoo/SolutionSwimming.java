import java.util.*;
import java.io.*;

public class SolutionSwimming{
    public static void checkVisit(boolean[][][] visited, int neigh_r, int neigh_c, int nt, Queue<int[]> q){
        int t = nt % 3;
        if(!visited[neigh_r][neigh_c][t]){
            visited[neigh_r][neigh_c][t] = true;
            q.add(new int[]{neigh_r, neigh_c, nt});
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine().trim());
            int[][] area = new int[N][N];
            int[] start = new int[2];
            int[] end = new int[2];

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    area[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            StringTokenizer en = new StringTokenizer(br.readLine());
            for(int i = 0; i < 2; i++){
                start[i] = Integer.parseInt(st.nextToken());
                end[i] = Integer.parseInt(en.nextToken());
            }

            int sec = 0;
            int result = -1;

            boolean[][][] visited = new boolean[N][N][3]; // 2: 소용돌이 건너려 함

            int[] dr = { -1, 1, 0, 0, 0 };
            int[] dc = { 0, 0, -1, 1, 0 }; 

            int curr_r = start[0];
            int curr_c = start[1];
            int neigh_r = 0;
            int neigh_c = 0;
            int neighbor = 0; // value of the neighbor

            Queue<int[]> q = new LinkedList<>();
            int[] curr;
            visited[start[0]][start[1]][0] = true;
            q.add(new int[]{start[0], start[1], 0});

            while(!q.isEmpty()){
                curr = q.poll();
                curr_r = curr[0];
                curr_c = curr[1];
                sec = curr[2];
                int nt;

                if(curr_r == end[0] && curr_c == end[1]){
                    result = sec;
                    break;
                }
                for(int i = 0; i < 5; i++){
                    nt = sec + 1;
                    neigh_r = curr_r + dr[i];
                    neigh_c = curr_c + dc[i];
                    if(neigh_r >= 0 && neigh_c >= 0 && neigh_r < N && neigh_c < N){
                        neighbor = area[neigh_r][neigh_c];
                        boolean isWait = (neigh_r == curr_r && neigh_c == curr_c);
                        if(isWait){
                            checkVisit(visited, neigh_r, neigh_c, nt, q);
                            continue;
                        }
                        if(neighbor == 1){
                            continue;
                        }
                        if(neighbor == 2 && sec % 3 != 2){
                            continue;
                        }
                        checkVisit(visited, neigh_r, neigh_c, nt, q);
                    }
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}