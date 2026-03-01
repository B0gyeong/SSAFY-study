import java.util.*;

public class Solution {
	static int N;
	static int cnt;
	static boolean [] selected;
	static int [] weights;
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	int T = sc.nextInt();
    	for(int tc=1; tc<=T; tc++) {
    		N = sc.nextInt();
    		cnt = 0;
    		weights = new int [N];
    		for(int i=0; i<N; i++) {
    			weights[i] = sc.nextInt();
    		}

    		selected = new boolean[N];
    		solve(0, 0, 0);;
    		System.out.println("#"+tc+" "+cnt);
    		
    	}
	}

	private static void solve(int idx, int leftSum, int rightSum) {
		if(idx == N) {
			cnt++;
			return;
		}
		
		int restSum = 0;
		int remainCount = 0;
	    for (int i = 0; i < N; i++) {
	        if (!selected[i]) {
	            restSum += weights[i];
	            remainCount++;
	        }
	    }

	    if (leftSum >= rightSum + restSum) {
	        cnt += (int) Math.pow(2, remainCount) * factorial(remainCount);
	        return;
	    }
		
		for(int i=0; i<N; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			
			solve(idx+1, leftSum+weights[i], rightSum);
			
			if(leftSum >= rightSum+weights[i]) {
				solve(idx+1, leftSum, rightSum+weights[i]);
			}
			
			selected[i] = false;
		}
		
	}
	private static int factorial(int n) {

		if(n==1) return 1;

		return n * factorial(n-1);

		}
}