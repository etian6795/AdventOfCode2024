import java.io.*;

public class Day7P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day7Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        long ans = 0;
        while((line = br.readLine()) != null) {
            long[] arr = AOCInterface.readLineAsLongs(line);
            long res = arr[0];
            if(can(res, arr, 2, arr[1])) {
                ans += res;
            }
        }
        
        out.println(ans);
        out.close();
    }

    public static boolean can(long res, long[] arr, int idx, long curr) {
        if(idx == arr.length) {
            return curr == res;
        }
        long pow = 1;
        while(arr[idx]/pow > 0) pow *= 10;
        return can(res, arr, idx+1, curr + arr[idx]) || can(res, arr, idx+1, curr * arr[idx]);
    }
}
