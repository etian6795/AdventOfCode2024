import java.io.*;
import java.util.*;

public class Day22P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day22Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long ans = 0;
        String line;
        ArrayList<long[][]> arr = new ArrayList<>();
        while((line = br.readLine()) != null) {
            long prev = Long.parseLong(line);
            long[][] temp = new long[2001][2];
            temp[0][0] = prev%10;
            for(int i = 0; i < 2000; i++) {
                long next = ((prev * 64) ^ prev) % 16777216;
                next = ((next / 32) ^ next) % 16777216;
                next = ((next * 2048) ^ next) % 16777216;
                prev = next;
                temp[i+1][0] = next%10;
            }
            arr.add(temp);
        }

        for(long[][] x : arr) {
            for(int i = 2000; i > 0; i--) {
                x[i][1] = x[i][0] - x[i-1][0];
            }
        }

        HashMap<Quad, Long> map = new HashMap<>();
        for(long[][] x : arr) {
            HashSet<Quad> seen = new HashSet<>();
            for(int i = 1; i < 2001 - 3; i++) {
                Quad q = new Quad(x[i][1], x[i+1][1], x[i+2][1], x[i+3][1]);
                if(!seen.contains(q)) {
                    map.put(q, map.getOrDefault(q, 0l) + x[i+3][0]);
                    seen.add(q);
                }
            }
        }
        for(long x : map.values()) {
            ans = Math.max(ans, x);
        }
        
        out.println(ans);
        out.close();
    }
}
