package com.epam.giorgilearn.controller;


import com.epam.giorgilearn.model.Patron;
import com.epam.giorgilearn.service.PatronService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patron")
public class PatronController{


    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping("/get")
    public List<Patron> getPatrons(){

        return patronService.getPatrons();



    }

    @PostMapping("/create")
    public Patron createPatron(@RequestBody Patron patron){

        return patronService.createPatron(patron);



    }


    @PutMapping ("/update")
    public Patron updatePatron(@RequestBody Patron patron){

        return patronService.updatePatron(patron);



    }

    @DeleteMapping ("/delete/{patronId}")
    public void deletePatron(@PathVariable("patronId") Long id){

        patronService.deletePatron(id);



    }

}
