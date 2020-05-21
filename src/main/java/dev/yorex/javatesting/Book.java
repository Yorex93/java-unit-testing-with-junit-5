package dev.yorex.javatesting;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;


/**
 * @author emmanueligbodudu
 * @date 21/05/2020
 */

@Data
@AllArgsConstructor
public class Book implements Comparable<Book> {

    private final String title;
    private final String author;
    private final LocalDate publishedOn;

    @Override
    public int compareTo(Book otherBook) {
        return this.title.compareTo(otherBook.title);
    }
}
