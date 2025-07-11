package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingServices {
    private User user;
    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    //app/src/main/java/tickets/booking/localDb....so on
    private static final String USERS_PATH = "../localDb/users.json";

    public UserBookingServices(User user1) throws IOException {
        this.user = user1;
        loadUser();
    }

    public UserBookingServices() throws IOException {
        loadUser();
    }

    public List<User> loadUser() throws IOException {
        File Users = new File(USERS_PATH);
        return objectMapper.readValue(Users, new TypeReference<List<User>>() {});
    }

    public boolean loginUser() {
        Optional<User> foundUser = userList.stream()
                .filter(user1 ->
                        user1.getName().equals(user.getName()) &&
                                UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword())
                )
                .findFirst();
        return foundUser.isPresent();
    }

    public boolean signUp(User user1) {
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(new File(USERS_PATH), userList);
    }

    public void fetchBooking() {
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId;
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        }else {
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }
}
