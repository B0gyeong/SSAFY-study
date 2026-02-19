import java.util.*;
import java.io.*;

public class DessertCafe{
    static int[][] cafe;
    static int total;
    static int result;
    static boolean[] visited; // each index referring to the # of dessert
    static int N;
    static int[] dr = { 1, 1, -1, -1};
    static int[] dc = { 1, -1, -1, 1 };
    static int sr;
    static int sc;

    public static boolean isWall(int r, int c){
        if(r < 0 || r >= N){
            return true;
        }
        if(c < 0 || c >= N){
            return true;
        }
        return false;
    }

    public static void route(int r, int c, int sum, int cnt, int direction){
        if(isWall(r,c)){
            return;
        }
        if(r == sr && c == sc && cnt != 0){ //came back to starting point
            total = Math.max(sum, total);
            result = Math.max(cnt, result);
            System.out.println("back!");
            return;
        }
        if(visited[cafe[r][c]]){ //visited
            System.out.println("visited");
            return;
        }

        if(isWall(r + dr[direction], c + dc[direction])){
            System.out.println("wall");
            if(direction < 3){
                route(r + dr[direction + 1], c + dc[direction + 1], sum, cnt + 1, direction + 1);
            }
            return;
        }else{
            sum += cafe[r][c];
            visited[cafe[r][c]] = true;
            System.out.println("direction: " + direction);
            if(direction < 3){
                route(r + dr[direction], c + dc[direction], sum, cnt + 1, direction);
                route(r + dr[direction + 1], c + dc[direction + 1], sum, cnt + 1, direction + 1);
            }else{
                route(r + dr[direction], c + dc[direction], sum, cnt + 1, direction);
            }
        }

        visited[cafe[r][c]] = false;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st;
            total = -1;
            result = -1;
            visited = new boolean[101]; // 1 <= dessert number <= 100
            cafe = new int[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    cafe[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("let's go");

            for(int r = 1; r < N - 1; r++){
                for(int c = 1; c < N - 1; c++){
                    System.out.println("calling");
                    sr = r;
                    sc = c;
                    route(r, c, 0, 0, 0);
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}