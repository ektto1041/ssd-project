import ssd.SSDImpl;

public class Main {
    public static void main(String[] args) {
        SSDImpl ssd = new SSDImpl();

        ssd.write(2, "0x12345678");
    }

}