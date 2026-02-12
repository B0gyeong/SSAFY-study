
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1248_공통조상 {

    static class Node{
        int parent;
        int left;
        int right;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V1 = Integer.parseInt(st.nextToken());
            int V2 = Integer.parseInt(st.nextToken());
            Node[] tree = new Node[V+1];
            for(int i=1; i<=V; i++) {
                tree[i] = new Node();
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                tree[child].parent = parent;
                if(tree[parent].left == 0) tree[parent].left = child;
                else tree[parent].right = child;
            }

            // 공통 조상 찾기
            int index = 1;
            index = getParent(tree, V1, V2, V);

            // 서브트리 크기 구하기 (DFS)
            int subTree = 0;
            subTree = getSubTree(tree, index);

            System.out.println("#"+t+" "+index+" "+subTree);

        }
    }

    public static int getParent(Node[] tree, int v1, int v2, int V) {
        boolean[] visited = new boolean[V+1];

        int curr = v1;
        // 루트의 부모는 0 -> 더이상 위로 갈곳 없음
        while(curr!=0) {
            visited[curr] = true;
            curr = tree[curr].parent; // 부모로 이동
        }

        curr = v2;
        int lca = 0;
        while(curr != 0) {
            // base case
            if(visited[curr]) {
                lca = curr;
                break;
            }
            curr = tree[curr].parent;
        }

        return lca;
    }

    public static int getSubTree(Node[] tree, int idx) {
        // 자식이 없으면 크기는 0
        if(idx==0) return 0;

        Node curr = tree[idx];

        return 1 + getSubTree(tree, curr.left) + getSubTree(tree, curr.right);
    }
}
