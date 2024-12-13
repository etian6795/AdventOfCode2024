import java.io.*;

public class Day13P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day13Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        long ans = 0;
        while((line = br.readLine()) != null) {
            long[] A = AOCInterface.readLineAsLongs(line);
            line = br.readLine();
            long[] B = AOCInterface.readLineAsLongs(line);
            line = br.readLine();
            long[] P = AOCInterface.readLineAsLongs(line);
            br.readLine();

            long temp = Long.MAX_VALUE;
            for(int i = 0; i <= 100; i++) {
                for(int j = 0; j <= 100; j++) {
                    if(A[0]*i + B[0]*j == P[0] && A[1]*i + B[1]*j == P[1]) {
                        temp = Math.min(temp, i*3+j);
                    }
                }
            }
            if(temp != Long.MAX_VALUE) {
                ans += temp;
            }
        }
        
        out.println(ans);
        out.close();
    }
}