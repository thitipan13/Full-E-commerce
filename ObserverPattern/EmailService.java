package ObserverPattern;

import DataModels.*;

/**
 * Concrete Observer: ระบบส่งอีเมล
 */
public class EmailService implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("EmailService: Confirmation email sent to " + order.customerEmail() + " for order:" + order.orderId());
    }
}