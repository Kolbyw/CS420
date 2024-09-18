package Lab01;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteProcess extends UnicastRemoteObject implements RemoteProcessInterface {
    private VectorClock vectorClock;
    private int processId;

    public RemoteProcess(int processId, int numProcesses) throws RemoteException {
        super();
        this.processId = processId;
        this.vectorClock = new VectorClock(numProcesses);
    }
    @Override
    public VectorClock getVectorClock() throws RemoteException {
        return vectorClock;
    }
    @Override
    public void send(int eventId, int[] data, RemoteProcessInterface targetProcess) throws RemoteException {
        vectorClock.increment(processId);
        targetProcess.receive(eventId, vectorClock, data);
        System.out.println("Process: " + processId + " sent Event ID: " + eventId + " with clock: " + vectorClock.toString());
    }
    @Override
    public void receive(int eventId, VectorClock clock, int[] data) throws RemoteException {
        System.out.println("Process: " + processId + " received Event ID: " + eventId + " with clock: " + clock.toString());
        vectorClock.update(clock);
        vectorClock.increment(processId);
        System.out.println("Process: " + processId + " updated its clock to: " + vectorClock.toString());
    }
    @Override
    public void internalEvent() {
        vectorClock.increment(processId);
        System.out.println("Process: " + processId + " had an internal event, updating its clock to: " + vectorClock.toString);
    }
}