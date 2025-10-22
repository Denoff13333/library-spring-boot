package com.example.library.service;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class BookServiceImpl implements BookService{
  private final BookRepository repository;
  public BookServiceImpl(BookRepository repository){this.repository=repository;}
  public List<Book> findAll(){return repository.findAll();}
  public Optional<Book> findById(Long id){return repository.findById(id);}
  public Book save(Book b){return repository.save(b);}
  public void deleteById(Long id){repository.deleteById(id);}
  public List<Book> searchByTitle(String q){return (q==null||q.isBlank())?repository.findAll():repository.findByTitleContainingIgnoreCase(q.trim());}
}
