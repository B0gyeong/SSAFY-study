import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 괄호 추가하기
 * */
public class B16637_괄호추가하기 {
	
	static int max=Integer.MIN_VALUE;
	static List<Integer> num;
	static List<Character> op;
	static int N;
	
	public static void main(String[] args) throws Exception{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = new ArrayList<>();
		op = new ArrayList<>();
		
		String line = br.readLine(); // 3+8*7-9*2 
		for(int i=0; i<line.length(); i++) {
			if(i%2==0) {
				//피연산자
				num.add(line.charAt(i)-'0');
			} else {
				//연산자
				op.add(line.charAt(i));
			}
		}
		
		// 수식이 주어졌을때 괄호를 추가해 만들수 있는 결과의 최댓값 구하기 (중첩X) 
		dfs(num.get(0), 0);
		
		System.out.println(max);
	}
	
	public static void dfs(int cur, int idx) {
		
		// basecase
		if(idx>=op.size()) {
			max = Math.max(max, cur);
			return;
		}
		
		// 그냥 계산
		int res1 = calculate(cur, num.get(idx+1), op.get(idx));
		dfs(res1, idx+1);
		
		// 다음 연산 괄호
		if(idx+1 < op.size()) {
			int tmp = calculate(num.get(idx+1), num.get(idx+2), op.get(idx+1));
			int res2 = calculate(cur, tmp, op.get(idx));
			dfs(res2, idx+2);
			
		}
		
	}
	
	public static int calculate(int a, int b, char op) {
		if(op=='+') {
			return a+b;
		} else if(op=='-') {
			return a-b;
		} else if(op=='*') {
			return a*b;
		} else {
			return a/b;
		}
	}
}
