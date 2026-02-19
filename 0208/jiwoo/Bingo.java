import java.util.*;
import java.io.*;

public class Bingo{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] bingo = new int[5][5];
        int[] host = new int[25];
        int curr = 0;
        int iIdx = 0;
        int jIdx = 0;
        boolean[][] checked = new boolean[5][5];
        int cnt = 0; //bingo count
        int tmp = 0; //checking each line

        for(int i = 0; i < 5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < 5; j++){
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < 5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < 5; j++){
                host[i * 5 + j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int h = 0; h < host.length; h++){
            curr = host[h];
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(bingo[i][j] == curr){
                        checked[i][j] = true;
                        iIdx = i;
                        jIdx = j;
                    }
                }
            }

            //check row
            for(int j = 0; j < 5; j++){
                if(checked[iIdx][j] == false){
                    break;
                }else{
                    tmp++;
                }
            }
            if(tmp == 5){
                cnt++;
            }
            tmp = 0;

            //check column
            for(int i = 0; i < 5; i++){
                if(checked[i][jIdx] == false){
                    break;
                }else{
                    tmp++;
                }
            }
            if(tmp == 5){
                cnt++;
            }
            tmp = 0;

            //check diagonal
            if(iIdx == jIdx){
                for(int i = 0; i < 5; i++){
                    if(checked[i][i] == false){
                        break;
                    }else{
                        tmp++;
                    }
                }
            }
            if(tmp == 5){
                cnt++;
            }
            tmp = 0;
            
            if(iIdx + jIdx == 4){
                for(int i = 0; i < 5; i++){
                    if(checked[i][4-i] == false){
                        break;
                    }else{
                        tmp++;
                    }
                }
            }
            if(tmp == 5){
                cnt++;
            }
            tmp = 0;

            if(cnt >= 3){
                System.out.println(h + 1);
                break;
            }
        }
    }
}