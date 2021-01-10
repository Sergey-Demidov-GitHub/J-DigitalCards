package cardPackage;

/**
 * only id and score
 */
public class Card {
    private int id;
    private Score score;

    public Card (int id, Score score) {
        this.id = id;
        this.score = score;

    }

    public Card (int id) {
        this.id = id;
        this.score = new Score();

    }

    @Override
    public String toString() {
        return "Card{" +
                "score=" + score +
                ", id=" + id +
                '}';
    }

    public CardType getCardType() {
        System.out.println("[WARNING] You tried to access BasicCard.getCardType() !");
        return null;
    }

    //==============================================================================================================

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
