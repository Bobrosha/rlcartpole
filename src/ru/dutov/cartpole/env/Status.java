package ru.dutov.cartpole.env;



public class Status {

    /**
     * Класс для удобной реализации выходных данных из окружения CartPole
     *
     * @param x переменная в окружении описывающая положение (координату) тележки на прямой
     * @param xDot вспомогательная переменная, используется при расчетах координаты
     * @param theta переменная в окружении описывающая угол отклонения маятника от нормали
     * @param thetaDot вспомогательная переменная, используется при расчетах угла отклонения
     * @param reward переменная передающая награду за каждое совершенное агентом действие
     * @param done переменная означающая окончание игры (true - окончена false - продолжается)

     */

    private double x;
    private double xDot;
    private double theta;
    private double thetaDot;
    private int reward;
    private boolean done;

    public double getX() {
        return x;
    }

    public double getXDot() {
        return xDot;
    }

    public double getTheta() {
        return theta;
    }

    public double getThetaDot() {
        return thetaDot;
    }

    public int getReward() {
        return reward;
    }

    public boolean isDone() {
        return done;
    }


    public Status() {

    }

    /**
     * Конструктор для инициализации
     */
    public Status(double x, double xDot, double theta, double thetaDot, int reward, boolean done) {
        this.x = x;
        this.xDot = xDot;
        this.theta = theta;
        this.thetaDot = thetaDot;
        this.reward = reward;
        this.done = done;
    }

    /**
     * Метод для установки рандомных параметров тележки перед началом игры
     */
    public void reset() {
        double min = -0.05;
        double max = 0.05;

        this.x = Math.random() * (max-min) + min;
        this.xDot = Math.random() * (max-min) + min;
        this.theta = Math.random() * (max-min) + min;
        this.thetaDot = Math.random() * (max-min) + min;
        this.reward = 0;
        this.done = false;
    }

    /**
     * Метод выводящий в консоль статус тележки
     */
    /*public void print() {
        System.out.format("%n" + "%f; %f; %f; %f" + "%n", this.x, this.xDot, this.theta, this.thetaDot);
        System.out.print("Reward: ");
        System.out.println(this.reward);
        System.out.print("Done: ");
        System.out.println(this.done);
    }*/ // Use only for tests
}
