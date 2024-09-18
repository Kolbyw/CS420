package Lab01;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            RemoteProcessInterface process0 = (RemoteProcessInterface) Naming.lookup("rmi://localhost/Process0");
            RemoteProcessInterface process1 = (RemoteProcessInterface) Naming.lookup("rmi://localhost/Process1");
            RemoteProcessInterface process2 = (RemoteProcessInterface) Naming.lookup("rmi://localhost/Process2");

            process0.send(1, new int[]{0, 0, 0}, process1);
            process2.internalEvent();
            process2.send(2, new int[]{0, 0, 0}, process2);
            process1.send(3, new int[]{0, 0, 0}, process0);
        } catch(Exception err) {
            err.printStackTrace();
        }
    }
}