package dbUtils;

import cardPackage.*;
import cardPackage.Types.AdjectiveType;
import cardPackage.Types.CardType;
import cardPackage.Types.VerbType;
import deckPackage.Deck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLUtility {
    private static HashMap<CardType, String> cardType_typeContentName_map;
    static {
        cardType_typeContentName_map = new HashMap<>();
        cardType_typeContentName_map.put(CardType.NOUN, "type_noun");
        cardType_typeContentName_map.put(CardType.VERB, "type_verb");
        cardType_typeContentName_map.put(CardType.ADJECTIVE, "type_adjective");
        cardType_typeContentName_map.put(CardType.OTHER, "type_other");
        cardType_typeContentName_map.put(CardType.KANJI, "type_kanji");
    }



    public static void clearCardBase() {
        String sql = "DELETE FROM card_base";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetCardBaseIndex() {
        resetAutoincrementIndex("card_base");
    }

    public static void clearDeckBase() {
        String sql = "DELETE FROM deck_base";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetDeckBaseIndex() {
        resetAutoincrementIndex("deck_base");
    }

    private static void resetAutoincrementIndex(String tableName) {
        String sql = "UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME=?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tableName);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCardBase_Card(int card_id, CardType cardType) {
        String sql = "INSERT INTO card_base(card_id , type) VALUES (?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.setString(2, cardType.value());
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCardBase_Card(CardType cardType) {
        String sql = "INSERT INTO card_base(type) VALUES (?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cardType.value());
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLatestIdCardBase() {
        String sql = "SELECT card_id FROM card_base WHERE card_id = (SELECT MAX(card_id) FROM card_base)";
        int latest_card_id = -1;

        try {
            Connection connection = DBConnection.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(sql);

            if (rs.next()) {
                latest_card_id = rs.getInt("card_id");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latest_card_id;

    }

    public static int getLatestIdDeckBase() {
        String sql = "SELECT deck_id FROM deck_base WHERE deck_id = (SELECT MAX(deck_id) FROM deck_base)";
        int latest_deck_id = -1;

        try {
            Connection connection = DBConnection.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(sql);

            if (rs.next()) {
                latest_deck_id = rs.getInt("deck_id");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latest_deck_id;
    }

    public static void deleteCardBase_Card(int card_id) {
        String sql = "DELETE FROM card_base WHERE card_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CardType getCardBase_CardType(int card_id) {
        String sql = "SELECT type FROM card_base WHERE card_id=?";
        CardType cardType = null;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                cardType = CardType.fromValue(rs.getString("type"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardType;
    }

    public static void changeCardBase_CardType(Card card) {
        update_genericEntry(card.getId(), "card_base", "type", ((BasicCard) card).getCardType().value());
    }


    public static void insertScore(int card_id, Score score) {
        String sql = "INSERT INTO score(card_id, total_j, correct_j, streak_j, total_e, correct_e, streak_e) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.setInt(2, score.getTotal_j());
            statement.setInt(3, score.getCorrect_j());
            statement.setInt(4, score.getStreak_j());
            statement.setInt(5, score.getTotal_e());
            statement.setInt(6, score.getCorrect_e());
            statement.setInt(7, score.getStreak_e());
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeScore(int card_id, Score score) {
        String sql = "UPDATE score SET total_j=?, correct_j=?, streak_j=?, " +
                "total_e=?, correct_e=?, streak_e=? " +
                "WHERE card_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, score.getTotal_j());
            statement.setInt(2, score.getCorrect_j());
            statement.setInt(3, score.getStreak_j());
            statement.setInt(4, score.getTotal_e());
            statement.setInt(5, score.getCorrect_e());
            statement.setInt(6, score.getStreak_e());
            statement.setInt(7, card_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Score getScore(int card_id) {
        String sql = "SELECT * FROM score WHERE card_id=?";
        Score score = null;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                score = new Score(rs.getInt(2), rs.getInt(3), rs.getInt(4),
                                  rs.getInt(5), rs.getInt(6), rs.getInt(7)
                );
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return score;
    }


    public static void insert_typeNoun(int card_id, String jap_1, String jap_2, String eng_1, String eng_2) {
        String sql = "INSERT INTO type_noun(card_id, j1, j2, e1, e2) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.setString(2, jap_1);
            statement.setString(3, jap_2);
            statement.setString(4, eng_1);
            statement.setString(5, eng_2);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert_typeVerb(int card_id, String jap_1, String jap_2, String eng_1, String eng_2, String type) {
        String sql = "INSERT INTO type_verb(card_id, j1, j2, e1, e2, type) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.setString(2, jap_1);
            statement.setString(3, jap_2);
            statement.setString(4, eng_1);
            statement.setString(5, eng_2);
            statement.setString(6, type);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert_typeAdjective(int card_id, String jap_1, String jap_2, String eng_1, String eng_2, String type) {
        String sql = "INSERT INTO type_adjective(card_id, j1, j2, e1, e2, type) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.setString(2, jap_1);
            statement.setString(3, jap_2);
            statement.setString(4, eng_1);
            statement.setString(5, eng_2);
            statement.setString(6, type);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert_typeOther(int card_id, String jap_1, String jap_2, String eng_1, String eng_2) {
        String sql = "INSERT INTO type_other(card_id, j1, j2, e1, e2) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.setString(2, jap_1);
            statement.setString(3, jap_2);
            statement.setString(4, eng_1);
            statement.setString(5, eng_2);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert_typeKanji(int card_id, String kanji, String kun, String on, String translation) {
        String sql = "INSERT INTO type_kanji(card_id, kanji, kun, onn, translation) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.setString(2, kanji);
            statement.setString(3, kun);
            statement.setString(4, on);
            statement.setString(5, translation);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTypeContent(CardType cardType, int card_id){
        String tableName = cardType_typeContentName_map.get(cardType);
        deleteTypeContent_generic(tableName, card_id);
    }

    private static void deleteTypeContent_generic(String tableName, int card_id) {
        String sql = "DELETE FROM " + tableName + " WHERE card_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static NounCard get_typeNoun_content(int card_id) {
        NounCard card = (NounCard) get_typeBasic_content("type_noun", card_id);
        return card;
    }

    public static VerbCard get_typeVerb_content(int card_id) {
        VerbCard card = (VerbCard) get_typeBasic_content("type_verb", card_id);
        return card;
    }

    public static AdjectiveCard get_typeAdjective_content(int card_id) {
        AdjectiveCard card = (AdjectiveCard) get_typeBasic_content("type_adjective", card_id);
        return card;
    }

    public static OtherCard get_typeOther_content(int card_id) {
        OtherCard card = (OtherCard) get_typeBasic_content("type_other", card_id);
        return card;
    }

    private static BasicCard get_typeBasic_content(String tableName, int card_id) {
        BasicCard card = null;

        String sql = "SELECT * FROM " + tableName + " WHERE card_id=?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                if(tableName.equals("type_noun")){
                    card = new NounCard(card_id,
                            rs.getString("j1"),
                            rs.getString("j2"),
                            rs.getString("e1"),
                            rs.getString("e2"));
                }

                if(tableName.equals("type_verb")){
                    card = new VerbCard(card_id,
                            rs.getString("j1"),
                            rs.getString("j2"),
                            rs.getString("e1"),
                            rs.getString("e2"),
                            VerbType.fromValue(rs.getString("type")));
                }

                if(tableName.equals("type_adjective")){
                    card = new AdjectiveCard(card_id,
                            rs.getString("j1"),
                            rs.getString("j2"),
                            rs.getString("e1"),
                            rs.getString("e2"),
                            AdjectiveType.fromValue(rs.getString("type")));
                }

                if(tableName.equals("type_other")){
                    card = new OtherCard(card_id,
                            rs.getString("j1"),
                            rs.getString("j2"),
                            rs.getString("e1"),
                            rs.getString("e2"));
                }

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return card;
    }

    public static KanjiCard get_typeKanji_content(int card_id) {
        KanjiCard card = null;

        String sql = "SELECT * FROM type_kanji WHERE card_id=?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                card = new KanjiCard(card_id,
                        rs.getString("kanji"),
                        rs.getString("kun"),
                        rs.getString("onn"),
                        rs.getString("translation"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    // update verb
    public static void update_typeVerb_jap1(int card_id, String jap_1) {
        update_genericEntry(card_id, "type_verb", "j1", jap_1);
    }

    public static void update_typeVerb_jap2(int card_id, String jap_2) {
        update_genericEntry(card_id, "type_verb", "j2", jap_2);
    }

    public static void update_typeVerb_eng1(int card_id, String eng_1) {
        update_genericEntry(card_id, "type_verb", "e1", eng_1);
    }

    public static void update_typeVerb_eng2(int card_id, String eng_2) {
        update_genericEntry(card_id, "type_verb", "e2", eng_2);
    }

    public static void update_typeVerb_type(int card_id, String type) {
        update_genericEntry(card_id, "type_verb", "type", type);
    }


    // update adjective
    public static void update_typeAdjective_jap1(int card_id, String jap_1) {
        update_genericEntry(card_id, "type_adjective", "j1", jap_1);
    }

    public static void update_typeAdjective_jap2(int card_id, String jap_2) {
        update_genericEntry(card_id, "type_adjective", "j2", jap_2);
    }

    public static void update_typeAdjective_eng1(int card_id, String eng_1) {
        update_genericEntry(card_id, "type_adjective", "e1", eng_1);
    }

    public static void update_typeAdjective_eng2(int card_id, String eng_2) {
        update_genericEntry(card_id, "type_adjective", "e2", eng_2);
    }

    public static void update_typeAdjective_type(int card_id, String type) {
        update_genericEntry(card_id, "type_adjective", "type", type);
    }


    // update noun
    public static void update_typeNoun_jap1(int card_id, String jap_1) {
        update_genericEntry(card_id, "type_noun", "j1", jap_1);
    }

    public static void update_typeNoun_jap2(int card_id, String jap_2) {
        update_genericEntry(card_id, "type_noun", "j2", jap_2);
    }

    public static void update_typeNoun_eng1(int card_id, String eng_1) {
        update_genericEntry(card_id, "type_noun", "e1", eng_1);
    }

    public static void update_typeNoun_eng2(int card_id, String eng_2) {
        update_genericEntry(card_id, "type_noun", "e2", eng_2);
    }


    // update other
    public static void update_typeOther_jap1(int card_id, String jap_1) {
        update_genericEntry(card_id, "type_other", "j1", jap_1);
    }

    public static void update_typeOther_jap2(int card_id, String jap_2) {
        update_genericEntry(card_id, "type_other", "j2", jap_2);
    }

    public static void update_typeOther_eng1(int card_id, String eng_1) {
        update_genericEntry(card_id, "type_other", "e1", eng_1);
    }

    public static void update_typeOther_eng2(int card_id, String eng_2) {
        update_genericEntry(card_id, "type_other", "e2", eng_2);
    }

    // update kanji
    public static void update_typeKanji_kanji(int card_id, String kanji) {
        update_genericEntry(card_id, "type_kanji", "kanji", kanji);
    }
    public static void update_typeKanji_kun(int card_id, String kun) {
        update_genericEntry(card_id, "type_kanji", "kun", kun);
    }
    public static void update_typeKanji_on(int card_id, String on) {
        update_genericEntry(card_id, "type_kanji", "onn", on);
    }
    public static void update_typeKanji_translation(int card_id, String translation) {
        update_genericEntry(card_id, "type_kanji", "translation", translation);
    }

    private static void update_genericEntry(int card_id, String tableName, String colName, String colValue) {
        String sql = "UPDATE " + tableName +  " SET " + colName + "=?  WHERE card_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, colValue);
            statement.setInt(2, card_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDeckBase(String deck_name) {
        String sql = "INSERT INTO deck_base(deck_name) VALUES (?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, deck_name);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDeckBase_name(int deck_id) {
        String deckName = null;
        String sql = "SELECT * FROM deck_base WHERE deck_id=?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, deck_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                deckName = rs.getString("deck_name");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deckName;
    }

    public static void changeDeckBase_name(int deck_id, String deck_name) {
        String sql = "UPDATE deck_base SET  deck_name=?  WHERE deck_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, deck_name);
            statement.setInt(2, deck_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDeckBase_Deck(int deck_id) {
        String sql = "DELETE FROM deck_base WHERE deck_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, deck_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Deck[] getDeckList() {
        int rowCount = getRowCount("deck_base");
        Deck[] deckList = new Deck[rowCount];

        String sql = "SELECT * FROM deck_base";
        try {
            Connection connection = DBConnection.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(sql);

            int i = 0;
            while (rs.next()) {
                deckList[i] = new Deck(rs.getInt("deck_id"), rs.getString("deck_name"));
                i++;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deckList;
    }


    public static void insertDeckDistribution(int deck_id, int card_id) {
        String sql = "INSERT INTO deck_distribution(deck_id, card_id) VALUES (?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, deck_id);
            statement.setInt(2, card_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDeckDistribution_Card(int deck_id, int card_id) {
        String sql = "DELETE FROM deck_distribution WHERE deck_id = ? and card_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, deck_id);
            statement.setInt(2, card_id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer[] getDeckDistribution_Deck(int deck_id) {
        ArrayList<Integer> distribution = new ArrayList<Integer>();
        String sql = "SELECT * FROM deck_distribution WHERE deck_id=?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, deck_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                distribution.add(rs.getInt("card_id"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Integer[] return_distribution = new Integer[distribution.size()];
        return_distribution = distribution.toArray(return_distribution);
        return return_distribution;
    }

    public static Integer[] getDeckDistribution_Card(int card_id) {
        ArrayList<Integer> distribution = new ArrayList<Integer>();
        String sql = "SELECT * FROM deck_distribution WHERE card_id=?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                distribution.add(rs.getInt("card_id"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Integer[] return_distribution = new Integer[distribution.size()];
        return_distribution = distribution.toArray(return_distribution);
        return return_distribution;
    }

    private static int getRowCount(String tableName) {
        int rowCount = -1;
        String sql = "SELECT COUNT(*) FROM " + tableName;
        try {
            Connection connection = DBConnection.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(sql);
            rs.next();
            rowCount = rs.getInt("COUNT(*)");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

}
