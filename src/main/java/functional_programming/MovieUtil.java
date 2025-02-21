package functional_programming;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieUtil {

// Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    public long countMovies(List<Movie> movies, int year) {
        return movies.stream().filter(movie -> movie.getYear() == year).count();
    }

// Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
//    Higher Order Function
    public int highestIntValueInMovie(List<Movie> movies, Function<Movie, Integer> getValue) {
        return movies.stream().map(getValue::apply).max(Integer::compareTo).orElse(0);
    }

// Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
// Hur många UNIKA språk har filmerna? Returnera ett tal.
//    Higher Order Function
    public long countDistinctInFlatMap(List<Movie> movies, Function<Movie, List<String>> function) {
        return movies.stream().flatMap(m -> function.apply(m).stream()).distinct().count();
    }

//     Vad hette den skådis som var med i flest filmer? Returnera en String
    public List<String> getActorsMostFeatured(List<Movie> movies) {
        return movies.stream().flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }

    public String getActorMostFeatured(List<Movie> movies) {
        return movies.stream().flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("No actor found");
    }

// Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en
//    List<String> med deras namn.
    public List<String> getActorsInBestMovie(List<Movie> movies) {
        return movies.stream().collect(Collectors.groupingBy(Movie::getImdbRating))
                .entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getKey))
                .map(Map.Entry::getValue).orElse(Collections.emptyList())
                .stream().flatMap(movie -> movie.getCast().stream())
                .toList();
//        return movies.stream().reduce((a, b) -> a.getImdbRating() > b.getImdbRating() ? a : b).get().getCast();
}

// Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
    public String getMovieTitleForLeastActors(List<Movie> movies) {
        return movies.stream().min(Comparator.comparingInt(m -> m.getCast().size()))
                .map(Movie::getTitle)
                .orElse("No title found");
    }

// Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public long countActorsFeaturedInMultipleMovies(List<Movie> movies) {
        return movies.stream().flatMap(m -> m.getCast().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .values().stream()
                .filter(movieCount -> movieCount > 1)
                .count();
    }

// Finns det någon titel som mer än en film har? Returnera en bool.
    public boolean existsDuplicateTitle(List<Movie> movies) {
        return movies.stream().collect(Collectors.groupingBy(Movie::getTitle, Collectors.counting()))
                .values().stream()
                .anyMatch(titleCount -> titleCount > 1);
    }
}
