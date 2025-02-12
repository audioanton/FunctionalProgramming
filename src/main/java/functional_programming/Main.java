package functional_programming;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Repository repository = new Repository();
//            repository.getMoviesByField("year",null, 1975).stream().map(Movie::getTitle).toList().forEach(System.out::println);
//            repository.getMoviesByField("languages","Japanese", 0).stream().map(Movie::getLanguages).toList().forEach(System.out::println);

            repository.getMoviesByField("languages","Japanese",0).stream().map(Movie::getTitle).forEach(System.out::println);
            System.out.println("\n///////\n");
            repository.getMoviesByField("year",null,1975).stream().map(Movie::getTitle).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}