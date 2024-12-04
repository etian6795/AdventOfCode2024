import java.io.*;
import java.util.*;

public class Day4P2 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day4Input.txt"));

        String line;
        long ans = 0;
        ArrayList<char[]> grid = new ArrayList<>();
        while((line = br.readLine()) != null) {
            grid.add(line.toCharArray());
        }
        int m = grid.size();
        int n = grid.get(0).length;
        for(int i = 0; i < m-2; i++) {
            for(int j = 0; j < n-2; j++) {
                if((grid.get(i)[j] == 'M' && grid.get(i+1)[j+1] == 'A' && grid.get(i+2)[j+2] == 'S' || grid.get(i)[j] == 'S' && grid.get(i+1)[j+1] == 'A' && grid.get(i+2)[j+2] == 'M') 
                    && (grid.get(i+2)[j] == 'M' && grid.get(i+1)[j+1] == 'A' && grid.get(i)[j+2] == 'S' || grid.get(i+2)[j] == 'S' && grid.get(i+1)[j+1] == 'A' && grid.get(i)[j+2] == 'M')) {
                    ans++;
                }
            }
        }
        out.println(ans);
        out.close();
    }
}
