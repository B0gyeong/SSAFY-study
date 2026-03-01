
import java.io.*;
import java.util.*;

public class S1767_프로세서연결하기 {

    static int N;
    static int[][] maxinos;
    static int bestCore, bestWire;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<int[]> cores;
    static int edgeCnt;

    public static void dfs(int idx, int sum, int cnt) {
        if(idx==cores.size()) {
            // best갱신
            if(sum>bestCore) {
                bestCore = sum;
                bestWire = cnt;
            } else if( sum == bestCore ){
                bestWire = Math.min(bestWire, cnt);
            }
            return;
        }

        int r = cores.get(idx)[0];
        int c = cores.get(idx)[1];

        dfs(idx+1, sum, cnt);

        for(int dir=0; dir<4; dir++) {
            int len = canConnectLen(r,c,dir);
            if(len==0) continue;
            setWire(r,c,dir,2); // 전선 깔기
            dfs(idx+1, sum+1, cnt+len);
            setWire(r,c,dir,0); // 전선 복구
        }
    }

    // 전선 복구
    public static void setWire(int r, int c, int dir, int val) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        while(0 <= nr && nr < N && 0 <= nc && nc < N) {
            maxinos[nr][nc] = val;
            nr += dr[dir];
            nc += dc[dir];
        }
    }

    public static int canConnectLen(int r, int c, int dir) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        int len = 0;

        while (0 <= nr && nr < N && 0 <= nc && nc < N) {
            if (maxinos[nr][nc] != 0) return 0; // 코어(1)나 전선(2)이 있으면 막힘
            len++;
            nr += dr[dir];
            nc += dc[dir];
        }
        // 격자 밖으로 나갔으면 끝까지 빈칸으로 연결 성공
        return len;
    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=1; t<=T; t++) {

            bestCore = -1;
            bestWire = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            maxinos = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    maxinos[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가장자리
            edgeCnt = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(maxinos[i][j]==1 && (i==0||j==0||i==N-1||j==N-1)) edgeCnt++;
                }
            }

            // 코어 좌표 리스트
            cores = new ArrayList<>();
            for(int i=1; i<N-1; i++) {
                for(int j=1; j<N-1; j++) {
                    if(maxinos[i][j]==1) cores.add(new int[]{i,j});
                }
            }

            // 리스트 0번 코어부터 결정
            dfs(0, edgeCnt, 0);

            System.out.println("#"+t+" "+bestWire);
        }
    }
}