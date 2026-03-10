import java.util.*;
import java.io.*;

public class SolutionGNS {
    static int N;
    static String[] numStr = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
    static HashMap<String, Integer> sToi;
    static String[] inputStr;
    static int[] inputNum;
    static int[] cntArr;
    static int[] sortedNum;
    static String[] outputStr;
    static StringBuilder sb;

    public static void sort(){
        for(int i = 0; i < N; i++){
            cntArr[inputNum[i]]++;
        }
        int idx = 0;
        for(int i = 0; i < N; i++){
            if(cntArr[idx] > 0){
                sortedNum[i] = idx;
                cntArr[idx]--;
            }else{
                idx++;
                sortedNum[i] = idx;
                cntArr[idx]--;
            }
        }
    }

    public static void arrange(){
        for(int i = 0; i < N; i++){
            inputNum[i] = sToi.get(inputStr[i]);
        }
    }

    public static void rearrange(){
        for(int i = 0; i < N; i++){
            outputStr[i] = numStr[sortedNum[i]];
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        sToi = new HashMap<String, Integer>() {{put("ZRO", 0); put("ONE", 1); put("TWO", 2); put("THR", 3); put("FOR", 4); put("FIV", 5); put("SIX", 6); put("SVN", 7); put("EGT", 8); put("NIN", 9);}};

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            String tc = st.nextToken();
            N = Integer.parseInt(st.nextToken());
            sb = new StringBuilder();

            inputStr = new String[N];
            inputNum = new int[N];
            outputStr = new String[N];
            sortedNum = new int[N];
            cntArr = new int[10];

            st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < N; i++){
                inputStr[i] = st.nextToken();
            }

            arrange();
            sort();
            rearrange();

            for(int i = 0; i < N; i++){
                sb.append(outputStr[i] + " ");
            }
            System.out.println("#" + test_case);
            System.out.println(sb.toString());
        }
    }
}
