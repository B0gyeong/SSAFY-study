import java.util.*;
import java.io.*;

public class SolutionWeirdMagnet {
    static int K;
    static int[][] magnet;
    static int[] rollM; //몇번 자석 돌릴지
    static int[] rollD; //그 자석 돌리는 방향
    static int[] red;
    static int totalScore;
    static int[] rollMagnets;
    static int[] rollDir;
    static int rollIdx;

    public static int callIdx(int idx){
        if(idx < 0) return 8 + idx;
        if(idx >= 8) return idx - 8;
        return idx;
    }

    public static void roll(int num, int dir){
        red[num] = red[num] - dir;
        red[num] = callIdx(red[num]);
    }

    public static void rollSpread(){
        rollIdx = 0;
        int m1r = callIdx(red[0] + 2);
        int m2l = callIdx(red[1] - 2);
        int m2r = callIdx(red[1] + 2);
        int m3l = callIdx(red[2] - 2);
        int m3r = callIdx(red[2] + 2);
        int m4l = callIdx(red[3] - 2);

        if(m1r == m2l && m2r == m3l && m3r == m4l) return;
        if(m1r != m2l){
            roll(0, rollDir[0]);
        }
        if(m2r != m3l){
            //roll()
        }
    }

    public static void move(int num, int dir){
        int right = callIdx(red[num] + 2);
        int left = callIdx(red[num] - 2);
        rollIdx = 0;
        rollMagnets[rollIdx] = num;
        rollDir[rollIdx++] = dir;
        
        if(num == 0){
            int rightM = callIdx(red[num + 1] - 2);
            if(magnet[num][right] != magnet[num + 1][rightM]){
                rollMagnets[rollIdx] = num + 1;
                rollDir[rollIdx++] = dir * (-1);
            }
        }else if(num == 3){
            int leftM = callIdx(red[num - 1] + 2);
            if(magnet[num][left] != magnet[num - 1][leftM]){
                rollMagnets[rollIdx] = num - 1;
                rollDir[rollIdx++] = dir * (-1);
            }
        }else{
            int rightM = callIdx(red[num + 1] - 2);
            int leftM = callIdx(red[num - 1] + 2);
            if(magnet[num][right] != magnet[num + 1][rightM]){
                rollMagnets[rollIdx] = num + 1;
                rollDir[rollIdx++] = dir * (-1);
            }
            if(magnet[num][left] != magnet[num - 1][leftM]){
                rollMagnets[rollIdx] = num - 1;
                rollDir[rollIdx++] = dir * (-1);
            }
        }

        for(int i = 0; i < rollIdx; i++){
            roll(rollMagnets[i], rollDir[i]);
        }
    }

    public static void calcScore(){
        for(int i = 0; i < 4; i++){
            totalScore += Math.round(Math.pow(2, magnet[i][red[i]]));
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int test_case = 1; test_case <= T; test_case++){
            K = Integer.parseInt(br.readLine().trim());
            magnet = new int[4][8];
            rollM = new int[K];
            rollD = new int[K];
            red = new int[4];
            rollMagnets = new int[4];
            rollDir = new int[4];

            StringTokenizer st;
            for(int i = 0; i < 4; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < 8; j++){
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine().trim());
                rollM[i] = Integer.parseInt(st.nextToken());
                rollD[i] = Integer.parseInt(st.nextToken());
            }

            totalScore = 0;

            for(int i = 0; i < K; i++){
                move(rollM[i] - 1, rollD[i]);
            }
            
            calcScore();

            System.out.println("#" + test_case + " " + totalScore);
        }
    }
}