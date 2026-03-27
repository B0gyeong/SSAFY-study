import java.util.*;
import java.io.*;

public class SolutionMicroDust{
    static int R;
    static int C;
    static int T;
    static int[][] area;
    static int[][] temp;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int[] ddr = {0, 1, 0, -1};
    static int[] ddc = {1, 0, -1, 0};
    static int[] purifierR;
    static int result;

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= R || c >= C) return true;
        return false;
    }

    public static void countSand(){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                result += area[i][j];
            }
        }
        result += 2; //air purifier -> 2 -1s
        return;
    }

    public static void updateArea(){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                area[i][j] = temp[i][j];
            }
        }
    }

    public static void wind(){
        int ur = purifierR[0];
        int uc = 1;
        int prev = 0;

        for(int d = 0; d < 4; d++){
            while(!isWall(ur, uc) || (area[ur][uc] != -1)){
                int tmp = area[ur][uc];
                temp[ur + dr[d]][uc + dc[d]] = tmp;
                temp[ur][uc] = prev;
                prev = tmp;
                ur = ur + dr[d];
                uc = uc + dc[d];
            }
        }

        int ur = purifierR[1];
        int uc = 1;
        int prev = 0;

        for(int d = 0; d < 4; d++){
            while(!isWall(ur, uc) || (area[ur][uc] != -1)){
                int tmp = area[ur][uc];
                temp[ur + ddr[d]][uc + ddc[d]] = tmp;
                temp[ur][uc] = prev;
                prev = tmp;
                ur = ur + ddr[d];
                uc = uc + ddc[d];
            }
        }
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
                    if(!(area[i][j] == -1)){
                        temp[i][j] = area[i][j] - spread * spreadDir;
                    }
                }
            }
        }

        updateArea();

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

        for(int i = 0; i < T; i++){
            spread();
        }

        countSand();
        System.out.println(result);
    }
}