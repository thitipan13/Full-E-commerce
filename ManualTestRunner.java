import DataModels.*;
import DecoratorPattern.*;
import FactoryMethodPattern.*;
import ObserverPattern.*;
import StrategyPattern.*;
import java.util.List;

public class ManualTestRunner {
    public static void main(String[] args) {
    System.out.println("--- 1. Setting up the order and system components ---");

        // สร้าง Products และ Order
        Product laptop = new Product("P001", "Gaming Laptop", 45000.0);
        Product mouse = new Product("P002", "RGB Mouse", 1500.0);
        Order order = new Order("ORD-2025-001", List.of(laptop, mouse), "customer@example.com");

        // สร้าง Components หลัก
        OrderCalculator calculator = new OrderCalculator();
        ShipmentFactory shipmentFactory = new ShipmentFactory();
        OrderProcessor orderProcessor = new OrderProcessor();

        // สร้างและลงทะเบียน Observers
        InventoryService inventoryService = new InventoryService();
        EmailService emailService = new EmailService();
        orderProcessor.register(inventoryService);
        orderProcessor.register(emailService);

        System.out.println("Order created with total price: " + String.format("%,.2f", order.getTotalPrice()) + " THB");
        System.out.println("====================================================\n");


        System.out.println("--- 2. Calculating final price with different strategies ---");
        
        // ทดลองคำนวณราคาสินค้าด้วยส่วนลด 2 แบบ
        double priceWithPercentageDiscount = calculator.calculateFinalPrice(order, new PercentageDiscount(10));
        System.out.println("Price with 10% discount: " + String.format("%,.2f", priceWithPercentageDiscount) + " THB");

        double priceWithFixedDiscount = calculator.calculateFinalPrice(order, new FixedDiscount(1000));
        System.out.println("Price with 1,000 THB fixed discount: " + String.format("%,.2f", priceWithFixedDiscount) + " THB");
        System.out.println("====================================================\n");


        System.out.println("--- 3. Creating shipment using Factory ---");
        Shipment shipment = shipmentFactory.createShipment("STANDARD");
        System.out.println("Base shipment created: " + shipment.getInfo() + " (" + shipment.getCost() + " THB)");
        System.out.println("====================================================\n");


        System.out.println("--- 4. Adding extra services with Decorators ---");
        // "ห่อ" shipment ด้วยบริการเสริมต่างๆ
        shipment = new GiftWrapDecorator(shipment);
        shipment = new InsuranceDecorator(shipment, order);
        System.out.println("Decorated shipment info: " + shipment.getInfo());
        System.out.println("Decorated shipment cost: " + String.format("%,.2f", shipment.getCost()) + " THB");
        System.out.println("====================================================\n");


        System.out.println("--- 5. Printing final summary ---");
        // ใช้ส่วนลดแบบ 10% สำหรับการสรุปยอดสุดท้าย
        double finalProductPrice = priceWithPercentageDiscount;
        double finalShipmentCost = shipment.getCost();
        double grandTotal = finalProductPrice + finalShipmentCost;
        System.out.println("Final Product Price (after 10% discount): " + String.format("%,.2f", finalProductPrice) + " THB");
        System.out.println("Final Shipment Cost (with services): " + String.format("%,.2f", finalShipmentCost) + " THB");
        System.out.println("----------------------------------------------------");
        System.out.println("Grand Total: " + String.format("%,.2f", grandTotal) + " THB");
        System.out.println("====================================================\n");

        
        System.out.println("--- 6. Confirming and processing the order ---");
        orderProcessor.processOrder(order);
        System.out.println("====================================================\n");
    }
}