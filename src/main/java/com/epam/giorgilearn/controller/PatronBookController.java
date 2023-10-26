package com.epam.giorgilearn.controller;

import com.epam.giorgilearn.model.PatronBook;
import com.epam.giorgilearn.service.PatronBookService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/patron-book")
public class PatronBookController {

    private final PatronBookService patronBookService;

    public PatronBookController(PatronBookService patronBookService) {
        this.patronBookService = patronBookService;
    }


    @PostMapping("/create")
    public PatronBook patronBookCreate(@RequestBody PatronBook patronBook){


        return patronBookService.createPatronBook(patronBook);
    }

    @GetMapping("/notify/all")
    public int notifyAllPatrons(){
       return patronBookService.notifyPatronDueBook();
    }

    @PutMapping("/return/{patronBookId}")
    public void returnBook(@PathVariable Long patronBookId){

        patronBookService.returnBook(patronBookId);
    }



}
