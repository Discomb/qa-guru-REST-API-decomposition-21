package guru.qa.api.collections;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AddBookRequestModel {
    String userId;
    ArrayList<ISBNModel> collectionOfIsbns;
}
