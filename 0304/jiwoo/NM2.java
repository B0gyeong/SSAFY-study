import java.util.*;
import java.io.*;

public class NM2 {
    static int N;
    static int M;
    static int[] comb;

    public static void printArr(){
        for(int i = 0; i < M; i++){
            System.out.print(comb[i] + " ");
        }
        System.out.println();
    }

    public static void comb(int depth, int start){
        if(depth == M){
            printArr();
            return;
        }

        for(int i = start; i <= N; i++){
            comb[depth] = i;
            comb(depth + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        comb = new int[M];

        comb(0, 1);
    }
}
