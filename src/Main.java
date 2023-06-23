package src;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CinemaList cinemaArr = new CinemaList();
        ClientList clientArr = new ClientList();
        MovieList movieArr = new MovieList();
        SessionList sessionArr = new SessionList(cinemaArr, movieArr);
        Marketolog marketolog = new Marketolog();
        SessionList goodSessions;
        Session exactSession;
        String buf = "";
        Session sessionObj = sessionArr.getObj(0);
        int ticketsN = 1;
        try (Scanner scanner = new Scanner(new File("src\\admin.txt"))) {
            scanner.useDelimiter("\n");
            sessionObj.setTickets(Integer.parseInt(scanner.nextLine()));
            sessionObj.setMoney(Integer.parseInt(scanner.nextLine()));
            sessionObj.setPass(scanner.nextLine());
            int typeN = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < typeN; i++) {
                String[] words = scanner.nextLine().split(" ");
                if (words[0].equals("Комфорт")) {
                    ComfortRoom.setMoney(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                } else if (words[0].equals("Стандарт")) {
                    StandardRoom.setMoney(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                } else if (words[0].equals("VIP")) {
                    VipRoom.setMoney(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                } else if (words[0].equals("IMAX")) {
                    ImaxRoom.setMoney(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                }
            }
            scanner.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scanner terminal = new Scanner(System.in);
        while (!buf.equals("в")) {
            System.out.print("Введите режим работы(к - клиент, а - админ, м - маркетолог, в - выход) ");
            buf = terminal.nextLine();
            switch (buf) {
                case ("а"):
                    while (!buf.equals("в")) {
                        System.out.print("Введите пароль(или в для выхода):");
                        buf = terminal.nextLine();
                        if (sessionObj.okPass(buf)) {
                            while (!buf.equals("в")) {
                                System.out.print("Введите режим работы(с - статистика, з - статистика по типу зала, к - количество клиентов по статусам, и - изменение кинотеатров/залов, в - выход) ");
                                buf = terminal.nextLine();
                                switch (buf) {
                                    case "с":
                                        System.out.println("Количество проданных билетов: " + sessionObj.getTickets() + " общая выручка: " + sessionObj.getMoney());
                                        break;
                                    case "з":
                                        System.out.println("Стандарт: " + StandardRoom.getMoney() + "\\" + StandardRoom.getTickets());
                                        System.out.println("Комфорт: " + ComfortRoom.getMoney() + "\\" + ComfortRoom.getTickets());
                                        System.out.println("VIP: " + VipRoom.getMoney() + "\\" + VipRoom.getTickets());
                                        System.out.println("IMAX: " + ImaxRoom.getMoney() + "\\" + ImaxRoom.getTickets());
                                        break;
                                    case "к":
                                        System.out.println("Обычных клиентов: " + (StandardСlient.getPeople() - FriendСlient.getFriend() - VipСlient.getVip()));
                                        System.out.println("Друзей сети: " + FriendСlient.getFriend());
                                        System.out.println("VIP: " + VipСlient.getVip());
                                        break;
                                    case "и":
                                        while (!buf.equals("в")) {
                                            System.out.println(cinemaArr.toString());
                                            System.out.println("Введите режим(д - добавление кинотеатра, р - редактирование существующих, в - выход)");
                                            buf = terminal.nextLine();
                                            Сinema exactСinema = null;
                                            if (buf.equals("д")) {
                                                cinemaArr.addObj(terminal);
                                                exactСinema = cinemaArr.getObj(cinemaArr.getSize() - 1);
                                                buf = "х";
                                            }
                                            if (buf.equals("р")) {
                                                System.out.println("Введите номер кинотеатра ");
                                                exactСinema = cinemaArr.getObj(terminal.nextInt());
                                                buf = "х";
                                            }
                                            if (buf.equals("х")) {
                                                System.out.println(exactСinema.toString());
                                                System.out.println("Введите, что делать дальше(у - удаление кинотеатра, р - редактирование кинотеатра, в - выход)");
                                                buf = terminal.nextLine();
                                                buf = terminal.nextLine();
                                                switch (buf) {
                                                    case "у":
                                                        cinemaArr.delete(exactСinema);
                                                        break;
                                                    case "р":
                                                        System.out.println("Что редактируем?(н - название, а - адрес, ф - форматы, к - кинозалы, в - выход) ");
                                                        buf = terminal.nextLine();
                                                        switch (buf) {
                                                            case "н":
                                                                System.out.println("Введите новое название");
                                                                exactСinema.setName(terminal.nextLine());
                                                                break;
                                                            case "а":
                                                                System.out.println("Введите новый адрес ");
                                                                exactСinema.setLocation(terminal.nextLine());
                                                                break;
                                                            case "ф":
                                                                System.out.println("Введите форматы через пробел ");
                                                                exactСinema.setFormats(terminal.nextLine().split(" "));
                                                                break;
                                                            case "к":
                                                                while (!buf.equals("в")) {
                                                                    exactСinema.printRooms();
                                                                    System.out.println("Что делаем с комнатами?(р - редактируем, д - добавляем, у - удаляем,  в - выход) ");
                                                                    buf = terminal.nextLine();
                                                                    Room exactRoom = null;
                                                                    int i = 0;
                                                                    if (buf.equals("д")) {
                                                                        i = exactСinema.getCinemaRoomSize();
                                                                        exactRoom = exactСinema.addRoom(terminal);
                                                                        buf = "х";
                                                                    }
                                                                    if (buf.equals("р")) {
                                                                        System.out.print("Введите номер кинозала для редактирования ");
                                                                        i = terminal.nextInt();
                                                                        exactRoom = exactСinema.getRoom(i);
                                                                        buf = "х";
                                                                    }
                                                                    if (buf.equals("у")) {
                                                                        System.out.print("Введите номер кинозала для удаления ");
                                                                        i = terminal.nextInt();
                                                                        exactСinema.delRoom(i);
                                                                    }
                                                                    if (buf.equals("х")) {
                                                                        System.out.println(exactRoom.toString('f'));
                                                                        System.out.println("Что делаем?(т - смена типа, ц - смена цены билета, в - выход) ");
                                                                        buf = terminal.nextLine();
                                                                        buf = terminal.nextLine();
                                                                        switch (buf) {
                                                                            case "т":
                                                                                System.out.println("Введите тип кинозала");
                                                                                exactСinema.changeRoomType(i, terminal.nextLine());
                                                                                break;
                                                                            case "ц":
                                                                                System.out.print("Введите цену билета ");
                                                                                exactRoom.setPrice(terminal.nextInt());
                                                                                break;
                                                                        }
                                                                    }
                                                                }
                                                                buf = "к";
                                                                break;
                                                        }
                                                }
                                                buf = "х";
                                                break;
                                            } else if (buf.equals("в")) {
                                                break;
                                            } else System.out.println("Введён неверный режим работы");
                                        }
                                        buf = "и";
                                        break;
                                    case "в":
                                        break;
                                    default:
                                        System.out.println("Введён неверный режим работы");
                                        break;
                                }
                            }
                        } else if (!buf.equals("в")) {
                            System.out.println("Неверный пароль");
                        }
                    }
                    buf = "а";
                    break;
                case ("к"):
                {
                    Сlient exactСlient = null;
                    while (exactСlient == null) {
                        System.out.print("Введите режим входа(в - вход, р - регистрация) ");
                        buf = terminal.nextLine();
                        switch (buf) {
                            case ("в"):
                                System.out.print("Введите email ");
                                buf = terminal.nextLine();
                                System.out.print("Введите телефон в формате +79999999999 ");
                                exactСlient = clientArr.getObj(buf, terminal.nextLine());
                                if (exactСlient == null) {
                                    System.out.println("Введены неверные данные");
                                }
                                break;
                            case ("р"):
                                System.out.print("Введите email ");
                                String email = terminal.nextLine();
                                System.out.print("Введите телефон в формате +79999999999 ");
                                String number = terminal.nextLine();
                                System.out.print("Введите ФИО ");
                                exactСlient = new StandardСlient(terminal.nextLine(), email, number);
                                clientArr.addObj(exactСlient);
                                break;
                            default:
                                System.out.println("Введён неверный режим входа");
                        }
                    }
                    buf = "к";
                    while (!buf.equals("в")) {
                        System.out.print("Выберите критерий выбора(ф - фильм, б - бюджет, н - начало сеанса, п - пополнить бюджет, с - счёт, в - выход) ");
                        buf = terminal.nextLine();
                        switch (buf) {
                            case ("ф"):
                                System.out.println("Введите название фильма");
                                String movieName = terminal.nextLine();
                                System.out.print("Введите количество билетов для покупки ");
                                ticketsN = terminal.nextInt();
                                goodSessions = sessionArr.sessionNew(movieName, exactСlient.getBudget(), ticketsN);
                                if (goodSessions.getSize() != 0) {
                                    System.out.println(goodSessions.toString('n'));
                                    System.out.print("Введите номер сеанса ");
                                    exactSession = goodSessions.getObj(terminal.nextInt());
                                    System.out.println("Выберите " + ticketsN + " мест в зале(номер ряда, номер места)\n" + exactSession.matrixToString());
                                    for (int s = 0; s < ticketsN; s++) {
                                        exactSession.buy(terminal.nextInt(), terminal.nextInt(), exactСlient);
                                    }
                                    exactСlient.plusAmount();
                                    System.out.println(exactSession.toString());
                                    marketolog.show(exactSession);
                                    marketolog.update(exactСlient.getTickets());
                                } else {
                                    System.out.println("Нет фильмов удовлетворяющих требованию, либо на Вашем счёте кончились деньги");
                                }
                                buf = terminal.nextLine();
                                break;
                            case ("б"):
                                System.out.println("Введите бюджет на фильм ");
                                int budget = terminal.nextInt();
                                System.out.print("Введите количество билетов для покупки ");
                                ticketsN = terminal.nextInt();
                                goodSessions = sessionArr.sessionNew(budget, exactСlient.getBudget(), ticketsN);
                                if (goodSessions.getSize() != 0) {
                                    System.out.print(goodSessions.toString('b'));
                                    System.out.print("Введите номер сеанса ");
                                    exactSession = goodSessions.getObj(terminal.nextInt());
                                    System.out.println("Выберите " + ticketsN + " мест в зале(номер ряда, номер места)\n" + exactSession.matrixToString());
                                    for (int s = 0; s < ticketsN; s++) {
                                        int price = exactSession.buy(terminal.nextInt(), terminal.nextInt(), exactСlient);
                                    }
                                    exactСlient.plusAmount();
                                    System.out.println(exactSession.toString());
                                    marketolog.show(exactSession);
                                    marketolog.update(exactСlient.getTickets());
                                } else {
                                    System.out.println("Нет фильмов удовлетворяющих требованию, либо на Вашем счёте кончились деньги");
                                }
                                buf = terminal.nextLine();
                                break;
                            case ("н"):
                                System.out.println("Введите время начала и конца фильма через пробел");
                                String[] times = terminal.nextLine().split(" ");
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                                try {
                                    Date time1 = formatter.parse(times[0]);
                                    Date time2 = formatter.parse(times[1]);
                                    System.out.print("Введите количество билетов для покупки ");
                                    ticketsN = terminal.nextInt();
                                    goodSessions = sessionArr.sessionNew(time1, time2, exactСlient.getBudget(), ticketsN);
                                    if (goodSessions.getSize() != 0) {
                                        System.out.println(goodSessions.toString('t'));
                                        System.out.print("Введите номер сеанса ");
                                        exactSession = goodSessions.getObj(terminal.nextInt());
                                        System.out.println("Выберите " + ticketsN + " мест в зале(номер ряда, номер места)\n" + exactSession.matrixToString());
                                        for (int s = 0; s < ticketsN; s++) {
                                            int price = exactSession.buy(terminal.nextInt(), terminal.nextInt(), exactСlient);
                                        }
                                        exactСlient.plusAmount();
                                        System.out.println(exactSession.toString());
                                        marketolog.show(exactSession);
                                        marketolog.update(exactСlient.getTickets());
                                    } else {
                                        System.out.println("Нет фильмов удовлетворяющих требованию, либо на Вашем счёте кончились деньги");
                                    }
                                    buf = terminal.nextLine();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case ("п"):
                                System.out.print("Введите сумму пополнения ");
                                exactСlient.plusBudget(terminal.nextInt());
                                buf = terminal.nextLine();
                                break;
                            case "с":
                                System.out.println("Денег на счету: " + exactСlient.getBudget());
                                break;
                            case ("в"):
                                break;
                            default:
                                System.out.println("Введён неверный режим работы");
                        }
                    }
                    buf = "к";
                    break;
                }
                case ("м"):
                    System.out.println("Введите пароль");
                    buf = terminal.nextLine();
                    if(marketolog.okPass(buf)) {
                        while (!buf.equals("в")) {
                            System.out.println(marketolog.toString());
                            System.out.println("Введите режим работы (д - добавить рекламу, у - удалить рекламу, в - выход)");
                            buf = terminal.nextLine();
                            switch (buf) {
                                case "д":
                                    marketolog.addAdv(terminal);
                                    break;
                                case "у":
                                    System.out.println("Введите номер рекламы для удаления ");
                                    marketolog.delAdv(terminal.nextInt());
                                    break;
                                case "в":
                                    break;
                                default:
                                    System.out.println(" Введён неверный режим работы");
                            }
                        }
                    }
                    buf = "м";
                    break;
                case "в":
                    break;
                default:
                    System.out.println("Введён неверный режим работы");
            }
        }
        terminal.close();
//        System.out.println(cinemaArr.toString());
//        System.out.println(movieArr.toString());
//        System.out.println(clientArr.toString());
//        System.out.println(sessionArr.toString());
        cinemaArr.write();
        movieArr.write();
        clientArr.write();
        sessionArr.write();
        marketolog.write();
    }
}
//реклама фильма на каких-то сенсах/фильмах по времени или по фильму
//+ маркетологу
//внутри вложенный класс рекламного предложения