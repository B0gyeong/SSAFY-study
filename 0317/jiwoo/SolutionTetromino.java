import java.util.*;
import java.io.*;

public class SolutionTetromino{
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int maxVal;

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= M) return true;
        return false;
    }

    public static void dfs(int depth, int r, int c, int acc){
        if(depth >= 4){
            maxVal = Math.max(maxVal, acc);
            return;
        }

        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!isWall(nr, nc)){
                if(!visited[nr][nc]){
                    visited[nr][nc] = true;
                    dfs(depth + 1, nr, nc, acc + map[nr][nc]);
                    visited[nr][nc] = false;
                }
            }
        }
    }

    public static void checkT(int r, int c){
        int minVal = 1001;
        int sumVal = map[r][c];
        int cnt = 0;

        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(!isWall(nr, nc)){
                cnt++;
                sumVal += map[nr][nc];
                minVal = Math.min(minVal, map[nr][nc]);
            }
        }

        if(cnt < 3) return;
        if(cnt == 4){
            sumVal = sumVal - minVal;
        }
        maxVal = Math.max(maxVal, sumVal);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        maxVal = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                visited[r][c] = true;
                dfs(1, r, c, map[r][c]);
                visited[r][c] = false;

                checkT(r, c);
            }
        }

        System.out.println(maxVal);
    }
}