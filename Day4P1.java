import java.io.*;
import java.util.*;

public class Day4P1 {
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
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n-3; j++) {
                if(grid.get(i)[j] == 'X' && grid.get(i)[j+1] == 'M' && grid.get(i)[j+2] == 'A' && grid.get(i)[j+3] == 'S') {
                    ans++;
                }
                if(grid.get(i)[j] == 'S' && grid.get(i)[j+1] == 'A' && grid.get(i)[j+2] == 'M' && grid.get(i)[j+3] == 'X') {
                    ans++;
                }
            }
        }
        for(int i = 0; i < m-3; i++) {
            for(int j = 0; j < n; j++) {
                if(grid.get(i)[j] == 'X' && grid.get(i+1)[j] == 'M' && grid.get(i+2)[j] == 'A' && grid.get(i+3)[j] == 'S') {
                    ans++;
                }
                if(grid.get(i)[j] == 'S' && grid.get(i+1)[j] == 'A' && grid.get(i+2)[j] == 'M' && grid.get(i+3)[j] == 'X') {
                    ans++;
                }
            }
        }
        for(int i = 0; i < m-3; i++) {
            for(int j = 0; j < n-3; j++) {
                if(grid.get(i)[j] == 'X' && grid.get(i+1)[j+1] == 'M' && grid.get(i+2)[j+2] == 'A' && grid.get(i+3)[j+3] == 'S') {
                    ans++;
                }
                if(grid.get(i)[j] == 'S' && grid.get(i+1)[j+1] == 'A' && grid.get(i+2)[j+2] == 'M' && grid.get(i+3)[j+3] == 'X') {
                    ans++;
                }
            }
        }
        for(int i = 0; i < m-3; i++) {
            for(int j = 0; j < n-3; j++) {
                if(grid.get(i+3)[j] == 'X' && grid.get(i+2)[j+1] == 'M' && grid.get(i+1)[j+2] == 'A' && grid.get(i)[j+3] == 'S') {
                    ans++;
                }
                if(grid.get(i+3)[j] == 'S' && grid.get(i+2)[j+1] == 'A' && grid.get(i+1)[j+2] == 'M' && grid.get(i)[j+3] == 'X') {
                    ans++;
                }
            }
        }
        out.println(ans);
        out.close();
    }
}
