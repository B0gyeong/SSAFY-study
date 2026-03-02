import java.util.*;

public class Solution {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	for(int tc=1; tc<=10; tc++) {
    		int L = sc.nextInt();
    		String formula = sc.next();
    		Stack<Character> oper = new Stack<>();
    		Stack<Character> nums = new Stack<>();
    		
    		for(int i=0; i<L; i++) {
    			char cur = formula.charAt(i);
    			if(Character.isDigit(cur)) {
    				nums.push(cur);
    			} else {
    				if(cur == '*') {
    					while(!(oper.isEmpty() || oper.peek()=='+' || oper.peek()=='(')) {
    						nums.push(oper.pop());
    					}
    					oper.push(cur);
    				} else if(cur == '+' ) {
    					while(!(oper.isEmpty() || oper.peek()=='(')) {
    						nums.push(oper.pop());
    					}
    					oper.push(cur);
    				} else if(cur == '(') {
    					oper.push(cur);
    				} else if(cur == ')') {
    					while(!(oper.peek()=='(')) {
    						nums.push(oper.pop());
    					}
    					oper.pop();
    				}
    			}
    		}
    		Stack<Character> numsRev = new Stack<>();
    		int L1 = nums.size();
    		for(int i=0; i<L1; i++) {
    			numsRev.push(nums.pop());
    		}
    		Stack<Integer> finalSt = new Stack<>();
    		int L2 = numsRev.size();
    		for(int i=0; i<L2; i++) {
    			char c = numsRev.pop();
    			if(Character.isDigit(c)) {
    				int cNum = c - '0';
    				finalSt.push(cNum);
    			} else {
    				if(c=='+') {
    					int num2 = finalSt.pop();
    					int num1 = finalSt.pop();
    					finalSt.push(num1 + num2);
    				} else {
    					int num2 = finalSt.pop();
    					int num1 = finalSt.pop();
    					finalSt.push(num1 * num2);
    				}
    			}
    		}
    		System.out.println("#"+tc+" "+finalSt.pop());
    	}
    }	
}
