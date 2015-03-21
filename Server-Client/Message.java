import com.google.gson.Gson;
public class Message{
    public static String makeMessage(Hand h) {
        Gson gson = new Gson();
        String json = gson.toJson(h);
        return json;
    }

    public static Hand unpackMessage(String json) {
        Gson gson = new Gson();
        Hand h = (Hand) gson.fromJson(json, Hand.class);
        return new Hand(1);
    }
}