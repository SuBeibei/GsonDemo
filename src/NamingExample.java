import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class NamingExample {
    static class Foo {
        String oneName;
        String anotherName;

        public Foo(String oneName, String anotherName) {
            this.oneName = oneName;
            this.anotherName = anotherName;
        }


        @Override
        public String toString() {
            return  "oneName: " + oneName + "\n" +
                    "anotherName: " + anotherName;
        }
    }

    static class Bar {
        @SerializedName("name")
        String oneName;
        String anotherName;

        public Bar(String oneName, String anotherName) {
            this.oneName = oneName;
            this.anotherName = anotherName;
        }

        @Override
        public String toString() {
            return  "oneName: " + oneName + "\n" +
                    "anotherName: " + anotherName;
        }
    }

    public static void main(String[] args) {
        final Gson gson1 = new Gson();
        final Foo foo = new Foo("oneFoo", "anotherFoo");

        final Gson gson2 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        final Bar bar = new Bar("oneFoo", "anotherFoo");

        // Serialization
        System.out.println(gson1.toJson(foo));
        System.out.println(gson2.toJson(bar));

        // Deserialization
        final String str1 = "{\"oneName\":\"oneFoo\",\"anotherName\":\"anotherFoo\"}";
        final String str2 = "{\"OneName\":\"oneFoo\",\"AnotherName\":\"anotherFoo\"}";
        System.out.println(gson1.fromJson(str1, Foo.class));
        System.out.println(gson1.fromJson(str2, Foo.class));
        System.out.println(gson2.fromJson(str2, Foo.class));
    }
}
