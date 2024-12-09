import java.io.*;
import java.util.*;

public class Day9P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day9Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long ans = 0;
        String line = br.readLine();
        int n = line.length();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = line.charAt(i) - '0';
        }

        ArrayList<Integer> qwe = new ArrayList<>();
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < arr[i]; j++) {
                if(i%2==0) {
                    qwe.add(idx);
                } else {
                    qwe.add(-1);
                }
            }
            if(i%2==0) {
                idx++;
            }
        }

        int l = 0;
        int r = qwe.size()-1;
        while(l < r) {
            while(l < r && qwe.get(l) != -1) l++;
            while(l < r && qwe.get(r) == -1) r--;
            if(l >= r) break;
            qwe.set(l, qwe.get(r));
            qwe.set(r, -1);
        }

        for(int i = 0; i < qwe.size(); i++) {
            if(qwe.get(i) == -1) break;
            ans += ((long)i) * qwe.get(i);
        }
        
        out.println(ans);
        out.close();
    }
}
