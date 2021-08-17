package com.mps.joinfork.arrayrecursiveaction.faktorielle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class FaktorielleRT extends RecursiveTask<BigInteger> {

    private static final int THRESHOLD = 5;
    private int start, end, mid;

    public FaktorielleRT(int start, int end) {

        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {

        int length = end - start;
        if (length < THRESHOLD) {
            BigInteger result = BigInteger.ONE;
            for (int i = start; i <= start; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        } else {

            mid = start + (end - start) / 2;
            FaktorielleRT leftTask = new FaktorielleRT(start, mid);
            leftTask.fork();
            FaktorielleRT rightTask = new FaktorielleRT(mid + 1, end);
            BigInteger rightResult = rightTask.compute();
            BigInteger leftResult = leftTask.join();
            BigInteger result = leftResult.multiply(rightResult);

            return result;

        }






    }
}
