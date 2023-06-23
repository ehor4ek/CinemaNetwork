package src;

import java.util.Scanner;

class MovieList extends ObjectList<Movie> {
    MovieList() {
        super("movies.txt");
    }

    @Override
    protected Movie createObj(String buf, Scanner scanner) {
        return new Movie(buf, scanner);
    }

    public void write() {
        write("movies.txt");
    }
}