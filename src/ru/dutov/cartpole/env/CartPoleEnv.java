package ru.dutov.cartpole.env;

public class CartPoleEnv {

    /**
     * Класс реализующий окружение (тележку)
     *
     * Все параметры в этом классе используются только внутри для расчетов положения тележки
     *
     * @param state единственная переменная, которая возвращается для передачи статуса тележки
     */

    private double pi;

    private double gravity;
    private final double masspole;
    private double totalMass;
    private double length;
    private final double polemassLength;
    private double forceMag;
    private double tau;

    private double thetaThresholdRadians;
    private double xThreshold;

    private int actionSpace;
    private int observationSpace;

    public int getObservationSpace() {
        return observationSpace;
    }

    public int getActionSpace() {
        return actionSpace;
    }

    private double seed;
    private Status state;

    private int stepsBeyondDone = 0;

    public CartPoleEnv() {
        double masscart = 1.0d;

        this.pi = Math.PI;

        this.gravity = 9.8d;
        this.masspole = 0.1d;
        this.totalMass = masspole + masscart;
        this.length = 0.5d;
        this.polemassLength = masspole * length;
        this.forceMag = 10.0d;
        this.tau = 0.02d;

        this.thetaThresholdRadians = 12 * 2 * pi / 360;
        this.xThreshold = 2.4d;

        this.actionSpace = 2;
        this.observationSpace = 4;

        this.state = new Status();
    }

    /**
     * Шаг изменения положения тележки
     * @param action действие переданное агентом
     * @return статус тележки
     */
    public Status step(int action) {
        double x = this.state.getX();
        double xDot = this.state.getXDot();
        double theta = this.state.getTheta();
        double thetaDot = this.state.getThetaDot();

        double force = action == 1 ? this.forceMag : -this.forceMag;
        double costheta = Math.cos(theta);
        double sintheta = Math.sin(theta);

        double tmp = (force + this.polemassLength * thetaDot * thetaDot * sintheta) / this.totalMass;
        double thetaacc = (this.gravity * sintheta - costheta * tmp) / (this.length * (4.0 / 3.0 - this.masspole * costheta * costheta / this.totalMass));
        double xacc = tmp - this.polemassLength * thetaacc * costheta / this.totalMass;

        x += this.tau * xDot;
        xDot += this.tau * xacc;
        theta += this.tau * thetaDot;
        thetaDot += this.tau * thetaacc;

        boolean done = false;
        if (x < -this.xThreshold || x > this.xThreshold || theta < -this.thetaThresholdRadians || theta > this.thetaThresholdRadians) {
            done = true;
        }

        int reward = 0;

        if (!done) {
            reward = 1;
        } else {
            if (this.stepsBeyondDone == -1) {
                this.stepsBeyondDone = 0;
                reward = 1;
            } else {
                if (this.stepsBeyondDone == 0) {
                    System.out.println("You are calling 'step()' even though this environment has already returned done = True." +
                            "\nYou should always call 'reset()' once you receive done = True' -- any further steps are undefined behavior.");
                    this.stepsBeyondDone += 1;
                    reward = 0;
                }
            }
        }

        this.state = new Status(x, xDot, theta, thetaDot, reward, done);

        return this.state;
    }


    /**
     * Метод для установки рандомных параметров тележки перед началом игры
     * @return статус тележки Status
     */
    public Status reset() {
        this.state.reset();
        this.stepsBeyondDone = -1;
        return this.state;
    }
}
