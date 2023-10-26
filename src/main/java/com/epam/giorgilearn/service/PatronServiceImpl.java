package com.epam.giorgilearn.service;

import com.epam.giorgilearn.model.Patron;
import com.epam.giorgilearn.repository.PatronRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatronServiceImpl implements PatronService{

    private final PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public List<Patron> getPatrons() {
        return patronRepository.findAll();
    }

    @Override
    public Patron createPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Override
    public Patron updatePatron(Patron patron) {
        // for this example we dont wish to upsert
        if (!patronRepository.existsById(patron.getId())){
            throw new ObjectNotFoundException(patron.getId(),"Patron");
        }


        return patronRepository.save(patron);
    }

    @Override
    public void deletePatron(Long id) {

        if (!patronRepository.existsById(id)){
            throw new ObjectNotFoundException(id,"Patron");
        }

        patronRepository.deleteById(id);


    }
}
