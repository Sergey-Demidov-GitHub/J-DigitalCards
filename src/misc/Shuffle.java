/*  Copyright (C) 2021 Sergey Demidov   */

package misc;

public class Shuffle {


    /*
     * shuffles the the array content
     * implementation of the Fisher–Yates shuffle (Durstenfeld's version)
     */
    public static void shuffle(Integer[] indexArray){
        int wall;   // first shuffled index
        int k;      // random
        int length = indexArray.length;

        for(int i= 0; i < length-1; i++){
            wall = length -1 -i;
            k = Math.max((int) (Math.random() * wall), 0);       //forced swap every time

            // swap array[wall] <=> array[k]
            int temp = indexArray[wall];
            indexArray[wall] = indexArray[k];
            indexArray[k] = temp;

            //System.out.print("i =" + i + " k = " + k + " array: " );
            //printArray(indexArray);
        }
        //printArray(indexArray);
    }

    public static void printArray(Integer[] array){
        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");

        }
        System.out.print("] \n");
    }
}
