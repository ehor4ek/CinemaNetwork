package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Marketolog {
    private String pass;
    private int money = 0;
    private ArrayList<Advert> adverts;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    Marketolog() {
        try(Scanner scanner = new Scanner(new File("src\\marketolog.txt"))) {
            scanner.useDelimiter("\n");
            pass = scanner.nextLine();
            money = Integer.parseInt(scanner.nextLine());
            int n = Integer.parseInt(scanner.nextLine());
            String times, preMovie, preCinema, movieName;
            int mon;
            adverts = new ArrayList<Advert>(n);
            for(int i = 0; i < n; i++) {
                times = scanner.nextLine();
                preMovie = scanner.nextLine();
                preCinema = scanner.nextLine();
                movieName = scanner.nextLine();
                mon = Integer.parseInt(scanner.nextLine());
                adverts.add(new Advert(times, preMovie, preCinema, movieName, mon));
            }
            scanner.close();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean okPass(String p) {
        return pass.equals(p);
    }

    public void addAdvert(Scanner terminal) {
        adverts.add(new Advert(terminal));
    }

    public void delAdv(int i) {
        adverts.remove(i);
    }

    public void addAdv(Scanner terminal) {
        adverts.add(new Advert(terminal));
    }

    public void add(int x) {
        money += x;
    }

    public void show(Session session) {
        for(int i = 0; i < adverts.size(); i++) {
            Advert ad = adverts.get(i);
            if(( ad.getL() ||
                    ((session.getSessionTime().getTime() < ad.getTime1().getTime())
                            && (session.getSessionTime().getTime() > ad.getTime0().getTime()))) &&
                    (ad.getPreMovie().equals("") || ad.getPreMovie().equals(session.getSessionMovie().getName())) &&
                    (ad.getPreCinema().equals("") || ad.getPreCinema().equals(session.getSessionCinema().getName())) &&
            !ad.getMovieName().equals(session.getSessionMovie())) {
                ad.show();
            }
        }
    }

    public void write() {
        try (FileWriter fw = new FileWriter("src\\marketolog.txt")) {
            fw.write(pass + "\n");
            fw.write(money + "\n");
            fw.write(adverts.size() + "\n");
            for(int i = 0; i < adverts.size(); i++) {
                fw.write(adverts.get(i).toWrite());
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(ArrayList<Ticket> tickets) {
        Ticket last = tickets.get(tickets.size() - 1);
        Ticket exact;
        Advert ad;
        String lastMovie = last.getTicketMovie();
        String exactMovie;
        String adMovie, adPreMovie;
        String exactCinema;
        String adCinema;
        Date exactTime;
        Date[] adTime = new Date[2];
        for(int i = 0; i < adverts.size(); i++) {
            ad = adverts.get(i);
            adMovie = ad.getMovieName();
            adPreMovie = ad.getPreMovie();
            adCinema = ad.getPreCinema();
            adTime[0] = ad.getTime0();
            adTime[1] = ad.getTime1();
            for(int j = 0; j < tickets.size() - 1; j++) {
                exact = tickets.get(j);
                exactCinema = exact.getTicketCinema();
                exactMovie = exact.getTicketMovie();
                exactTime = exact.getTicketTime();
                if (lastMovie.equals(adMovie) && (adPreMovie.equals("") || adPreMovie.equals(exactMovie)) &&
                        (adCinema.equals("") || adCinema.equals(exactCinema)) &&
                        (ad.getL() || (adTime[0].getTime() < exactTime.getTime() && exactTime.getTime() < adTime[1].getTime()))) {
                    money += last.getTicketPrice();
                    ad.plusMoney(last.getTicketPrice());
                }
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < adverts.size(); i++)
            s += "\n" + i + "\n" + adverts.get(i).toString();
        return s;
    }
}