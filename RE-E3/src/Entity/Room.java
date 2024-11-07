package Entity;

public class Room {
    private int id;
    private String code;
    private RoomType roomType;
    private double price_per_hour;

    public Room() {
    }

    public Room(int id, String code, RoomType roomType, double price_per_hour) {
        this.id = id;
        this.code = code;
        this.roomType = roomType;
        this.price_per_hour = price_per_hour;
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

    public double getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(double price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", roomType=" + roomType +
                ", price_per_hour=" + price_per_hour +
                '}';
    }
}
