
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1232_사칙연산 {

    static class Node {
        int id; // 정점 번호
        int val; // 피연산자
        String op; // 연산자
        int left;
        int right;

        //숫자인 경우 생성자
        public Node(int id, int val) {
            this.id = id;
            this.val = val;
        }

        //연산자인 경우 생성자
        public Node(int id, String op, int left, int right) {
            this.id = id;
            this.op = op;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t=1; t<=10; t++) {
            int N = Integer.parseInt(br.readLine());
            Node[] tree = new Node[N+1];

            StringTokenizer st;
            // 1. 트리 입력받기
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int id = Integer.parseInt(st.nextToken()); // 정점 번호
                String val = st.nextToken(); // 연산자 or 숫자

                // 뒤에 토큰이 더 있다면 연산자 없다면 숫자
                if(st.hasMoreTokens()) {
                    int leftId = Integer.parseInt(st.nextToken());
                    int rightId = Integer.parseInt(st.nextToken());
                    tree[id] = new Node(id, val, leftId, rightId);

                } else {
                    tree[id] = new Node(id, Integer.parseInt(val));
                }
            }

            // 2. 후위순회를 통해 연산 계산
            int answer = 0;
            answer = preorder_traverse(tree, 1);
            System.out.println("#"+t+" "+answer);
        }
    }

    public static int preorder_traverse(Node[] tree, int idx) {
        Node node = tree[idx];

        // 연산자
        if(node.op == null) {
            return node.val;
        }

        // 피연산자
        int leftVal = preorder_traverse(tree, node.left);
        int rightVal = preorder_traverse(tree, node.right);

        // 계산결과 합치기
        if(node.op.equals("+"))  {
            return leftVal + rightVal;
        } else if(node.op.equals("-")) {
            return leftVal - rightVal;
        } else if(node.op.equals("*")) {
            return leftVal * rightVal;
        } else if(node.op.equals("/")) {
            return leftVal / rightVal;
        }

        return 0;
    }


}
