import java.io.*;

public class Day22P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day22Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long ans = 0;
        String line;
        while((line = br.readLine()) != null) {
            long prev = Long.parseLong(line);
            for(int i = 0; i < 2000; i++) {
                long next = ((prev * 64) ^ prev) % 16777216;
                next = ((next / 32) ^ next) % 16777216;
                next = ((next * 2048) ^ next) % 16777216;
                prev = next;
            }
            ans += prev;
        }
        
        out.println(ans);
        out.close();
    }
}
