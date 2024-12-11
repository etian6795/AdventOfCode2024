import java.io.*;
import java.util.*;

public class Day11P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day11Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        ArrayList<Long> arr = new ArrayList<>();
        while((line = br.readLine()) != null) {
            long[] temp = AOCInterface.readLineAsLongs(line);
            for(long x : temp) arr.add(x);
        }

        for(int i = 0; i < 25; i++) {
            int idx = 0;
            while(idx < arr.size()) {
                int len = String.valueOf(arr.get(idx)).length();
                if(arr.get(idx) == 0) {
                    arr.set(idx, 1l);
                    idx++;
                } else if(len% 2 == 0) {
                    long num = arr.get(idx);
                    arr.set(idx, Long.valueOf(String.valueOf(num).substring(0, len/2)));
                    arr.add(idx+1, Long.valueOf(String.valueOf(num).substring(len/2)));
                    idx+=2;
                } else {
                    arr.set(idx, arr.get(idx) * 2024);
                    idx++;
                }
            }
        }
        
        out.println(arr.size());
        out.close();
    }
}
