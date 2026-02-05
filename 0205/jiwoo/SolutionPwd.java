import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class SolutionPwd{
    public static int checkExclude(char[] input, int[] exclude, int i, int exidx, int checked){
        int step = 1;
        while(i > checked || i < input.length){
            if(input[i - step] == input[i + step - 1]){
                exclude[exidx++] = i - step;
                exclude[exidx++] = i + step - 1;
                checked = i + step - 1;
                step++;
            }else{
                break;
            }
        }
        return checked + 1;
    }

    public static void main(String[] args) throws Exception{
        int T = 10;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            String inputStr = st.nextToken();

            char[] input = new char[inputStr.length()];
            int[] exclude = new int[input.length];
            Arrays.fill(exclude, -1);
            int exidx = 0;
            int checked = 0; //already checked to exclude

            char[] pwd = new char[input.length - (exclude.length / 2)];

            for(int i = 1; i < input.length; i++){
                char prev = input[i - 1];
                char curr = input[i];
                if(prev == curr){
                    System.out.println("curr: " + curr);
                    exclude[exidx++] = i - 1;
                    exclude[exidx++] = i;
                    i = checkExclude(input, exclude, i, exidx, checked); //returns the index of next one to check
                    checked = i - 1;
                }
            }

            System.out.print("#" + test_case + " ");
            int pwdIdx = 0;
            for(int i = 0; i < input.length; i++){
                int target = i;
                boolean excluded = IntStream.of(exclude).anyMatch(x -> x == target);
                if(!excluded){
                    pwd[pwdIdx] = input[i];
                    System.out.print(pwdIdx);
                    pwdIdx++;
                }
            }
            System.out.println();
        }
    }
}