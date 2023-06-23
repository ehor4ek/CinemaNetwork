package src;
import java.util.ArrayList;
import java.util.Scanner;
public class Сinema implements Savable {
    private String name;
    private int amount = 0;
    private ArrayList<Room> cinemaRoom;
    private String location;
    private String[] formats;
    Сinema(Scanner terminal) {
        System.out.print("Введите название кинотеатра ");
        name = terminal.nextLine();
        System.out.print("Введите адрес ");
        location = terminal.nextLine();
        System.out.print("Введите форматы через пробел ");
        formats = terminal.nextLine().split(" ");
        System.out.print("Введите количество кинозалов ");
        int k = terminal.nextInt();
        cinemaRoom = new ArrayList<Room>(k);
        for(int i = 0; i < k; i++) {
            addRoom(terminal);
        }
    }
    Сinema(String name, Scanner scanner, int n) {
        scanner.useDelimiter("\n");
        cinemaRoom = new ArrayList<Room>(n);
        this.name = name;
        for(int k = 0; k < n; k++) {
            String[] nums = scanner.nextLine().split(" ");
            String type = scanner.nextLine();
            int rows = Integer.parseInt(nums[0]);
            int columns = Integer.parseInt(nums[1]);
            amount += rows * columns;
            int[][] matrix = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                nums = scanner.nextLine().split(" ");
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = Integer.parseInt(nums[j]);
                }
            }
            if (type.equals("VIP")) {
                cinemaRoom.add(new VipRoom(type, matrix));
            } else if (type.equals("IMAX")) {
                cinemaRoom.add(new ImaxRoom(type, matrix));
            } else if (type.equals("Комфорт")) {
                cinemaRoom.add(new ComfortRoom(type, matrix));
            } else {
                cinemaRoom.add(new StandardRoom(type, matrix));
            }
        }
        this.setLocation(scanner.nextLine());
        this.setFormats(scanner.nextLine().split(" "));
    }

    Сinema(String name, Scanner scanner) {
        this(name, scanner, Integer.parseInt(scanner.nextLine()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getCinemaRoom(String type) {
        for(int i = 0; i < cinemaRoom.size(); i++) {
            if (type.equals(cinemaRoom.get(i).getType()))
                return cinemaRoom.get(i);
        }
        return null;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getFormats() {
        return formats;
    }

    public void setFormats(String[] formats) {
        this.formats = formats;
    }

    public String toWrite() {
        String s = name + "\n" + cinemaRoom.size() + "\n";
        for(int i = 0; i < cinemaRoom.size(); i++) {
            s += cinemaRoom.get(i).toWrite();
        }
        s += location + "\n";
        for(int i = 0; i < formats.length; i++) {
            s += formats[i];
            if (i != formats.length - 1) s += " ";
        }
        s += "\n";
        return s;
    }

    @Override
    public String toString() {
        String s = "Название кинотеатра: " + name +
                "\nВместимость: " + amount +
                "\nАдрес: " + location +
                "\nФормат: ";
        for(int i = 0; i < formats.length; i++) {
            s += formats[i] + " ";
        }
        s += "\nБилеты:\n";
        for(int i = 0; i < cinemaRoom.size(); i++)
            s += cinemaRoom.get(i).toString();
        return s;
    }

    public String toString(String t) {
        String s = "";
        if (t.equals("s")) {
            s = "Название кинотеатра: " + name +
                    "\nВместимость: " + amount +
                    "\nАдрес: " + location +
                    "\nФормат: ";
            for (int i = 0; i < formats.length; i++) {
                s += formats[i] + " ";
            }
        }
        else {
            s = "Название кинотеатра: " + name +
                    "\nВместимость: " + amount +
                    "\nАдрес: " + location +
                    "\nФормат: ";
            for (int i = 0; i < formats.length; i++) {
                s += formats[i] + " ";
            }
            s += "\nБилеты:\n";
            s += getCinemaRoom(t).toString();
        }
        return s;
    }

    public void printRooms() {
        for(int i = 0; i < cinemaRoom.size(); i++) {
            System.out.print(i + "\n" + cinemaRoom.toString());
        }
    }

    public Room addRoom(Scanner terminal) {
        System.out.println("Введите тип кинозала");
        String type = terminal.nextLine();
        type = terminal.nextLine();
        System.out.print("Введите вместимость кинозала ");
        int rows = terminal.nextInt();
        int columns = terminal.nextInt();
        amount += rows * columns;
        System.out.print("Введите цену билета ");
        int price = terminal.nextInt();
        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = price;
            }
        }
        if (type.equals("VIP")) {
            VipRoom r = new VipRoom(type, matrix);
            cinemaRoom.add(r);
            return r;
        } else if (type.equals("IMAX")) {
            ImaxRoom r = new ImaxRoom(type, matrix);
            cinemaRoom.add(r);
            return r;
        } else if (type.equals("Комфорт")) {
            ComfortRoom r = new ComfortRoom(type, matrix);
            cinemaRoom.add(r);
            return r;
        } else {
            StandardRoom r = new StandardRoom(type, matrix);
            cinemaRoom.add(r);
            return r;
        }
    }

    public void delRoom(int i) {
        cinemaRoom.remove(i);
    }
    public Room getRoom(int x) {
        return cinemaRoom.get(x);
    }

    public int getCinemaRoomSize() {
        return cinemaRoom.size();
    }

    public void changeRoomType(int i, String type) {
        if (type.equals("VIP")) {
            cinemaRoom.set(i, new VipRoom(type, cinemaRoom.get(i).getMatrix()));
        } else if (type.equals("IMAX")) {
            cinemaRoom.set(i, new ImaxRoom(type, cinemaRoom.get(i).getMatrix()));
        } else if (type.equals("Комфорт")) {
            cinemaRoom.set(i, new ComfortRoom(type, cinemaRoom.get(i).getMatrix()));
        } else {
            cinemaRoom.set(i, new StandardRoom(type, cinemaRoom.get(i).getMatrix()));
        }
    }
}