package es.edemingo.moviesmanager.utils;

import java.util.Scanner;

public class utilities {

    public static String getScannerString(Scanner scString){
        String myResponse;
        myResponse = scString.nextLine();
        return myResponse;
    }

    public static Long getScannerLong(Scanner scString){
        Long myResponse;
        myResponse = scString.nextLong();
        return myResponse;
    }

    public static int getScannerInt(Scanner scString){
        int myResponse;
        myResponse = scString.nextInt();
        scString.nextLine();
        return myResponse;
    }

}
