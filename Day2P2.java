import java.io.*;
import java.util.ArrayList;

public class Day2P2 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day2Input.txt"));

        String line;
        int ans = 0;
        while((line = br.readLine()) != null) {
            String[] arr = line.split(" ");
            int n = arr.length;
            ArrayList<Integer> qwe = new ArrayList<>();
            for(int i = 0; i < n; i++) qwe.add(Integer.valueOf(arr[i]));
            boolean inc = qwe.get(1) > qwe.get(0);
            boolean safe = true;
            for(int i = 1; i < n; i++) {
                if((inc ^ (qwe.get(i) > qwe.get(i-1))) || Math.abs(qwe.get(i) - qwe.get(i-1)) < 1 || Math.abs(qwe.get(i) - qwe.get(i-1)) > 3) {
                    safe = false;
                    break;
                }
            }
            if(safe) {
                ans++;
                continue;
            }
            for(int j = 0; j < n; j++) {
                int temp = qwe.remove(j);
                inc = qwe.get(1) > qwe.get(0);
                safe = true;
                for(int i = 1; i < n-1; i++) {
                    if((inc ^ (qwe.get(i) > qwe.get(i-1))) || Math.abs(qwe.get(i) - qwe.get(i-1)) < 1 || Math.abs(qwe.get(i) - qwe.get(i-1)) > 3) {
                        safe = false;
                        break;
                    }
                }
                if(safe) {
                    ans++;
                    break;
                }
                qwe.add(j, temp);
            }
        }
        out.println(ans);
        out.close();
    }
}
