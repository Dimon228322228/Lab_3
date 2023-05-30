package monitoring;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@Slf4j
public class HitCounter extends NotificationBroadcasterSupport implements HitCounterMXBean{
    private int sequenceNumber = 0;
    @Setter
    private int count = 0, hitCount = 0;

    private static HitCounter hitCounter;

    public static HitCounter getInstance(){
        if (hitCounter == null) hitCounter = new HitCounter();
        return hitCounter;
    }
    @Override
    public void check(boolean result) {
        count++;
        if (result) hitCount++;
        if (count % 4 == 0) {
            sendNotification(new Notification("multiple_of_4", this, sequenceNumber++, System.currentTimeMillis(), "The number of points is " + count));
        }
    }

    @Override
    public int getHitCount() {
        return hitCount;
    }

    @Override
    public int getCount() {
        return count;
    }
}
