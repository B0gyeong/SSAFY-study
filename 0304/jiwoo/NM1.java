import java.util.*;
import java.io.*;

public class NM1 {
    static boolean[] visited;
    static int N;
    static int M;
    static int[] permArr;
    static int[] original;

    public static void printArr(){
        for(int i = 0; i < M; i++){
            System.out.print(permArr[i] + " ");
        }
        System.out.println();
    }

    public static void perm(int depth){
        if(depth == M){
            printArr();
            return;
        }else{
            for(int i = 0; i < N; i++){
                if(visited[i]) continue;
                visited[i] = true;
                permArr[depth] = original[i];
                perm(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        permArr = new int[M];
        original = new int[N];

        for(int i = 1; i <= N; i++){
            original[i - 1] = i;
        }

        perm(0);
    }
}
