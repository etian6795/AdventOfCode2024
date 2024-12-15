import java.io.*;
import java.util.*;

public class Day15P2 implements AOCInterface {
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
        char[][] grid = new char[qwerty.size()][qwerty.get(0).length*2];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j+=2) {
                if(qwerty.get(i)[j/2] == 'O') {
                    grid[i][j] = '[';
                    grid[i][j+1] = ']';
                } else {
                    grid[i][j] = qwerty.get(i)[j/2];
                    grid[i][j+1] = qwerty.get(i)[j/2];
                }
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
                    break;
                }
            }
        }
        grid[r][c] = '.';
        grid[r][c+1] = '.';
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
                while(grid[r][nc] == '[' || grid[r][nc] == ']') {
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
                while(grid[r][nc] == '[' || grid[r][nc] == ']') {
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
                if(grid[r-1][c] == '.') {
                    r--;
                    continue;
                } else if(grid[r-1][c] == '#') {
                    continue;
                }
                Queue<Point> q = new LinkedList<>();
                TreeSet<Point> seen = new TreeSet<>((a, b) -> (b.x == a.x ? Long.compare(b.y, a.y) : Long.compare(a.x, b.x)));
                q.add(new Point(r-1, c));
                seen.add(new Point(r-1, c));
                if(grid[r-1][c] == '[') {
                    q.add(new Point(r-1, c+1));
                    seen.add(new Point(r-1, c+1));
                } else if(grid[r-1][c] == ']') {
                    q.add(new Point(r-1, c-1));
                    seen.add(new Point(r-1, c-1));
                } 
                
                boolean blocked = false;
                while(!q.isEmpty() && !blocked) {
                    Point curr = q.remove();
                    if(grid[(int)curr.x-1][(int)curr.y] == '[') {
                        if(!seen.contains(new Point(curr.x-1, curr.y))) {
                            q.add(new Point(curr.x-1, curr.y));
                            seen.add(new Point(curr.x-1, curr.y));
                        }
                        if(!seen.contains(new Point(curr.x-1, curr.y+1))) {
                            q.add(new Point(curr.x-1, curr.y+1));
                            seen.add(new Point(curr.x-1, curr.y+1));
                        }
                    } else if(grid[(int)curr.x-1][(int)curr.y] == ']') {
                        if(!seen.contains(new Point(curr.x-1, curr.y))) {
                            q.add(new Point(curr.x-1, curr.y));
                            seen.add(new Point(curr.x-1, curr.y));
                        } 
                        if(!seen.contains(new Point(curr.x-1, curr.y-1))) {
                            q.add(new Point(curr.x-1, curr.y-1));
                            seen.add(new Point(curr.x-1, curr.y-1));
                        }
                    } else if(grid[(int)curr.x-1][(int)curr.y] == '.') {

                    } else if(grid[(int)curr.x-1][(int)curr.y] == '#') {
                        blocked = true;
                    }
                }
                if(blocked) continue;
                for(Point p : seen) {
                    grid[(int)p.x-1][(int)p.y] = grid[(int)p.x][(int)p.y];
                    grid[(int)p.x][(int)p.y] = '.';
                }
                r--;
            } else if(move == 'v') {
                if(grid[r+1][c] == '.') {
                    r++;
                    continue;
                } else if(grid[r+1][c] == '#') {
                    continue;
                }
                Queue<Point> q = new LinkedList<>();
                TreeSet<Point> seen = new TreeSet<>((a, b) -> (b.x == a.x ? Long.compare(b.y, a.y) : Long.compare(b.x, a.x)));
                q.add(new Point(r+1, c));
                seen.add(new Point(r+1, c));
                if(grid[r+1][c] == '[') {
                    q.add(new Point(r+1, c+1));
                    seen.add(new Point(r+1, c+1));
                } else if(grid[r+1][c] == ']') {
                    q.add(new Point(r+1, c-1));
                    seen.add(new Point(r+1, c-1));
                } 
                
                boolean blocked = false;
                while(!q.isEmpty() && !blocked) {
                    Point curr = q.remove();
                    if(grid[(int)curr.x+1][(int)curr.y] == '[') {
                        if(!seen.contains(new Point(curr.x+1, curr.y))) {
                            q.add(new Point(curr.x+1, curr.y));
                            seen.add(new Point(curr.x+1, curr.y));
                        }
                        if(!seen.contains(new Point(curr.x+1, curr.y+1))) {
                            q.add(new Point(curr.x+1, curr.y+1));
                            seen.add(new Point(curr.x+1, curr.y+1));
                        }
                    } else if(grid[(int)curr.x+1][(int)curr.y] == ']') {
                        if(!seen.contains(new Point(curr.x+1, curr.y))) {
                            q.add(new Point(curr.x+1, curr.y));
                            seen.add(new Point(curr.x+1, curr.y));
                        } 
                        if(!seen.contains(new Point(curr.x+1, curr.y-1))) {
                            q.add(new Point(curr.x+1, curr.y-1));
                            seen.add(new Point(curr.x+1, curr.y-1));
                        }
                    } else if(grid[(int)curr.x+1][(int)curr.y] == '.') {

                    } else if(grid[(int)curr.x+1][(int)curr.y] == '#') {
                        blocked = true;
                    }
                }
                if(blocked) continue;
                for(Point p : seen) {
                    grid[(int)p.x+1][(int)p.y] = grid[(int)p.x][(int)p.y];
                    grid[(int)p.x][(int)p.y] = '.';
                }
                r++;
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '[') {
                    ans += 100 * (i) + j;
                }
            }
        }

        out.println(ans);
        out.close();
    }
}
