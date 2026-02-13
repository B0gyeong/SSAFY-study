import java.util.*;
import java.io.*;

public class SolutionBasicArithmetic{
    static String[] value;
    static int[] left;
    static int[] right;
    static BufferedReader br;
    static StringTokenizer st;

    public static void addNode(int n) throws Exception{
        st = new StringTokenizer(br.readLine().trim());
        int node = Integer.parseInt(st.nextToken());
        value[node] = st.nextToken();

        if(n == 1){
            return;
        }else{
            if(value[node].equals("+") || value[node].equals("-") || value[node].equals("*") || value[node].equals("/")){
                left[node] = Integer.parseInt(st.nextToken());
                right[node] = Integer.parseInt(st.nextToken());
            }
            addNode(n - 1);
        }
    }

    public static int postOrder(int n){
        int n1;
        int n2;

        if(left[n] == 0){
            return Integer.parseInt(value[n]);
        }else{
            n1 = postOrder(left[n]);
            n2 = postOrder(right[n]);
            if(value[n].equals("+")){
                return n1 + n2;
            }else if(value[n].equals("-")){
                return n1 - n2;
            }else if(value[n].equals("*")){
                return n1 * n2;
            }else{
                return n1 / n2;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));

        for(int test_case = 1; test_case <= 10; test_case++){
            int N = Integer.parseInt(br.readLine().trim());
            
            value = new String[N + 1];
            left = new int[N + 1];
            right = new int[N + 1];

            addNode(N);

            int result = postOrder(1); //put node number

            System.out.println("#" + test_case + " " + result);
        }
    }
}