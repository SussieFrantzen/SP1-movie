package app.service;

import app.dao.ActorDAO;
import app.dao.DirectorDAO;
import app.dao.GenreDAO;
import app.dao.MovieDAO;
import app.dtos.ActorDTO;
import app.dtos.DirectorDTO;
import app.dtos.GenreDTO;
import app.dtos.MovieDTO;
import app.entities.Actor;
import app.entities.Director;
import app.entities.Genre;
import app.entities.Movie;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieService {

    private final MovieDAO movieDAO;
    private final ActorDAO actorDAO;
    private final DirectorDAO directorDAO;
    private final GenreDAO genreDAO;


    public MovieService(
            MovieDAO movieDAO,
            ActorDAO actorDAO,
            DirectorDAO directorDAO,
            GenreDAO genreDAO) {

        this.movieDAO = movieDAO;
        this.actorDAO = actorDAO;
        this.directorDAO = directorDAO;
        this.genreDAO = genreDAO;
    }



    public MovieDTO createMovie(MovieDTO dto) {

        Movie movie = convertToEntity(dto);

        Movie savedMovie = movieDAO.create(movie);

        return convertToDTO(savedMovie);
    }



    public MovieDTO getMovie(int id) {
        Movie movie = movieDAO.findById(id);

        if (movie == null) {
            return null;
        }

        return convertToDTO(movie);
    }

    public List<MovieDTO> getAllMovies() {
        return movieDAO.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public MovieDTO updateMovie(MovieDTO dto) {
        Movie movie = convertToEntity(dto);

        Movie updatedMovie = movieDAO.update(movie);

        return convertToDTO(updatedMovie);
    }

    public void removeMovie(int id) {
        movieDAO.remove(id);
    }

    private Movie convertToEntity(MovieDTO dto) {

        Movie movie = new Movie();

        movie.setId(dto.getId());
        movie.setMdbId(dto.getMdbId());
        movie.setMovieTitle(dto.getMovieTitle());
        movie.setOverview(dto.getOverview());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setVoteAverage(dto.getRating());
        movie.setPopularity(dto.getPopularity());

        // Director
        if (dto.getDirector() != null) {

            Director director =
                    directorDAO.findByMdbId(dto.getDirector().getMdbId());

            if (director == null) {
                director = new Director();

                director.setMdbId(dto.getDirector().getMdbId());
                director.setName(dto.getDirector().getName());

                director = directorDAO.create(director);
            }

            movie.setDirector(director);
        }

        // Actors
        if (dto.getActors() != null) {

            Set<Actor> actors = dto.getActors()
                    .stream()
                    .map(actorDTO -> {

                        Actor actor =
                                actorDAO.findByMdbId(actorDTO.getMdbId());

                        if (actor == null) {
                            actor = new Actor();

                            actor.setMdbId(actorDTO.getMdbId());
                            actor.setName(actorDTO.getName());

                            actor = actorDAO.create(actor);
                        }

                        return actor;
                    })
                    .collect(Collectors.toSet());

            movie.setActors(actors);
        }


            // Genres
        if (dto.getGenres() != null) {

            Set<Genre> genres = dto.getGenres()
                    .stream()
                    .map(genreDTO -> {

                        Genre genre =
                                genreDAO.findByMdbId(genreDTO.getMdbId());

                        if (genre == null) {
                            genre = new Genre();

                            genre.setMdbId(genreDTO.getMdbId());
                            genre.setName(genreDTO.getName());

                            genre = genreDAO.create(genre);
                        }

                        return genre;
                    })
                    .collect(Collectors.toSet());

            movie.setGenres(genres);
        }

        return movie;
    }


    private MovieDTO convertToDTO(Movie movie) {

        MovieDTO dto = new MovieDTO();

        dto.setId(movie.getId());
        dto.setMdbId(movie.getMdbId());
        dto.setMovieTitle(movie.getMovieTitle());
        dto.setOverview(movie.getOverview());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRating(movie.getVoteAverage());
        dto.setPopularity(movie.getPopularity());

        // Director
        if (movie.getDirector() != null) {
            DirectorDTO directorDTO = new DirectorDTO();

            directorDTO.setId(movie.getDirector().getId());
            directorDTO.setMdbId(movie.getDirector().getMdbId());
            directorDTO.setName(movie.getDirector().getName());

            dto.setDirector(directorDTO);
        }

        // Actors
        if (movie.getActors() != null) {
            Set<ActorDTO> actorDTOs = movie.getActors()
                    .stream()
                    .map(actor -> {
                        ActorDTO actorDTO = new ActorDTO();

                        actorDTO.setId(actor.getId());
                        actorDTO.setMdbId(actor.getMdbId());
                        actorDTO.setName(actor.getName());

                        return actorDTO;
                    })
                    .collect(Collectors.toSet());

            dto.setActors(actorDTOs);
        }

        // Genres
        if (movie.getGenres() != null) {
            Set<GenreDTO> genreDTOs = movie.getGenres()
                    .stream()
                    .map(genre -> {
                        GenreDTO genreDTO = new GenreDTO();

                        genreDTO.setId(genre.getId());
                        genreDTO.setMdbId(genre.getMdbId());
                        genreDTO.setName(genre.getName());

                        return genreDTO;
                    })
                    .collect(Collectors.toSet());

            dto.setGenres(genreDTOs);
        }

        return dto;
    }




}


