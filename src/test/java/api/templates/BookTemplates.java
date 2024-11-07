package api.templates;

import helpers.models.BookAddPostRequestModel;
import helpers.models.BookDeleteRequestModel;
import helpers.models.IsbnModel;
import java.util.List;
import java.util.Map;

public class BookTemplates {

  public BookAddPostRequestModel formAddBook(String userId, List<IsbnModel> isbnArray) {
    BookAddPostRequestModel book = new BookAddPostRequestModel();
    book.setUserId(userId);
    book.setCollectionOfIsbns(isbnArray);
    return book;
  }

  public BookDeleteRequestModel formChangeBook(String isbn, String userId) {
    BookDeleteRequestModel book = new BookDeleteRequestModel();
    book.setIsbn(isbn);
    book.setUserId(userId);
    return book;
  }

  public IsbnModel formIsbn(Map<String, ?> bookMap) {
    IsbnModel isbn = new IsbnModel();
    isbn.setIsbn(bookMap.get("isbn").toString());
    return isbn;
  }

}
