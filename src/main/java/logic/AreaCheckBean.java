package logic;

import domain.Coordinates;
import domain.ShotResult;
import domain.HitResult;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.time.Instant;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class AreaCheckBean implements Serializable {
    @Inject
    HistoryBean historyBean;

    public void shoot(double x, double y, double r) {
        HitResult hitResult = doCheck(x,y,r);
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