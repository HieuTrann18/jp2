package Entity;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private Room roomId;
    private Customer cusId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public Booking() {
    }

    public Booking(int id, Room roomId, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.id = id;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Booking(int id, Room roomId, Customer cusId, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.id = id;
        this.roomId = roomId;
        this.cusId = cusId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public Customer getCusId() {
        return cusId;
    }

    public void setCusId(Customer cusId) {
        this.cusId = cusId;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", cusId=" + cusId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
