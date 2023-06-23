package src;

public class ComfortRoom extends StandardRoom {
    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money, int tickets) {
        ComfortRoom.money = money;
        ComfortRoom.tickets = tickets;
    }

    public void addMoney(int x, int k) {
        tickets += k;
        money += x;
    }
    public void addTickets(int k) {
        tickets += k;
    }

    private static int money;

    public static int getTickets() {
        return tickets;
    }

    public static void setTickets(int tickets) {
        ComfortRoom.tickets = tickets;
    }

    private static int tickets;
    ComfortRoom(String type, int[][] matrix) {
        super(type, matrix);
    }
}