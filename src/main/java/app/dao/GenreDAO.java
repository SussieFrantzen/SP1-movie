package app.dao;

import app.entities.Genre;
import app.entities.Movie;

import java.util.Set;

public interface GenreDAO extends DAO<Genre> {

    Set<Movie> findMoviesByGenre(int genreId);

    Genre findByMdbId(int mdbId);
}
