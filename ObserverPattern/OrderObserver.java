package ObserverPattern;

import DataModels.*;

/**
 * Interface (Observer) สำหรับผู้สังเกตการณ์
 */
public interface OrderObserver {
    void update(Order order);
}