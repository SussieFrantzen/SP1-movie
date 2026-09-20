package app.dao;

import app.entities.Actor;
import app.utils.TransactionUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ActorDAOImpl implements ActorDAO {

    private final EntityManager em;

    public ActorDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Actor create(Actor actor) {
        TransactionUtil.execute(em, () -> em.persist(actor));
        return actor;
    }

    @Override
    public Actor findById(int id) {
        return em.find(Actor.class, id);
    }

    @Override
    public List<Actor> findAll() {
        return em.createQuery(
                "SELECT a FROM Actor a",
                Actor.class
        ).getResultList();
    }

    @Override
    public Actor update(Actor actor) {
        return TransactionUtil.executeWithResult(
                em,
                () -> em.merge(actor)
        );
    }

    @Override
    public void remove(int id) {
        TransactionUtil.execute(em, () -> {
            Actor actor = findById(id);

            if (actor != null) {
                em.remove(actor);
            }
        });
    }

    public Actor findByMdbId(int mdbId) {
        return em.createQuery(
                        "SELECT a FROM Actor a WHERE a.mdbId = :mdbId",
                        Actor.class
                )
                .setParameter("mdbId", mdbId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
