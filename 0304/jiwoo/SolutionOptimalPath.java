import java.util.*;
import java.io.*;

public class SolutionOptimalPath{ //SWEA 1247
    static int N;
    static int[][] customers; // [customer][0]: x // [customer][1]: y
    static int[] company; // (company x, company y)
    static int[] home; //(home x, home y)
    static boolean[] visited;
    static int minPath;

    public static void calculate(int depth, int cusNum, int acc){
        if(minPath <= acc) return;

        if(depth == N){
            int goHome = Math.abs(home[0] - customers[cusNum][0]) + Math.abs(home[1] - customers[cusNum][1]);
            acc += goHome;
            minPath = Math.min(minPath, acc);
            return;
        }


        for(int i = 0; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                calculate(depth + 1, i, acc + Math.abs(customers[cusNum][0] - customers[i][0]) + Math.abs(customers[cusNum][1] - customers[i][1]));
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            company = new int[2];
            home = new int[2];
            customers = new int[N][2];
            visited = new boolean[N];
            minPath = Integer.MAX_VALUE;
            int fromCompany = 0;

            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            company[0] = Integer.parseInt(st.nextToken());
            company[1] = Integer.parseInt(st.nextToken());
            home[0] = Integer.parseInt(st.nextToken());
            home[1] = Integer.parseInt(st.nextToken());

            for(int i = 0; i < N; i++){
                customers[i][0] = Integer.parseInt(st.nextToken());
                customers[i][1] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < N; i++){
                visited[i] = true;
                fromCompany = Math.abs(company[0] - customers[i][0]) + Math.abs(company[1] - customers[i][1]);
                calculate(1, i, fromCompany);
                visited[i] = false;
            }

            System.out.println("#" + test_case + " " + minPath);
        }
    }
}