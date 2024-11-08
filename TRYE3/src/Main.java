import Controller.BookingController;
import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "RS001", RoomType.SINGLE, 8));
        rooms.add(new Room(2, "RD001", RoomType.DOUBLE, 12));
        rooms.add(new Room(3, "RQ002", RoomType.QUEEN, 35));
        rooms.add(new Room(4, "RT001", RoomType.TRIPLE, 12.5));
        rooms.add(new Room(5, "RQ001", RoomType.QUAD, 20.5));

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Mr.Linus Tovaldo", "0123456789" ));
        customers.add(new Customer(2, "Mr.Bill", "012345679" ));
        customers.add(new Customer(3, "Mr.Turing", "012345689" ));

        List<Booking> bookings = new ArrayList<>();


        bookings.add(new Booking(1, rooms.get(0), customers.get(0),
                LocalDateTime.of(2023, 3, 15, 9, 30, 15),
                LocalDateTime.of(2023, 3, 16, 12, 30, 15)));

        bookings.add(new Booking(2, rooms.get(1), customers.get(1),
                LocalDateTime.of(2023, 6, 9, 19, 30, 15),
                LocalDateTime.of(2023, 6, 10, 11, 25, 15)));

        bookings.add(new Booking(3, rooms.get(2), customers.get(1),
                LocalDateTime.of(2023, 3, 11, 10, 10),
                LocalDateTime.of(2023, 3, 13, 11, 5)));

        bookings.add(new Booking(4, rooms.get(3), customers.get(2),
                LocalDateTime.of(2023, 11, 11, 11, 11),
                LocalDateTime.of(2023, 11, 13, 11, 15)));

        bookings.add(new Booking(5, rooms.get(4), customers.get(0),
                LocalDateTime.of(2023, 10, 25, 9, 20),
                LocalDateTime.of(2023, 10, 26, 12, 25)));

        BookingService bookingService = new BookingService(bookings, customers, rooms);
        BookingController bookingController = new BookingController(bookingService);
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("=====Menu=====");
            System.out.println("1. Book Room");
            System.out.println("2. Find Booking");
            System.out.println("3. Revenue statistics by hours");
            System.out.println("4. Max revenue in 2023");
            System.out.println("Your choice: ");
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    bookingController.bookingRoom();
                    break;
                case 2:
                    bookingController.findBooking();
                    break;
                case 3:
                    bookingController.CalculateRevenue();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice != 5);

    }
}