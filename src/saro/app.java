package saro;

import java.util.Scanner;

public class app {
	public static void main(String[] args) throws InterruptedException {
        int bufferSize;
        int numProducers;
        int numConsumers;
        int minSleepTime;
        int maxSleepTime;
        int numActions;

        // Lettura dei parametri dall'input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Dimensione del buffer: ");
        bufferSize = scanner.nextInt();

        System.out.print("Numero di produttori: ");
        numProducers = scanner.nextInt();

        System.out.print("Numero di consumatori: ");
        numConsumers = scanner.nextInt();

        System.out.print("Tempo minimo di sleep (in millisecondi): ");
        minSleepTime = scanner.nextInt();

        System.out.print("Tempo massimo di sleep (in millisecondi): ");
        maxSleepTime = scanner.nextInt();

        System.out.print("Numero di azioni per produttore/consumatore: ");
        numActions = scanner.nextInt();

        BoundedBuffer buffer = new BoundedBuffer(bufferSize);
        Producer[] producers = new Producer[numProducers];
        Consumer[] consumers = new Consumer[numConsumers];

        // Creazione e avvio dei produttori
        for (int i = 0; i < numProducers; i++) {
            producers[i] = new Producer(i + 1, buffer, minSleepTime, maxSleepTime, numActions);
            producers[i].start();
        }

        // Creazione e avvio dei consumatori
        for (int i = 0; i < numConsumers; i++) {
            consumers[i] = new Consumer(i + 1, buffer, minSleepTime, maxSleepTime, numActions);
            consumers[i].start();
        }

        // Attesa della terminazione dei produttori
        for (int i = 0; i < numProducers; i++) {
            producers[i].join();
        }

        // Attesa della terminazione dei consumatori
        for (int i = 0; i < numConsumers; i++) {
            consumers[i].join();
        }

        System.out.println("Simulazione terminata.");
    }
}
