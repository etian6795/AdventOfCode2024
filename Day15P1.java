import java.io.*;
import java.util.*;

public class Day15P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day15Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        ArrayList<char[]> qwerty = new ArrayList<>();
        String line;
        while(!(line = br.readLine()).equals("")) {
            qwerty.add(line.toCharArray());
        }
        char[][] grid = new char[qwerty.size()][qwerty.get(0).length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                grid[i][j] = qwerty.get(i)[j];
            }
        }
        int m = grid.length;
        int n = grid[0].length;
        long ans = 0;
        int r = 0;
        int c = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '@') {
                    r = i;
                    c = j;
                }
            }
        }
        grid[r][c] = '.';
        ArrayList<char[]> asdfg = new ArrayList<>();
        while((line = br.readLine()) != null) {
            asdfg.add(line.toCharArray());
        }
        char[] moves = new char[asdfg.size() * asdfg.get(0).length];
        int idx = 0;
        for(int i = 0; i < asdfg.size(); i++) {
            for(int j = 0; j < asdfg.get(0).length; j++) {
                moves[idx++] = asdfg.get(i)[j];
            }
        }

        for(char move : moves) {
            if(move == '<') {
                int nc = c-1;
                while(grid[r][nc] == 'O') {
                    nc--;
                }
                if(grid[r][nc] == '#') {
                    continue;
                } else {
                    while(nc < c) {
                        grid[r][nc] = grid[r][nc+1];
                        nc++;
                    }
                    grid[r][nc] = '.';
                    c--;
                }
            } else if(move == '>') {
                int nc = c+1;
                while(grid[r][nc] == 'O') {
                    nc++;
                }
                if(grid[r][nc] == '#') {
                    continue;
                } else {
                    while(c < nc) {
                        grid[r][nc] = grid[r][nc-1];
                        nc--;
                    }
                    grid[r][nc] = '.';
                    c++;
                }
            }  else if(move == '^') {
                int nr = r-1;
                while(grid[nr][c] == 'O') {
                    nr--;
                }
                if(grid[nr][c] == '#') {
                    continue;
                } else {
                    while(nr < r) {
                        grid[nr][c] = grid[nr+1][c];
                        nr++;
                    }
                    grid[nr][c] = '.';
                    r--;
                }
            } else if(move == 'v') {
                int nr = r+1;
                while(grid[nr][c] == 'O') {
                    nr++;
                }
                if(grid[nr][c] == '#') {
                    continue;
                } else {
                    while(r < nr) {
                        grid[nr][c] = grid[nr-1][c];
                        nr--;
                    }
                    grid[nr][c] = '.';
                    r++;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'O') {
                    ans += 100 * (i) + j;
                }
            }
        }
        
        out.println(ans);
        out.close();
    }
}
