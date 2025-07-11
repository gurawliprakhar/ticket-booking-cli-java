/*
 * Author : Prakhar Tripathi
 * Date : 2025-07-10
 * Description : This is the main class for the Ticket Booking Application.
 */
package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingServices;
import ticket.booking.services.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Ticket Booking Application...");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingServices userBookingServices;

        try {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            userBookingServices = new UserBookingServices();
        } catch (IOException ex) {
            System.out.println("Error: Thier is something wrong ");
            return;
        }
        while (option != 7) {
            System.out.println("Choose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Booking");
            System.out.println("4. Search Trains");
            System.out.println("5. Book Ticket");
            System.out.println("6. Cancel My Booking");
            System.out.println("7. Exit the Application");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the username for Sign Up");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password for Sign Up");
                    String passwordToSignUp = scanner.next();
                    User userToSignUp = new User(nameToSignUp, passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingServices.signUp(userToSignUp);
                    break;

                case 2:
                    System.out.println("Enter the username for Login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password for Login");
                    String passwordToLogin = scanner.next();
                    User userToLogin = new User(nameToLogin, passwordToLogin,
                            UserServiceUtil.hashPassword(passwordToLogin),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingServices = new UserBookingServices(userToLogin);
                    }catch (IOException ex) {
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Fetching your Booking...");
                    userBookingServices.fetchBooking();
                    break;

                case 4:
                    System.out.println("Type your source station:");
                    String source = scanner.next();
                    System.out.println("Type your destination station:");
                    String destination = scanner.next();
                    List<Train> trains = userBookingServices.getTrains(source, destination);
                    int index = 1;
                    for (Train train : trains) {
                        System.out.println(index+ "Train ID: " + train.getTrainId());
                        for (Map.Entry<String, String> entry : train.getStationTimes().entrySet()) {
                            System.out.println("Station: " + entry.getKey() + ", Time: " + entry.getValue());
                        }
                    }
            }
        }
    }
}
