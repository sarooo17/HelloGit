package saro;

public class BoundedBuffer {
	private final Object[] buffer;
    private int readPos, writePos, count;

    public BoundedBuffer(int size) {
        buffer = new Object[size];
        readPos = 0;
        writePos = 0;
        count = 0;
    }

    public synchronized void put(int id, Object item) throws InterruptedException {
        if (count == buffer.length) {
            System.out.println("BUFFER PIENO");
            return;
        }

        buffer[writePos] = item;
        writePos = (writePos + 1) % buffer.length;
        count++;

        System.out.println("Produttore " + id + " ha prodotto " + item + " (posizione " + writePos + ")");
    }

    public synchronized Object take(int id) throws InterruptedException {
        if (count == 0) {
            System.out.println("BUFFER VUOTO");
            return null;
        }

        Object item = buffer[readPos];
        readPos = (readPos + 1) % buffer.length;
        count--;

        System.out.println("Consumatore " + id + " ha consumato " + item + " (posizione " + readPos + ")");

        return item;
    }
}
