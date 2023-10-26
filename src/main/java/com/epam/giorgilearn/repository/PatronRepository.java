package com.epam.giorgilearn.repository;

import com.epam.giorgilearn.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron,Long> {
}
