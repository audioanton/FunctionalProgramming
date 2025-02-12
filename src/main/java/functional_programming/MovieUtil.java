package functional_programming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieUtil {

// Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    public long countMovies(List<Movie> movies) {
        return movies.stream().map(Movie::getId).distinct().count(); //movies.size() bättre..
    }

// Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
    public int longestMovie(List<Movie> movies) {
        return movies.stream().map(Movie::getRuntime).max(Integer::compareTo).orElse(0);
    }

// Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
    public long countDistinctGenres(List<Movie> movies) {
        return movies.stream().flatMap(m -> m.getGenres().stream()).distinct().count();
    }

// Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en
//    List<String> med deras namn.
    public List<String> getActorsInBestMovie(List<Movie> movies) {
        return movies.stream().sorted(Comparator.comparingDouble(Movie::getImdbRating).reversed()).limit(1).flatMap(m -> m.getCast().stream()).toList();
}

// Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
    public String getMovieTitleForLeastActors(List<Movie> movies) {
        // collect, groupingBy ??
//        return movies.stream().collect(Collectors.groupingBy(Movie::getTitle))
//        return movies.stream().flatMap(m -> m.getCast().stream()).map(Movie::getTitle);
        throw new UnsupportedOperationException("Not implemented");
    }

// Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public int countActorsFeaturedInMultipleMovies(List<Movie> movies) {
        throw new UnsupportedOperationException("Not implemented");
    }

// Vad hette den skådis som var med i flest filmer? Returnera en String
    public String getActorMostFeatured(List<Movie> movies) {
        throw new UnsupportedOperationException("Not implemented");
    }

// Hur många UNIKA språk har filmerna? Returnera ett tal.
    public int countDistinctLanguages(List<Movie> movies) {
        throw new UnsupportedOperationException("Not implemented");
    }

// Finns det någon titel som mer än en film har? Returnera en bool.
    public boolean existsDuplicateTitle(List<Movie> movies) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static List<Movie> getTestMovieList() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(
                "1",
                "Rambo",
                1999,
                List.of("drama", "romcom"),
                "Lasse",
                List.of("Sylvester"),
                10.0,
                List.of("English", "Svenska"),
                103));
        movies.add(new Movie(
                "2",
                "Harry Potter",
                2003,
                List.of("fantasy", "romcom"),
                "Jessica",
                List.of("Hermione", "Statist", "Pistoler"),
                10.0,
                List.of("English", "Svenska"),
                15));
        movies.add(new Movie(
                "3",
                "Dumbo",
                1963,
                List.of("drama", "romcom", "animation"),
                "Lasse",
                List.of("Elefanto", "Statist", "Pistoler"),
                100.0,
                List.of("English", "Svenska"),
                210));
        movies.add(new Movie(
                "4",
                "Alien",
                1999,
                List.of("horror, romcom"),
                "Jessica",
                List.of("Bingo", "Ufo"),
                1000.0,
                List.of("English", "Svenska"),
                143));
        movies.add(new Movie(
                "5",
                "Wall-E",
                1999,
                List.of("drama", "romcom", "action"),
                "Lasse",
                List.of("Robot", "Eva"),
                50.43,
                List.of("English", "Svenska"),
                67));
        return movies;
    }
}
