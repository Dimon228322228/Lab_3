package monitoring;

public interface HitCounterMXBean {
    void check(boolean result);

    int getHitCount();

    int getCount();
}
