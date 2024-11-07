package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Booking> bookings = new ArrayList<>();

    public BookingService(List<Room> rooms, List<Customer> customers) {
        this.rooms = rooms;
        this.customers = customers;
    }

    public Booking bookRoom(RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        Room room = rooms.stream()
                .filter(r -> r.getRoomType() == roomType)
                .findFirst()
                .orElse(null);

        if (room == null) {
            return null;
        }

        Booking booking = new Booking(bookings.size() + 1, room, checkIn, checkOut);
        bookings.add(booking);
        return booking;
    }

    public void updateBookingWithCustomer(int bookingId, int cusId) {
        Booking booking = bookings.stream()
                .filter(b -> b.getId() == bookingId)
                .findFirst()
                .orElse(null);

        if (booking != null) {
            Customer customer = customers.stream()
                    .filter(c -> c.getId() == cusId)
                    .findFirst()
                    .orElse(null);
            if (customer != null) {
                booking.setCusId(customer);
            } else {
                throw new IllegalArgumentException("Customer not found.");
            }
        } else {
            throw new IllegalArgumentException("Booking not found.");
        }
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Optional <Booking> findBooking(String keyword){
        return bookings.stream()
                .filter(b -> b.getCusId().getName().equals(keyword) ||
                        b.getRoomId().getCode().equals(keyword) ||
                        b.getCusId().getPhone().equals(keyword))
                .findFirst();
    }
}
