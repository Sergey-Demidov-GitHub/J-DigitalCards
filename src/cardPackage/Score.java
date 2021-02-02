/*  Copyright (C) 2021 Sergey Demidov   */

package cardPackage;

public class Score {
    private int total_j;
    private int correct_j;
    private int streak_j;
    private int total_e;
    private int correct_e;
    private int streak_e;

    private int absolute_j;
    private int absolute_e;

    public Score(){
        this.total_j = 0;
        this.correct_j = 0;
        this.streak_j = 0;
        this.total_e = 0;
        this.correct_e = 0;
        this.streak_e = 0;

        this.absolute_j = 0;
        this.absolute_e = 0;
    }

    public Score(int total_j, int correct_j, int streak_j, int total_e, int correct_e, int streak_e){
        this.total_j = total_j;
        this.correct_j = correct_j;
        this.streak_j = streak_j;
        this.total_e = total_e;
        this.correct_e = correct_e;
        this.streak_e = streak_e;

        calcAbsolute_j();
        calcAbsolute_e();
    }

    @Override
    public String toString() {
        return "Score{" +
                "total_j=" + total_j +
                ", correct_j=" + correct_j +
                ", streak_j=" + streak_j +
                ", total_e=" + total_e +
                ", correct_e=" + correct_e +
                ", streak_e=" + streak_e +
                ", absolute_j=" + absolute_j +
                ", absolute_e=" + absolute_e +
                '}';
    }

    public void plusScore(boolean reversed_b) {
        if (!reversed_b)
            plus_j();
        else
            plus_e();

    }

    private void plus_j() {
        total_j++;
        correct_j++;

        if (streak_j > 0)
            streak_j++;
        else
            streak_j = 1;

        calcAbsolute_j();
    }

    private void plus_e() {
        total_e++;
        correct_e++;

        if (streak_e > 0)
            streak_e++;
        else
            streak_e = 1;

        calcAbsolute_e();
    }

    public void minusScore(boolean reversed_b) {
        if (!reversed_b)
            minus_j();
        else
            minus_e();

    }

    private void minus_j() {
        total_j++;

        if (streak_j < 0)
            streak_j--;
        else
            streak_j = -1;


        calcAbsolute_j();
    }

    private void minus_e() {
        total_e++;

        if (streak_e < 0)
            streak_e--;
        else
            streak_e = -1;

        calcAbsolute_e();
    }


    private void calcAbsolute_j() {
        this.absolute_j = calcAbsolute(total_j, correct_j);
    }

    private void calcAbsolute_e() {
        this.absolute_e = calcAbsolute(total_e, correct_e);
    }

    private int calcAbsolute(int total, int correct) {
        int absolute = 0;
        if (total != 0)
            absolute = (int) (((double) correct / (double) total) * 100.0);
        return absolute;
    }

    //==============================================================================================================


    public int getTotal_j() {
        return total_j;
    }

    public void setTotal_j(int total_j) {
        this.total_j = total_j;
    }

    public int getCorrect_j() {
        return correct_j;
    }

    public void setCorrect_j(int correct_j) {
        this.correct_j = correct_j;
    }

    public int getStreak_j() {
        return streak_j;
    }

    public void setStreak_j(int streak_j) {
        this.streak_j = streak_j;
    }

    public int getTotal_e() {
        return total_e;
    }

    public void setTotal_e(int total_e) {
        this.total_e = total_e;
    }

    public int getCorrect_e() {
        return correct_e;
    }

    public void setCorrect_e(int correct_e) {
        this.correct_e = correct_e;
    }

    public int getStreak_e() {
        return streak_e;
    }

    public void setStreak_e(int streak_e) {
        this.streak_e = streak_e;
    }

    public int getAbsolute_j() {
        return absolute_j;
    }

    public int getAbsolute_e() {
        return absolute_e;
    }

    public Integer[] getScoreValues(boolean orderJapEng) {
        Integer[] scoreValues = new Integer[2];
        if (orderJapEng){
            scoreValues[0] = absolute_j;
            scoreValues[1] = streak_j;
        } else {
            scoreValues[0] = absolute_e;
            scoreValues[1] = streak_e;
        }
        return scoreValues;
    }
}
