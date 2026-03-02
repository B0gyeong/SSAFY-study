import java.util.*;
import java.io.*;

public class Solution{
    public static boolean checkLeft(int i, int[][] a){
        if(i < 0) return false;
        return true;
    }

    public static boolean checkRight(int i, int[][] a){
        if(i > a.length) return false;
        return true;
    }

    public static boolean checkTop(int j, int[][] a){
        if(j < 0) return false;
        return true;
    }

    public static boolean checkBottom(int j, int[][] a){
        if(j > a.length) return false;
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine().trim());
            int[][] a = new int[N][N];

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    a[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int startRoom = a[0][0];
            int maxCnt = 0;

            int currRoom = a[0][0];
            int cnt = 1;
            boolean end = false;

            for(int r = 0; r < N; r++){
                for(int c = 0; c < N; c++){
                    currRoom = a[r][c];
                    System.out.println("starting from: " + currRoom);
                    int i = r;
                    int j = c;
                    while(!end){
                        System.out.println("i: " + i + ", j: " + j + ", " + currRoom + ", cnt: " + cnt);
                        if(checkLeft(i-1, a)){
                            if(a[i-1][j] == currRoom + 1){
                                System.out.println("i-1");
                                currRoom = a[i - 1][j];
                                cnt++;
                            }
                            i--;
                        }
                        
                        if(checkRight(i+1,a)){
                            if(a[i+1][j] == currRoom + 1){
                                System.out.println("i+1");
                                currRoom = a[i + 1][j];
                                cnt++;
                            }
                            i++;
                        }
                        
                        if(checkTop(j - 1,a)){
                            System.out.println("j-1");
                            if(a[i][j-1] == currRoom + 1){
                                currRoom = a[i][j - 1];
                                cnt++;
                            }
                            j--;
                        }
                        
                        if(checkBottom(j + 1,a)){
                            System.out.println("j+1");
                            if(a[i][j+1] == currRoom + 1){
                                currRoom = a[i][j + 1];
                                cnt++;
                            }
                            j++;
                        }else{
                            end = true;
                            break;
                        }
                    }

                    if(cnt > maxCnt){
                        maxCnt = cnt;
                        startRoom = a[r][c];
                    }
                    cnt = 1;
                    end = false;
                }
            }

            System.out.println("#" + test_case + " " + startRoom + " " + maxCnt);
        }
    }
}