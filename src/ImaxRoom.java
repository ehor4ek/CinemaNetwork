package src;

public class ImaxRoom extends StandardRoom {
    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money, int k) {
        ImaxRoom.money = money;
        ImaxRoom.tickets += k;
    }

    public void addMoney(int x) {
        money += x;
    }
    public void addTickets(int k) {
        tickets += k;
    }

    private static int money;
    private static int tickets;

    public static int getTickets() {
        return tickets;
    }

    public static void setTickets(int tickets) {
        ImaxRoom.tickets = tickets;
    }

    ImaxRoom(String type, int[][] matrix) {
        super(type, matrix);
    }
}
