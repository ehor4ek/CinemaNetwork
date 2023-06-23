package src;

public class StandardRoom implements Room {
    private int[][] matrix;
    private String type;

    StandardRoom(String type, int[][] matrix) {
        this.type = type;
        this.matrix = matrix;
    }

    public void setPrice(int x) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != -1)
                    matrix[i][j] = x;
            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public boolean hasPlace(int x, int y) {
        int best[] = new int[(matrix.length)*(matrix[0].length)];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                best[i * (matrix[i].length) + j] = matrix[i][j];
            }
        }
//        for (int i = 0; i < matrix.length * matrix[0].length; i++) {
//            System.out.print(best[i] + " ");
//        }
//        System.out.println("");
        int minI = -1, minValue = 100000;
        for(int bestN = 0; bestN < y; bestN++) {
            for(int i = bestN; i < (matrix.length)*(matrix[0].length); i++) {
                if(best[i] != -1 && best[i] < minValue) {
                    minValue = best[i];
                    minI = i;
                }
            }
            if (minI != -1) {
                best[minI] = best[bestN];
                best[bestN] = minValue;
                minValue = 100000;
                minI = -1;
            } else return false;
        }
        int s = x;
        for (int i = 0; i < y; i++) {
//            System.out.print(best[i] + " ");
            if (best[i] == -1) return false;
            s -= best[i];
        }
//        System.out.println("");
        if (s >= 0)
            return true;
        return false;
    }

    public int buy (int i, int j) {
        int x = matrix[i][j];
        matrix[i][j] = -1;
        System.out.println(toString());
        return x;
    }

    @Override
    public String toString() {
        String s = type + "\n";
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                s += matrix[i][j];
                if (j != matrix[0].length - 1) s += " ";
            }
            s += "\n";
        }
        return s;
    }

    public String toString(char c) {
        if (c == 'f')
            return type + "\n" + toString();
        return toString();
    }

    public String toWrite() {
        return matrix.length + " " + matrix[0].length + "\n" + this.toString();
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money, int tickets) {
        StandardRoom.tickets = tickets;
        StandardRoom.money = money;
    }

    public void addMoney(int x, int k) {
        money += x; tickets += k;
    }

    private static int money;

    public static int getTickets() {
        return tickets;
    }

    public static void setTickets(int tickets) {
        StandardRoom.tickets = tickets;
    }

    private static int tickets;

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }
}