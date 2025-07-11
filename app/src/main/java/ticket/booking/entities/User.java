package ticket.booking.entities;

import java.util.List;

public class User {
    private String name;
    private String hashedPassword;
    private String password;
    private List<Ticket> ticketsBooked;
    private String userId;

    public User(String name, String hashedPassword, String password, List<Ticket> ticketsBooked, String userId) {
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.password = password;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void printTickets() {
        for (int i = 0; i < ticketsBooked.size(); i++) {
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
