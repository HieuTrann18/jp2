package Entity;

import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private String dateTime;
    private List<OrderDetail> orderDetails;

    public Order(int id, int customerId, String dateTime, List<OrderDetail> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.orderDetails = orderDetails;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Customer ID: " + customerId + ", Date: " + dateTime;
    }
}
