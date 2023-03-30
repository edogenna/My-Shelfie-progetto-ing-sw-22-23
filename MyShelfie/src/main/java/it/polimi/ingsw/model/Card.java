package it.polimi.ingsw.model;

import it.polimi.ingsw.ItemEnum;

public class Card {

    int idCard, i;
    private Triplet[] dataTriad = new Triplet[6];
    private ItemEnum[][] matrixPersonal = new ItemEnum[6][5];

    Card(int idCard) {
        this.idCard = idCard;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++)
                matrixPersonal[i][j] = ItemEnum.BLANK;
    }

    Card() {
        idCard = -1;
    }

    void addTriplet(int idTriplet, int x, int y, ItemEnum color) {
        matrixPersonal[x][y] = color;
        dataTriad[idTriplet] = new Triplet(x, y, color);
    }

    public ItemEnum[][] getMatrix() {
        ItemEnum[][] copy = new ItemEnum[6][5];
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++)
                copy[i][j] = matrixPersonal[i][j];
        return copy;
    }

    public Triplet getTriplet(int i) {
        Triplet tris;

        tris = dataTriad[i];

        return tris;
    }
}
