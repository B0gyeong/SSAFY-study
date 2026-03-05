import java.util.*;
import java.io.*;

public class SolutionPrize { //swea 1244
    static String original;
    static int[] origArr;
    static int cnt;
    static int maxVal;
    static boolean[][] visited;

    public static void swap(int i, int j){
        origArr[i] = origArr[i] ^ origArr[j];
        origArr[j] = origArr[i] ^ origArr[j];
        origArr[i] = origArr[i] ^ origArr[j];
    }

    public static int calculate(){
        int result = 0;

        for(int i = original.length() - 1; i >= 0; i--){
            result += origArr[i] * Math.round(Math.pow(10.0, (double)(original.length() - i - 1)));
        }

        return result;
    }

    public static void update(int depth){
        if(depth == cnt){
            int curr = calculate();
            if(maxVal < curr) maxVal = curr;
            return;
        }

        int curr = calculate();
        if(visited[curr][depth]) return;
        visited[curr][depth] = true;

        for(int i = 0; i < original.length() - 1; i++){
            for(int j = i + 1; j < original.length(); j++){
                swap(i, j);
                curr = calculate(); 
                update(depth + 1);
                swap(i, j);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            maxVal = 0;

            original = st.nextToken();
            cnt = Integer.parseInt(st.nextToken());
            origArr = new int[original.length()];
            visited = new boolean[1000000][10];

            for(int i = 0; i < original.length(); i++){
                origArr[i] = original.charAt(i) - '0';
            }

            update(0);
            System.out.println("#" + test_case + " " + maxVal);
        }
    }
}
