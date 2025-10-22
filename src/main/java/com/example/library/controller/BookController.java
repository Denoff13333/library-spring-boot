package com.example.library.controller;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
@Controller @RequestMapping({"/","/books"})
public class BookController{
  private final BookService service;
  public BookController(BookService service){this.service=service;}
  @GetMapping public String list(@RequestParam(value="q",required=false) String q, Model model){
    List<Book> books=(q==null)?service.findAll():service.searchByTitle(q);
    model.addAttribute("books",books); model.addAttribute("q", q==null? "": q); return "books/list";
  }
  @GetMapping("/new") public String createForm(Model model){ model.addAttribute("book",new Book()); model.addAttribute("title","Добавить книгу"); return "books/form";}
  @PostMapping public String create(@Valid @ModelAttribute("book") Book book, BindingResult errors, RedirectAttributes ra){
    if(errors.hasErrors()) return "books/form"; service.save(book); ra.addFlashAttribute("success","Книга добавлена"); return "redirect:/books";
  }
  @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model){
    Book b=service.findById(id).orElseThrow(()->new IllegalArgumentException("Книга не найдена: id="+id));
    model.addAttribute("book",b); model.addAttribute("title","Редактировать книгу"); return "books/form";
  }
  @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute("book") Book book, BindingResult errors, RedirectAttributes ra){
    if(errors.hasErrors()) return "books/form"; book.setId(id); service.save(book); ra.addFlashAttribute("success","Книга обновлена"); return "redirect:/books";
  }
  @PostMapping("/{id}/delete") public String delete(@PathVariable Long id, RedirectAttributes ra){ service.deleteById(id); ra.addFlashAttribute("success","Книга удалена"); return "redirect:/books";}
  @ExceptionHandler(IllegalArgumentException.class) public String handle(IllegalArgumentException ex, Model model){ model.addAttribute("message",ex.getMessage()); return "error"; }
}
