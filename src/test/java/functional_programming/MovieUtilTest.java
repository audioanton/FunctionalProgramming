package functional_programming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
public class MovieUtilTest {
    MovieUtil m = new MovieUtil();
    List<Movie> testMovies = MovieUtil.getTestMovieList();
    List<Movie> emptyTest = new ArrayList<>();
    Movie testMovie = new Movie("1", "1", 1, List.of("1", "1"), "1", List.of("1", "1"), 1, List.of("1", "1"), 1);

    @Test
    public void getTestMovieList() {
        List<Movie> test = MovieUtil.getTestMovieList();
        assertNotNull(test);
        assertEquals(5, test.size());
        assertEquals("Rambo", test.getFirst().getTitle());
        assertEquals("Wall-E", test.getLast().getTitle());
        assertEquals("Lasse", test.getFirst().getDirector());
        assertEquals(3,test.getLast().getGenres().size());
    }

    @Test
    public void countMovies() {
        assertEquals(5, m.countMovies(testMovies));
        assertEquals(0, m.countMovies(emptyTest));
        assertEquals(1, m.countMovies(List.of(testMovie)));
    }

    @Test
    public void longestMovie() {
        assertEquals(0, m.longestMovie(emptyTest));
        assertEquals(210, m.longestMovie(testMovies));
        assertEquals(1, m.longestMovie(List.of(testMovie, testMovie)));
    }

    @Test
    public void countDistinctGenres() {
        assertNotEquals(12, m.countDistinctGenres(testMovies));
        assertEquals(6, m.countDistinctGenres(testMovies));
        assertEquals(0, m.countDistinctGenres(emptyTest));
        assertEquals(1, m.countDistinctGenres(List.of(testMovie, testMovie)));
    }

    @Test
    public void getActorsInBestMovie() {
        List<String> actors = m.getActorsInBestMovie(testMovies);
        assertEquals("Bingo", actors.getFirst());
        assertEquals("Statist", actors.getLast());
        assertEquals(3, actors.size());
        assertFalse(actors.contains("Hermione"));

        //Test om fler filmer har samma betyg
    }

    @Test
    public void getMovieTitleForLeastActors() {
        assertNotEquals("Harry Potter", m.getMovieTitleForLeastActors(testMovies));
        assertEquals("Rambo", m.getMovieTitleForLeastActors(testMovies));
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
        assertThrows(NoSuchElementException.class, () -> m.getActorMostFeatured(emptyTest));
    }

    @Test
    public void countDistinctLanguages() {
        assertNotEquals(13, m.countDistinctLanguages(testMovies));
        assertEquals(5, m.countDistinctLanguages(testMovies));
        assertEquals(0, m.countDistinctLanguages(emptyTest));
    }

    @Test
    public void existsDuplicateTitle() {
        assertFalse(m.existsDuplicateTitle(testMovies));
        testMovies.add(new Movie("5", "title", 1, null, "director", null, 1, null, 1));
        testMovies.add(new Movie("5", "title", 1, null, "director", null, 1, null, 1));
        assertTrue(m.existsDuplicateTitle(testMovies));
    }
}