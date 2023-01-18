package dao;

import domain.HitResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.criteria.CriteriaQuery;

@ApplicationScoped
public class HitResultDao extends AbstractDao<HitResult> {
    protected HitResultDao() {
        super(HitResult.class);
    }

    @Override
    public HitResult get(long id) {
        CriteriaQuery<HitResult> query = criteriaSelectEqual(id, "id");
        return super.entityManager.createQuery(query).getSingleResult();
    }
}