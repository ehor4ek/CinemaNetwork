package src;

import java.util.ArrayList;

interface Сlient extends Savable {
    void changeBudget(Session exactSession, int price);
    int buy(Session exactSession, int x);
    String getName();
    void setFullName(String fullName);
    String getNumber();
    void setNumber(String number);

    String getEmail();

    void setEmail(String email);

    int getBudget();

    void setBudget(int budget);

    void minusBudget(int x);

    void plusBudget(int x);

    String toWrite();

    String toString();
    void plusAmount();
    ArrayList<Ticket> getTickets();
}