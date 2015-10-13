import com.google.gson.Gson;

public class ExtendExample {
    static class Parent {
        protected int value1 = 1;
        private int value2 = 2;
    }

    static class Child extends Parent {
        private int value3;

        public Child(int value) {
            value3 = value;
        }

        @Override
        public String toString() {
            return  "value1: " + value1 + "\n" +
                    "value3: " + value3 + "\n";
        }
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        Child child = new Child(3);
        System.out.println(gson.toJson(child));

        // Deserialization
        String str = "{\"value1\":1,\"value2\":2}";
        System.out.println(gson.fromJson(str, Child.class).toString());
    }
}
