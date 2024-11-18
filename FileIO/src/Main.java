import Entity.Customer;
import Service.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String customerFilePath = "path/to/customerFile.txt";
        String orderFilePath = "path/to/orderFile.txt";
        String orderDetailFilePath = "path/to/orderDetailFile.txt";
        String outputFilePath = "path/to/outputFile.txt";


        FileService fileService = new FileService(customerFilePath, outputFilePath);

        fileService.readCustomersFromFile(customerFilePath);
        fileService.readOrdersFromFile(orderFilePath);
        fileService.readOrderDetailsFromFile(orderDetailFilePath);

        int customerId = 1;
        fileService.writeBillingToFile(customerId);


    }
}
