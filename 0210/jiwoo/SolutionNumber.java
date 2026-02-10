import java.util.*;
import java.io.*;

public class SolutionNumber{
    static char[] ops = { '+', '-', '*', '/' };
    static int[] opsCnt = new int[4];
    static int[] numbers;
    static ArrayList<ArrayList<Integer>> opPer;
    static ArrayList<Integer> opTmp;
    static int N;

    public static void dfs(int n){
        if(n == N - 1){
            opPer.add(new ArrayList<>(opTmp));
            return;
        }

        for(int i = 0; i < 4; i++){
            if(opsCnt[i] > 0){
                opsCnt[i]--;
                opTmp.add(i);
                dfs(n + 1);
                opsCnt[i]++;
                opTmp.remove(opTmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            opPer = new ArrayList<ArrayList<Integer>>();
            opTmp = new ArrayList<Integer>();
            numbers = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < 4; i++){
                opsCnt[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < N; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0);

            int curr = numbers[0];
            int max = -1000000000;
            int min = 1000000000;

            for(ArrayList<Integer> opList : opPer){
                for(int i = 0; i < N - 1; i++){
                    int opIdx = opList.get(i);
                    int num = numbers[i + 1];
                    if(opIdx == 0){
                        curr += num;
                    }else if(opIdx == 1){
                        curr -= num;
                    }else if(opIdx == 2){
                        curr *= num;
                    }else{
                        curr /= num;
                    }
                }

                max = Math.max(curr, max);
                min = Math.min(curr, min);
                curr = numbers[0];
            }

            int result = max - min;

            System.out.println("#" + test_case + " " + result);
        }
    }
}