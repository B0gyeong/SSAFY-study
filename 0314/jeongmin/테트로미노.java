import java.io.*;
import java.util.*;

public class 테트로미노 {

    static int N;
    static int M;
    static int[][] grid;
    static int ans;
    static boolean[][] visited;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static boolean isWall(int r, int c) {
        return (r<0 || r>=N || c<0 || c>=M);
    }

    public static void dfs(int r, int c, int m, int curSum) {

        if(m==4) {
            ans = Math.max(ans, curSum);
            return;
        }

        for(int d=0; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(isWall(nr,nc)) continue;
            if(visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr,nc,m+1,curSum+grid[nr][nc]);
            visited[nr][nc] = false;

        }

    }

    public static void other(int r, int c) {

        for(int skip=0; skip<4; skip++) {
            int sum = grid[r][c];
            boolean valid = true;

            for(int d=0; d<4; d++) {

                if(d==skip) continue;

                int nr = r+dr[d];
                int nc = c+dc[d];

                if(isWall(nr,nc)) {
                    valid = false;
                    break;
                }

                sum += grid[nr][nc];
            }

            if(valid) {
                ans = Math.max(ans, sum);
            }

        }

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = -1;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {

                visited[i][j] = true;
                dfs(i,j,1,grid[i][j]);
                visited[i][j] = false;
                other(i,j);

            }
        }

        System.out.println(ans);

    }
}