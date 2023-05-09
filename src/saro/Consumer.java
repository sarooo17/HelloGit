package saro;

import java.util.Random;

public class Consumer extends Thread {
    private int id;
    private BoundedBuffer buffer;
    private int minSleepTime;
    private int maxSleepTime;
    private int numActions;

    public Consumer(int id, BoundedBuffer buffer, int minSleepTime, int maxSleepTime, int numActions) {
        this.id = id;
        this.buffer = buffer;
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
        this.numActions = numActions;
    }

    public void run() {
        Random random = new Random();

        for (int i = 0; i < numActions; i++) {
            try {
				int value = (int) buffer.take(id);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            try {
                Thread.sleep(random.nextInt(maxSleepTime - minSleepTime + 1) + minSleepTime);
            } catch (InterruptedException e) {}
        }
    }
}
