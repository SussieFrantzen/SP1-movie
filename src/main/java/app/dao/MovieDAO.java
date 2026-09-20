package app.dao;


import app.entities.Movie;

import java.util.List;

public interface MovieDAO extends DAO<Movie> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(int genreId);

    Double getAverageRating();

    List<Movie> getTop10Rated();

    List<Movie> getBottom10Rated();

    List<Movie> getTop10Popular();
}

