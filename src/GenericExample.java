import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GenericExample {
    static class Foo<T> {
        T value;
        public Foo(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
    static class Bar {
        String name = "bar";
    }
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        Foo<String> foo1 = new Foo<>("abc");
        System.out.println(gson.toJson(foo1));
        Foo<Bar> foo2 = new Foo<>(new Bar());
        System.out.println(gson.toJson(foo2));
        System.out.println(gson.toJson(foo2, new TypeToken<Foo<Bar>>(){}.getType()));

        // Deserialization
        String str1 = "{\"value\":\"abc\"}";
        String str2 = "{\"value\":{\"name\":\"bar\"}}";
        foo1 = gson.fromJson(str1, new TypeToken<Foo<String>>(){}.getType());
        System.out.println(foo1);
        foo2 = gson.fromJson(str2, new TypeToken<Foo<Bar>>(){}.getType());
        System.out.println(foo2.value.name);
    }
}
