package dev.yorex.javatesting;

import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author emmanueligbodudu
 * @date 21/05/2020
 */
public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... booksToAdd) {
        books.addAll(Arrays.asList(booksToAdd));
    }

    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(@NonNull Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }
}
