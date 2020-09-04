package ru.dutov.cartpole.main;

import ru.dutov.cartpole.env.Agent;
import ru.dutov.cartpole.env.CartPoleEnv;
import ru.dutov.cartpole.env.Status;

/**
 * Основной класс
 */
public class Main {
    public static void main(String[] args) {
        CartPoleEnv env = new CartPoleEnv();

        Agent agent = new Agent(env.getObservationSpace(), env.getActionSpace());

        /**
         * Количество эпизодов игры
         */
        int episodes = 1000;

        for (int e = 0; e < episodes; e++) {
            Status state = env.reset();

            int frames = 0;

            boolean done = false;

            while (!done) {
                frames++;

                int action = agent.getAction(state);

                Status stateNext = env.step(action);

                done = stateNext.isDone();
                agent.remember(state, stateNext);
                state = stateNext;
            }

            System.out.format("Эпизод: %4d / %d, продолжительность игры в кадрах: %4d%n", e + 1, episodes, frames);
        }
    }
}
