package monitoring;

public interface StatMissingMXBean {
    void calculatePercentage(int count, int hit);

    double getMissStat();
}
