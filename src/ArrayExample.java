import com.google.gson.Gson;

public class ArrayExample {
    public static class Foo {
        String name;
        public Foo(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        final Gson gson = new Gson();

        // Serialization
        final Foo[] foos = {new Foo("name1"), new Foo("name2")};
        System.out.println(gson.toJson(foos));

        // Deserialization
        final String str = "[{\"name\":\"name1\"},{\"name\":\"name2\"}]";
        System.out.println(gson.fromJson(str, Foo[].class));

    }
}
