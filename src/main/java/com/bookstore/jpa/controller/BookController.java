package com.bookstore.jpa.controller;

import com.bookstore.jpa.dtos.BookModelDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getallbook")
    public ResponseEntity<List<BookModel>> getAllBook() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PostMapping("/savebook")
    public ResponseEntity<BookModel> saveBook(@RequestBody BookModelDto bookModelDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookModelDto));
    }
    
    @DeleteMapping("/saveBook")
    public ResponseEntity deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book deleted.");
    }

}
