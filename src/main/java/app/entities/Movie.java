package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int mdbId;

    private String movieTitle;

    @Column(columnDefinition = "TEXT")
    private String overview;


    private LocalDate releaseDate;

    private Double voteAverage;

    private Double popularity;

    public Movie(Set<Actor> actors, Director director, Set<Genre> genres, int id, int mdbId, String movieTitle, String overview, Double popularity, LocalDate releaseDate, Double voteAverage) {
        this.actors = actors;
        this.director = director;
        this.genres = genres;
        this.id = id;
        this.mdbId = mdbId;
        this.movieTitle = movieTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;


    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;





}
