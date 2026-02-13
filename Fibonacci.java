import java.util.*;
import java.io.*;

public class Fibonacci{
    static int[] zerocnt = new int[40];
    static int[] onecnt = new int[40];
    static boolean[] visited = new boolean[40];

    public static int fibonacci(int n){
        if(n == 0){
            zerocnt[0]++;
            visited[0] = true;
            return 0;
        }else if(n==1){
            onecnt[1]++;
            visited[1] = true;
            return 1;
        }else{
            if(visited[n - 1] && visited[n - 2]){
                zerocnt[n] = zerocnt[n - 2] + zerocnt[n - 1];
                onecnt[n] = onecnt[n - 2] + onecnt[n - 1];
                visited[n] = true;
                return 0;
            }
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            int cases = Integer.parseInt(br.readLine().trim());

            int fib = fibonacci(cases);

            System.out.println(zerocnt[cases] + " " + onecnt[cases]);
        }
    }
}