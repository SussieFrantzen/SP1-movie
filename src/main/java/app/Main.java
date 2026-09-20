package app;

import app.config.HibernateConfig;
import app.dao.*;
import app.dtos.ActorDTO;
import app.dtos.DirectorDTO;
import app.dtos.GenreDTO;
import app.dtos.MovieDTO;
import app.mdb.MdbClient;
import app.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        // Hibernate / database
        EntityManagerFactory emf =
                HibernateConfig.getEntityManagerFactory();

        EntityManager em = emf.createEntityManager();

        // DAO'er
        MovieDAO movieDAO = new MovieDAOImpl(em);
        ActorDAO actorDAO = new ActorDAOImpl(em);
        DirectorDAO directorDAO = new DirectorDAOImpl(em);
        GenreDAO genreDAO = new GenreDAOImpl(em);

        // Services
        ActorService actorService = new ActorService(actorDAO);
        DirectorService directorService = new DirectorService(directorDAO);
        GenreService genreService = new GenreService(genreDAO);

        MovieService movieService = new MovieService(
                movieDAO,
                actorDAO,
                directorDAO,
                genreDAO
        );


        MdbClient mdbClient = new MdbClient();

        // Import service
        MdbImportService importService =
                new MdbImportService(
                        mdbClient,
                        movieService
                );

        importService.importMovies();


        em.close();
        emf.close();


        // test af MdbClient

        List<MovieDTO> movies = mdbClient.getMovies();

        System.out.println("Antal film: " + movies.size());

        if (!movies.isEmpty()) {

            MovieDTO movie = movies.get(0);

            System.out.println("Film: " + movie.getMovieTitle());
            System.out.println("TMDb ID: " + movie.getMdbId());

            MovieDTO details =
                    mdbClient.getMovieDetails(movie.getMdbId());

            System.out.println("Titel: " + details.getMovieTitle());
            System.out.println("Rating: " + details.getRating());
            System.out.println("Popularitet: " + details.getPopularity());
            System.out.println("Release date: " + details.getReleaseDate());


        }

        System.out.println("oversigt over 1 film");

        MovieDTO movie = movies.get(0);

        List<ActorDTO> actors =
                mdbClient.getActors(movie.getMdbId());

        DirectorDTO director =
                mdbClient.getDirector(movie.getMdbId());

        List<GenreDTO> genres =
                mdbClient.getGenres(movie.getMdbId());

        System.out.println("\nFilm: " + movie.getMovieTitle());

        System.out.println("\nActors:");
        for (ActorDTO actor : actors) {
            System.out.println(
                    actor.getMdbId() + " - " + actor.getName()
            );
        }

        System.out.println("\nDirector:");
        if (director != null) {
            System.out.println(
                    director.getMdbId() + " - " + director.getName()
            );
        } else {
            System.out.println("Ingen director fundet");
        }

        System.out.println("\nGenres:");
        for (GenreDTO genre : genres) {
            System.out.println(
                    genre.getMdbId() + " - " + genre.getName()
            );
        }

    }

}
