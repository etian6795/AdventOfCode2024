import java.io.*;
import java.util.*;

public interface AOCInterface {
    public static long[] readLineAsLongs(String s) {
        ArrayList<Long> arr = new ArrayList<>();
        int idx = 0;
        int n = s.length();
        while(idx < n) {
            long curr = 0;
            while(idx < n && Character.isDigit(s.charAt(idx))) {
                curr = curr * 10 + (s.charAt(idx)-'0');
                idx++;
            }
            arr.add(curr);
            while(idx < n && !Character.isDigit(s.charAt(idx))) {
                idx++;
            }
        }
        long[] res = new long[arr.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = arr.get(i);
        }
        return res;
    }

    public static char[][] readInputAsGrid(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<char[]> arr = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null) {
            arr.add(line.toCharArray());
        }
        char[][] res = new char[arr.size()][arr.get(0).length];
        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[0].length; j++) {
                res[i][j] = arr.get(i)[j];
            }
        }
        return res;
    }
}
