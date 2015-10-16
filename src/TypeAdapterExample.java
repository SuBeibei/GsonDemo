import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TypeAdapterExample {
    public static void main(String[] args) {
        final GsonBuilder builder1 = new GsonBuilder();
        builder1.registerTypeAdapter(Book.class, new BookTypeAdapter1());
        builder1.setPrettyPrinting();
        final Gson gson1 = builder1.create();

        final String str1 = "[\"978-0321336781\",\"Java Puzzlers: Traps, Pitfalls, and Corner Cases\",1,\"Joshua Bloch\",2,\"Neal Gafter\"]";
        final Book book = gson1.fromJson(str1, Book.class);
        System.out.printf("isbn: %s%n", book.isbn);
        System.out.printf("title: %s%n", book.title);
        for (final Author author : book.authors) {
            System.out.printf("id: %s, name: %s%n", author.id, author.name);
        }
        System.out.println(gson1.toJson(book));

        final GsonBuilder builder2 = new GsonBuilder();
        builder2.registerTypeAdapter(Book.class, new BookTypeAdapter2());
        builder2.setPrettyPrinting();
        final Gson gson2 = builder2.create();

        final String str2 =
                "{" +
                        "\"isbn\": \"978-0321336781\"," +
                        "\"title\": \"Java Puzzlers: Traps, Pitfalls, and Corner Cases\"," +
                        "\"authors\": [" +
                            "{" +
                                "\"id\": 1," +
                                "\"name\": \"Joshua Bloch\"" +
                            "},"+
                            "{" +
                                "\"id\": 2,"+
                                "\"name\": \"Neal Gafter\"" +
                            "}" +
                        "]" +
                "}";
        final Book book2 = gson2.fromJson(str2, Book.class);
        System.out.printf("isbn: %s%n", book2.isbn);
        System.out.printf("title: %s%n", book2.title);
        for (final Author author : book2.authors) {
            System.out.printf("id: %s, name: %s%n", author.id, author.name);
        }
        System.out.println(gson2.toJson(book));

    }

    static class Author {
        public int id;
        public String name;
    }

    static class Book {
        public Author[] authors;
        public String isbn;
        public String title;
    }

    static class BookTypeAdapter1 extends TypeAdapter<Book> {
        @Override
        public Book read(final JsonReader in) throws IOException {
            final Book book = new Book();

            in.beginArray();
            book.isbn = in.nextString();
            book.title = in.nextString();
            final List<Author> authors = new ArrayList<Author>();
            while (in.hasNext()) {
                final Author author = new Author();
                author.id = in.nextInt();
                author.name = in.nextString();
                authors.add(author);
            }
            book.authors = authors.toArray(new Author[authors.size()]);
            in.endArray();
            return book;
        }

        @Override
        public void write(final JsonWriter out, final Book book) throws IOException {
            out.beginArray();
            out.value(book.isbn);
            out.value(book.title);
            for (final Author author: book.authors) {
                out.value(author.id);
                out.value(author.name);
            }
            out.endArray();
        }
    }

    static class BookTypeAdapter2 extends TypeAdapter<Book> {
        @Override
        public Book read(final JsonReader in) throws IOException {
            final Book book = new Book();

            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case "isbn":
                        book.isbn = in.nextString();
                        break;
                    case "title":
                        book.title = in.nextString();
                        break;
                    case "authors":
                        in.beginArray();
                        final List<Author> authors = new ArrayList<>();
                        while (in.hasNext()) {
                            in.beginObject();
                            final Author author = new Author();
                            while (in.hasNext()) {
                                switch (in.nextName()) {
                                    case "id":
                                        author.id = in.nextInt();
                                        break;
                                    case "name":
                                        author.name = in.nextString();
                                        break;
                                }
                            }
                            authors.add(author);
                            in.endObject();
                        }
                        book.authors = authors.toArray(new Author[authors.size()]);
                        in.endArray();
                        break;
                }
            }
            in.endObject();

            return book;
        }

        @Override
        public void write(final JsonWriter out, final Book book) throws IOException {
            out.beginObject();
            out.name("isbn").value(book.isbn);
            out.name("title").value(book.title);
            out.name("authors").beginArray();
            for (final Author author : book.authors) {
                out.beginObject();
                out.name("id").value(author.id);
                out.name("name").value(author.name);
                out.endObject();
            }
            out.endArray();
            out.endObject();
        }
    }
}

















