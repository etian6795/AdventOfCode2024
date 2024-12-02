import java.io.*;
import java.lang.*;

public class Day2P1 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day2Input.txt"));

        String line;
        int ans = 0;
        while((line = br.readLine()) != null) {
            String[] arr = line.split(" ");
            int n = arr.length;
            int[] qwe = new int[n];
            for(int i = 0; i < n; i++) qwe[i] = Integer.parseInt(arr[i]);
            boolean inc = qwe[1] > qwe[0];
            boolean safe = true;
            for(int i = 1; i < n; i++) {
                if((inc ^ (qwe[i] > qwe[i-1])) || Math.abs(qwe[i] - qwe[i-1]) < 1 || Math.abs(qwe[i] - qwe[i-1]) > 3) {
                    safe = false;
                    break;
                }
            }
            if(safe) ans++;
        }
        out.println(ans);
        out.close();
    }
}
