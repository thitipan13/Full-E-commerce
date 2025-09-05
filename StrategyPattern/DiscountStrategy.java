package StrategyPattern;

import DataModels.*;

/**
 * Interface สำหรับกลยุทธ์ส่วนลด
 */
public interface DiscountStrategy {
    double applyDiscount(Order order);
}
