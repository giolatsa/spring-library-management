package com.epam.giorgilearn.repository;

import com.epam.giorgilearn.model.PatronBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PatronBookRepository extends JpaRepository<PatronBook,Long> {

    List<PatronBook> findAllByReturnedIsFalseAndDueDateIsLessThan(Date date);
}
