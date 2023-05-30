package monitoring;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatMissing implements StatMissingMXBean{
    @Setter
    private double missStat;

    private static StatMissing statMissing;

    public static StatMissing getInstance(){
        if (statMissing == null) statMissing = new StatMissing();
        return statMissing;
    }
    @Override
    public void calculatePercentage(int count, int hit) {
        missStat = (double) (count - hit) / count;
    }

    @Override
    public double getMissStat() {
        return missStat;
    }
}
