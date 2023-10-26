package com.epam.giorgilearn.service;

import com.epam.giorgilearn.model.Patron;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PatronService {

    List<Patron> getPatrons();


    Patron createPatron(Patron patron);


    Patron updatePatron(Patron patron);

    void deletePatron(Long id);
}
