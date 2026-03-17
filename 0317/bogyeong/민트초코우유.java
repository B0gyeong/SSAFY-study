import java.io.*;
import java.util.*;

public class Solution {
    static int N, T;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int map[][];
    static Person pietyMap[][];

    static class Person {
        boolean defenseState;
        int piety;
        Person(int piety) {
            this.piety = piety;
            this.defenseState = false;
        }
    }

    static class Group implements Comparable<Group> {
        List<Node> childrens;
        Node represent;
        int fanFood;

        Group(List<Node> childrens, Node represent, int fanFood) {
            this.childrens = childrens;
            this.represent = represent;
            this.fanFood = fanFood;
        }

        // 우선순위 카테고리: 단일(1,2,4) -> 이중(3,5,6) -> 삼중(7)
        private int getCategory(int f) {
            if (f == 1 || f == 2 || f == 4) return 1;
            if (f == 3 || f == 5 || f == 6) return 2;
            if (f == 7) return 3;
            return 4;
        }

        @Override
        public int compareTo(Group o) {
            int c1 = this.getCategory(this.fanFood);
            int c2 = o.getCategory(o.fanFood);
            if (c1 != c2) return Integer.compare(c1, c2);

            int p1 = pietyMap[this.represent.r][this.represent.c].piety;
            int p2 = pietyMap[o.represent.r][o.represent.c].piety;
            if (p1 != p2) return Integer.compare(p2, p1);

            if (this.represent.r != o.represent.r)
                return Integer.compare(this.represent.r, o.represent.r);
            return Integer.compare(this.represent.c, o.represent.c);
        }
    }

    static class Node {
        int r, c;
        Node(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            String line = br.readLine(); 
            for (int c = 0; c < N; c++) {
                char C = line.charAt(c);
                if (C == 'M') map[r][c] = 1;      // 우유
                else if (C == 'C') map[r][c] = 2; // 초코
                else if (C == 'T') map[r][c] = 4; // 민트
            }
        }

        pietyMap = new Person[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                pietyMap[r][c] = new Person(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < T; i++) {
            morning();
            PriorityQueue<Group> groups = lunch();
            dinner(groups);

            // 출력용 합계: 7, 6, 5, 3, 1, 2, 4 순서
            int[] order = {7, 6, 5, 3, 1, 2, 4};
            long[] sums = new long[8];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    sums[map[r][c]] += pietyMap[r][c].piety;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int foodType : order) sb.append(sums[foodType]).append(" ");
            System.out.println(sb.toString().trim());
        }
    }

    private static void morning() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                pietyMap[r][c].piety++;
                pietyMap[r][c].defenseState = false;
            }
        }
    }

    private static PriorityQueue<Group> lunch() {
        boolean[][] visited = new boolean[N][N];
        List<Group> tempList = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) continue;

                List<Node> children = new ArrayList<>();
                Queue<Node> q = new LinkedList<>();
                q.add(new Node(r, c));
                visited[r][c] = true;
                int currentFood = map[r][c];
                Node rep = new Node(r, c);

                while (!q.isEmpty()) {
                    Node curr = q.poll();
                    children.add(curr);

                    if (pietyMap[curr.r][curr.c].piety > pietyMap[rep.r][rep.c].piety) {
                        rep = curr;
                    } else if (pietyMap[curr.r][curr.c].piety == pietyMap[rep.r][rep.c].piety) {
                        if (curr.r < rep.r || (curr.r == rep.r && curr.c < rep.c)) {
                            rep = curr;
                        }
                    }

                    for (int d = 0; d < 4; d++) {
                        int nr = curr.r + dr[d], nc = curr.c + dc[d];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
                        if (map[nr][nc] == currentFood) {
                            visited[nr][nc] = true;
                            q.add(new Node(nr, nc));
                        }
                    }
                }
                tempList.add(new Group(children, rep, currentFood));
            }
        }

        // 신앙심 업데이트
        for (Group g : tempList) {
            int moveAmount = g.childrens.size() - 1;
            for (Node node : g.childrens) {
                if (node.r == g.represent.r && node.c == g.represent.c) {
                    pietyMap[node.r][node.c].piety += moveAmount;
                } else {
                    pietyMap[node.r][node.c].piety -= 1;
                }
            }
        }

        PriorityQueue<Group> pq = new PriorityQueue<>();
        pq.addAll(tempList);
        return pq;
    }

    private static void dinner(PriorityQueue<Group> groups) {
        while (!groups.isEmpty()) {
            Group curr = groups.poll();
            Node rep = curr.represent;

            // 방어 상태면 전파 불가
            if (pietyMap[rep.r][rep.c].defenseState) continue;

            int x = pietyMap[rep.r][rep.c].piety - 1;
            if (x <= 0) continue;

            int dir = pietyMap[rep.r][rep.c].piety % 4;
            pietyMap[rep.r][rep.c].piety = 1; // 전파자는 1만 남음

            int nr = rep.r, nc = rep.c;
            while (x > 0) {
                nr += dr[dir];
                nc += dc[dir];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                if (map[nr][nc] == curr.fanFood) continue;

                int y = pietyMap[nr][nc].piety;
                // 전파를 시도하는 대상이 누구든(강한/약한) 즉시 방어 상태가 됨
                pietyMap[nr][nc].defenseState = true; 

                if (x > y) { // 강한 전파
                    map[nr][nc] = curr.fanFood;
                    pietyMap[nr][nc].piety += 1;
                    x -= (y + 1);
                } else { // 약한 전파
                    map[nr][nc] |= curr.fanFood;
                    pietyMap[nr][nc].piety += x;
                    x = 0;
                }
            }
        }
    }
}