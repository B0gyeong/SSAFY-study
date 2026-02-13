import java.util.*;

public class 공통조상 {
	static int cnt;
	static int [][] tree;
	
    public static void main(String[] args) throws Exception {
    	Scanner sc = new Scanner(System.in);
    	int T = sc.nextInt();
    	for(int tc=1; tc<=T; tc++) {
    		int V = sc.nextInt();
    		int E = sc.nextInt();
    		int node1 = sc.nextInt(); int node2 = sc.nextInt();

				// 열을 3개를 두어서 양쪽 자식과 부모까지 기록
    		tree = new int [V+1][3];
    		for(int i=0; i<E; i++) {
    			int from = sc.nextInt();
    			int to = sc.nextInt();
    			if(tree[from][0]==0)  tree[from][0] = to;
    			else tree[from][1] = to;
    			tree[to][2] = from;
    		}
    		
    		ArrayList<Integer> node1Parents = new ArrayList<>();  // 조상이 몇 개일지 모르니 ArrayList로 구현
    		ArrayList<Integer> node2Parents = new ArrayList<>();

    		int node1P = tree[node1][2]; int node2P = tree[node2][2];
    		while(node1P != 1) {  // 루트가 아닌 조상만 리스트에 추가 (루트는 초기화 값으로 세팅)
					node1Parents.add(node1P);
    			node1P = tree[node1P][2];
    		}
    		while(node2P != 1) {
					node2Parents.add(node2P);
    			node2P = tree[node2P][2];
    		}

				// 공통 조상을 찾기 위해서는 뒤집어서 뒤에서부터 비교해야함 
    		Collections.reverse(node1Parents); 
    		Collections.reverse(node2Parents);
    		int sameParent = 1;    // 모든 노드의 조상인 1로 초기화하여 1말고 공통 조상이 없어도 1이 나오도록 하였음
    		for(int i=0; i<Math.min(node1Parents.size(), node2Parents.size()); i++) {
    			if(node1Parents.get(i).equals(node2Parents.get(i))) {  // 리스트의 integer 객체는 ==말고 equals로 비교하여야 함
    				sameParent = node1Parents.get(i);
    			} else break;
    		}
    		
    		cnt = 0;
    		traverse(sameParent);  // 순회는 계수만 세면 되기 때문에 어떤 순회든 상관 없음
    		
    		System.out.println("#" + tc + " " + sameParent+ " " + cnt);
    	}
    }

	private static void traverse(int v) {
		if(v != 0) {
			cnt++;
			traverse(tree[v][0]);
			traverse(tree[v][1]);
		}
	}
}
