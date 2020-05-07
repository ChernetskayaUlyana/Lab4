import java.util.Scanner;
import java.util.Vector;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static int arraymin(int[] array, boolean[] vis){
        int n = 0;
        while(n < vis.length - 1 && vis[n]) n++;
        int a = array[n];

        for(int i = n + 1; i < array.length; i++){
            if(array[i] != -1 && array[i]< a && !vis[i]){
                a = array[i];
                n = i;
            }
        }

        return n;
    }

    public static void main(String[] args){

        int[][] g;
        boolean[] vis;
        int[] w;


        System.out.print("Введите количество вершин графа: ");
        int n = in.nextInt();
        g = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                g[i][j] = -1;
        System.out.print("Введите количество дуг в графе: ");
        int m = in.nextInt();
        System.out.println("Введите список смежности:");
        for(int i = 0; i < m; i++){
            int a;
            int b;
            a = in.nextInt();
            b = in.nextInt();
            g[a-1][b-1] = in.nextInt();
        }
        System.out.println("Введите из какой вершину в какую необходимо добраться:");
        int st = in.nextInt() - 1;
        int f = in.nextInt() - 1;

        vis = new boolean[n];
        w = new int[n];
        Vector<Integer>[] s = new Vector[n];
        for(int i = 0; i < n; i++){
            s[i] = new Vector<Integer>();
        }
        for(int i = 0; i < n; i++){
            vis[i] = false;
            w[i] = -1;
        }
        w[st] = 0;

        int flag = 0;
        while(flag < n){
            flag++;
            vis[st] = true;
            for(int i = 0; i < n; i++){
                if(g[st][i] != -1 && !vis[i]){
                    if(w[i] != -1){
                        if(w[i] > w[st] + g[st][i]) {
                            w[i] = w[st] + g[st][i];
                            s[i] = new Vector<Integer>(s[st]);
                            s[i].add(st+1);
                        }
                    }
                    else{
                        w[i] = g[st][i] + w[st];
                        s[i] = new Vector<Integer>(s[st]);
                        s[i].add(st+1);
                    }
                }
            }
            st = arraymin(w, vis);
        }

        if(w[f] != -1) {
            System.out.print("Минимальный путь: " + w[f] + "\nПуть перемещения: ");
            for(int i = 0; i < s[f].size(); i++) System.out.print(s[f].elementAt(i) + "->" );
            System.out.println(f+1);
        }
        else System.out.println("Невозможно добраться до этой вершины.");
    }
}
