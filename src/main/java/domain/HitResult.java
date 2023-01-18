package domain;


import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import clock.TimeZoneBean;

import java.time.*;

@Entity
@Table(name = "hit-results")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HitResult {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coord_id", referencedColumnName = "id")
    private Coordinates coords;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shot_id", referencedColumnName = "id")
    private ShotResult shot;

    private ZonedDateTime currentTime;


    public static HitResult fromHit(Coordinates coordinates, ShotResult shotResult) {
        String timezone = CDI.current().select(TimeZoneBean.class).get().getTimezone();
        return new HitResult(null, coordinates, shotResult, ZonedDateTime.now(ZoneId.of(timezone)));
    }

    @Override
    public String toString() {
        return "HitResult{" +
                "id=" + id +
                ", Coordinates=" + coords +
                ", ShotResult=" + shot +
                ", currentTime=" + currentTime +
                '}';
    }
}