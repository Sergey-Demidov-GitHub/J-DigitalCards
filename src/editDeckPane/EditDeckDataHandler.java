package editDeckPane;

import cardPackage.Card;
import deckPackage.Deck;
import fancyTableView.FancyTableView;
import main.MainController;

/**
 * This class handles the data related operations for the EditDeckController.
 */
public class EditDeckDataHandler {
    MainController mainController;
    EditDeckController controller;

    EditDeckDataHandler(EditDeckController controller) {
        mainController = MainController.getInstance();
        this.controller = controller;
    }

    /**
     * Adds existing card to deck currently loaded in Session.
     * @param card Card to be added to the deck
     */
    protected void addExistingCard(Card card) {
        boolean isNew = !mainController.getSession().getDeck().checkCardInDeckById(card.getId());
        if(isNew) {
            mainController.getSession().addNewRelation(card.getId());
            mainController.getSession().addNewCardToDeck(card);
        } else {
            System.out.println("[INFO] Card is already in deck!");
        }

        controller.reloadFancyTableView();
        controller.setPendingChanges(true);
    }

    /**
     * Removes selected card from deck currently loaded in Session.
     * @param fancyTableView - FancyTableView containing currently loaded deck.
     *                       TODO: change param dependency
     */
    protected void removeSelection(FancyTableView fancyTableView) {
        int selectedCardIndex = fancyTableView.getSelectedCardIndex();
        if (!fancyTableView.isEmpty() && selectedCardIndex != -1) {
            this.mainController.getSession().addDeletedIds(selectedCardIndex);
            this.mainController.getSession().getDeck().removeCard(selectedCardIndex);
            fancyTableView.removeSelectedElementFromTable();
            controller.setPendingChanges(true);
        }
    }

    /**
     * Update content of card in deck.
     * @param card - Card with updated content.
     */
    protected void processCardChange(Card card) {
        boolean isNew = !mainController.getSession().getDeck().checkCardInDeckById(card.getId());
        if (!isNew)
            this.mainController.getSession().addChangedIds(card.getId());

        mainController.getSession().addNewCardToDeck(card);     // overrides if card exists

        controller.reloadFancyTableView();;
        controller.setPendingChanges(true);
    }

    /**
     * Apply scheduled changes to data base.
     */
    protected void applySessionChanges() {
        mainController.getSession().applySessionChanges();
    }

    protected Deck getCurrentlyCachedDeck() {
        return mainController.getSession().getDeck();
    }
}
