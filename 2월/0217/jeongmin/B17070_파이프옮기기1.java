import java.io.*;
import java.util.*;

public class B17070_파이프옮기기1 {

    static int N;
    static int[][] house;
    static int cnt;
    public static void dfs(int r0, int c0, int r1, int c1) {
        if(r1==N-1&&c1==N-1) {
            cnt++;
            return;
        }
        // 가로
        if(r0==r1&&c0+1==c1) {
            // 범위체크먼저!
            if(c1+1<N&&house[r1][c1+1]==0) {
                dfs(r1, c1, r1, c1 + 1);
            }
            if(c1+1<N&&r1+1<N&&house[r1+1][c1+1]==0&&house[r1+1][c1]==0&&house[r1][c1+1]==0) {
                dfs(r1, c1, r1 + 1, c1 + 1);
            }
        }

        // 세로
        if(r0+1==r1&&c0==c1) {
            if(r1+1<N&&house[r1+1][c1]==0) {
                dfs(r1, c1, r1+1, c1);
            }

            // 대각선 인 경우 3자리가 빈칸
            if(c1+1<N&&r1+1<N&&house[r1+1][c1+1]==0&&house[r1+1][c1]==0&&house[r1][c1+1]==0) {
                dfs(r1, c1, r1 + 1, c1 + 1);
            }
        }

        // 대각선
        if(r0+1==r1&&c0+1==c1) {
            if(c1+1<N&&house[r1][c1+1]==0) {
                dfs(r1, c1, r1, c1 + 1);
            }
            if(r1+1<N&&house[r1+1][c1]==0) {
                dfs(r1, c1, r1+1, c1);
            }
            if(c1+1<N&&r1+1<N&&house[r1+1][c1+1]==0&&house[r1+1][c1]==0&&house[r1][c1+1]==0) {
                dfs(r1, c1, r1 + 1, c1 + 1);
            }
        }

    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        house = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cnt = 0;
        dfs(0,0,0,1);
        System.out.println(cnt);
    }
}