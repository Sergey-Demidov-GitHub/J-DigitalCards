/*  Copyright (C) 2021 Sergey Demidov   */

package misc;

import javafx.scene.control.Label;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Misc {
    //public static final String CSS_NOUNCARDGUI = "-fx-background: ";

    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;

    public static final int CARDGUI_WIDTH = 400;
    public static final int CARDGUI_HEIGHT = 300;

    public static final String CSS_DEFAULT_BG_COLOR = "-fx-background: #7CAFC2;";

    public static final int CARD_FONT_SIZE = 30;
    public static final String CSS_CARD_FONT_SIZE = "-fx-font-size: " + CARD_FONT_SIZE + ";";

    public static boolean isBlank(String string) {
        return string.trim().isEmpty();
    }



    public static int getCardFontSize(double space, double magnification) {
        //System.out.println("[INFO] space: " + space);
        int minFontSize = 20;
        int maxFontSize = 50;
        int prefFontSize = (int) (space * magnification);
        int size = (int) (Math.max(Math.min(maxFontSize, prefFontSize), minFontSize));
        //System.out.println("[INFO] dynamic font size: " + size);
        return size;
    }

    public static void setDynamicFontSize(Label node, double magnification) {
        //System.out.println(node.getText());
        Integer[] lineStartIndexArr = getNewLineIndexesInString(node.getText());
        //System.out.println("New Lines at: " + objArrToString(lineStartIndexArr));
        int maxLineLength = maxLineLength(lineStartIndexArr, node.getText().length());
        //System.out.println("maxLineLength: " + maxLineLength);
        double space =  Math.min((node.prefHeight(-1) / lineStartIndexArr.length), (node.prefWidth(-1) / maxLineLength));
        node.setStyle("-fx-font-size: " + Misc.getCardFontSize(space, magnification) + ";");
    }

    // returns empty list if only one line
    public static Integer[] getNewLineIndexesInString(String string) {
        List<Integer> startIndexList = new ArrayList<>();

        int start = string.indexOf('\n', 0);
        if (start != -1) {
            startIndexList.add(0);
            startIndexList.add(start);


            for (int i = start + 1; ; i = startIndexList.get(startIndexList.size() - 1) + 1) {
                int index = string.indexOf('\n', i);
                if (index != -1)
                    startIndexList.add(index);
                else
                    break;
            }
        }

        Integer[] startIndexArr = startIndexList.toArray(new Integer[0]);
        return startIndexArr;
    }

    public static double avg(double a, double b){
        return ((a + b) / 2);
    }

    private static String objArrToString(Object[] objArr) {
        String result = "[ ";
        if (objArr.length > 0) {
            for(int i = 0; i < objArr.length - 1; i++) {
                result += "" + objArr[i] + ", ";
            }
            result += "" + objArr[objArr.length - 1] + " ]";
        } else {
            result += " ]";
        }

        return result;
    }

    private static int maxLineLength(Integer[] arr, int totalLength) {
        int max = 0;
        //int startIndex = 0;
        if (arr.length != 0) {
            for (int i = 0; i < arr.length - 1; i++ ) {
                int difference = arr[i + 1] - arr[i];
                if (difference > max){
                    max = difference;
                    //startIndex = arr[i];
                }

            }
            int difference = totalLength - arr[arr.length - 1];
            if (difference > max){
                max = difference;
                //startIndex = arr[arr.length - 1];
            }
        } else {
            max = totalLength;
        }

        return max;
    }

    /**
     * Compares two japanese strings
     * @param a first String
     * @param b second String
     * @return true if same japanese character sequences
     */
    public static boolean equalsJap(String a, String b) {
        Collator japCollator = Collator.getInstance(Locale.JAPANESE);
        return japCollator.equals(a, b);
    }

}
