package src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class CinemaList extends ObjectList<Сinema> {
    CinemaList() {
        try(Scanner scanner = new Scanner(new File("src\\cinemas.txt"))) {
            scanner.useDelimiter("\n");
            String buf;
            while (scanner.hasNext()) {
                buf = scanner.nextLine();
                if (scanner.hasNext()) {
                    int x = Integer.parseInt(scanner.nextLine());
                    Сinema obj = new Сinema(buf, scanner, x);
                    objects.add(obj);
                }
            }
            scanner.close();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected Сinema createObj(String buf, Scanner scanner) {
        return new Сinema(buf, scanner);
    }

    public void write() {
        write("cinemas.txt");
    }

    public Сinema addObj(Scanner terminal) {
        Сinema obj = new Сinema(terminal);
        objects.add(obj);
        return obj;
    }

    void delete(Сinema exactСinema) {
        objects.remove(exactСinema);
    }
}