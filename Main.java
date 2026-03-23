import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]) {
        int numFilosofos = 5;
        
        Semaphore[] garfos = new Semaphore[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new Semaphore(1);
        }

        Semaphore mutex = new Semaphore(1);

        Filosofo filosofos[] = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            Semaphore garfoEsquerdo = garfos[i];
            Semaphore garfoDireito = garfos[(i + 1) % numFilosofos];

            filosofos[i] = new Filosofo(i, garfoEsquerdo, garfoDireito, mutex);
            filosofos[i].start();
        }
    }
}