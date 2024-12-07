package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookModelDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repository.AuthorRepository;
import com.bookstore.jpa.repository.BookRepository;
import com.bookstore.jpa.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public BookModel saveBook(BookModelDto bookModelDto) {
        BookModel book = new BookModel();
        book.setTitle(bookModelDto.title());
        book.setPublisher(publisherRepository.findById(bookModelDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookModelDto.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookModelDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);

    }

    @Transactional
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
