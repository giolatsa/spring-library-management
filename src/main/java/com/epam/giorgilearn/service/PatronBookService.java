package com.epam.giorgilearn.service;

import com.epam.giorgilearn.model.PatronBook;

public interface PatronBookService {
    PatronBook createPatronBook(PatronBook patronBook);
    int notifyPatronDueBook();

    void returnBook(Long patronBookId);
}
