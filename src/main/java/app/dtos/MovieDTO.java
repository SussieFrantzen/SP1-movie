package app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private int id;
    private int mdbId;
    private String movieTitle;
    private String overview;
    private LocalDate releaseDate;
    private Double rating;
    private Double popularity;
    private Set<ActorDTO> actors;
    private DirectorDTO director;
    private Set<GenreDTO> genres;

}
