import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정사각형 방
 */
public class swea1861 {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int count;

    public static void dfs(int[][] map, int r, int c) {
        int N = map.length;

        count++;

        // 탐색
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr<0||nr>=N||nc<0||nc>=N) continue;

            if(map[nr][nc]==map[r][c]+1) {
                dfs(map, nr, nc);
            }
        }

        return;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=1; t<=T; t++) {

            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            int max = Integer.MIN_VALUE;
            int maxRoom = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    count = 0;
                    boolean[][] visited = new boolean[N][N];
                    dfs(map, i, j); // 몇개의 방을 이동하는지 count 업데이트
                    if(count>max) {
                        max = count;
                        maxRoom = map[i][j];
                    } else if(count == max) {
                        maxRoom = Math.min(maxRoom, map[i][j]); // 동률처리
                    }
                }
            }

            System.out.println("#"+t+" "+maxRoom+" "+max);
        }


    }
}
