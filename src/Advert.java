package src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Advert {
    private Date time[] = new Date[2];
    private String preMovie;
    private String preCinema;
    private String movieName;
    private int money = 0;
    private boolean l = true;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    Advert(Scanner terminal) {
        System.out.println("Введите название фильма перед которым будет реклама(если для любого фильма просто нажмите Enter)");
        preMovie = terminal.nextLine();
        System.out.println("Введите название кинотеатра в котором будет реклама(если для любого кинотеатра просто нажмите Enter)");
        preCinema = terminal.nextLine();
        System.out.println("Введите две временных точки, в которые попадает начало фильма через пробел(хх:хх хх:хх) или введите Enter");
        String s = terminal.nextLine();
        if (!s.equals("")) {
            l = false;
            String[] timeStr = s.split(" ");
            try {
                time[0] = formatter.parse(timeStr[0]);
                time[1] = formatter.parse(timeStr[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Введите название рекламируемого фильма");
        movieName = terminal.nextLine();
    }

    Advert(String s, String preMovie, String preCinema, String movieName, int money) {
        this.preMovie = preMovie;
        this.preCinema = preCinema;
        if (!s.equals("")) {
            l = false;
            String[] timeStr = s.split(" ");
            try {
                time[0] = formatter.parse(timeStr[0]);
                time[1] = formatter.parse(timeStr[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        this.movieName = movieName;
        this.money = money;
    }

    public void plusMoney(int x) {
        money += x;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getPreCinema() {
        return preCinema;
    }

    public String getPreMovie() {
        return preMovie;
    }

    public boolean getL() {
        return l;
    }

    public Date getTime0() {
        return time[0];
    }

    public Date getTime1() {
        return time[1];
    }

    public void show() {
        System.out.println("Нереальный фильм " + movieName + " ,успейте купить билет!");
    }
    public String toWrite() {
        String s = "";
        if (!l)
            s += formatter.format(time[0]) + " " + formatter.format(time[1]);
        s += "\n" + preMovie + "\n" + preCinema + "\n" + movieName + "\n" + money + "\n";
        return s;
    }

    @Override
    public String toString() {
        String s = "Временной диапазон начала фильма: ";
        if (!l) s += formatter.format(time[0]) + " " + formatter.format(time[1]);
        s += "\nФильм перед которым, покажут рекламу: " + preMovie + "\n" +
                "Кинотеатр, в котором покажут рекламу: " + preCinema + "\n" +
                "Рекламируемый фильм: " + movieName + "\n" +
                "Деньги, которые принесла реклама: " + money;
        return s;
    }
}