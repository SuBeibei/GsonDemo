import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class CustomFieldExample {
    public static void main(String[] args) {
        final Gson gson = new Gson();

        final Box box = new Box();
        box.width = 30;
        box.height = 20;
        box.depth = 10;

        final String json = gson.toJson(box);

        System.out.printf("Serialised: %s%n", json);

        final Box otherBox = gson.fromJson(json, Box.class);
        System.out.printf("Some box: %s%n", box.equals(otherBox));
    }

    static class Box {
        @SerializedName("w")
        int width;

        @SerializedName("h")
        int height;

        @SerializedName("d")
        int depth;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Box) {
                Box box = (Box)obj;
                return box.width==this.width && box.height==this.height && box.depth==this.depth;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return  Integer.valueOf(this.width).hashCode() +
                    Integer.valueOf(this.height).hashCode() +
                    Integer.valueOf(this.depth).hashCode();
        }
    }
}

