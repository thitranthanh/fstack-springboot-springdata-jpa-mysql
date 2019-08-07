package vn.fstack.springboot.controller;

import java.util.List;

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

import vn.fstack.springboot.entity.BookEntity;
import vn.fstack.springboot.repository.BookRepository;

@RestController
@RequestMapping(value = { "book" })
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<BookEntity>> getAllBook() {
		List<BookEntity> books = bookRepository.findAll();
		return new ResponseEntity<List<BookEntity>>(books, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BookEntity> getBookById(@PathVariable("id") int id) {
		BookEntity book = bookRepository.findById(id).get();
        if (book == null) {
            return new ResponseEntity<BookEntity>(book, HttpStatus.NOT_FOUND);
        }
		
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<BookEntity> postCreateNewBook(@RequestBody BookEntity book) {
		BookEntity bookCreated = bookRepository.save(book);
		return new ResponseEntity<BookEntity>(bookCreated, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BookEntity> putCreateNewBook(@PathVariable("id") int id, @RequestBody BookEntity book) {
		
		BookEntity found = bookRepository.findById(book.getId()).get();
		if (found == null) {
			return new ResponseEntity<BookEntity>(book, HttpStatus.NOT_FOUND);
		}
		
		BookEntity bookUpdated = new BookEntity();
		return new ResponseEntity<BookEntity>(bookUpdated, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<BookEntity> deleteBookById(@PathVariable("id") int id) {
		BookEntity bookDeleted = new BookEntity();
		return new ResponseEntity<BookEntity>(bookDeleted, HttpStatus.OK);
	}

}
