package functional_programming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import static org.junit.Assert.*;

public class MovieUtilTest {
    MovieUtil m = new MovieUtil();

    final List<Movie> testMovies = List.of(
            new Movie("1", "Rambo", 1999, List.of("drama", "romcom"), "Lasse", List.of("Sylvester"), 10.0, List.of("English", "Svenska", "Danish"), 103),
            new Movie("2", "Harry Potter", 2003, List.of("fantasy", "romcom"), "Jessica", List.of("Hermione", "Statist", "Pistoler"), 10.0, List.of("English", "Svenska", "Chinese"), 15),
            new Movie("3", "Dumbo", 1975, List.of("drama", "romcom", "animation"), "Lasse", List.of("Elefanto", "Statist", "Pistoler"), 100.0, List.of("English", "Svenska", "Francaise"), 210),
            new Movie("4", "Alien", 1999, List.of("horror, romcom"), "Jessica", List.of("Bingo", "Ufo", "Statist"), 1000.0, List.of("English", "Svenska"), 143),
            new Movie("5", "Wall-E", 1999, List.of("drama", "romcom", "action"), "Lasse", List.of("Robot", "Eva"), 50.43, List.of("English", "Svenska"), 67)
    );
    final List<Movie> emptyTest = Collections.emptyList();
    final Movie testMovie = new Movie("1", "1", 1, List.of("1", "1"), "1", List.of("1", "1"), 1, List.of("1", "1"), 1);


    @Test
    public void countMovies() {
        assertEquals(1, m.countMovies(testMovies, 1975));
        assertEquals(0, m.countMovies(emptyTest, 1975));
        assertEquals(0, m.countMovies(List.of(testMovie), 1975));
    }

    @Test
    public void highestIntValueInMovie() {
//        longest runtime
        assertEquals(0, m.highestIntValueInMovie(emptyTest, Movie::getRuntime));
        assertEquals(210, m.highestIntValueInMovie(testMovies, Movie::getRuntime));
        assertEquals(1, m.highestIntValueInMovie(List.of(testMovie, testMovie), Movie::getRuntime));

//        number of largest cast
        assertEquals(3, m.highestIntValueInMovie(testMovies, m -> m.getCast().size()));
//        most characters in title
        assertEquals(12, m.highestIntValueInMovie(testMovies, m -> m.getTitle().length()));
    }

    @Test
    public void countDistinctInFlatMap() {
        assertNotEquals(12, m.countDistinctInFlatMap(testMovies, Movie::getGenres));
        assertEquals(6, m.countDistinctInFlatMap(testMovies, Movie::getGenres));
        assertEquals(0, m.countDistinctInFlatMap(emptyTest, Movie::getGenres));
        assertEquals(1, m.countDistinctInFlatMap(List.of(testMovie, testMovie), Movie::getGenres));

        assertNotEquals(13, m.countDistinctInFlatMap(testMovies, Movie::getLanguages));
        assertEquals(5, m.countDistinctInFlatMap(testMovies, Movie::getLanguages));
        assertEquals(0, m.countDistinctInFlatMap(emptyTest, Movie::getLanguages));
    }

    @Test
    public void getActorsInBestMovie() {
        List<String> actors = m.getActorsInBestMovie(testMovies);
        assertEquals("Bingo", actors.getFirst());
        assertEquals("Statist", actors.getLast());
        assertEquals(3, actors.size());
        assertFalse(actors.contains("Hermione"));

        List<Movie> movies = new ArrayList<>(testMovies);

        movies.add(new Movie("7", "Kopps", 1999, List.of("horror, romcom"), "Jessica", List.of("Anton", "Bert", "Fisk"), 1000.0, List.of("English", "Svenska"), 143));
        List<String> actorsTwo = m.getActorsInBestMovie(movies);
        assertEquals(6, actorsTwo.size());
        assertEquals("Fisk", actorsTwo.getLast());
        assertEquals(0, m.getActorsInBestMovie(emptyTest).size());
    }

    @Test
    public void getMovieTitleForLeastActors() {
        assertNotEquals("Harry Potter", m.getMovieTitleForLeastActors(testMovies));
        assertEquals("Rambo", m.getMovieTitleForLeastActors(testMovies));
        assertEquals("No title found", m.getMovieTitleForLeastActors(emptyTest));
    }

    @Test
    public void countActorsFeaturedInMultipleMovies() {
        assertEquals(2, m.countActorsFeaturedInMultipleMovies(testMovies));
        assertEquals(0, m.countActorsFeaturedInMultipleMovies(emptyTest));
    }

    @Test
    public void getActorMostFeatured() {
        assertNotEquals("Pistoler", m.getActorMostFeatured(testMovies));
        assertEquals("Statist", m.getActorMostFeatured(testMovies));
        assertEquals("No actor found", m.getActorMostFeatured(emptyTest));
    }

    @Test
    public void getActorsMostFeatured() {
        assertEquals("Statist", m.getActorsMostFeatured(testMovies).getFirst());
        assertEquals(1, m.getActorsMostFeatured(testMovies).size());
        assertEquals(0, m.getActorsMostFeatured(emptyTest).size());

        List<Movie> movies = new ArrayList<>(testMovies);
        String newActor = "Kalle Anka";
        String otherActor = "Mimmi Pigg";

        IntStream.range(10,15).forEach(i ->
                movies.add(new Movie(String.valueOf(i), "Kvack!", i, null, null, List.of(newActor), 100, null, 100))
        );
        assertEquals(newActor, m.getActorsMostFeatured(movies).getFirst());
        assertEquals(1, m.getActorsMostFeatured(movies).size());

        IntStream.rangeClosed(16, 20).forEach(i ->
                movies.add(new Movie(String.valueOf(i), "Kvack!", i, null, null, List.of(otherActor), 100, null, 100))
        );
        assertEquals(2, m.getActorsMostFeatured(movies).size());
    }

    @Test
    public void existsDuplicateTitle() {
        assertFalse(m.existsDuplicateTitle(testMovies));
        List<Movie> movies = new ArrayList<>(testMovies);
        movies.add(new Movie("5", "title", 1, null, "director", null, 1, null, 1));
        movies.add(new Movie("5", "title", 1, null, "director", null, 1, null, 1));
        assertTrue(m.existsDuplicateTitle(movies));
        assertFalse(m.existsDuplicateTitle(emptyTest));
    }

}