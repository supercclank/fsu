import com.google.gson.Gson;
public class Message{
    public static String makeMessage(Hand h) {
        Gson gson = new Gson();
        String json = gson.toJson(h);
        return json;
    }

    public static BlackJackHand unpackMessage(String json) {
        Gson gson = new Gson();
        BlackJackHand h = gson.fromJson(json, BlackJackHand.class);
        return h;
    }
}