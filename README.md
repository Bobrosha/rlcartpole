# rlcartpole
Портирована модель CartPole-v1 из OpenAI Gym с Python на Java Написана заготовка под Reinforcement lerning. 
Не хватает тольно нейронной сети, на вход которой подается 4 значения (массив из 4х значений) 
1, 2 - положение тележки в окружении; 
3, 4 - угол отклонения маятника от нормали 
Нейронная сеть должна генерировать предсказание действия (0 или 1)
Программа последовательно выводит выводит эпизоды игры и награду, полученную агентом за каждый эпизод. По умолчанию их 1000, можно изменить в основном классе. 
