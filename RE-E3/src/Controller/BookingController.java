package Controller;

import Entity.Booking;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookRoom() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter room type: ");
            RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());

            System.out.println("Enter check-in date and time (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime checkIn = LocalDateTime.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.println("Enter check-out date and time (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime checkOut = LocalDateTime.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Booking booking = bookingService.bookRoom(roomType, checkIn, checkOut);

            if (booking != null) {
                System.out.println("Room booked successfully: " + booking.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void updateBookingWithCustomer() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter booking ID: ");
            int bookingId = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter customer ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            bookingService.updateBookingWithCustomer(bookingId, customerId);
            System.out.println("Customer successfully updated the booking.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Optional<Booking> findBooking(String keyword){
        Optional<Booking> result = bookingService.findBooking(keyword);
        return result;
    }

}
