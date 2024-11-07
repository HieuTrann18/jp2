package Entity;

public enum RoomType {
    SINGLE("Single"), DOUBLE("Double"), QUEEN("Queen"), TRIPLE("Triple"), QUAD("Quad");

    private String roomType;

    RoomType(String roomType){
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
