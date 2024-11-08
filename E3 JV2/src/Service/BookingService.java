package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private List<Booking> bookings;
    private List<Customer> customers;
    private List<Room> rooms;

    public BookingService(List<Booking> bookings, List<Customer> customers, List<Room> rooms) {
        this.bookings = bookings;
        this.customers = customers;
        this.rooms = rooms;
    }

    public boolean checkRoomAvailability(Room room, LocalDateTime checkIn, LocalDateTime checkOut) {
        return bookings.stream()
                .noneMatch(booking -> booking.getRoomId().equals(room) &&
                        (checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn())));
    }

    public boolean isValidCheckInOut(LocalDateTime checkIn, LocalDateTime checkOut) {
        return !checkIn.isAfter(checkOut) && !checkIn.isEqual(checkOut);
    }

    public List<Room> getAvailableRooms(RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        return rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .filter(room -> checkRoomAvailability(room, checkIn, checkOut))
                .collect(Collectors.toList());
    }

    public Booking createBooking(int id, Room room, Customer customer, LocalDateTime checkIn, LocalDateTime checkOut) throws IllegalArgumentException {
        if (!isValidCheckInOut(checkIn, checkOut)) {
            throw new IllegalArgumentException("Invalid dates: Check-in cannot be after check-out, and they cannot be the same.");
        }

        if (!checkRoomAvailability(room, checkIn, checkOut)) {
            throw new IllegalArgumentException("Room is not available for the selected dates.");
        }

        Booking newBooking = new Booking(id, room, customer, checkIn, checkOut);
        bookings.add(newBooking);
        return newBooking;
    }

    public Optional<Customer> findCustomerById(String name, String phone) {
        return customers.stream()
                .filter(customer -> customer.getName().equals(name) && customer.getPhone().equals(phone))
                .findFirst();
    }

    public List<Booking> getBookings() {
        return bookings;
    }


    public double calculateRevenue(Booking booking){
        Duration duration = Duration.between(booking.getCheckIn(), booking.getCheckOut());
        long hours = duration.toHours();
        if(hours < 1){
            hours = 1;
        }

        return hours * booking.getRoomId().getPricePerHour();
    }

    public double calculateRevenue(){
        return bookings.stream()
                .mapToDouble(this::calculateRevenue)
                .sum();
    }


    public Optional<Booking> findBooking(String keyword){
        try{
            int inputFormat = Integer.parseInt(keyword);
            return bookings.stream()
                    .filter(b -> b.getRoomId().getId() == inputFormat)
                    .findFirst();
        }catch(NumberFormatException e){
           return bookings.stream()
                    .filter(b -> b.getCusId().getName().equalsIgnoreCase(keyword) ||
                            b.getCusId().getPhone().equalsIgnoreCase(keyword))
                    .findFirst();
        }
    }
}
