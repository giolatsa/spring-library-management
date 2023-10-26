package com.epam.giorgilearn.service;

import com.epam.giorgilearn.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();


    Book createBook( Book book);


    Book updateBook(Book book);


    void deleteBook( Long id);

    List<Book> searchBooks(String title, String author, String ISBN, String genre, String sortBy, String sortOrder);
}
