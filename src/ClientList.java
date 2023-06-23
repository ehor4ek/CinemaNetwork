package src;

import java.util.Scanner;

class ClientList extends ObjectList<Сlient> {
    ClientList() {
        super("clients.txt");
    }

    @Override
    protected Сlient createObj(String buf, Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        if (n > 7) {
            return new VipСlient(buf, n, scanner);
        } else if (n > 3) {
            return new FriendСlient(buf, n, scanner);
        } else {
            return new StandardСlient(buf, n, scanner);
        }
    }


    public void write() {
        write("clients.txt");
    }

    public Сlient getObj(String email, String number) {
        for(int i = 0; i < objects.size(); i++) {
            Сlient exactСlient = objects.get(i);
            if (exactСlient.getEmail().equals(email) && exactСlient.getNumber().equals(number))
                return exactСlient;
        }
        return null;
    }
}
