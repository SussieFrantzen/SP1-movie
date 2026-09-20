package app.dao;

import app.entities.Director;
import app.utils.TransactionUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DirectorDAOImpl implements DirectorDAO {

    private final EntityManager em;

    public DirectorDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Director create(Director director) {
        TransactionUtil.execute(em, () -> em.persist(director));
        return director;
    }

    @Override
    public Director findById(int id) {
        return em.find(Director.class, id);
    }

    @Override
    public List<Director> findAll() {
        return em.createQuery(
                "SELECT d FROM Director d",
                Director.class
        ).getResultList();
    }

    @Override
    public Director update(Director director) {
        return TransactionUtil.executeWithResult(
                em,
                () -> em.merge(director)
        );
    }

    @Override
    public void remove(int id) {
        TransactionUtil.execute(em, () -> {
            Director director = findById(id);

            if (director != null) {
                em.remove(director);
            }
        });
    }

    @Override
    public Director findByMdbId(int mdbId) {
        return em.createQuery(
                        "SELECT d FROM Director d WHERE d.mdbId = :mdbId",
                        Director.class
                )
                .setParameter("mdbId", mdbId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }


}
