package com.example.parckinglot_c2.Models;

import java.util.Observable;
import java.util.concurrent.Semaphore;

public class Car extends Observable implements Runnable {

    private Position pos;
    private boolean status;
    private Semaphore semaforo;
    private int direccionActual; // 0 = Norte -> Sur, 1 = Sur -> Norte
    private int cantidadCochesEsperando;
    private int place;
    private int velocity_x = 10;
    private int velocity_y = -10;
    private boolean back = false;
    private boolean return_car = false;

    public Car (int place) {
        semaforo = new Semaphore(1);
        direccionActual = 0; // Iniciamos en dirección Norte -> Sur
        cantidadCochesEsperando = 0;
        this.status = true;
        this.place = place;
    }

    public void entrarDesdeNorte() throws InterruptedException {
//        synchronized(this) {
//            while (direccionActual != 0 || cantidadCochesEsperando > 0) {
//                wait();
//            }
//            cantidadCochesEsperando++;
        //}
        semaforo.acquire();
        synchronized(this) {
            cantidadCochesEsperando--;
            direccionActual = 0;
            notifyAll();
        }
    }

    public void entrarDesdeSur() throws InterruptedException {
//        synchronized(this) {
//            while (direccionActual != 1 || cantidadCochesEsperando > 0) {
//                wait();
//            }
//            cantidadCochesEsperando++;
//        }
        semaforo.acquire();
        synchronized(this) {
            cantidadCochesEsperando--;
            direccionActual = 1;
            notifyAll();
        }
    }

    public void salir() {
        semaforo.release();
    }

    public void setPos (Position pos) {
        this.pos = pos;
    }

    @Override
    public void run() {
        while (status) {

            setChanged();
            notifyObservers(pos);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (place) {
                case 0: {
                    if (pos.getX() >= 330) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 1: {
                    if (pos.getX() >= 390) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 2: {
                    if (pos.getX() >= 450) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 3: {
                    if (pos.getX() >= 515) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 4: {
                    if (pos.getX() >= 570) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 5: {
                    if (pos.getX() >= 635) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 6: {
                    if (pos.getX() >= 700) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 7: {
                    if (pos.getX() >= 755) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 8: {
                    if (pos.getX() >= 815) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 9: {
                    if (pos.getX() >= 870) {
                        if (pos.getY() > 28 && !back && !return_car) {
                            pos.setY(pos.getY() - 10);
                        } else if (pos.getY() > 28 && back) {
                            pos.setY(pos.getY() + velocity_x);
                            this.return_car = true;
                            if (pos.getY() >= 140 && return_car) {
                                velocity_x = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() + 10);
                            else
                                pos.setY(pos.getY() + 0);
                            back = true;
                        }
                    } else {
                        if (!this.back && !return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 10: {
                    if (pos.getX() >= 340) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 11: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 390) {
                        if (pos.getY() < 220 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 12: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 450) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 13: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 515) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 14: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 570) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 15: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 635) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 16: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 700) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 17: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 755) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 18: {
                    //System.out.println(pos.getX() + "   " + pos.getY() + "   " + return_car + "   " + back);
                    if (pos.getX() >= 815) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
                case 19: {
                    if (pos.getX() >= 870) {
                        if (pos.getY() < 190 && !back && !return_car) {
                            pos.setY(pos.getY() + 10);
                        } else if (pos.getY() > 140 && back) {
                            pos.setY(pos.getY() + velocity_y);
                            this.return_car = true;
                            if (pos.getY() <= 140 && return_car) {
                                velocity_y = 0;
                                pos.setX(pos.getX() - 10);
                                this.back = false;
                            }
                        } else {
                            try {
                                //Función para estacionarse en un tiempo aleatorio
                                Thread.sleep(1200);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!return_car)
                                pos.setY(pos.getY() - 10);
                            else
                                pos.setY(pos.getY() + 10);
                            this.back = true;
                        }
                    } else {
                        if (!this.back && !this.return_car)
                            pos.setX(pos.getX() + 10);
                        else
                            pos.setX(pos.getX() - 10);
                    }
                    break;
                }
            }



        }
    }

    public void setStatus (boolean status) { this.status = status; }

    public boolean getStatus () { return status; }

}
