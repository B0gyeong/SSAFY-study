import java.util.*;

public class Solution {
	static class Node {
		int k, time;
		Node(int k, int time){
			this.k = k; this.time = time;
		}
	}
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	int T = sc.nextInt();
    	for(int tc=1; tc<=T; tc++) {
    		int N = sc.nextInt();      // input
    		int M = sc.nextInt();
    		int K = sc.nextInt();
    		int A = sc.nextInt();
    		int B = sc.nextInt();
    		int ans = 0;
    		int [] Atime = new int [N];
    		int [] Btime = new int [M];
    		int [] Ktime = new int [K];
    		boolean [] visitedA = new boolean [K];
    		boolean [] visitedB = new boolean [K];
    		for(int i=0; i<N; i++) {
    			Atime[i] = sc.nextInt();
    		}
    		for(int i=0; i<M; i++) {
    			Btime[i] = sc.nextInt();
    		}
    		for(int i=0; i<K; i++) {
    			Ktime[i] = sc.nextInt();
    		}
    		
    		Node [] Atake = new Node [N];
    		Node [] Btake = new Node [M];
    		Queue <Integer> Await = new LinkedList<>();
    		Queue <Integer> Bwait = new LinkedList<>();
    		

    		boolean Kfinish = false;

    		while(true) {
    			boolean Afinish = true;
        		boolean Bfinish = true;
    			for(int i=0; i<K; i++) {
    				if(Ktime[i]==0) Await.add(i);
    				Ktime[i]--;
    			}
    			for(int i=0; i<K; i++) {
    				if(Ktime[i]>0) {
    					Kfinish=false;
    					break;
    				}
    				Kfinish=true; 
    			}
    			
    			for(int i=0; i<N; i++) {
    				if(Atake[i] != null && Atake[i].time == 0) {
    					Bwait.add(Atake[i].k);
    					Atake[i] = null;
    				}
    				if(Atake[i] == null && !Await.isEmpty()) {
    					Atake[i] = new Node(Await.poll(), Atime[i]-1);
    					Afinish = false;
    					if(i+1 == A) visitedA[Atake[i].k] = true;
    				} else if(Atake[i] != null) {
    					Atake[i].time--;
    					Afinish = false;
    				}
    			}
    			
    			
    			for(int i=0; i<M; i++) {
    				if(Btake[i] != null && Btake[i].time == 0) {
    					Btake[i] = null;
    				}
    				if(Btake[i] == null && !Bwait.isEmpty()) {
    					Btake[i] = new Node(Bwait.poll(), Btime[i]-1);
    					Bfinish = false;
    					if(i+1 == B) visitedB[Btake[i].k] = true;
    				} else if(Btake[i] != null) {
    					Btake[i].time--;
    					Bfinish = false;
    				}
    			}
    			
    			if(Kfinish && Afinish && Bfinish) break;
    		}
    		
    		for(int i=0; i<K; i++) {
    			if(visitedA[i] && visitedB[i]) {
    				ans+=(i+1);
    			}
    		}
    		
    		System.out.println("#"+tc+" "+(ans==0? -1:ans));
    	}
	}
}