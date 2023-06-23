package src;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Session implements Savable {

    private static String pass;
    private Сinema sessionСinema;
    private Room sessionRoom;
    private Movie sessionMovie;
    private Date sessionTime;
    private String sessionRoomType;
    private static int tickets = 0;
    private static int money = 0;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    private int rows, columns;

    public static boolean okPass(String p) {
        return pass.equals(p);
    }

    public static void setPass(String p) {
        pass = p;
    }
    public Room getSessionRoom() {
        return sessionRoom;
    }

    public void setSessionRoom(Room sessionRoom) {
        this.sessionRoom = sessionRoom;
    }
    Session(String sessionTime, Scanner scanner, CinemaList cinemaArr, MovieList movieArr) {
        try {
            this.sessionTime = formatter.parse(sessionTime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        sessionСinema = cinemaArr.getObj(scanner.nextLine());
        sessionMovie = movieArr.getObj(scanner.nextLine());
        String[] nums = scanner.nextLine().split(" ");
        rows = Integer.parseInt(nums[0]);
        columns = Integer.parseInt(nums[1]);
        int[][] matrix = new int[rows][columns];
        sessionRoomType = scanner.nextLine();
        for (int i = 0; i < rows; i++) {
            nums = scanner.nextLine().split(" ");
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Integer.parseInt(nums[j]);
            }
        }
        if (sessionRoomType.equals("VIP")) {
            sessionRoom = new VipRoom(sessionRoomType, matrix);
        } else if (sessionRoomType.equals("IMAX")) {
            sessionRoom = new ImaxRoom(sessionRoomType, matrix);
        } else if (sessionRoomType.equals("Комфорт")) {
            sessionRoom = new ComfortRoom(sessionRoomType, matrix);
        } else {
            sessionRoom = new StandardRoom(sessionRoomType, matrix);
        }
    }
    public static int getTickets() {
        return tickets;
    }

    public static void setTickets(int tickets) {
        Session.tickets = tickets;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Session.money = money;
    }
    public Сinema getSessionCinema() {
        return sessionСinema;
    }

    public void setSessionCinema(Сinema sessionСinema) {
        this.sessionСinema = sessionСinema;
    }

    public Movie getSessionMovie() {
        return sessionMovie;
    }

    public void setSessionMovie(Movie sessionMovie) {
        this.sessionMovie = sessionMovie;
    }

    public Date getSessionTime() {
        return sessionTime;
    }
    public String matrixToString() {
        return sessionRoom.toString();
    }

    public void setSessionTime(String sessionTime) {
        try {
            this.sessionTime = formatter.parse(sessionTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String toWrite() {
        return formatter.format(sessionTime) + "\n" + sessionСinema.getName() + "\n" + sessionMovie.getName() + "\n" + sessionRoom.getRows() + " " + sessionRoom.getColumns() +"\n" + sessionRoom.toString();
    }

    public int buy(int i, int j, Сlient exactСlient) {
        tickets++;
        int price = exactСlient.buy(this, sessionRoom.buy(i - 1, j - 1));
        sessionRoom.addMoney(price, 1);
        money += price;
        return price;
    }

    @Override
    public String toString() {
        String s = sessionСinema.toString("s") + "\n" + sessionRoom.getRows() + " " + sessionRoom.getColumns() + "\n" + sessionRoom.toString() + "\n";
        s += sessionMovie.toString();
        s += "Время: " + formatter.format(sessionTime) + "\n";
        return s;
    }

    public String toString(char c) {
        String s = "";
        switch (c) {
            case ('f'):
                s += "Кинотеатр:\n" + sessionСinema.getName();
                s += "Фильм:\n" + sessionMovie.getName();
                s += "Время: " + formatter.format(sessionTime) + "\n";
                return s;
            default:
                s += "Кинотеатр: " + sessionСinema.getName() + "\n";
                s += "Фильм: " + sessionMovie.getName() + "\n";
                s += "Время: " + formatter.format(sessionTime) + "\n";
                s += "Зал: " + sessionRoom.toString() + "\n";
                return s;
        }
    }

    public String getName() {
        return sessionСinema.toString() + "\n"
                + sessionMovie.toString() + "\n"
                + formatter.format(sessionTime) + "\n";
    }

    public void writeAdmin() {
        try (FileWriter fw = new FileWriter("src\\admin.txt")) {
            fw.write(tickets + "\n");
            fw.write(money + "\n");
            fw.write(pass + "\n");
            fw.write("4\n");
            fw.write("Стандарт " + StandardRoom.getMoney() + " " + StandardRoom.getTickets() + "\n");
            fw.write("Комфорт " + ComfortRoom.getMoney() + " " + ComfortRoom.getTickets() + "\n");
            fw.write("VIP " + VipRoom.getMoney() + " " + VipRoom.getTickets() + "\n");
            fw.write("IMAX " + ImaxRoom.getMoney() + " " + ImaxRoom.getTickets());
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}