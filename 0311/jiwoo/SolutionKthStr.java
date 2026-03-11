import java.util.*;
import java.io.*;

public class SolutionKthStr {
    static int K;
    static String inputStr;
    static String outputStr;
    static int offset;
    static String[] suffix;
    static int[] SA; //suffix array

    public static void split(){

    }

    public static void arrange(){

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            K = Integer.parseInt(br.readLine().trim());
            inputStr = br.readLine().trim();
            outputStr = "none";
            offset = 0;
            SA = new int[inputStr.length()];
            suffix = new String[inputStr.length()];

            makeSuffix();
            sort();
            calLCP();
            NewSubCnt();
            findKSub();
            findExactSub();

            System.out.println("#" + test_case + " " + outputStr);
        }
    }
}
