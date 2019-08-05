package demo.great.zhang.poket.eventmsg;

public class Meberid {
    public final String meberid;

    public static Meberid getInstance(String message) {
        return new Meberid(message);
    }

    public Meberid(String message) {
        this.meberid = message;
    }
}
