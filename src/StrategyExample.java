import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrategyExample {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    static @interface Foo {

    }

    static class Bar {
        @Foo private String name1;
        private static String name2 = "bar2";
        private String name3;
        private volatile String name4;

        public Bar(String name1, String name3, String name4) {
            this.name1 = name1;
            this.name3 = name3;
            this.name4 = name4;
        }

        @Override
        public String toString() {
            return  "name1: " + this.name1 + "\n" +
                    "name2: " + this.name2 + "\n" +
                    "name3: " + this.name3 + "\n" +
                    "name4: " + this.name4 + "\n";
        }
    }

    static class A {
        private String name = "A";
    }

    static class MyExclusionStrategy implements ExclusionStrategy {
        private List<Class<?>> classesToSkip = new ArrayList<>();

        public MyExclusionStrategy(Class<?> ...classes) {
            classesToSkip.addAll(Arrays.asList(classes));
        }

        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return fieldAttributes.getAnnotation(Foo.class) != null;
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass) {
            return classesToSkip.contains(aClass);
        }
    }

    public static void main(String[] args) {
        Bar bar = new Bar("name1", "name3", "name4");
        A a = new A();

        Gson gson1 = new GsonBuilder().excludeFieldsWithModifiers().create();
        Gson gson2 = new Gson();
        Gson gson3 = new GsonBuilder().excludeFieldsWithModifiers(Modifier.VOLATILE).create();
        Gson gson4 = new GsonBuilder().setExclusionStrategies(new MyExclusionStrategy(A.class)).create();


        // Serialization
        System.out.println(gson1.toJson(bar));
        System.out.println(gson2.toJson(bar));
        System.out.println(gson3.toJson(bar));
        System.out.println(gson4.toJson(bar));
        System.out.println(gson4.toJson(a));

        // Deserialization
        String str = "{\"name1\":\"name1\",\"name2\":\"name2\",\"name3\":\"name3\",\"name4\":\"name4\"}";
        System.out.println(gson2.fromJson(str, Bar.class));
        System.out.println(gson1.fromJson(str, Bar.class));
        System.out.println(gson3.fromJson(str, Bar.class));
        System.out.println(gson4.fromJson(str, Bar.class));
    }
}
