package com.epam.giorgilearn.service;

import com.epam.giorgilearn.model.Book;
import com.epam.giorgilearn.repository.BookRepository;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }


    // Used ChatGPT to help me write this method
    @Override
    public List<Book> searchBooks(String title, String author, String ISBN, String genre, String sortBy, String sortOrder) {
        return bookRepository.findAll(getBookSpecification(title, author, ISBN, genre), getSort(sortBy, sortOrder));
    }

    private Specification<Book> getBookSpecification(String title, String author, String ISBN, String genre) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("author"), "%" + author + "%"));
            }
            if (ISBN != null && !ISBN.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("ISBN"), ISBN));
            }
            if (genre != null && !genre.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("genre"), "%" + genre + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Sort getSort(String sortBy, String sortOrder) {
        Sort.Direction direction = (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(direction, (sortBy != null) ? sortBy : "id"); // default sortBy is 'id'
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        // for this example we dont wish to upsert
        if(!bookRepository.existsById(book.getId())){
            throw new ObjectNotFoundException(book.getId(),"Book");
        }

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {

        if(!bookRepository.existsById(id)){
            throw new ObjectNotFoundException(id,"Book");
        }


        bookRepository.deleteById(id);
    }
}
