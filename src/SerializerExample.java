import com.google.gson.*;

import java.lang.reflect.Type;

public class SerializerExample {
    public static void main(String[] args) {
        final Book javaPuzzlers = new Book(
                new Author[]{new Author(1, "Joshua Bloch"), new Author(2, "Neal Gafter\"")},
                "032133678X",
                "Java Puzzler"
        );

        final Book effectiveJava = new Book(
                new Author[]{new Author(1, "Joshua Bloch")},
                "0321356683",
                "Effective Java (2nd Edition)"
        );

        Book[] books = new Book[]{javaPuzzlers, effectiveJava};

        final GsonBuilder builder1 = new GsonBuilder();
        builder1.setPrettyPrinting();
        builder1.registerTypeAdapter(Book.class, new BookSerializer1());
        final Gson gson1 = builder1.create();
        System.out.println(gson1.toJson(books));

        final GsonBuilder builder2 = new GsonBuilder();
        builder2.setPrettyPrinting();
        builder2.registerTypeAdapter(Book.class, new BookSerializer2());
        final Gson gson2 = builder2.create();
        System.out.println(gson2.toJson(books));
    }

    static class Author {
        public int id;
        public String name;

        public Author(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Book {
        public Author[] authors;
        public String isbn;
        public String title;

        public Book(Author[] authors, String isbn,String title) {
            this.authors = authors;
            this.isbn = isbn;
            this.title = title;
        }

        public int[] getAuthorIds() {
            final int[] ids = new int[authors.length];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = authors[i].id;
            }
            return ids;
        }
    }

    static class BookSerializer1 implements JsonSerializer<Book> {
        @Override
        public JsonElement serialize(final Book book, final Type type, final JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("title", book.title);
            jsonObject.addProperty("isbn", book.isbn);

            final JsonElement jsonAuthors = context.serialize(book.authors);
            jsonObject.add("authors", jsonAuthors);

            return jsonObject;
        }
    }

    static class BookSerializer2 implements JsonSerializer<Book> {
        @Override
        public JsonElement serialize(final Book book, final Type type, final JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("title", book.title);
            jsonObject.addProperty("isbn", book.isbn);

            final JsonElement jsonAuthors = context.serialize(book.getAuthorIds());
            jsonObject.add("authors", jsonAuthors);

            return jsonObject;
        }
    }
}
