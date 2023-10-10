package guru.qa.api.collections.models;

import lombok.Data;

@Data
public class BookModel {
    String isbn,
    title,
            subTitle,
            author,
            publish_date,
            publisher,
            description,
            website;

    int pages;
}
