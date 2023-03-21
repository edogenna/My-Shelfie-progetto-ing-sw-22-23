package it.polimi.ingsw;

public final class PersonalCard {
     private int i, j;
     private Card[] bookshelf = new Card[12];


}

class Card {
     int IdCard, i;
     private Triplet[] DataTriad = new Triplet[6];

     Card(int IdCard, int x, int y, Enum color){
          this.IdCard = IdCard;
          for(i=0; i<6; i++)
               DataTriad[i] = new Triplet(x, y, color);
     }
}

class Triplet {
     private int x, y;
     private Enum color;

     Triplet(int x, int y, Enum color){
          this.x = x;
          this.y = y;
          this.color = color;
     }
}
/*
x e y sono interi; color è un enum.
Card 1:   {x_1; y_1; color_1}
          {x_2; y_2; color_2}

array di lunghezza 12 card
*/