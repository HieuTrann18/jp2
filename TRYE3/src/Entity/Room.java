package Entity;

public class Room {
    private int id;
    private String code;
    private RoomType roomType;
    private double pricePerHour;

    public Room() {
    }

    public Room(int id, String code, RoomType roomType, double pricePerHour) {
        this.id = id;
        this.code = code;
        this.roomType = roomType;
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", roomType=" + roomType +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}
