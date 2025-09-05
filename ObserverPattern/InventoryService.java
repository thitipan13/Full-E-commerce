package ObserverPattern;

import DataModels.*;

/**
 * Concrete Observer: ระบบจัดการสินค้า
 */
public class InventoryService implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("InventoryService: Inventory updated for order " + order.orderId());
    }
}
