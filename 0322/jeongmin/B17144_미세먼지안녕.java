import java.io.*;
import java.util.*;

public class B17144_미세먼지안녕 {

    static int R;
    static int C;
    static int T;
    static int[][] grid;

    static int UP;
    static int DOWN;

    static int[][] arr;
    static int aCnt;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static boolean isWall(int r, int c) {
        return (r<0 || r>=R || c<0 || c>=C);
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        grid = new int[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<R; i++) {
            if(grid[i][0] == -1) {
                UP = i;
                DOWN=i+1;
                break;
            }
        }

        while(T-->0) {
            // 1. 미세먼지 확산
            aCnt = 0;
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    if(grid[i][j] != -1 && grid[i][j] != 0) {
                        aCnt++;
                    }
                }
            }

            arr = new int[aCnt][3]; // 0:r 1:c 2:인접한 방향으로 확산될 먼지양
            int aIdx = 0;
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    if(grid[i][j] != -1 && grid[i][j] != 0) {
                        arr[aIdx][0] = i;
                        arr[aIdx][1] = j;
                        arr[aIdx][2] = 0;
                        aIdx++;
                    }
                }
            }

            for(int i=0; i<aCnt; i++) {
                int r = arr[i][0];
                int c = arr[i][1];

                // 확산된 방향 개수
                int dCnt = 0;
                for(int d=0; d<4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // 공기청정기가 있거나 칸이 없으면 확산 안됨
                    if(!isWall(nr,nc) && grid[nr][nc] != -1) {
                        dCnt++;
                    }
                }

                int sum = grid[r][c];
                // 인접 방향으로 확산될 양 계산
                arr[i][2] = sum / 5;
                grid[r][c] = grid[r][c] - (sum/5) * dCnt;
            }

            // 확산될 양을 실제 인접 방향으로 전파
            // 양을 먼저 계산해놓고 실제로 옮기는걸 한번에
            for(int i=0; i<aCnt; i++) {
                int r = arr[i][0];
                int c = arr[i][1];
                int dustAmt = arr[i][2];

                for(int d=0; d<4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!isWall(nr,nc) && grid[nr][nc] != -1) {
                        grid[nr][nc] += dustAmt;
                    }
                }

            }

            // 2. 공기청정기 작동
            // 반시계 방향
            for(int i=UP-1; i>0; i--) {
                grid[i][0] = grid[i-1][0];
            }

            for(int i=0; i<C-1; i++) {
                grid[0][i] = grid[0][i+1];
            }

            for(int i=0; i<UP; i++) {
                grid[i][C-1] = grid[i+1][C-1];
            }

            for(int i=C-1; i>=2; i--) {
                grid[UP][i] = grid[UP][i-1];
            }

            grid[UP][1] = 0;

            for(int i=DOWN+1; i<R-1; i++) {
                grid[i][0] = grid[i+1][0];
            }

            for(int i=0; i<C-1; i++) {
                grid[R-1][i] = grid[R-1][i+1];
            }

            for(int i=R-1; i>=DOWN+1; i--) {
                grid[i][C-1] = grid[i-1][C-1];
            }

            for(int i=C-1; i>=2; i--) {
                grid[DOWN][i] = grid[DOWN][i-1];
            }

            grid[DOWN][1] = 0;

        }

        int fineDust = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(grid[i][j]>=1) {
                    fineDust += grid[i][j];
                }
            }
        }

        System.out.println(fineDust);


    }

}