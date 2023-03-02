package com.example.parckinglot_c2.Controllers;

import com.example.parckinglot_c2.Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;

public class HelloController implements Observer {
    @FXML
    private Rectangle gateway;
    @FXML
    private Rectangle exit;
    @FXML
    private Button start;
    @FXML
    private AnchorPane stage;
    private Car car;
    boolean flag = false;
    boolean flag_exit = false;
    boolean flag_y = false;
    private Semaphore semaphore, productor, consumidor, mutex;
    private final int N = 100;
    private final int condicion_productor_consumidor = N + 1;
    final String path = "C:\\Users\\crash\\Documents\\7 Cuatrimestre\\Programación Concurrente\\parckinglot_c2\\src\\main\\resources\\assets\\imgs\\car_";
    ImageView[] imageViews = new ImageView[N];
    Thread[] hilo_carro = new Thread[N];
    Label[] labels = new Label[N];
    private final int tam_parking = 20;
    private boolean[] parked = new boolean[N];
    private boolean[] orientation = new boolean[N];
    private int[] space_table = new int[N];
    private int[] buffer = new int [N];
    private final int box_x[] = {330, 390, 450, 515, 570, 635, 700, 755, 815, 870, 330, 390, 450, 515, 570, 635, 700, 755, 815, 870};
    private final int box_y[] = {140, 140, 140, 140, 140, 140, 140, 140, 140, 140, 225, 225, 225, 225, 225, 255, 255, 225, 225, 225};
    public int generate_random () {
        return (int)(Math.random() * 20);
    }

    public int generate_random_image () {
        return (int)(Math.random() * 10)+1;
    }
    @FXML
    void startOnMouseClicked(MouseEvent event) {
        int count = 0;
        int space = 0;
        int serial_number = 0;

        for (int i = 0; i < N; i++) {
            int random = generate_random_image();
            int random_position = generate_random();
            Image image = new Image(path + (random) + ".png");
            imageViews[i] = new ImageView(image);
            imageViews[i].setFitWidth(40); // ajusta el ancho de la imagen
            imageViews[i].setFitHeight(80); // ajusta la altura de la imagen
            imageViews[i].setImage(image);
            imageViews[i].setRotate(imageViews[i].getRotate() + 90);
            imageViews[i].setLayoutX(-300 + space);
            imageViews[i].setLayoutY(98);
            space_table[i] = random_position;
            space-=500;
            car = new Car(random_position);
            car.addObserver(this);
            car.setPos(new Position(i, (int)(imageViews[i].getLayoutX()), (int)(imageViews[i].getLayoutY())));
            hilo_carro[i] = new Thread(car);
            hilo_carro[i].setName("carro " + serial_number);
            labels[i] = new Label(hilo_carro[i].getName());
            stage.getChildren().addAll(imageViews[i], labels[i]);
            hilo_carro[i].start();

            count++;
            serial_number++;
            if (count >= 10)
                count = 0;
        }
    }

    public void fill_array_to_false () {
        for (int i=0; i<N; i++) {
            parked[i] = false;
            orientation[i] = false;
            buffer[i] = 0;
        }
    }

    @FXML
    public void initialize () {
        fill_array_to_false();
        semaphore = new Semaphore(1);
        productor = new Semaphore(tam_parking);
        consumidor = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    @Override
    public synchronized void update (Observable o, Object arg) {
        Position pos = (Position) arg;

        parking(pos.getId(), pos.getX(), pos.getY(), box_x[space_table[pos.getId()]], box_y[space_table[pos.getId()]]);
    }

    public void parking (int pos, int posX, int posY, int x, int y) {

        //System.out.println(pos + "   " + posX + "   " + posY + "    " + x + "   " + y);
//        else
//            System.out.println(pos + "   " + posX + "   " + posY + "    " + x + "   " + y + "   " + parked + "   " + orientation);
        if (imageViews[pos].getBoundsInParent().intersects(gateway.getBoundsInParent()) && imageViews[pos].getLayoutX() == 146) {
            if (!flag) {
                try {
                    semaphore.acquire();
                    //Thread.sleep(200);
                    wait(20);
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println(pos + " tocó la entrada.. " + hilo_carro[pos].getName());
                flag = true;
            }
        } else {
            flag = false;
        }
        if (imageViews[pos].getBoundsInParent().intersects(exit.getBoundsInParent()) &&
                ((int)(imageViews[pos].getLayoutX()) == 256 || ((int)(imageViews[pos].getLayoutX()) == 236))) {
            if (!flag_exit) {
                try {
                    semaphore.acquire();
                    //Thread.sleep(200);
                    wait(20);
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println(pos.getId() + " tocó la salida...");
                flag_exit = true;
            }
        } else
            flag_exit = false;
        imageViews[pos].setLayoutX(posX);
        imageViews[pos].setLayoutY(posY);
        labels[pos].setLayoutX(imageViews[pos].getLayoutX());
        labels[pos].setLayoutY(imageViews[pos].getLayoutY());
        if(pos>100)
            System.out.println(pos);
        if (pos < 10) {
            if(pos==condicion_productor_consumidor) {
//                try {
//                    productor.acquire();
//                    mutex.acquire();
//                    buffer[pos] = 1;
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                consumidor.release();
//                buffer[pos] = 0;
            }
            if (posX >= x && !parked[pos] && !orientation[pos]) {

                //System.out.println("Giró el id: " + pos + " con condicional posX: " + posX + " y " + posY);
                imageViews[pos].setRotate(imageViews[pos].getRotate() + 90);
                this.parked[pos] = true;
                this.orientation[pos] = true;

            } else if (posY >= y && orientation[pos]) {
                //System.out.println("Giró el id: " + pos + " con condicional posX: " + posX + " y " + posY);
                imageViews[pos].setRotate(imageViews[pos].getRotate() - 270);
                this.orientation[pos] = false;
            }
        } else {
//            if(pos==condicion_productor_consumidor) {
//                try {
//                    productor.acquire();
//                    mutex.acquire();
//                    buffer[pos] = 1;
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                consumidor.release();
//                buffer[pos] = 0;
//            }
            if (posX >= x && !parked[pos] && !orientation[pos]) {

                //System.out.println("Giró el id: " + pos + " con condicional posX: " + posX + " y " + posY);
                imageViews[pos].setRotate(imageViews[pos].getRotate() + 90);
                this.parked[pos] = true;
                this.orientation[pos] = true;
            } else if (posY <= y && orientation[pos] && flag_y) {
                //System.out.println("Giró el id: " + pos + " con condicional posX: " + posY + " y " + y);
                imageViews[pos].setRotate(imageViews[pos].getRotate() - 270);
                this.orientation[pos] = false;
            }
            if (posY >= 180 && parked[pos] && orientation[pos])
                flag_y = true;
        }
    }
}