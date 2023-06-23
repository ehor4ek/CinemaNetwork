package src;

import java.util.Scanner;

public class FriendСlient extends StandardСlient {
    private static int friend = 0;
    public static int getFriend() {
        return friend;
    }

    public static void setFriend(int friend) {
        FriendСlient.friend = friend;
    }

    FriendСlient(String fullName, int n, Scanner scanner) {
        super(fullName, n, scanner);
        friend++;
    }

    FriendСlient(String fullName, String email, String number) {
        super(fullName, email, number);
        friend++;
    }

    public int buy(Session exactSession, int x) {
        x = (int)(Math.round(x * 0.9));
        changeBudget(exactSession, x);
        return x;
    }
}
