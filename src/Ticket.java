package src;

import java.util.Date;

public class Ticket {
    private int ticketPrice;
    private String ticketCinema;
    private String ticketMovie;
    private Date ticketTime;

    Ticket(Session ticketSession, int ticketPrice) {
        this.ticketPrice = ticketPrice;
        ticketCinema = ticketSession.getSessionCinema().getName();
        ticketMovie = ticketSession.getSessionMovie().getName();
        ticketTime = ticketSession.getSessionTime();
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public String getTicketCinema() {
        return ticketCinema;
    }

    public void setTicketCinema(String ticketCinema) {
        this.ticketCinema = ticketCinema;
    }

    public String getTicketMovie() {
        return ticketMovie;
    }

    public void setTicketMovie(String ticketMovie) {
        this.ticketMovie = ticketMovie;
    }

    public Date getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }
}
