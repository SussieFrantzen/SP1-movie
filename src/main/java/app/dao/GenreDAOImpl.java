package app.dao;

import app.entities.Genre;
import app.entities.Movie;
import app.utils.TransactionUtil;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenreDAOImpl implements GenreDAO {

    private final EntityManager em;

    public GenreDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre create(Genre genre) {
        TransactionUtil.execute(em, () -> em.persist(genre));
        return genre;
    }

    @Override
    public Genre findById(int id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery(
                "SELECT g FROM Genre g",
                Genre.class
        ).getResultList();
    }

    @Override
    public Genre update(Genre genre) {
        return TransactionUtil.executeWithResult(
                em,
                () -> em.merge(genre)
        );
    }

    @Override
    public void remove(int id) {
        TransactionUtil.execute(em, () -> {
            Genre genre = findById(id);

            if (genre != null) {
                em.remove(genre);
            }
        });
    }

    @Override
    public Set<Movie> findMoviesByGenre(int genreId) {
        return new HashSet<>(
                em.createQuery(
                                "SELECT m FROM Movie m " +
                                        "JOIN m.genres g " +
                                        "WHERE g.id = :genreId",
                                Movie.class
                        )
                        .setParameter("genreId", genreId)
                        .getResultList()
        );
    }


    @Override
    public Genre findByMdbId(int mdbId) {
        return em.createQuery(
                        "SELECT g FROM Genre g WHERE g.mdbId = :mdbId",
                        Genre.class
                )
                .setParameter("mdbId", mdbId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }


}
