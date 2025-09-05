import DataModels.*;
import DecoratorPattern.*;
import FactoryMethodPattern.*;
import ObserverPattern.*;
import StrategyPattern.*;
import java.util.List;

public class ManualTestRunner {
    public static void main(String[] args) {
    System.out.println("--- E-commerce System Simulation ---");

        // --- 1. Setup ---
        Product laptop = new Product("P001", "Gaming Laptop", 45000.0);
        Product mouse = new Product("P002", "RGB Mouse", 1500.0);
        Order myOrder = new Order("ORD-001", List.of(laptop, mouse), "customer@example.com");

        OrderCalculator calculator = new OrderCalculator();
        ShipmentFactory shipmentFactory = new ShipmentFactory();

        OrderProcessor orderProcessor = new OrderProcessor();
        InventoryService inventory = new InventoryService();
        EmailService emailer = new EmailService();
        orderProcessor.register(inventory);
        orderProcessor.register(emailer);

        System.out.println("--- 2. Testing Strategy Pattern (Discounts) ---");
        double originalPrice = myOrder.getTotalPrice();
        System.out.println("Original Price: " + originalPrice);

        DiscountStrategy tenPercentOff = new PercentageDiscount(10);
        double priceAfterPercentage = calculator.calculateFinalPrice(myOrder, tenPercentOff);
        System.out.println("Price with 10% discount: " + priceAfterPercentage);

        DiscountStrategy fiveHundredOff = new FixedDiscount(500);
        double priceAfterFixed = calculator.calculateFinalPrice(myOrder, fiveHundredOff);
        System.out.println("Price with 500 THB discount: " + priceAfterFixed);

        System.out.println("\n--- 3. Testing Factory and Decorator Pattern (Shipment) ---");
        // สร้างการจัดส่งแบบมาตรฐาน
        Shipment standardShipment = shipmentFactory.createShipment("STANDARD");
        System.out.println("Base Shipment : " + standardShipment.getInfo() + " Cost: " + standardShipment.getCost());

        // "ห่อ" ด้วยบริการห่อสินค้า
        Shipment fullyLoaded = new InsuranceDecorator(standardShipment);
        System.out.println("Decorated: " + fullyLoaded.getInfo());

        //"ห่อ" ด้วยบริการประกันสินค้า
        System.out.println("Decorated Shipment Info: " + decoratedShipment.getInfo());
        System.out.println("Final Shipment Cost (with services): " + String.format("%,.2f", decoratedShipment.getCost()));


        // --- 5. Final Order Summary ---
        System.out.println("\n--- 5. Final Order Summary ---");
        // เราจะใช้ราคาสินค้าหลังหักส่วนลด 10% มาคำนวณยอดสุดท้าย
        double finalProductPrice = priceAfterPercentage; 
        double finalShipmentCost = decoratedShipment.getCost();
        double grandTotal = finalProductPrice + finalShipmentCost;
        
        System.out.println("Final Product Price: " + String.format("%,.2f", finalProductPrice) + " THB");
        System.out.println("Final Shipment Cost: " + String.format("%,.2f", finalShipmentCost) + " THB");
        System.out.println("----------------------------------------");
        System.out.println("Grand Total: " + String.format("%,.2f", grandTotal) + " THB");


        // --- 6. Testing Observer Pattern (Processing Order) ---
        orderProcessor.processOrder(myOrder);
    }
}