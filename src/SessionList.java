package src;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

class SessionList extends ObjectList<Session> {
    private CinemaList cinemaArr;
    private MovieList movieArr;
    @Override
    protected Session createObj(String buf, Scanner scanner) {
        return new Session(buf, scanner, cinemaArr, movieArr);
    }

    protected Session createObj(String buf, Scanner scanner, CinemaList cinemaArr, MovieList movieArr) {
        this.movieArr = movieArr;
        this.cinemaArr = cinemaArr;
        return createObj(buf, scanner);
    }

    public void write() {
        objects.get(0).writeAdmin();
        write("sessions.txt");
    }

    SessionList() {
        super();
    }

    SessionList(CinemaList cinemaArr, MovieList movieArr) {
        super();
        try(Scanner scanner = new Scanner(new File("src\\sessions.txt"))) {
            scanner.useDelimiter("\n");
            String buf;
            while (scanner.hasNext()) {
                buf = scanner.nextLine();
                if (scanner.hasNext()) {
                    Session obj = new Session(buf, scanner, cinemaArr, movieArr);
                    objects.add(obj);
                }
            }
            scanner.close();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public SessionList sessionNew(Date time1, Date time2, int clientBudget, int ticketsN) {
        SessionList sessionNew = new SessionList();
        for(int i = 0; i < objects.size(); i++) {
            Date sessionTime = objects.get(i).getSessionTime();
            if (time1.getTime() <= sessionTime.getTime() &&
                    time2.getTime() >= sessionTime.getTime() + objects.get(i).getSessionMovie().getDuration() * 60000 &&
                    objects.get(i).getSessionRoom().hasPlace(clientBudget, ticketsN))
                sessionNew.objects.add(objects.get(i));
        }
        return sessionNew;
    }

    public SessionList sessionNew(int x, int clientBudget, int ticketsN) {
        SessionList sessionNew = new SessionList();
        for(int i = 0; i < objects.size(); i++) {
            Session exactSession = objects.get(i);
            if (exactSession.getSessionRoom().hasPlace(x, ticketsN) && exactSession.getSessionRoom().hasPlace(clientBudget, ticketsN))
                sessionNew.objects.add(exactSession);
        }
        return sessionNew;
    }

    public SessionList sessionNew(String name, int clientBudget, int ticketsN) {
        SessionList sessionNew = new SessionList();
        for(int i = 0; i < objects.size(); i++) {
            Session exactSession = objects.get(i);
            if (name.equals(exactSession.getSessionMovie().getName()) && exactSession.getSessionRoom().hasPlace(clientBudget, ticketsN))
                sessionNew.objects.add(exactSession);
        }
        return sessionNew;
    }

    public String toString(char c) {
        String s = "";
        for(int i = 0; i < objects.size(); i++) {
            s += Integer.toString(i) + "\n";
            Session sessionObj = objects.get(i);
            s += sessionObj.toString(c);
            s += "\n";
        }
        return s;
    }
}