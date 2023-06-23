package src;

import java.util.Scanner;

public class VipСlient extends StandardСlient {
    private static int vip = 0;
    public static int getVip() {
        return vip;
    }

    public static void setVip(int vip) {
        VipСlient.vip = vip;
    }
    VipСlient(String fullName, int n, Scanner scanner) {
        super(fullName, n, scanner);
        vip++;
    }

    VipСlient(String fullName, String email, String number) {
        super(fullName, email, number);
        vip++;
    }

    public int buy(Session exactSession, int x) {
        x = (int)(Math.round(x * 0.8));
        changeBudget(exactSession, x);
        System.out.println("Бесплатный напиток");
        return x;
    }
}