package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookingRoom() {
        Scanner sc = new Scanner(System.in);

        RoomType selectedRoomType;
        System.out.println("Booking Room");
        System.out.println("Available room types:");

        for (RoomType type : RoomType.values()) {
            System.out.println("- " + type);
        }
        System.out.print("Choose room type: ");
        selectedRoomType = RoomType.valueOf(sc.nextLine().toUpperCase());


        System.out.print("Enter check-in date (yyyy-MM-dd HH:mm:ss): ");
        String checkInInput = sc.nextLine();
        LocalDateTime checkIn = LocalDateTime.parse(checkInInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.print("Enter check-out date (yyyy-MM-dd HH:mm:ss): ");
        String checkOutInput = sc.nextLine();
        LocalDateTime checkOut = LocalDateTime.parse(checkOutInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        if (!bookingService.isValidCheckInOut(checkIn, checkOut)) {
            System.out.println("Invalid dates: Check-in must be before check-out, and they cannot be the same.");
            return;
        }

        List<Room> availableRooms = bookingService.getAvailableRooms(selectedRoomType, checkIn, checkOut);

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the selected dates.");
            return;
        }

        System.out.println("Available rooms of type " + selectedRoomType + ":");
        for (int i = 0; i < availableRooms.size(); i++) {
            System.out.println((i + 1) + ". " + availableRooms.get(i).getCode());
        }

        System.out.print("Choose the room number to book: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        if (roomNumber < 1 || roomNumber > availableRooms.size()) {
            System.out.println("Invalid room number.");
            return;
        }

        Room selectedRoom = availableRooms.get(roomNumber - 1);

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
        System.out.print("Enter customer phone: ");
        String customerPhone = sc.nextLine();

        Optional<Customer> customerOptional = bookingService.findCustomerById(customerName, customerPhone);

        if (!customerOptional.isPresent()) {
            System.out.println("Customer not found. Adding a new customer...");
            Customer newCustomer = new Customer(customerName, customerPhone);  // Tạo khách hàng mới nếu không tìm thấy
            customerOptional = Optional.of(newCustomer);
        }

        Customer customer = customerOptional.get();

        int bookingId = bookingService.getBookings().size() + 1; // Tạo ID booking mới
        Booking booking = bookingService.createBooking(bookingId, selectedRoom, customer, checkIn, checkOut);

        if (booking != null) {
            System.out.println("Booking successful!");
            System.out.println("Customer: " + customer.getName());
            System.out.println("Room: " + selectedRoom.getCode());
            System.out.println("Check-in: " + checkIn);
            System.out.println("Check-out: " + checkOut);
        } else {
            System.out.println("Room is not available for the selected dates.");
        }
    }


   public void CalculateRevenue(){
        Scanner scanner = new Scanner(System.in);
       List<Booking> bookings = bookingService.getBookings();
       if (bookings.isEmpty()) {
           System.out.println("Not found booking");
           return;
       }

       System.out.println("Danh sách các booking:");
       for (int i = 0; i < bookings.size(); i++) {
           Booking booking = bookings.get(i);
           System.out.println((i + 1) + ". " + "Customer: " + booking.getCusId().getName() +
                   ", Room: " + booking.getRoomId().getRoomType() +
                   ", Check-in: " + booking.getCheckIn() +
                   ", Check-out: " + booking.getCheckOut());
       }
       System.out.print("Choose booking pay (1-" + bookings.size() + "): ");
       int choice = scanner.nextInt();

       if (choice < 1 || choice > bookings.size()) {
           System.out.println("Invalid choice.");
           return;
       }

       Booking selectedBooking = bookings.get(choice - 1);
       double revenue = bookingService.calculateRevenue(selectedBooking);
       System.out.println("Total payment: " + revenue);
   }

   public void findBooking(){
       System.out.print("Enter keyword: ");
       Scanner scanner = new Scanner(System.in);
       String key= scanner.nextLine();
       Optional<Booking> result = bookingService.findBooking(key);
       if(!result.isEmpty()){
           System.out.println("Found: " + result);
       }else {
           System.out.println("Not found booking");
       }


   }

}
