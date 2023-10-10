package guru.qa.api.collections.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BooksListModel {
    ArrayList<BookModel> books;
}
