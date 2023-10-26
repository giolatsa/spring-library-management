package com.epam.giorgilearn.service;

import com.epam.giorgilearn.model.Patron;
import com.epam.giorgilearn.model.PatronBook;
import com.epam.giorgilearn.repository.BookRepository;
import com.epam.giorgilearn.repository.PatronBookRepository;
import com.epam.giorgilearn.repository.PatronRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class PatronBookServiceImpl implements PatronBookService{

    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;
    private final PatronBookRepository patronBookRepository;

    public PatronBookServiceImpl(PatronRepository patronRepository, BookRepository bookRepository, PatronBookRepository patronBookRepository) {
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
        this.patronBookRepository = patronBookRepository;
    }

    @Override
    public PatronBook createPatronBook(PatronBook patronBook) {
        if(!patronRepository.existsById(patronBook.getPatronId())){
            throw new ObjectNotFoundException(patronBook.getPatronId(),"patron");

        }

        if(!bookRepository.existsById(patronBook.getBookId())){
            throw new ObjectNotFoundException(patronBook.getBookId(),"book");

        }

        patronBook.setReturned(false);

        return patronBookRepository.save(patronBook);
    }

    @Override
    public int notifyPatronDueBook() {
        //passing current date to check if there are any overdue
        List<PatronBook> duePatronBooks = patronBookRepository.findAllByReturnedIsFalseAndDueDateIsLessThan(new Date());

        //Using set to make sure we dont spam someone who has multiple overdue books
        Set<Patron> patronsToNotify= new HashSet<>(patronRepository.findAllById(
                duePatronBooks.stream().map(PatronBook::getPatronId).collect(Collectors.toList())
        ));


        for (Patron patron : patronsToNotify) {

            String contactInfo=patron.getContactInfo();

            //Implement logic to send email or sms to contact info
            // emailService.sendNotifyEmail(contactInfo)
        }

        return patronsToNotify.size();

    }

    @Override
    public void returnBook(Long patronBookId) {
        Optional<PatronBook> patronBookOptional = patronBookRepository.findById(patronBookId);

        patronBookOptional.ifPresent(patronBook->{
            patronBook.setReturned(true);
            patronBookRepository.save(patronBook);
        });

    }
}
