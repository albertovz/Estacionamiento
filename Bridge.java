package com.example.parckinglot_c2.Models;

import java.util.concurrent.Semaphore;

public class Bridge implements  Runnable {
    @Override
    public void run() {
        System.out.println("Hola, soy un hilo");
    }

    /*Semaphore bridge, north, south;
    int amount_car_north, amount_car_south;

    public Bridge (Semaphore bridge, Semaphore north, Semaphore south) {
        this.bridge = bridge;
        this.north = north;
        this.south =  south;
        amount_car_north = 0;
        amount_car_south = 0;
    }

    public void enter_north_car (Car car) {
        try {
            System.out.println("El carro " + car.id + " Ha llegado al puente desde el norte");
            north.acquire();

            bridge.acquire();
            System.out.println("el carro " + car.id + " está cruzando el puente desde el norte");
            amount_car_north++;
            System.out.println("Hay " + amount_car_north + " carros del norte cruzando el puente");

            if (amount_car_north == 1){
                System.out.println("No pueden cruzar carros del sur");
                south.acquire();
            }

            bridge.release();
            north.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exit_north_car (Car car) {
        try {
            bridge.acquire();
            amount_car_north--;
            System.out.println("El carro " + car.id + " terminó de cruzar el puente desde el norte \n Hay " + amount_car_north + " carros cruzando el puente");

            if (amount_car_north == 0){
                south.release();
            }

            bridge.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enter_south_car (Car car) {
        try {
            System.out.println("El carro " + car.id + " Ha llegado al puente desde el sur");
            south.acquire();

            bridge.acquire();
            System.out.println("el carro " + car.id + " está cruzando el puente desde el sur");
            amount_car_south++;
            System.out.println("Hay " + amount_car_south + " carros del sur cruzando el puente");

            if (amount_car_south == 1){
                System.out.println("No pueden cruzar carros del norte");
                north.acquire();
            }

            bridge.release();
            south.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exit_south_car (Car car) {
        try {
            bridge.acquire();
            amount_car_south--;
            System.out.println("El carro " + car.id + " terminó de cruzar el puente desde el sur \n Hay " + amount_car_south + " carros cruzando el puente");

            if (amount_car_south == 0){
                north.release();
            }

            bridge.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

}
