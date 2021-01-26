package legacy.legacyPatcher;

import cardPackage.*;
import cardPackage.Types.AdjectiveType;
import cardPackage.Types.CardType;
import cardPackage.Types.VerbType;
import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import misc.Misc;

/**
 * expected String format:
 * jap$eng$jap_c$eng_c$CardType.value()$0$0$0$0$0$0$
 *                       or
 * kanji$kun$translation$on$KANJI$0$0$0$0$0$0$
 *
 * jap_c && eng_c can be null
 * '$' after every argument as divider (11 total)
 * '§' := '\n'
 */
public class LegacyConverter {
    private static DBCommInterface dBCommunicator = new DBComm();

    // =============================================================

    /**
     * Main function of this class.
     * Converts a legacyDeck into a readable format and adds it to the DB.
     */
    public void convert(String fileName) {
        Card[] cards = getCardArr(fileName);
        addToDB(cards, fileName);
    }


    private Card[] getCardArr(String filename) {
        String path = "legacy/legacyDecks/" + filename;
        IO io = new IO();
        String[] stringCards = io.read(path);
        Card[] cards = new Card[stringCards.length];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = stringToCard(stringCards[i]);
        }

        return cards;
    }

    private void addToDB(Card[] cards, String deckName) {
        int deck_id = dBCommunicator.addDeck(deckName);
        for (Card card: cards) {
            int card_id = dBCommunicator.addCard(card);
            dBCommunicator.addCardToDeck_relation(deck_id, card_id);
        }
    }
























    // ============================================================= String to Card

    private Card stringToCard(String legacyCard) {
        int AttributeCount = 11;
        String[] cardAttributes = new String[AttributeCount];

        //--------------------------------------------------------//
        boolean corrupted = false;
        int arg[] = new int[AttributeCount];
        int argCount = 0;
        //--------------------------------------------------------//
        if(legacyCard != null){
            char c_str[] = legacyCard.toCharArray();
            for(int i = 0; i < c_str.length; i++){
                if(c_str[i] == '$'){
                    if(argCount < AttributeCount){
                        arg[argCount] = i;
                    }
                    argCount++;
                }
            }
        } else {
            System.out.println("[ERROR] 'LegacyConverter.stringToCard' line is null");
        }
        //--------------------------------------------------------//
        if(argCount != AttributeCount){
            corrupted = true;
            System.out.println("[ERROR] 'LegacyConverter.stringToCard' following line has a wrong argument count");
            System.out.println(legacyCard);
        } else {
            cardAttributes[0] = legacyCard.substring(0, arg[0]);
            for(int i = 1; i < AttributeCount; i++){
                cardAttributes[i] = convertString(legacyCard.substring(arg[i-1]+1, arg[i]));
            }
        }
        //--------------------------------------------------------//
        if(corrupted){
            System.err.println("[ERROR] 'LegacyConverter.stringToCard' corrupted!!!");
            return null;
        } else{
            return getCard_fromArray(cardAttributes);
        }
    }

    private Card getCard_fromArray(String[] cardAttributes){
        CardType cardType = null;
        Card card = null;
        int tempId = -100;


        // CardType
        if (cardAttributes[4] != null && !Misc.isBlank(cardAttributes[4])) {
            try {
                cardType = CardType.fromValue(cardAttributes[4]);
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR] 'LegacyConverter.getCard_fromArray()' No such CardType: " + cardAttributes[4] + ".");
                return null;
            }
        } else {
            System.err.println("[ERROR] 'LegacyConverter.getCard_fromArray()' CardType is null or empty.");
            return null;
        }

        // content
        switch (cardType) {
            case NOUN: {
                if (NounCard.isValid(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3]))
                        card = new NounCard(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3]);
                else
                    return null;
                break;
            }
            case VERB: {
                if (VerbCard.isValid(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3], VerbType.UNKNOWN))
                    card = new VerbCard(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3]);
                else
                    return null;
                break;
            }
            case ADJECTIVE: {
                if (AdjectiveCard.isValid(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3], AdjectiveType.UNKNOWN))
                    card = new AdjectiveCard(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3]);
                else
                    return null;
                break;
            }
            case OTHER: {
                if (OtherCard.isValid(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3]))
                    card = new OtherCard(tempId, cardAttributes[0], cardAttributes[2], cardAttributes[1], cardAttributes[3]);
                else
                    return null;
                break;
            }
            case KANJI: {
                if (KanjiCard.isValid(tempId, cardAttributes[0], cardAttributes[1], cardAttributes[3], cardAttributes[2]))
                    card = new KanjiCard(tempId, cardAttributes[0], cardAttributes[1], cardAttributes[3], cardAttributes[2]);
                else
                    return null;
                break;
            }
        }


        return card;
    }

    // substitute: '§' -> '\n'    and      "null" -> ""
    private String convertString(String str_old) {
        String str_new = "";

        if (!str_old.trim().equals("null")) {
            str_new = str_old.trim();

            if (str_old.contains("§"))
                str_new = str_new.replace("§", "\n");

        }

        return str_new;
    }
















































    // Test no equals method defined for Card subclasses -> visual comparison will suffice
    public void test_stringToCard() {
        String str_noun = "映\u200B画$movie$えいが$null$NOUN$0$0$0$4$6$3$";
        String str_verb = "読\u200Bむ$to read$よむ$(~を)$VERB$0$0$0$6$6$6$";
        String str_adjective = "いい$good$null$null$ADJECTIVE$0$0$0$6$6$6$";
        String str_other = "たいてい$usually$null$null$OTHER$0$0$0$6$6$6$";
        String str_kanji = "画$ $picture$が、かく$KANJI$0$0$0$1$3$1$";

        int tempId = -100;
        NounCard card_noun = new NounCard(tempId, "映\u200B画", "えいが", "movie", "");
        VerbCard card_verb = new VerbCard(tempId, "読\u200Bむ", "よむ", "to read", "(~を)");
        AdjectiveCard card_adjective = new AdjectiveCard(tempId, "いい", "", "good", "");
        OtherCard card_other = new OtherCard(tempId, "たいてい", "", "usually", "");
        KanjiCard card_kanji = new KanjiCard(tempId, "画", "", "が、かく", "picture");

        NounCard newNoun = (NounCard) stringToCard(str_noun);
        VerbCard newVerb = (VerbCard) stringToCard(str_verb);
        AdjectiveCard newAdjective = (AdjectiveCard) stringToCard(str_adjective);
        OtherCard newOther = (OtherCard) stringToCard(str_other);
        KanjiCard newKanji = (KanjiCard) stringToCard(str_kanji);

        System.out.println("==========================================");
        System.out.println("[TEST] LegacyConverter.test_stringToCard");
        System.out.println("==========================================");

        System.out.println("[TEST] card.noun: ");
        System.out.println(card_noun.toString());
        System.out.println(newNoun.toString() + "\n");

        System.out.println("[TEST] card.verb: ");
        System.out.println(card_verb.toString());
        System.out.println(newVerb.toString() + "\n");

        System.out.println("[TEST] card.adjective: ");
        System.out.println(card_adjective.toString());
        System.out.println(newAdjective.toString() + "\n");

        System.out.println("[TEST] card.other: ");
        System.out.println(card_other.toString());
        System.out.println(newOther.toString() + "\n");

        System.out.println("[TEST] card.kanji: ");
        System.out.println(card_kanji.toString());
        System.out.println(newKanji.toString() + "\n");
    }














}
