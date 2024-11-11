package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private List<Customer> customers;
    private List<Room> rooms;
    private List<Booking> bookings;

    public BookingService(List<Customer> customers, List<Room> rooms, List<Booking> bookings){
        this.customers = customers;
        this.rooms = rooms;
        this.bookings = bookings;
    }

    public boolean availableRoom(Room room, LocalDateTime checkIn, LocalDateTime checkOut){
        return bookings.stream()
                .noneMatch(b -> b.getRoom().equals(room) && checkIn.isBefore(b.getCheckOut())
                && checkOut.isAfter(b.getCheckIn()));
    }

    public boolean isValidCheckInOut(LocalDateTime checkIn, LocalDateTime checkOut){
        return !checkIn.isAfter(checkOut) && !checkIn.isEqual(checkOut);
    }

    public List<Room> getAvailableRooms(RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        return rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .filter(room -> availableRoom(room, checkIn, checkOut))
                .collect(Collectors.toList());
    }

    public Booking createBooking(Room room, Customer customer, LocalDateTime checkIn, LocalDateTime checkOut){
        if(!availableRoom(room, checkIn, checkOut)){
            throw new IllegalArgumentException("Invalid dates: Check-in cannot be after check-out, and they cannot be the same");
        }

        if(!isValidCheckInOut(checkIn, checkOut)){
            throw new IllegalArgumentException("Room is not available for the selected dates.");
        }

        Booking newBooking = new Booking(bookings.size() + 1, room, customer, checkIn, checkOut);
        bookings.add(newBooking);
        return newBooking;
    }

    public boolean checkUser(int id){
        return customers.stream()
                .anyMatch(c -> c.getId() == id);
    }

    public Customer createCustomer(String name, String phone){
        int cusId = customers.size() + 1;
        if(!checkUser(cusId)){
            Customer customer = new Customer(cusId, name, phone);
            customers.add(customer);
            return customer;
        }
        return null;
    }

    public Optional<Booking> findBooking(String keyword){
        return null;
    }
}
