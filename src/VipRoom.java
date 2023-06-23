package src;

public class VipRoom extends StandardRoom {
    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money, int tickets) {
        VipRoom.money = money;
        VipRoom.tickets = tickets;
    }

    public void addMoney(int x, int k) {
        money += x; tickets += k;
    }
    private static int money;

    public static int getTickets() {
        return tickets;
    }

    public static void setTickets(int tickets) {
        VipRoom.tickets = tickets;
    }
    private static int tickets;
    VipRoom(String type, int[][] matrix) {
        super(type, matrix);
    }
}
