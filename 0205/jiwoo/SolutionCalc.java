import java.util.*;
import java.io.*;

public class SolutionCalc{
    public static void main(String[] args){
        int T = 10;
        BufferedReader br = new BufferedReader(InputStreamReader(System.in));

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.nextLine().trim());
            int len = Integer.parseInt(st.nextToken());
            String infix = st.nextToken();

            Stack<String> ops = new Stack<String>();
            char curr = '';
            char top = '';
            char[] postfix = new char[len];

            for(int i = 0; i < len; i++){
                curr = infix.charAt(i);
                if(curr == '*' || curr = '/'){
                    if(ops.peek() == '+' || '-' || '(' || ops.empty){
                        ops.push(curr);
                    }else{
                        while(!ops.empty() && ops.peek() == '+' || '-' || '('){
                            postfix[i++] = ops.pop();
                        }
                        ops.push(curr);
                    }
                }else if(curr == '+' || '-'){
                    if(ops.peek() == '(' || ops.empty){
                        ops.push(curr);
                    }else{
                        while(!ops.empty()){
                            postfix[i++] = ops.pop();
                        }
                        ops.push(curr);
                    }
                }else if(curr == ')'){
                    while(ops.peek() == '('){
                        postfix[i++] = ops.pop();
                    }
                    ops.push(curr);
                }else{
                    postfix[i++] = curr;
                }
            }
        }
    }
}