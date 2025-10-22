package com.example.library.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
@Entity @Table(name="books")
public class Book {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message="Название обязательно") @Column(nullable=false)
  private String title;
  @NotBlank(message="Автор обязателен") @Column(nullable=false)
  private String author;
  @Min(value=1,message="Год должен быть > 0") @Column(name="publish_year",nullable=false)
  private int publishYear;
  public Book(){}
  public Book(String title,String author,int publishYear){this.title=title;this.author=author;this.publishYear=publishYear;}
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getTitle(){return title;} public void setTitle(String t){this.title=t;}
  public String getAuthor(){return author;} public void setAuthor(String a){this.author=a;}
  public int getPublishYear(){return publishYear;} public void setPublishYear(int y){this.publishYear=y;}
}
