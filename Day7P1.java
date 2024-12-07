import java.io.*;

public class Day7P1 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day7Input.txt"));

        String line;
        long ans = 0;
        while((line = br.readLine()) != null) {
            String[] arr = line.split(" ");
            long res = Long.parseLong(arr[0].substring(0, arr[0].length()-1));
            if(can(res, arr, 2, Long.parseLong(arr[1]))) {
                ans += res;
            }
        }
        
        out.println(ans);
        out.close();
    }

    public static boolean can(long res, String[] arr, int idx, long curr) {
        if(idx == arr.length) {
            return curr == res;
        }
        return can(res, arr, idx+1, curr + Long.parseLong(arr[idx])) || can(res, arr, idx+1, curr * Long.parseLong(arr[idx]));
    }
}
