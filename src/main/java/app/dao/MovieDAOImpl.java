package app.dao;

import app.entities.Movie;
import app.utils.TransactionUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    private final EntityManager em;

    public MovieDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Movie create(Movie movie) {
        System.out.println("Før persist: " + movie.getMovieTitle());
        TransactionUtil.execute(em, () -> { // starting the database transaction.
            em.persist(movie); // save the movie on the database.
        });
        System.out.println("Efter persist: " + movie.getMovieTitle());
        System.out.println("ID: " + movie.getId());

        return movie;
    }

    @Override
    public Movie findById(int id) {
        return em.find(Movie.class, id);
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery(
                "SELECT m FROM Movie m",
                Movie.class
        ).getResultList();
    }

    @Override
    public Movie update(Movie movie) {
        return TransactionUtil.executeWithResult(
                em,
                () -> em.merge(movie)
        );
    }

    @Override
    public void remove(int id) {

        TransactionUtil.execute(em, () -> {
            Movie movie = findById(id);

            if (movie != null) {
                em.remove(movie);
            }
        });
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return em.createQuery(
                        "SELECT m FROM Movie m " +
                                "WHERE LOWER(m.movieTitle) LIKE LOWER(CONCAT('%', :title, '%'))", // the LOWER make it casesensitive so all text been small
                        Movie.class
                )
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Movie> findByGenre(int genreId) {
        return em.createQuery(
                        "SELECT m FROM Movie m " +
                                "JOIN m.genres g " +
                                "WHERE g.id = :genreId",
                        Movie.class
                )
                .setParameter("genreId", genreId)
                .getResultList();
    }

    @Override
    public Double getAverageRating() {
        return em.createQuery(
                "SELECT AVG(m.voteAverage) FROM Movie m",
                Double.class
        ).getSingleResult();
    }

    @Override
    public List<Movie> getTop10Rated() {
        return em.createQuery(
                        "SELECT m FROM Movie m " +
                                "ORDER BY m.voteAverage DESC",
                        Movie.class
                )
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<Movie> getBottom10Rated() {
        return em.createQuery(
                        "SELECT m FROM Movie m " +
                                "ORDER BY m.voteAverage ASC",
                        Movie.class
                )
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<Movie> getTop10Popular() {
        return em.createQuery(
                        "SELECT m FROM Movie m " +
                                "ORDER BY m.popularity DESC",
                        Movie.class
                )
                .setMaxResults(10)
                .getResultList();
    }

}
