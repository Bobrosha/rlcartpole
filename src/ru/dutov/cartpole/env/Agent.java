package ru.dutov.cartpole.env;

import java.util.ArrayDeque;

public class Agent {

    /**
     * Класс реализующий агента.
     * Подразумевалось использование нейронных сетей
     *
     * @param stateSize количество параметров входящих в нейронную сеть
     * @param actionSize количество вариантов действий агента
     * @param storage хранилище совершенных агентом действий и их последствий
     * @param explorationRate процент рандомных действий агента
     * @param explorationMin минимальный процент
     * @param explorationDecay уменьшение рандомных решений после каждого обучения
     * @param learningRate изменение скорости обучения оптимизатора с течением времени
     */
    private int stateSize;
    private int actionSize;
    private ArrayDeque<Memory> storage = new ArrayDeque<>(2000);
    private double explorationRate = 0.9;
    private final double explorationMin = 0.01;
    private final double explorationDecay = 0.995;
    private final double learningRate = 0.001;

    /**
     * Конструктор
     * @param stateSize
     * @param actionSize
     */
    public Agent(int stateSize, int actionSize) {
        this.stateSize = stateSize;
        this.actionSize = actionSize;
    }

    /**
     * Метод для добавления в память действия агента
     * @param past до действия агента
     * @param present после
     */
    public void remember(Status past, Status present) {
        Memory tmp = new Memory(past, present);
        this.storage.addLast(tmp);
    }

    /**
     * Метод для выбора действия агента
     * @param state статус тележки, из которого используется только положение тележки
     * @return возвращает действие 0 или 1 (рандомное, т.к. нет нейронной сети)
     */
    public int getAction(Status state) {
        if (Math.random() <= this.explorationRate) {
            return (int) Math.round(Math.random());
        }

        return 0;
    }

    /**
     * Тренировка агента
     * @param batchSize количество накопленных действий перед началом обучения и длительность обучения
     */
    public void training(int batchSize) {
        if (this.storage.size() < batchSize) {
            return;
        }

        ArrayDeque<Memory> cloneStorage = this.storage.clone();

        Memory[] sample = new Memory[batchSize];// = this.storage.element();
        for (int i = 0; i < batchSize; i++) {
            sample[i] = cloneStorage.pop();
        }

        for (int i = 0; i < batchSize; i++) {
            int target = sample[i].getReward();
            if (!sample[i].isDone()) {
                double gamma = 0.95;
                target = (int) (sample[i].getReward() + gamma);// * prediction
                // Qsa = (1 - alpha) * Qsa + alpha * (Rsan + gamma * prediction)
                // Qsa = reward + gamma * prediction
            }
            double[] probability; //= prediction
            // training
            if (this.explorationRate > this.explorationMin) {
                this.explorationRate *= this.explorationDecay;
            }
        }
    }
}
