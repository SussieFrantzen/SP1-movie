package app.daos;

import app.dao.MovieDAOImpl;
import app.config.HibernateTestConfig;
import app.entities.Movie;

import app.testutils.MovieTestPopulator;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieDAOImplTest {

    private final EntityManagerFactory emf = HibernateTestConfig.getEntityManagerFactory();

    private MovieDAOImpl movieDAOImpl;
    private Map<String, Movie> seeded;

    @BeforeEach
    void beforeEach() {
        seeded = MovieTestPopulator.populate(emf);
        movieDAOImpl = new MovieDAOImpl(emf.createEntityManager());
    }

    @AfterAll
    void shutdown() {
        emf.close();
    }

    @Test
    void create() {

        Movie movie = new Movie();

        movie.setMdbId(9999);
        movie.setMovieTitle("Ny test film");
        movie.setOverview("En film oprettet i integrationstesten");
        movie.setReleaseDate(LocalDate.of(2026, 1, 1));
        movie.setVoteAverage(7.0);
        movie.setPopularity(500.0);

        Movie result = movieDAOImpl.create(movie);

        assertNotNull(result);
        assertEquals("Ny test film", result.getMovieTitle());

        Movie found = movieDAOImpl.findById(result.getId());

        assertNotNull(found);
        assertEquals(result.getId(), found.getId());
        assertEquals(9999, found.getMdbId());
        assertEquals("Ny test film", found.getMovieTitle());
    }

    @Test
    void findById() {

        Movie expected = seeded.get("movie1");

        Movie result = movieDAOImpl.findById(expected.getId());

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getMdbId(), result.getMdbId());
        assertEquals(expected.getMovieTitle(), result.getMovieTitle());
    }

    @Test
    void findAll() {

        List<Movie> result = movieDAOImpl.findAll();

        assertNotNull(result);
        assertEquals(25, result.size());
    }

    @Test
    void update() {

        Movie movie = seeded.get("movie2");

        movie.setMovieTitle("Opdateret film");
        movie.setVoteAverage(9.9);

        Movie result = movieDAOImpl.update(movie);

        assertNotNull(result);
        assertEquals(movie.getId(), result.getId());
        assertEquals("Opdateret film", result.getMovieTitle());
        assertEquals(9.9, result.getVoteAverage());

        Movie updatedMovie = movieDAOImpl.findById(movie.getId());

        assertNotNull(updatedMovie);
        assertEquals(movie.getId(), updatedMovie.getId());
        assertEquals("Opdateret film", updatedMovie.getMovieTitle());
        assertEquals(9.9, updatedMovie.getVoteAverage());
    }

    @Test
    void remove() {

        Movie movie = seeded.get("movie3");

        int id = movie.getId();

        assertNotNull(movieDAOImpl.findById(id));

        movieDAOImpl.remove(id);

        Movie result = movieDAOImpl.findById(id);

        assertNull(result);
    }

    @Test
    void findByTitle() {

        List<Movie> result = movieDAOImpl.findByTitle("film 1");

        assertNotNull(result);
        assertFalse(result.isEmpty());

        assertTrue(
                result.stream()
                        .allMatch(movie ->
                                movie.getMovieTitle()
                                        .toLowerCase()
                                        .contains("film 1")
                        )
        );
    }

    @Test
    void findByGenre() {

        int genreId = 1;

        List<Movie> result = movieDAOImpl.findByGenre(genreId);

        assertNotNull(result);
    }

    @Test
    void getAverageRating() {

        Double result = movieDAOImpl.getAverageRating();

        assertNotNull(result);

        double expected = seeded.values()
                .stream()
                .mapToDouble(Movie::getVoteAverage)
                .average()
                .orElseThrow();

        assertEquals(expected, result, 0.0001);
    }

    @Test
    void getTop10Rated() {

        List<Movie> result = movieDAOImpl.getTop10Rated();

        assertNotNull(result);
        assertEquals(10, result.size());

        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(
                    result.get(i).getVoteAverage()
                            >= result.get(i + 1).getVoteAverage()
            );
        }
    }

    @Test
    void getBottom10Rated() {

        List<Movie> result = movieDAOImpl.getBottom10Rated();

        assertNotNull(result);
        assertEquals(10, result.size());

        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(
                    result.get(i).getVoteAverage()
                            <= result.get(i + 1).getVoteAverage()
            );
        }
    }

    @Test
    void getTop10Popular() {

        List<Movie> result = movieDAOImpl.getTop10Popular();

        assertNotNull(result);
        assertEquals(10, result.size());

        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(
                    result.get(i).getPopularity()
                            >= result.get(i + 1).getPopularity()
            );
        }
    }
}