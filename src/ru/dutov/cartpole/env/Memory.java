package ru.dutov.cartpole.env;

public class Memory {

    /**
     * Класс для запоминания проделанных действий агента
     *
     * @param statePast хранит 4 переменных положения тележки (две координаты положения и две координаты отклонения) до совершения агентом действия
     * @param statePresent хранит 4 переменных положения тележки после совершения агентом действия
     * @param reward хранит награду за совершенное агентом действие
     * @param done флаг окончания игры
     *
     */

    private double[] statePast = new double[4];
    private double[] statePresent = new double[4];
    private int reward;
    private boolean done;

    public double[] getStatePast() {
        return statePast;
    }

    public double[] getStatePresent() {
        return statePresent;
    }

    public int getReward() {
        return reward;
    }

    public boolean isDone() {
        return done;
    }


    /**
     * Конструктор
     *
     * @param state положение тележки до совершения действия агента
     * @param stateNext положение тележки после совершения действия агента
     */
    public Memory(Status state, Status stateNext) {
        this.statePast[0] = state.getX();
        this.statePast[1] = state.getXDot();
        this.statePast[2] = state.getTheta();
        this.statePast[3] = state.getThetaDot();

        this.statePresent[0] = stateNext.getX();
        this.statePresent[1] = stateNext.getXDot();
        this.statePresent[2] = stateNext.getTheta();
        this.statePresent[3] = stateNext.getThetaDot();

        this.reward = stateNext.getReward();
        this.done = stateNext.isDone();
    }
}
