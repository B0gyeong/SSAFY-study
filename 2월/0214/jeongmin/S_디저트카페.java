import java.io.*;
import java.util.*;
/**
 * 마름모 구현
 * */


public class S_디저트카페 {

    // 시계방향
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};

    static int[][] cafe;
    static int maxD; // 최대 디저트 개수
    static int N;

    public static void tryDiamond(int sr, int sc, int a, int b) {
        boolean[] D = new boolean[101]; // 디저트 종류 체크

        int r = sr, c = sc;
        D[cafe[r][c]] = true;

        int[] len = {a,b,a,b};

        for(int dir=0; dir<4; dir++) {
            for(int step=0; step<len[dir]; step++) {
                r += dr[dir]; //step만큼 반복
                c += dc[dir];

                if(r==sr&&c==sc&&dir==3&&step==len[3]-1) {
                    maxD = Math.max(maxD, 2*(a+b));
                    return;
                }
                if(r<0||r>=N||c<0||c>=N) return;
                if(D[cafe[r][c]]) return;
                D[cafe[r][c]] = true;
            }
        }

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            cafe = new int[N][N];
            maxD = -1;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    cafe[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 마름모는 두변의 길이 a,b만 정하면
            // dir 0, 2로 a칸, dir 1,3으로 b칸 가면 시작점으로 돌아옴
            // 모든 경우의 수를 다 해보자
            for(int sr=0; sr<N; sr++) {
                for(int sc=0; sc<N; sc++) {
                    for(int a=1; a<N; a++) {
                        for(int b=1; b<N; b++) {
                            tryDiamond(sr,sc,a,b);
                        }
                    }
                }
            }

            System.out.println("#"+t+" "+maxD);

        }
    }


}
