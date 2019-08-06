package vn.fstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fstack.springboot.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {	

}
