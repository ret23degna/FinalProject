package helpers.models;

import lombok.Data;

import java.util.List;

@Data
public class BookAddPostRequestModel {

  private String userId;
  private List<IsbnModel> collectionOfIsbns;

}