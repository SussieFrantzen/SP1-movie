package app.service;

import app.dao.GenreDAO;
import app.dtos.GenreDTO;
import app.dtos.MovieDTO;
import app.entities.Genre;
import app.entities.Movie;

import java.util.List;

public class GenreService {

    private final GenreDAO genreDAO;

    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public GenreDTO createGenre(GenreDTO dto) {
        Genre genre = convertToEntity(dto);

        Genre savedGenre = genreDAO.create(genre);

        return convertToDTO(savedGenre);
    }

    public GenreDTO getGenre(int id) {
        Genre genre = genreDAO.findById(id);

        if (genre == null) {
            return null;
        }

        return convertToDTO(genre);
    }

    public List<GenreDTO> getAllGenres() {
        return genreDAO.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public GenreDTO updateGenre(GenreDTO dto) {
        Genre genre = convertToEntity(dto);

        Genre updatedGenre = genreDAO.update(genre);

        return convertToDTO(updatedGenre);
    }

    public void removeGenre(int id) {
        genreDAO.remove(id);
    }

    public List<MovieDTO> getMoviesByGenre(int genreId) {
        return genreDAO.findMoviesByGenre(genreId)
                .stream()
                .map(this::convertMovieToDTO)
                .toList();
    }

    private Genre convertToEntity(GenreDTO dto) {
        Genre genre = new Genre();

        genre.setId(dto.getId());
        genre.setMdbId(dto.getMdbId());
        genre.setName(dto.getName());

        return genre;
    }

    private GenreDTO convertToDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();

        dto.setId(genre.getId());
        dto.setMdbId(genre.getMdbId());
        dto.setName(genre.getName());

        return dto;
    }

    private MovieDTO convertMovieToDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();

        dto.setId(movie.getId());
        dto.setMdbId(movie.getMdbId());
        dto.setMovieTitle(movie.getMovieTitle());
        dto.setOverview(movie.getOverview());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRating(movie.getVoteAverage());
        dto.setPopularity(movie.getPopularity());

        return dto;
    }

    public GenreDTO getGenreByMdbId(int mdbId) {

        Genre genre = genreDAO.findByMdbId(mdbId);

        if (genre == null) {
            return null;
        }

        return convertToDTO(genre);
    }



}
