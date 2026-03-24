import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private final int id;
    private final Semaphore garfoEsquerdo;
    private final Semaphore garfoDireito;
    //private final Semaphore mutex;

    // public Filosofo(int id, Semaphore garfoEsquerdo, Semaphore garfoDireito, Semaphore mutex) {
    //     this.id = id;
    //     this.garfoEsquerdo = garfoEsquerdo;
    //     this.garfoDireito = garfoDireito;
    //     this.mutex = mutex;
    // }

    public Filosofo(int id, Semaphore garfoEsquerdo, Semaphore garfoDireito) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    public void run() {
        try {
            while (true) {
                pensar();

                if(this.id < 4) {
                    garfoEsquerdo.acquire();
                    garfoDireito.acquire();
                }
                else {
                    garfoDireito.acquire();
                    garfoEsquerdo.acquire();
                }
                //pegarGarfos();
                comer();
                liberarGarfos();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando");
        Thread.sleep((long) (Math.random() * 1));
    }

    private void pegarGarfos() throws InterruptedException {
        //mutex.acquire();
        System.out.println("Filósofo " + id + " está com fome");
        garfoEsquerdo.acquire();
        System.out.println("Filósofo " + id + " pegou garfo esquerdo");
        garfoDireito.acquire();
        System.out.println("Filósofo " + id + " pegou garfo direito");
        //mutex.release();
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comendo");
        Thread.sleep((long) (Math.random() * 1));
    }

    private void liberarGarfos() {
        garfoEsquerdo.release();
        garfoDireito.release();
    }
}