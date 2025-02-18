package functional_programming;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Repository repository = new Repository();
            List<Movie> movies = repository.getMoviesByField("year",null,1975);

            MovieUtil util = new MovieUtil();

            System.out.printf("Number of Movies 1975: %d%n", util.countMovies(movies));
            System.out.printf("Longest movie: %d minutes%n", util.highestIntValueInMovie(movies, Movie::getRuntime));
            System.out.printf("Unique genres: %d%n", util.countDistinctInFlatMap(movies, Movie::getGenres));
            System.out.printf("Actors in highest rated movie: %s%n", util.getActorsInBestMovie(movies));
            System.out.printf("Movie with least actors: %s%n", util.getMovieTitleForLeastActors(movies));
            System.out.printf("Number of actors featured in multiple movies: %d%n", util.countActorsFeaturedInMultipleMovies(movies));
            System.out.printf("The actor(s) featured in most movies: %s%n", util.getActorsMostFeatured(movies));
            System.out.printf("Number of languages listed for movies: %d%n", util.countDistinctInFlatMap(movies, Movie::getLanguages));
            System.out.println(util.existsDuplicateTitle(movies) ? "There are duplicate titles in database." : "All titles are unique.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}