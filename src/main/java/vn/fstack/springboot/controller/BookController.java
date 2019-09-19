package vn.fstack.springboot.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mysql.cj.log.Log;

import vn.fstack.springboot.entity.BookEntity;
import vn.fstack.springboot.repository.BookRepository;

@RestController
@RequestMapping(value = { "book" })
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@GetMapping
	public ResponseEntity<List<BookEntity>> getAllBook() {
		List<BookEntity> books = bookRepository.findAll();
		return new ResponseEntity<List<BookEntity>>(books, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BookEntity> getBookById(@PathVariable("id") long id) {
		Optional<BookEntity> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            return new ResponseEntity("Not found book with id=" + id, HttpStatus.NOT_FOUND);
        }
		
		return new ResponseEntity<>(book.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BookEntity> postCreateNewBook(@RequestBody BookEntity book) {
		book.setCreated(new Date());
		book.setUpdated(new Date());
		BookEntity bookCreated = bookRepository.save(book);
		return new ResponseEntity<BookEntity>(bookCreated, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BookEntity> putCreateNewBook(@PathVariable("id") long id, @RequestBody BookEntity bookUpdate) {
		
		Optional<BookEntity> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            return new ResponseEntity("Not found book with id=" + id, HttpStatus.NOT_FOUND);
        }
		
		BookEntity found = book.get();
		found.setTitle(bookUpdate.getTitle());
		found.setAuthor(bookUpdate.getAuthor());
		found.setDescription(bookUpdate.getDescription());
		found.setStatus(bookUpdate.getStatus());
		bookRepository.saveAndFlush(found);
		return new ResponseEntity<BookEntity>(found, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<BookEntity> deleteBookById(@PathVariable("id") long id) {
		
		Optional<BookEntity> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            return new ResponseEntity("Not found book with id=" + id, HttpStatus.NOT_FOUND);
        }
		
		BookEntity bookDeleted = book.get();
		bookRepository.deleteById(id);
		return new ResponseEntity<BookEntity>(bookDeleted, HttpStatus.OK);
	}

}
