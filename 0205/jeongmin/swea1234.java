
import java.util.*;
import java.io.*;

public class swea1234 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t=1; t<=10; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String line = st.nextToken();

            int[] arr = new int[N];
            for(int i=0; i<N; i++) {
                arr[i] = line.charAt(i) - '0';
            }

            Stack<Integer> stack = new Stack<>();

            for(int i=0; i<N; i++) {
                if(stack.isEmpty()) {
                    stack.push(arr[i]);
                } else {
                    if(stack.peek()==arr[i]) {
                        stack.pop();
                    } else {
                        stack.push(arr[i]);
                    }
                }
            }


            System.out.print("#" + t + " ");
            for(int i=0; i<stack.size(); i++) {
                System.out.print(stack.get(i));
            }
            System.out.println();
        }


    }
}