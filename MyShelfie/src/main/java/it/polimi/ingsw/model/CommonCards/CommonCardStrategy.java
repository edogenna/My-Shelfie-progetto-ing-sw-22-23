package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;

/**
 * Strategy Pattern for common cards
 * @author Alessandro Fornara
 * @author Samuele Galli
 */
public interface CommonCardStrategy {
    /**
     * Determines if a player's bookshelf has a disposition of tiles that matches a certain common goal card.
     * @param b Bookshelf
     * @return true if the bookshelf matches the common card, false otherwise.
     */
    boolean checkBookshelf(ItemEnum[][] b);

    /**
     * Prints a certain common goal card on CLI
     */
    void printCommonCard();

    /**
     * Returns the design of a common goal card as a CharMatrix
     * @return Common Card design
     */
    CharMatrix printCommonCardMatrix();
}

