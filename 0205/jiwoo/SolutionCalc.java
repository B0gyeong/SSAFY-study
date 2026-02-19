import java.util.*;
import java.io.*;

public class SolutionCalc{
    public static void main(String[] args){
        int T = 10;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int len = Integer.parseInt(st.nextToken());
            String infix = st.nextToken();

            Stack<String> ops = new Stack<String>();
            char curr = 0;
            char top = 0;
            char[] postfix = new char[len];
            char[] numStack = new char[len];
            char[] calcStack = new char[len];
            int postfixIdx = 0;
            int numIdx = 0;

            for(int i = 0; i < len; i++){
                curr = infix.charAt(i);
                if(curr == '*' || curr = '/'){
                    if(ops.peek() == '+' || '-' || '(' || ops.empty){
                        ops.push(curr);
                    }else{
                        while(!ops.empty() && ops.peek() == '+' || '-' || '('){
                            postfix[postfixIdx++] = ops.pop();
                        }
                        ops.push(curr);
                    }
                }else if(curr == '+' || '-'){
                    if(ops.peek() == '(' || ops.empty){
                        ops.push(curr);
                    }else{
                        while(!ops.empty()){
                            postfix[postfixIdx++] = ops.pop();
                        }
                        ops.push(curr);
                    }
                }else if(curr == ')'){
                    while(ops.peek() == '('){
                        postfix[postfixIdx++] = ops.pop();
                    }
                    ops.push(curr);
                }else{
                    numStack[numIdx++] = curr;
                }
            }

            top = -1;
            for(int i = 0; i < len; i++){
                if(postfix[i] >= '0'){
                    calcStack[++top] = postfix[i];
                }else if(postfix[i] == '+'){
                    top--;
                    calcStack[top] = (char)(calcStack[top] + calcStack[top + 1] - '0');
                }else{
                    top--;
                    calcStack[top] = (char)(calcStack[top] * calcStack[top + 1] + '0');
                }
            }

            System.out.println("#" + test_case + " " + (int)(calcStack[top] - '0')); 
        }
    }
}