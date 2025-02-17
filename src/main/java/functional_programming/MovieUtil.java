package functional_programming;

import java.util.*;
import java.util.stream.Collectors;

public class MovieUtil {

// Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    public long countMovies(List<Movie> movies) {
        return movies.stream().map(Movie::getId).distinct().count();
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
//        movies.stream().collect(Collectors.groupingBy(Movie::getImdbRating, Collectors.m),

//        movies.stream().max(Comparator.comparingDouble(Movie::getImdbRating)).collect(Collectors.groupingBy(Movie::getImdbRating, Collectors.)).values().stream().filter()

        return movies.stream().reduce((a, b) -> a.getImdbRating() > b.getImdbRating() ? a : b).get().getCast();
}

// Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
    public String getMovieTitleForLeastActors(List<Movie> movies) {
        return movies.stream().reduce((a, b) -> a.getCast().size() < b.getCast().size() ? a : b).orElseThrow().getTitle();
    }

// Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public long countActorsFeaturedInMultipleMovies(List<Movie> movies) {
        return movies.stream().flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .values().stream().filter(i -> i > 1)
                .count();
    }

// Vad hette den skådis som var med i flest filmer? Returnera en String
    public String getActorMostFeatured(List<Movie> movies) {
        return movies.stream().flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(entry -> entry.getValue()))
                .orElseThrow()
                .getKey();
    }

// Hur många UNIKA språk har filmerna? Returnera ett tal.
    public long countDistinctLanguages(List<Movie> movies) {
        return movies.stream().flatMap(m -> m.getLanguages().stream()).distinct().count();
    }

// Finns det någon titel som mer än en film har? Returnera en bool.
    public boolean existsDuplicateTitle(List<Movie> movies) {
        return movies.stream().collect(Collectors.groupingBy(Movie::getTitle, Collectors.counting()))
                .values().stream().anyMatch(i -> i > 1);
    }

    public static List<Movie> getTestMovieList() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("1", "Rambo", 1999, List.of("drama", "romcom"), "Lasse", List.of("Sylvester"), 10.0, List.of("English", "Svenska", "Danish"), 103));
        movies.add(new Movie("2", "Harry Potter", 2003, List.of("fantasy", "romcom"), "Jessica", List.of("Hermione", "Statist", "Pistoler"), 10.0, List.of("English", "Svenska", "Chinese"), 15));
        movies.add(new Movie("3", "Dumbo", 1963, List.of("drama", "romcom", "animation"), "Lasse", List.of("Elefanto", "Statist", "Pistoler"), 100.0, List.of("English", "Svenska", "Francaise"), 210));
        movies.add(new Movie("4", "Alien", 1999, List.of("horror, romcom"), "Jessica", List.of("Bingo", "Ufo", "Statist"), 1000.0, List.of("English", "Svenska"), 143));
        movies.add(new Movie("5", "Wall-E", 1999, List.of("drama", "romcom", "action"), "Lasse", List.of("Robot", "Eva"), 50.43, List.of("English", "Svenska"), 67));
        return movies;
    }
}
