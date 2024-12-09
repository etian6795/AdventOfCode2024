import java.io.*;
import java.util.*;

public class Day9P2 implements AOCInterface {
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
        
        ArrayList<int[]> qwe = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            qwe.add(new int[]{i/2, arr[i], i%2});
        }
        for(int i = qwe.size()-1; i >= 0; i--) {
            if(qwe.get(i)[2] == 1) continue;
            int l = 0;
            while(l < i && !(qwe.get(l)[2] == 1 && qwe.get(l)[1] >= qwe.get(i)[1])) {
                l++;
            }
            if(l >= i) continue;
            qwe.add(l, new int[]{qwe.get(i)[0], qwe.get(i)[1], qwe.get(i)[2]});
            qwe.get(l+1)[1] -= qwe.get(l)[1];
            i++;
            qwe.get(i)[2] = 1;
        }
        long idx = 0;
        for(int i = 0; i < qwe.size(); i++) {
            if(qwe.get(i)[2] == 1) {
                idx += qwe.get(i)[1];
            } else {
                for(int j = 0; j < qwe.get(i)[1]; j++) {
                    ans += idx * qwe.get(i)[0];
                    idx++;
                }
            }
        }

        out.println(ans);
        out.close();
    }
}
