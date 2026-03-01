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

            char[] stack = new char[N];
            int top = -1;
            for(int i = 0; i < N; i++){
                if(top == -1){
                    stack[++top] = inputStr.charAt(i);
                }else{
                    if(stack[top] == inputStr.charAt(i)){
                        stack[top--] = 0; // null -> pop
                    }else{
                        stack[++top] = inputStr.charAt(i);
                    }
                }
            }

            System.out.print("#" + test_case + " ");
            for(int i = 0; i <= top; i++){
                System.out.print(stack[i]);
            }
            System.out.println();
        }
    }
}