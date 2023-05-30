package logic;

import domain.Coordinates;
import domain.ShotResult;
import domain.HitResult;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.time.Instant;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import monitoring.HitCounter;
import monitoring.StatMissing;

import java.io.Serializable;

@Named
@SessionScoped
@Slf4j
public class AreaCheckBean implements Serializable {
    @Inject
    HistoryBean historyBean;

    public void shoot(double x, double y, double r) {
        HitResult hitResult = doCheck(x,y,r);
        HitCounter.getInstance().check(hitResult.getShot().isRes());
        log.info("The count of hits is {}", HitCounter.getInstance().getCount());
        StatMissing.getInstance().calculatePercentage(HitCounter.getInstance().getCount(), HitCounter.getInstance().getHitCount());
        historyBean.add(hitResult);
    }

    public HitResult doCheck(double x, double y, double r) {
        Instant startTime = Instant.now();
        Validation validation = new Validation();
        Coordinates coordinates = Coordinates.create(x,y,r);
        if (r <= 0) return HitResult.fromHit(coordinates, ShotResult.create(false, "Invalid R value", startTime));
        if ( validation.isPointInShapes( x, y, r ) )
            return HitResult.fromHit(coordinates, ShotResult.create(true, "Попал", startTime));
        return HitResult.fromHit(coordinates, ShotResult.create(false, "Промах", startTime));

    }
}