package mensajero;

public interface MailServer {

    void send(String email, String msgContent);

    void send(Cliente cliente, String msgContent);

}
