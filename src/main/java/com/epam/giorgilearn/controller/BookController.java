package com.epam.giorgilearn.controller;

import com.epam.giorgilearn.model.Book;
import com.epam.giorgilearn.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/get")
    public List<Book> getBooks(){

        return bookService.getBooks();



    }

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam( required = false) String title,
            @RequestParam( required = false) String author,
            @RequestParam( required = false) String ISBN,
            @RequestParam( required = false) String genre,
            @RequestParam( required = false) String sortBy,
            @RequestParam( required = false) String sortOrder) {

        return bookService.searchBooks(title, author,ISBN,genre, sortBy, sortOrder);
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book){

        return bookService.createBook(book);



    }


    @PutMapping ("/update")
    public Book updateBook(@RequestBody Book book){

        return bookService.updateBook(book);



    }

    @DeleteMapping ("/delete/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long id){

        bookService.deleteBook(id);



    }


}
