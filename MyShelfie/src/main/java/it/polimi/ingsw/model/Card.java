package it.polimi.ingsw.model;

import it.polimi.ingsw.ItemEnum;

public class Card {
    int idCard;
    private final Triplet[] dataTriad = new Triplet[6];
    private final ItemEnum[][] matrixPersonal = new ItemEnum[6][5];

    public Card(int idCard) {
        this.idCard = idCard;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++)
                matrixPersonal[i][j] = ItemEnum.BLANK;
    }

    public Card() {
        idCard = -1;
    }

    public void addTriplet(int idTriplet, int x, int y, ItemEnum color) {
        matrixPersonal[x][y] = color;
        dataTriad[idTriplet] = new Triplet(x, y, color);
    }

    public ItemEnum[][] getMatrix() {
        ItemEnum[][] copy = new ItemEnum[6][5];
        for (int i = 0; i < 6; i++)
            System.arraycopy(this.matrixPersonal[i], 0, copy[i], 0, 5);
        return copy;
    }

    public Triplet getTriplet(int i) {
        Triplet tris;

        tris = dataTriad[i];

        return tris;
    }
}
