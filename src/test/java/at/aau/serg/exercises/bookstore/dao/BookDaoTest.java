package at.aau.serg.exercises.bookstore.dao;

import at.aau.serg.exercises.bookstore.dao.impl.ListBookDao;
import at.aau.serg.exercises.bookstore.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookDaoTest {
  private ListBookDao listBookDao = new ListBookDao();

  @Test
    public void testInsertBook(){

      listBookDao.insert(new Book(1130101301L,"978-3-16-148410-0","Lord Of the Rings 1","J.R.R. Tolkien",35.0));
      listBookDao.insert(new Book(1130101301L,"978-3-16-148410-0","Lord Of the Rings 1","J.R.R. Tolkien",39.0));
      Assertions.assertEquals(2,listBookDao.findAll().size());

  }
  @Test
  public void updateBook(){
    listBookDao.insert(new Book(1130101301L,"978-3-16-148410-0","Lord Of the Rings 1","J.R.R. Tolkien",35.0));
    listBookDao.insert(new Book(1130101301L,"978-3-16-148410-0","Lord Of the Rings 1","J.R.R. Tolkien",39.0));
    listBookDao.update(new Book(11301324231L,"978-3-16-148410-1","Lord Of the Rings 2","J.R.R. Tolkien",39.0));
    Assertions.assertEquals(3,listBookDao.findAll().size());

  }


  @Test
  public void deleteBook(){
    listBookDao.insert(new Book(1130101301L,"978-3-16-148410-0","Lord Of the Rings 1","J.R.R. Tolkien",35.0));
    listBookDao.insert(new Book(1130101301L,"978-3-16-148410-0","Lord Of the Rings 1","J.R.R. Tolkien",39.0));
    listBookDao.delete(1L);
    Assertions.assertEquals(1,listBookDao.findAll().size());

  }

}
