package logic;

import dao.HitResultDao;
import domain.HitResult;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named("historyBean")
@SessionScoped
public class HistoryBean implements Serializable {

    @Inject
    HitResultDao hitResultDao;

    public void add(HitResult hitResult) {
        hitResultDao.save(hitResult);
    }

    public List<HitResult> getHitResults() {
        List<HitResult> history = hitResultDao.getAll();
        history.sort((o1, o2) -> o2.getCurrentTime().compareTo(o1.getCurrentTime()));
        return history;
    }

    public boolean isEmpty() {
        return hitResultDao.isEmpty();
    }

    @Transactional
    public void clear() {
        hitResultDao.clear();
    }
}