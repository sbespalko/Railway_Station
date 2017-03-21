package ru.sbespalko;

/**
 * Класс поезда для задачи о вокзальном журнале
 * Хранит время прибытия и отправления (в рамках одного дня)
 */
class Train {

    public int arrive;
    public int depart;

    //метод автозаполнения времени прибытия-убытия
    public void setAutoTime() {
        arrive = (int) (Math.random() * 23.0 + 1); //1-23
        depart = (int) (arrive + (Math.random() * (24.0 - arrive) + 1));
    }

    //поезд на вокзале?
    public boolean isArrived(int timeNow) {
        if (arrive <= timeNow && depart >= timeNow) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Train [arrive=" + arrive + ", depart=" + depart + "]";
    }

}
