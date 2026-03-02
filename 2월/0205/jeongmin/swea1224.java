
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * 계산기
 */
public class swea1224 {
    
    public static void main(String[] args) throws IOException {

         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         for(int t=1; t<=10; t++) {
            int N = Integer.parseInt(br.readLine());
            String line = br.readLine();

            Stack<Character> stack = new Stack<>(); 
            Stack<Integer> stack2 = new Stack<>();
            List<Character> arr = new ArrayList<>();

            // 후위표기식 변환
            for(int i=0; i<N; i++) {
                char cur = line.charAt(i);

                if(cur == '(') {
                    stack.push(cur);
                } else if(cur == '+' || cur =='-') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        char tmp = stack.peek();
                        if(tmp == '*' || tmp == '/' || tmp == '+' || tmp =='-') {
                            arr.add(stack.pop());
                        } else {
                            break;
                        }
                    }

                    stack.push(cur);
                } else if(cur == '*' || cur == '/') {
                    // 나보다 우선순위가 낮은 연산이면 push 
                    while(!stack.isEmpty() && stack.peek() != '(') {
                        char tmp = stack.peek();
                        if(tmp == '+' || tmp =='-') {
                            break;
                        } else {
                            tmp = stack.pop();
                            arr.add(tmp);
                        }
                    }

                    stack.push(cur);
                    // 나보다 낮은 연산이 나올때까지 pop
                    
                } else if(cur == ')') {

                    while(!stack.isEmpty()&&stack.peek()!='(') {
                        arr.add(stack.pop());
                    }

                    // ( 은 arr 에 넣지않음 
                    if(!stack.isEmpty()&&stack.peek()=='(') {
                        stack.pop();
                    }

                } else {
                    // 피연산자
                    arr.add(cur);
                }
            }

            // 스택비우기
            while(!stack.isEmpty()) {
                char tmp = stack.pop();
                if(tmp!='(') arr.add(tmp);
            }

            // 계산
            // 345+6*+7+
            for(int i=0; i<arr.size(); i++) {
                char tmp = arr.get(i);

                if(tmp == '+') {
                    int a = stack2.pop();
                    int b = stack2.pop();
                    int res = a + b;
                    stack2.push(res);

                } else if(tmp == '*') {
                    int a = stack2.pop();
                    int b = stack2.pop();
                    int res = a*b;
                    stack2.push(res);
                } 
                else {
                    int tmp2 = tmp - '0';
                    stack2.push(tmp2);
                }
            }

            int answer = stack2.pop();
            System.out.println("#"+t+" "+answer);
         }
    }
}
