package com.mps.joinfork.arrayrecursiveaction;

import java.util.concurrent.RecursiveAction;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class ArrayBerechnungRA extends RecursiveAction {

    private static final int THRESHOLD = 10000;
    private double[] data;
    private int startIndex, endIndex;


    public ArrayBerechnungRA(double[] data, int startIndex, int endIndex) {

        this.data = data;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }





    @Override
    protected void compute() {

        // ISt dieser Sub-Task schon klein genug für die direkte Berechnung?
        //task.length < Threshhold?

        int length = endIndex - startIndex;
        if (length <= THRESHOLD){
            //Ergebnis direkt berechnen
            for (int i = startIndex; i < endIndex ; i++) {
                data[i] = i + Math.tan(Math.sqrt(i));
            }

        }else {
            //Task in weitere kleinere Tasks aufteilen
            int mid = startIndex + (endIndex - startIndex) / 2;

            //in linken Teiltask aufteilen
            ArrayBerechnungRA leftTask = new ArrayBerechnungRA(data, startIndex, mid);
            //linken Teiltask asynchron starten
            leftTask.fork();

            // in rechten Teiltask aufteilen
            ArrayBerechnungRA rightTask = new ArrayBerechnungRA(data, mid+1, endIndex);
            // rechte Teiltask ausführen
            rightTask.compute();

            //auf den linken Teiltask warten
            // join() <-- warte hier so lange bis der linke fertig ist
            leftTask.join();
        }
    }
}
