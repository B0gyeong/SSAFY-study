import java.util.*;
import java.io.*;

public class SolutionMintChocoMilk{
    static int R;
    static int C;
    static int T;
    static int[][] area;
    static int[][] temp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] purifierR;

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= R || c >= C) return true;
        return false;
    }

    public static void updateArea(){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                area[i][j] = temp[i][j];
            }
        }
    }

    public static void wind(){
        //
    }

    public static boolean spread(){
        temp = new int[R][C];

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                for(int d = 0; d < 4; d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    int spread = area[i][j]/5;
                    int spreadDir = 0;
                    if(!isWall(nr, nc)){
                        spreadDir++;
                        temp[nr][nc] += spread;
                    }
                    temp[i][j] = area[i][j] - spread * spreadDir;
                }
            }
        }

        wind();

        updateArea();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        area = new int[R][C];
        purifierR = new int[2];
        int p = 0;

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < C; j++){
                area[i][j] = Integer.parseInt(st.nextToken());
                if(area[i][j] == -1){
                    purifierR[p++] = i;
                }
            }
        }
    }
}