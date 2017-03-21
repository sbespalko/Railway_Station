package ru.sbespalko;

import java.util.ArrayList;
import java.util.List;

/**
 * На вокзал прибывают-отбывают поезда. Ведется журнал (время прибытия-отбытия (1-24)).
 * Необходимо узнать, какое максимальное кол-во поездов было на вокзале одновременно.
 * Также необходимо указать период, в который был максимум.
 * Вокзал может принять неограниченное кол-во поездов.
 * 
 */

public class Station {
    int maxCount = 0;
    int timeMaxStart = -1;
    int timeMaxFinish = -1;
    public List<Train> trains = new ArrayList<Train>();


    //заполняем журнал расписанием поездов
    public Station(int countTrains) {
        for (int i = 0; i < countTrains; i++) {
            Train train = new Train();
            train.setAutoTime();
            trains.add(train);
        }
    }

    //проверить, сколько поездов в этот час (time 1-24)
    public int checkNow(int time) {
        int count = 0;
        for (int i = 0; i < trains.size(); i++) {
            Train train = trains.get(i);
            if (train.isArrived(time)) {
                count++;
            }
        }
        return count;
    }

    public void calcMaxCount(int startTime, int finishTime) {
        //true - идет период максимума, false - максимум пройден;
        boolean maxStart = false; 
        for (int time = startTime; time <= finishTime; time++) {
            //считаем кол-во поездов на вокзале в этот час
            int trainNowCount = checkNow(time);

            //был ли достигнут максимум
            if (trainNowCount > maxCount) {
                maxCount = trainNowCount;
                //начинается новый период максимума
                maxStart = true;
                timeMaxStart = time;
            } else if (maxStart && trainNowCount != maxCount) {
                //период максимума закончился 
                maxStart = false;
                timeMaxFinish = time - 1;
            }
            
            //печать результат за каждый час
            int fWidth = (int) Math.log10(trains.size())+1;
            System.out.format(
                    "time: %" + 2 + "d | " + "NowCount: %" + fWidth + "d | " + "MaxCount: %" + fWidth + "d | \n",
                    time, trainNowCount, maxCount);
        }
    }
    
    public static void main(String[] args) {
        int countTrains = 5;
        if (args.length == 1){
            try{
                countTrains = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                System.out.println("Введено неверное число. \nКол-во поездов по умолчанию: 5");
            }
        }
        Station station = new Station(countTrains);
        //проверяем каждый час
        station.calcMaxCount(1, 24);
        
        System.out.print("Период наибольшего числа поездов -> ");
        System.out.println("Начало: " + station.timeMaxStart + " Конец: " + station.timeMaxFinish);
        System.out.println("Максимальное число поездов: " + station.maxCount);
    }

}
