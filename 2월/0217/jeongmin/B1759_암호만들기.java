import java.io.*;
import java.util.*;

public class B1759_암호만들기 {

    static int L;
    static int C;
    static char[] arr;
    static char[] sel;

    public static boolean isVowel(char ch) {
        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u') {
            return true;
        } else return false;
    }

    public static void dfs(int idx, int start) {
        if(idx==L) {
            int vCnt = 0;
            int cCnt = 0;
            for(int i=0; i<sel.length; i++) {
                if(isVowel(sel[i])) vCnt++;
                else cCnt++;
            }
            if(vCnt>=1&&cCnt>=2) {
                System.out.println(new String(sel));
            }
            return;
        }

        // 조합
        for(int i=start; i<C; i++) {

            sel[idx] = arr[i];
            dfs(idx+1, i+1);

        }

    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        sel = new char[L];

        dfs(0, 0);
    }
}