import java.util.*;
import java.io.*;

public class NM3 {
    static int N;
    static int M;
    static int[] result;
    static StringBuilder sb;

    public static void dupPerm(int depth){
        if(depth == M){
            for(int i = 0; i < M; i++){
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++){
            result[depth] = i;
            dupPerm(depth + 1);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[M];
        sb = new StringBuilder();

        dupPerm(0);

        String pstr = sb.toString();
        System.out.println(pstr);
    }
}
