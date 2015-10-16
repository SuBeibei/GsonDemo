import com.google.gson.*;

import java.lang.reflect.Type;

public class DeserializerExample {
    public static void main(String[] args) {
        final String str = "{\"title\":\"Java Puzzler\",\"isbn\":\"032133678X\",\"authors\":[\"Joshua Bloch\",\"Neal Gafter\"]}";

        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Book.class, new BookDeserializer());
        final Gson gson = builder.create();
        System.out.println(gson.fromJson(str, Book.class));
    }

    static class Book {
        public String[] authors;
        public String isbn;
        public String title;

        public Book(String[] authors, String isbn,String title) {
            this.authors = authors;
            this.isbn = isbn;
            this.title = title;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append("isbn: " + isbn + "\n")
                    .append("title: " + title + "\n")
                    .append("authors: [")
                    .append("".join(",", authors))
                    .append("]");
            return builder.toString();
        }
    }

    static class BookDeserializer implements JsonDeserializer<Book> {
        @Override
        public Book deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();

            final String title = jsonObject.get("title").getAsString() + "(deserialize)";

            final String isbn = jsonObject.get("isbn").getAsString() + "(deserialize)";

            final JsonArray jsonArray = jsonObject.getAsJsonArray("authors");
            final String[] authors = new String[jsonArray.size()];
            for (int i = 0; i < authors.length; i++) {
                final JsonElement jsonAuthor = jsonArray.get(i);
                authors[i] = jsonAuthor.getAsString();
            }

            return new Book(authors, title, isbn);
        }
    }
}
