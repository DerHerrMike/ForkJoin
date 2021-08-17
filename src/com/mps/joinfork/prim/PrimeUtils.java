package com.mps.joinfork.prim;

/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class PrimeUtils {

    public static boolean isPrime(int x) {

        for(int i = 2; i<= x/2; i++){
            if(x % i == 0){
                return false;
             }
        }


	return true;
    }

}
