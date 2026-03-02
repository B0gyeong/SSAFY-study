import java.util.*;
import java.io.*;

public class SolutionInorder{
    static String[] value;
    static int[] left;
    static int[] right;

    public static void inorder(int node){
        if(left[node] > 0){
            inorder(left[node]);
        }
        System.out.print(value[node]);
        if(right[node] > 0){
            inorder(right[node]);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        int node = 0;

        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine().trim());
            value = new String[N + 1];
            left = new int[N + 1];
            right = new int[N + 1];
            
            //add nodes
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine().trim());

                node = Integer.parseInt(st.nextToken());
                value[node] = st.nextToken();

                if(st.hasMoreTokens()){
                    left[node] = Integer.parseInt(st.nextToken());
                    if(st.hasMoreTokens()){
                        right[node] = Integer.parseInt(st.nextToken());
                    }
                }
            }

            System.out.print("#" + test_case + " ");
            inorder(1);
            System.out.println();
        }
    }
}