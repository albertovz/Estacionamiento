package com.example.parckinglot_c2.Models;

import java.util.concurrent.Semaphore;

public class Puente {

    private static Semaphore semaphore = new Semaphore(1); // semaphore with 1 permit

    public static void main(String[] args) throws InterruptedException {

        // Vehicle 1
        Thread vehicle1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Vehicle 1 approaching the semaphore.");
                    semaphore.acquire();
                    System.out.println("Vehicle 1 passed through the semaphore.");
                    Thread.sleep(2000); // simulating time taken to pass through
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Vehicle 2
        Thread vehicle2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Vehicle 2 approaching the semaphore.");
                    semaphore.acquire();
                    System.out.println("Vehicle 2 passed through the semaphore.");
                    Thread.sleep(2000); // simulating time taken to pass through
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start the threads
        vehicle1.start();
        vehicle2.start();

        // Wait for the threads to finish
        vehicle1.join();
        vehicle2.join();
    }
}
