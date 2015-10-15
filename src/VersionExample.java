import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

public class VersionExample {
    public static void main(String[] args) {
        final GsonBuilder builder = new GsonBuilder();
        builder.setVersion(1.0);
        final Gson gson = builder.create();

        final SoccerPlayer account = new SoccerPlayer("Albert Attard", 10, "Zejtun Corinthians", "Malta");
        System.out.println(gson.toJson(account));

        String str = "{\"name\":\"Albert Attard\",\"shirtName\":10,\"country\":\"Zejtun Corinthians\",\"teamName\":\"Malta\"}";
        System.out.println(gson.fromJson(str, SoccerPlayer.class));
    }

    static class SoccerPlayer {
        private String name;
        @Since(1.2)
        private int shirtName;
        @Until(0.9)
        private String country;

        private String teamName;

        public SoccerPlayer(String name, int shirtName, String country, String teamName) {
            this.name = name;
            this.shirtName = shirtName;
            this.country = country;
            this.teamName = teamName;
        }

        @Override
        public String toString() {
            return  "name: " + name + "\n" +
                    "shirtName: " + shirtName + "\n" +
                    "country: " + country + "\n" +
                    "teamName: " + teamName + "\n";
        }
    }
}

