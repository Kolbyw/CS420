package Lab01;
import java.rmi.Remote;
import java.rmi.RemoteException;

interface RemoteProcessInterface extends Remote {
    public VectorClock getVectorClock() throws RemoteException;
    public void send(int eventId, int[] data, RemoteProcessInterface targetProcess) throws RemoteException;
    public void receive(int eventId, VectorClock vectorClock, int[] data) throws RemoteException;
    public void internalEvent() throws RemoteException;
}
