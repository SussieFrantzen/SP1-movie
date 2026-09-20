package app.testutils;

import app.entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public final class MovieTestPopulator {

    private MovieTestPopulator() {}

    public static Map<String, Movie> populate(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {

            em.getTransaction().begin();

            Movie movie1 = new Movie();

            movie1.setMdbId(202);
            movie1.setMovieTitle("film 1");
            movie1.setOverview(
                    "Der var en test film 2"
            );
            movie1.setReleaseDate(LocalDate.of(2026, 8, 31));
            movie1.setVoteAverage(7.5);
            movie1.setPopularity(1795702.0);


            Movie movie2 = new Movie();
            movie2.setMdbId(101);
            movie2.setMovieTitle("film 2");
            movie2.setOverview(
                    "Der var en test film 2"
            );
            movie2.setReleaseDate(LocalDate.of(2023, 2, 2));
            movie2.setVoteAverage(8.5);
            movie2.setPopularity(10000.0);



            Movie movie3 = new Movie();
            movie3.setMdbId(150);
            movie3.setMovieTitle("film 3");
            movie3.setOverview(
                    "Der var en test film 3"
            );
            movie3.setReleaseDate(LocalDate.of(2024, 4, 22));
            movie3.setVoteAverage(4.5);
            movie3.setPopularity(400.0);


            Movie movie4 = new Movie();
            movie4.setMdbId(200);
            movie4.setMovieTitle("film 4");
            movie4.setOverview(
                    "Der var en test film 4"
            );
            movie4.setReleaseDate(LocalDate.of(2022, 5, 10));
            movie4.setVoteAverage(7.2);
            movie4.setPopularity(2500.0);


            Movie movie5 = new Movie();
            movie5.setMdbId(250);
            movie5.setMovieTitle("film 5");
            movie5.setOverview(
                    "Der var en test film 5"
            );
            movie5.setReleaseDate(LocalDate.of(2021, 8, 15));
            movie5.setVoteAverage(6.8);
            movie5.setPopularity(1800.0);


            Movie movie6 = new Movie();
            movie6.setMdbId(300);
            movie6.setMovieTitle("film 6");
            movie6.setOverview(
                    "Der var en test film 6"
            );
            movie6.setReleaseDate(LocalDate.of(2020, 1, 20));
            movie6.setVoteAverage(5.4);
            movie6.setPopularity(750.0);


            Movie movie7 = new Movie();
            movie7.setMdbId(350);
            movie7.setMovieTitle("film 7");
            movie7.setOverview(
                    "Der var en test film 7"
            );
            movie7.setReleaseDate(LocalDate.of(2023, 7, 7));
            movie7.setVoteAverage(9.1);
            movie7.setPopularity(15000.0);


            Movie movie8 = new Movie();
            movie8.setMdbId(400);
            movie8.setMovieTitle("film 8");
            movie8.setOverview(
                    "Der var en test film 8"
            );
            movie8.setReleaseDate(LocalDate.of(2019, 11, 3));
            movie8.setVoteAverage(3.9);
            movie8.setPopularity(320.0);


            Movie movie9 = new Movie();
            movie9.setMdbId(450);
            movie9.setMovieTitle("film 9");
            movie9.setOverview(
                    "Der var en test film 9"
            );
            movie9.setReleaseDate(LocalDate.of(2024, 2, 14));
            movie9.setVoteAverage(7.7);
            movie9.setPopularity(5200.0);


            Movie movie10 = new Movie();
            movie10.setMdbId(500);
            movie10.setMovieTitle("film 10");
            movie10.setOverview(
                    "Der var en test film 10"
            );
            movie10.setReleaseDate(LocalDate.of(2022, 10, 31));
            movie10.setVoteAverage(6.1);
            movie10.setPopularity(1100.0);


            Movie movie11 = new Movie();
            movie11.setMdbId(550);
            movie11.setMovieTitle("film 11");
            movie11.setOverview(
                    "Der var en test film 11"
            );
            movie11.setReleaseDate(LocalDate.of(2021, 3, 12));
            movie11.setVoteAverage(8.0);
            movie11.setPopularity(6800.0);


            Movie movie12 = new Movie();
            movie12.setMdbId(600);
            movie12.setMovieTitle("film 12");
            movie12.setOverview(
                    "Der var en test film 12"
            );
            movie12.setReleaseDate(LocalDate.of(2020, 6, 25));
            movie12.setVoteAverage(4.2);
            movie12.setPopularity(450.0);


            Movie movie13 = new Movie();
            movie13.setMdbId(650);
            movie13.setMovieTitle("film 13");
            movie13.setOverview(
                    "Der var en test film 13"
            );
            movie13.setReleaseDate(LocalDate.of(2023, 9, 18));
            movie13.setVoteAverage(7.9);
            movie13.setPopularity(3900.0);


            Movie movie14 = new Movie();
            movie14.setMdbId(700);
            movie14.setMovieTitle("film 14");
            movie14.setOverview(
                    "Der var en test film 14"
            );
            movie14.setReleaseDate(LocalDate.of(2018, 12, 1));
            movie14.setVoteAverage(5.0);
            movie14.setPopularity(600.0);


            Movie movie15 = new Movie();
            movie15.setMdbId(750);
            movie15.setMovieTitle("film 15");
            movie15.setOverview(
                    "Der var en test film 15"
            );
            movie15.setReleaseDate(LocalDate.of(2024, 6, 30));
            movie15.setVoteAverage(8.7);
            movie15.setPopularity(12500.0);


            Movie movie16 = new Movie();
            movie16.setMdbId(800);
            movie16.setMovieTitle("film 16");
            movie16.setOverview(
                    "Der var en test film 16"
            );
            movie16.setReleaseDate(LocalDate.of(2019, 4, 9));
            movie16.setVoteAverage(6.5);
            movie16.setPopularity(950.0);


            Movie movie17 = new Movie();
            movie17.setMdbId(850);
            movie17.setMovieTitle("film 17");
            movie17.setOverview(
                    "Der var en test film 17"
            );
            movie17.setReleaseDate(LocalDate.of(2022, 1, 5));
            movie17.setVoteAverage(7.0);
            movie17.setPopularity(2100.0);


            Movie movie18 = new Movie();
            movie18.setMdbId(900);
            movie18.setMovieTitle("film 18");
            movie18.setOverview(
                    "Der var en test film 18"
            );
            movie18.setReleaseDate(LocalDate.of(2020, 9, 22));
            movie18.setVoteAverage(4.8);
            movie18.setPopularity(380.0);


            Movie movie19 = new Movie();
            movie19.setMdbId(950);
            movie19.setMovieTitle("film 19");
            movie19.setOverview(
                    "Der var en test film 19"
            );
            movie19.setReleaseDate(LocalDate.of(2023, 12, 24));
            movie19.setVoteAverage(8.3);
            movie19.setPopularity(8700.0);


            Movie movie20 = new Movie();
            movie20.setMdbId(1000);
            movie20.setMovieTitle("film 20");
            movie20.setOverview(
                    "Der var en test film 20"
            );
            movie20.setReleaseDate(LocalDate.of(2021, 7, 16));
            movie20.setVoteAverage(6.9);
            movie20.setPopularity(1600.0);


            Movie movie21 = new Movie();
            movie21.setMdbId(1050);
            movie21.setMovieTitle("film 21");
            movie21.setOverview(
                    "Der var en test film 21"
            );
            movie21.setReleaseDate(LocalDate.of(2023, 3, 15));
            movie21.setVoteAverage(7.4);
            movie21.setPopularity(3200.0);


            Movie movie22 = new Movie();
            movie22.setMdbId(1100);
            movie22.setMovieTitle("film 22");
            movie22.setOverview(
                    "Der var en test film 22"
            );
            movie22.setReleaseDate(LocalDate.of(2024, 1, 28));
            movie22.setVoteAverage(8.2);
            movie22.setPopularity(7600.0);


            Movie movie23 = new Movie();
            movie23.setMdbId(1150);
            movie23.setMovieTitle("film 23");
            movie23.setOverview(
                    "Der var en test film 23"
            );
            movie23.setReleaseDate(LocalDate.of(2023, 10, 5));
            movie23.setVoteAverage(6.3);
            movie23.setPopularity(1400.0);


            Movie movie24 = new Movie();
            movie24.setMdbId(1200);
            movie24.setMovieTitle("film 24");
            movie24.setOverview(
                    "Der var en test film 24"
            );
            movie24.setReleaseDate(LocalDate.of(2025, 5, 20));
            movie24.setVoteAverage(9.0);
            movie24.setPopularity(14500.0);


            Movie movie25 = new Movie();
            movie25.setMdbId(1250);
            movie25.setMovieTitle("film 25");
            movie25.setOverview(
                    "Der var en test film 25"
            );
            movie25.setReleaseDate(LocalDate.of(2024, 11, 12));
            movie25.setVoteAverage(5.7);
            movie25.setPopularity(850.0);



            try {
                em.createNativeQuery("TRUNCATE TABLE movie RESTART IDENTITY CASCADE").executeUpdate();
                em.persist(movie1);
                em.persist(movie2);
                em.persist(movie3);
                em.persist(movie4);
                em.persist(movie5);
                em.persist(movie6);
                em.persist(movie7);
                em.persist(movie8);
                em.persist(movie9);
                em.persist(movie10);
                em.persist(movie11);
                em.persist(movie12);
                em.persist(movie13);
                em.persist(movie14);
                em.persist(movie15);
                em.persist(movie16);
                em.persist(movie17);
                em.persist(movie18);
                em.persist(movie19);
                em.persist(movie20);
                em.persist(movie21);
                em.persist(movie22);
                em.persist(movie23);
                em.persist(movie24);
                em.persist(movie25);

                em.flush();
            } catch (PersistenceException e) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw e;
            }
            em.getTransaction().commit();

            Map<String, Movie> seeded = new LinkedHashMap<>();
            seeded.put("movie1", movie1);
            seeded.put("movie2", movie2);
            seeded.put("movie3", movie3);
            seeded.put("movie4", movie4);
            seeded.put("movie5", movie5);
            seeded.put("movie6", movie6);
            seeded.put("movie7", movie7);
            seeded.put("movie8", movie8);
            seeded.put("movie9", movie9);
            seeded.put("movie10", movie10);
            seeded.put("movie11", movie11);
            seeded.put("movie12", movie12);
            seeded.put("movie13", movie13);
            seeded.put("movie14", movie14);
            seeded.put("movie15", movie15);
            seeded.put("movie16", movie16);
            seeded.put("movie17", movie17);
            seeded.put("movie18", movie18);
            seeded.put("movie19", movie19);
            seeded.put("movie20", movie20);
            seeded.put("movie21", movie21);
            seeded.put("movie22", movie22);
            seeded.put("movie23", movie23);
            seeded.put("movie24", movie24);
            seeded.put("movie25", movie25);

            return seeded;
        }
    }
}