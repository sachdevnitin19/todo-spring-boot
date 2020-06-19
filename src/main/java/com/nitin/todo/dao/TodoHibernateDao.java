package com.nitin.todo.dao;

import com.nitin.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//JPARepository contains methods for CRUD operations
//internally it extends CRUDRepository and PaginationAndSortingRepository
@Repository
public interface TodoHibernateDao extends JpaRepository<Todo, UUID> {
    //custom query for search by title starting with and passing custom parameter
    @Query("FROM Todo t WHERE t.title LIKE ?1% ")
    List<Todo> searchByTitle(String title);

//    List<Todo> findByTitleStartingWith( String title);
}
