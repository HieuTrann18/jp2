package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    public void bookingRoom(){
        Scanner scanner = new Scanner(System.in);
        RoomType selectedRoomType;
        System.out.println("Booking room");
        System.out.println("Available room types: ");

        for(RoomType type : RoomType.values()){
            System.out.println("- " + type);
        }

        System.out.println("Choose room type: ");
        selectedRoomType = RoomType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter check-in date: ");
        String checkInInput = scanner.nextLine();
        LocalDateTime checkIn = LocalDateTime.parse(checkInInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("Enter check-out date: ");
        String checkOutInput = scanner.nextLine();
        LocalDateTime checkOut = LocalDateTime.parse(checkOutInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if(!bookingService.isValidCheckInOut(checkIn, checkOut)){
            System.out.println("Invalid dates: Check-in must be before check-out, and they cannot be the same.");
        }

        List<Room> availableRooms = bookingService.getAvailableRooms(selectedRoomType, checkIn, checkOut);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the selected dates.");
            return;
        }

        System.out.println("Available room type: ");
        for(int i = 0; i < availableRooms.size(); i++){
            System.out.println((i + 1) + ". " + availableRooms.get(i).getCode());
        }
        System.out.print("Choose the room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        if (roomNumber < 1 || roomNumber > availableRooms.size()) {
            System.out.println("Invalid room number.");
            return;
        }

        Room selectedRoom = availableRooms.get(roomNumber - 1);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String customerPhone = scanner.nextLine();

        Customer customer = bookingService.createCustomer(customerName, customerPhone);
        if (customer == null) {
            System.out.println("Customer already exists with the given ID.");
            return;
        }


        Booking booking = bookingService.createBooking(selectedRoom, customer, checkIn, checkOut);

        if(booking != null){
            System.out.println("Booking successful!");
            System.out.println("Customer: " + customer.getName());
            System.out.println("Room: " + selectedRoom.getCode());
            System.out.println("Check-in: " + checkIn);
            System.out.println("Check-out: " + checkOut);
        }else {
            System.out.println("Room is not available for the selected dates.");
        }

    }

    public void findBooking(){

    }

    public Double CalculateRevenue(){
        return null;
    }

    public Double maxRevenueIn2023(){
        return null;
    }
}
