
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1231_중위순회 {

    static class Node {
        int id;
        char val;
        Node left;
        Node right;
        public Node(int id, char val) {
            this.id = id;
            this.val = val;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t=1; t<=10; t++) {

            int N = Integer.parseInt(br.readLine());
            Node[] tree = new Node[N+1];

            StringTokenizer st;

            // 1. 문자, 숫자 같이 입력받기
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine()); // " 1 W 2 3 "

                int id = Integer.parseInt(st.nextToken()); // 1
                char val = st.nextToken().charAt(0); // "W" -> 'W'

                // 노드가 배열에 없으면 생성, 있으면 val만 업데이트
                if(tree[id] == null) {
                    tree[id] = new Node(id, val);
                } else {
                    tree[id].val = val;
                }

                Node current = tree[id];

                // 남은 토큰이 있는지 확인하면서 자식 연결
                if(st.hasMoreTokens()) {
                    int leftId = Integer.parseInt(st.nextToken());
                    // 트리와 연결
                    if(tree[leftId]==null) tree[leftId] = new Node(leftId, ' ');
                    current.left = tree[leftId];

                }

                if(st.hasMoreTokens()) {
                    int rightId = Integer.parseInt(st.nextToken());
                    // 트리와 연결
                    if(tree[rightId]==null) tree[rightId] = new Node(rightId, ' ');
                    current.right = tree[rightId];
                }
            }

            // 2. 중위순회 L -> V -> R
            System.out.print("#"+t+" ");
            inorder_traverse(tree[1]);

            System.out.println();
        }

    }

    public static void inorder_traverse(Node root) {
        if(root == null) {
            return;
        } else {
            inorder_traverse(root.left);
            // 방문
            System.out.print(root.val);
            inorder_traverse(root.right);
        }
    }

}
