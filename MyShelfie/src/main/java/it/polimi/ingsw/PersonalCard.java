package it.polimi.ingsw;

import java.util.ArrayList;

public final class PersonalCard {
     private int i, j;
     private Card[] bookshelf = new Card[12];

     //implementation of the 12 personal cards; each card has 6 triplets.
     PersonalCard(){
          for(i=0; i<12; i++)
               this.bookshelf[i] = new Card(i);

          bookshelf[0].addTriplet(0,2,0, ItemEnum.AZURE);
          bookshelf[0].addTriplet(1,4,1, ItemEnum.WHITE);
          bookshelf[0].addTriplet(2,2,2, ItemEnum.BLUE);
          bookshelf[0].addTriplet(3,4,2, ItemEnum.GREEN);
          bookshelf[0].addTriplet(4,3,3, ItemEnum.PURPLE);
          bookshelf[0].addTriplet(5,0,4, ItemEnum.YELLOW);

          bookshelf[1].addTriplet(0,2,0, ItemEnum.YELLOW);
          bookshelf[1].addTriplet(1,1,1, ItemEnum.WHITE);
          bookshelf[1].addTriplet(2,0,2, ItemEnum.PURPLE);
          bookshelf[1].addTriplet(3,3,2, ItemEnum.BLUE);
          bookshelf[1].addTriplet(4,5,3, ItemEnum.AZURE);
          bookshelf[1].addTriplet(5,4,4, ItemEnum.GREEN);

          bookshelf[2].addTriplet(0,0,0, ItemEnum.PURPLE);
          bookshelf[2].addTriplet(1,3,1, ItemEnum.YELLOW);
          bookshelf[2].addTriplet(2,0,2, ItemEnum.BLUE);
          bookshelf[2].addTriplet(3,5,2, ItemEnum.AZURE);
          bookshelf[2].addTriplet(4,2,3, ItemEnum.WHITE);
          bookshelf[2].addTriplet(5,1,4, ItemEnum.GREEN);

          bookshelf[3].addTriplet(0,1,0, ItemEnum.BLUE);
          bookshelf[3].addTriplet(1,5,0, ItemEnum.WHITE);
          bookshelf[3].addTriplet(2,3,1, ItemEnum.GREEN);
          bookshelf[3].addTriplet(3,2,2, ItemEnum.PURPLE);
          bookshelf[3].addTriplet(4,1,3, ItemEnum.YELLOW);
          bookshelf[3].addTriplet(5,3,4, ItemEnum.AZURE);

          bookshelf[4].addTriplet(0,5,0, ItemEnum.YELLOW);
          bookshelf[4].addTriplet(1,1,1, ItemEnum.AZURE);
          bookshelf[4].addTriplet(2,3,1, ItemEnum.BLUE);
          bookshelf[4].addTriplet(3,3,2, ItemEnum.WHITE);
          bookshelf[4].addTriplet(4,5,3, ItemEnum.GREEN);
          bookshelf[4].addTriplet(5,4,4, ItemEnum.PURPLE);

          bookshelf[5].addTriplet(0,5,0, ItemEnum.BLUE);
          bookshelf[5].addTriplet(1,4,1, ItemEnum.AZURE);
          bookshelf[5].addTriplet(2,0,2, ItemEnum.YELLOW);
          bookshelf[5].addTriplet(3,2,2, ItemEnum.GREEN);
          bookshelf[5].addTriplet(4,3,4, ItemEnum.WHITE);
          bookshelf[5].addTriplet(5,4,4, ItemEnum.PURPLE);
     }

}

class Card{
     int IdCard, i;
     private triplet[] dataTriad = new triplet[6];

     Card(int IdCard){
          this.IdCard = IdCard;
     }
     void addTriplet(int idTriplet,int x, int y, ItemEnum color){
          dataTriad[idTriplet] = new triplet(x, y, color);
     }
}

class triplet{
     private int x, y;
     private ItemEnum color;

     triplet(int x, int y, ItemEnum color){
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