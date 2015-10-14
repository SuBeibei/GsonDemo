import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NullExample {
    static class Foo {
        String str = "";
        int num;

        public Foo() {
            this.str = null;
            num = 5;
        }

        @Override
        public String toString() {
            return  "str: " + str + "\n" +
                    "num: " + num + "\n";
        }
    }

    public static void main(String[] args) {
        final Foo foo = new Foo();

        final Gson gson1 = new Gson();
        final Gson gson2 = new GsonBuilder().serializeNulls().create();

        // Serialization
        System.out.println(gson1.toJson(foo));
        System.out.println(gson2.toJson(foo));

        // Deserialization
        final String str1 = "{\"num\":5}";
        System.out.println(gson1.fromJson(str1, Foo.class));
        System.out.println(gson2.fromJson(str1, Foo.class));

        final String str2 = "{\"str\":null,\"num\":5}";
        System.out.println(gson1.fromJson(str2, Foo.class));
        System.out.println(gson2.fromJson(str2, Foo.class));
    }
}
