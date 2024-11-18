package Service;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileService {
    private List<Customer> customers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<OrderDetail> orderDetails = new ArrayList<>();
    private String fileInPath;
    private String fileOutPath;

    // Constructor nhận đường dẫn file
    public FileService(String fileInPath, String fileOutPath) {
        this.fileInPath = fileInPath;
        this.fileOutPath = fileOutPath;
    }

    // Đọc dữ liệu từ file customer
    public void readCustomersFromFile(String customerFilePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFilePath))) {
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                if (!lineData.isEmpty()) {
                    String[] arData = lineData.split(";");
                    Customer customer = new Customer(Integer.parseInt(arData[0]), arData[1], arData[2]);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
    }


    public void readOrdersFromFile(String orderFilePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(orderFilePath))) {
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                if (!lineData.isEmpty()) {
                    String[] arData = lineData.split(";");
                    Order order = new Order(Integer.parseInt(arData[0]), Integer.parseInt(arData[1]),
                            Integer.parseInt(arData[2]), Integer.parseInt(arData[3]), Double.parseDouble(arData[4]));
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading order file: " + e.getMessage());
        }
    }

    public void readOrderDetailsFromFile(String orderDetailFilePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(orderDetailFilePath))) {
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                if (!lineData.isEmpty()) {
                    String[] arData = lineData.split(";");
                    OrderDetail orderDetail = new OrderDetail(Integer.parseInt(arData[0]), Integer.parseInt(arData[1]),
                            Integer.parseInt(arData[2]), Integer.parseInt(arData[3]), Double.parseDouble(arData[4]));
                    orderDetails.add(orderDetail);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading orderDetail file: " + e.getMessage());
        }
    }


    public void writeBillingToFile(int customerId) {

        Optional<Customer> customerOpt = customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst();

        if (customerOpt.isEmpty()) {
            System.out.println("Customer not found!");
            return;
        }

        Customer customer = customerOpt.get();
        System.out.println("Generating billing information for customer: " + customer.getName());


        List<Order> customerOrders = orders.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutPath))) {
            writer.write("Billing Information for Customer: " + customer.getName());
            writer.newLine();

            for (Order order : customerOrders) {
                writer.write("Order ID: " + order.getId() + ", Date: " + order.getDateTime());
                writer.newLine();

                List<OrderDetail> customerOrderDetails = orderDetails.stream()
                        .filter(detail -> detail.getOrderId() == order.getId())
                        .collect(Collectors.toList());

                for (OrderDetail detail : customerOrderDetails) {
                    writer.write(detail.getId() + ";" + detail.getOrderId() + ";" + detail.getProductId() + ";" + detail.getQuantity() + ";" + detail.getPrice());
                    writer.newLine();
                }
            }
            System.out.println("Billing information for customer ID " + customerId + " has been written to " + fileOutPath);
        } catch (IOException e) {
            System.out.println("Error writing billing information: " + e.getMessage());
        }
    }
}
