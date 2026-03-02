import java.util.*;

public class pass {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	for(int tc=1; tc<=10; tc++) {
    		int N = sc.nextInt();
    		String nums = sc.next();
    		Stack<Integer> st = new Stack<>();
    		for (int i = 0; i < N; i++) {
                int current = nums.charAt(i) - '0';

                if (!st.isEmpty() && st.peek() == current) {
                    st.pop();
                } else {
                    st.push(current); 
                }
            }
    		Stack<Integer> ans = new Stack<>();
    		System.out.print("#"+tc+" ");
    		while(!st.isEmpty()) {
    			ans.push(st.pop());
    		}
    		while(!ans.isEmpty()) {
    			System.out.print(ans.pop());
    		}
    		System.out.println();
    	}
    }	
}
