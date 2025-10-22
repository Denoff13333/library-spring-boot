package com.example.library.service;
import com.example.library.model.Book;
import java.util.List; import java.util.Optional;
public interface BookService{
  List<Book> findAll(); Optional<Book> findById(Long id); Book save(Book book);
  void deleteById(Long id); List<Book> searchByTitle(String q);
}
