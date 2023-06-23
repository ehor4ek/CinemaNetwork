package src;

import java.util.Scanner;
public class Movie implements Savable {
    private String name;
    private int year;
    private String jenre;
    private int duration;
    private String[] formats;
    Movie(String name, Scanner scanner) {
        this.name = name;
        year = Integer.parseInt(scanner.nextLine());
        jenre = scanner.nextLine();
        duration = Integer.parseInt(scanner.nextLine());
        formats = scanner.nextLine().split(" ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getJenre() {
        return jenre;
    }

    public void setJenre(String jenre) {
        this.jenre = jenre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String[] getFormats() {
        return formats;
    }

    public void setFormats(String[] formats) {
        this.formats = formats;
    }

    public String toWrite() {
        String s = name + "\n" + year + "\n" + jenre + "\n" + duration + "\n";
        for (int i = 0; i < formats.length; i++) {
            s += formats[i];
            if (i != formats.length - 1) s += " ";
        }
        s += "\n";
        return s;
    }

    @Override
    public String toString() {
        String s = "Название фильма: " + name +
                "\nГод: " + year +
                "\nЖанр: " + jenre +
                "\nДлительность: " + duration +
                "\nФормат: ";
        for (int i = 0; i < formats.length; i++) {
            s += formats[i] + " ";
        }
        s += "\n";
        return s;
    }
}
