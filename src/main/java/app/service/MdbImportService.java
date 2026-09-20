package app.service;

import app.dtos.ActorDTO;
import app.dtos.DirectorDTO;
import app.dtos.GenreDTO;
import app.dtos.MovieDTO;
import app.mdb.MdbClient;

import java.util.List;
import java.util.stream.Collectors;

public class MdbImportService {

    private final MdbClient mdbClient;
    private final MovieService movieService;

    public MdbImportService(
            MdbClient mdbClient,
            MovieService movieService) {

        this.mdbClient = mdbClient;
        this.movieService = movieService;
    }

    public void importMovies() {

        List<MovieDTO> movies = mdbClient.getMovies();


        if (movies.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }

        int limit = Math.min(20, movies.size()); // I have make a limit so it not take for much down


        for (int i = 0; i < limit; i++) {

            MovieDTO movie = movies.get(i);

            System.out.println(
                    "Starter film: " + movie.getMdbId()
            );

            MovieDTO details =
                    mdbClient.getMovieDetails(movie.getMdbId());

            List<ActorDTO> actors =
                    mdbClient.getActors(movie.getMdbId());

            DirectorDTO director =
                    mdbClient.getDirector(movie.getMdbId());

            List<GenreDTO> genres =
                    mdbClient.getGenres(movie.getMdbId());

            details.setActors(
                    actors.stream().collect(Collectors.toSet())
            );

            details.setDirector(director);

            details.setGenres(
                    genres.stream().collect(Collectors.toSet())
            );

            movieService.createMovie(details);

            System.out.println(
                    "Importerede film: " + details.getMovieTitle()
            );
        }


    }


}
