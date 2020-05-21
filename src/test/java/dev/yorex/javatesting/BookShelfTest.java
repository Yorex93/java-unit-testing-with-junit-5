package dev.yorex.javatesting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author emmanueligbodudu
 * @date 22/05/2020
 */

@DisplayName("Book Shelf Tests")
@ExtendWith(BooksParameterResolver.class)
class BookShelfTest {

    private BookShelf bookShelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init(Map<String, Book> books) {
        bookShelf = new BookShelf();
        this.effectiveJava = books.get("Effective Java");
        this.codeComplete = books.get("Code Complete");
        this.mythicalManMonth = books.get("The Mythical Man-Month");
        this.cleanCode = books.get("Clean Code");
    }

    @Nested
    @DisplayName("When Empty")
    class EmptyBookShelf {

        @Test
        @DisplayName("Shelf should be empty when no books added")
        public void shelfEmptyWhenNoBooksAdded() {
            assertThat(bookShelf.books()).isEmpty();
        }

        @Test
        @DisplayName("Empty Bookshelf should still be empty when add called without books")
        public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
            bookShelf.add();
            assertThat(bookShelf.books()).isEmpty();
        }
    }

    @Nested
    @DisplayName("After adding books")
    class BookAdded {
        @Test
        @DisplayName("Should contain two books when two books are added")
        public void containsTwoBooksWhenTwoBooksAdded() {
            bookShelf.add(effectiveJava, codeComplete);
            assertThat(bookShelf.books().size()).isEqualTo(2);
        }

        @Test
        @DisplayName("Should not be able to mutate list of books returned from bookshelf")
        public void booksReturnedFromBookShelfIsImmutableForClient() {
            bookShelf.add(effectiveJava, codeComplete);
            List<Book> books = bookShelf.books();
            try {
                books.add(mythicalManMonth);
                fail(() -> "Should not be able to add book to books");
            } catch (Exception e) {
                assertThat(e).isInstanceOf(UnsupportedOperationException.class);
            }
        }
    }

    @Nested
    @DisplayName("When arranging books")
    class ArrangingBooks {
        @Test
        @DisplayName("Books should be arranged by title when arrange method called")
        void bookshelfArrangedByBookTitle() {
            bookShelf.add(effectiveJava, mythicalManMonth, codeComplete);
            List<Book> books = bookShelf.arrange();
            assertThat(books).containsSequence(codeComplete, effectiveJava, mythicalManMonth);
        }

        @Test
        @DisplayName("Books in shelf order should not be modified after calling arrange")
        void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
            bookShelf.add(effectiveJava, codeComplete, mythicalManMonth);
            bookShelf.arrange();
            List<Book> books = bookShelf.books();
            assertThat(books).containsSequence(effectiveJava, codeComplete, mythicalManMonth);
        }
    }
}
