package app.mdb;

import app.dtos.ActorDTO;
import app.dtos.DirectorDTO;
import app.dtos.GenreDTO;
import app.dtos.MovieDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MdbClient {

    private static final String BASE_URL =
            "https://api.themoviedb.org/3";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String apiKey;

    public MdbClient() {

        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();

        String apiKey = System.getenv("api_key");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "API_KEY is not configured"
            );
        }

        this.apiKey = apiKey;
    }

    private JsonNode get(String url) {

        String separator = url.contains("?") ? "&" : "?";

        String finalUrl =
                url + separator + "api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(finalUrl))
                .header("accept", "application/json")
                .GET()
                .build();

        try {

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "TMDb request failed: "
                                + response.statusCode()
                                + " - "
                                + response.body()
                );
            }

            return objectMapper.readTree(response.body());

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(
                    "Could not call TMDb API",
                    e
            );
        }
    }

    public List<MovieDTO> getMovies() {

        LocalDate today = LocalDate.now();
        LocalDate fiveYearsAgo = today.minusYears(5);

        String url = BASE_URL +
                "/discover/movie" +
                "?with_origin_country=DK" +
                "&primary_release_date.gte=" + fiveYearsAgo +
                "&primary_release_date.lte=" + today +
                "&language=en-US" +
                "&sort_by=popularity.desc" +
                "&page=1";

        JsonNode root = get(url);

        List<MovieDTO> movies = new ArrayList<>();

        for (JsonNode node : root.get("results")) {

            MovieDTO movie = new MovieDTO();

            movie.setMdbId(node.get("id").asInt());
            movie.setMovieTitle(node.get("title").asText());
            movie.setOverview(node.get("overview").asText());
            movie.setPopularity(node.get("popularity").asDouble());
            movie.setRating(node.get("vote_average").asDouble());

            String releaseDate =
                    node.get("release_date").asText();

            if (!releaseDate.isEmpty()) {
                movie.setReleaseDate(
                        LocalDate.parse(releaseDate)
                );
            }

            movies.add(movie);
        }

        return movies;
    }

    public MovieDTO getMovieDetails(int id) {

        String url =
                BASE_URL +
                        "/movie/" +
                        id +
                        "?language=en-US";

        JsonNode node = get(url);

        MovieDTO movie = new MovieDTO();

        movie.setMdbId(node.get("id").asInt());
        movie.setMovieTitle(node.get("title").asText());
        movie.setOverview(node.get("overview").asText());
        movie.setPopularity(node.get("popularity").asDouble());
        movie.setRating(node.get("vote_average").asDouble());

        String releaseDate =
                node.get("release_date").asText();

        if (!releaseDate.isEmpty()) {
            movie.setReleaseDate(
                    LocalDate.parse(releaseDate)
            );
        }

        return movie;
    }

    public List<ActorDTO> getActors(int id) {

        String url =
                BASE_URL +
                        "/movie/" +
                        id +
                        "/credits?language=en-US";

        JsonNode root = get(url);

        List<ActorDTO> actors = new ArrayList<>();

        for (JsonNode node : root.get("cast")) {

            ActorDTO actor = new ActorDTO();

            actor.setMdbId(node.get("id").asInt());
            actor.setName(node.get("name").asText());

            actors.add(actor);
        }

        return actors;
    }

    public DirectorDTO getDirector(int id) {

        String url =
                BASE_URL +
                        "/movie/" +
                        id +
                        "/credits?language=en-US";

        JsonNode root = get(url);

        for (JsonNode node : root.get("crew")) {

            if ("Director".equals(node.get("job").asText())) {

                DirectorDTO director = new DirectorDTO();

                director.setMdbId(node.get("id").asInt());
                director.setName(node.get("name").asText());

                return director;
            }
        }

        return null;
    }

    public List<GenreDTO> getGenres(int id) {

        String url =
                BASE_URL +
                        "/movie/" +
                        id +
                        "?language=en-US";

        JsonNode root = get(url);

        List<GenreDTO> genres = new ArrayList<>();

        for (JsonNode node : root.get("genres")) {

            GenreDTO genre = new GenreDTO();

            genre.setMdbId(node.get("id").asInt());
            genre.setName(node.get("name").asText());

            genres.add(genre);
        }

        return genres;
    }
}

