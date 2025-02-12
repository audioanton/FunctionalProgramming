package functional_programming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class MovieUtilTest {
    MovieUtil m = new MovieUtil();
    List<Movie> testMovies = MovieUtil.getTestMovieList();
    List<Movie> emptyTest = new ArrayList<>();

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
    }

    @Test
    public void longestMovie() {
        assertEquals(0, m.longestMovie(emptyTest));
        assertEquals(210, m.longestMovie(testMovies));
    }


    @Test
    public void countDistinctGenres() {
        assertNotEquals(12, m.countDistinctGenres(testMovies));
        assertEquals(6, m.countDistinctGenres(testMovies));
        assertEquals(0, m.countDistinctGenres(emptyTest));
    }

    @Test
    public void getActorsInBestMovie() {
        List<String> actors = m.getActorsInBestMovie(testMovies);
        assertEquals("Bingo", actors.getFirst());
        assertEquals("Ufo", actors.getLast());
        assertEquals(2, actors.size());
        assertFalse(actors.contains("Hermione"));
    }

    @Test
    public void getMovieTitleForLeastActors() {
        assertNotEquals("Harry Potter", m.getMovieTitleForLeastActors(testMovies));
        assertEquals("Rambo", m.getMovieTitleForLeastActors(testMovies));
    }

    @Test
    public void countActorsFeaturedInMultipleMovies() {
    }

    @Test
    public void getActorMostFeatured() {
    }

    @Test
    public void countDistinctLanguages() {
    }

    @Test
    public void existsDuplicateTitle() {
    }
}