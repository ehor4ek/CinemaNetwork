package src;

import java.util.ArrayList;
import java.util.Scanner;

public class StandardСlient implements Сlient {

    private ArrayList<Ticket> tickets = new ArrayList<>();
    private String fullName;
    private String number;
    private String email;
    private int budget = 0;
    private int amount = 0;
    static private int people = 0;
    public static int getPeople() {
        return people;
    }

    public static void setPeople(int people) {
        StandardСlient.people = people;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    StandardСlient(String fullName, int n, Scanner scanner) {
        this.fullName = fullName;
        this.amount = n;
        number = scanner.nextLine();
        email = scanner.nextLine();
        budget = Integer.parseInt(scanner.nextLine());
        people++;
    }

    StandardСlient(String fullName, String email, String number) {
        this.fullName = fullName;
        this.number = number;
        this.email = email;
        people++;
    }

    public void changeBudget(Session exactSession, int price) {
        tickets.add(new Ticket(exactSession, price));
        minusBudget(price);
    }

    public void plusAmount() {
        amount++;
    }

    public String getName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
    public void minusBudget(int x) {
        budget -= x;
    }

    public void plusBudget(int x) {
        budget += x;
    }

    public String toWrite() {
        return fullName + "\n" + amount + "\n" +  number + "\n" + email + "\n" + budget + "\n";
    }

    @Override
    public String toString() {
        return "ФИО: " + fullName +
                "\nТелефон: " + number +
                "\nE-mail: " + email + "\nБюджет: " + budget + "\n";
    }

    public int buy(Session exactSession, int x) {
        changeBudget(exactSession, x);
        return x;
    }
}
