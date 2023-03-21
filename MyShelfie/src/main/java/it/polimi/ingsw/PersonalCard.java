package it.polimi.ingsw;

import java.util.ArrayList;

public final class PersonalCard {
     private int i, j;
     private card[] bookshelf = new card[12];


}

class card{
     int IdCard, i;
     private triplet[] DataTriad = new triplet[6];

     card(int IdCard, int x, int y, Enum color){
          this.IdCard = IdCard;
          for(i=0; i<6; i++)
               DataTriad[i] = new triplet(x, y, color);
     }
}

class triplet{
     private int x, y;
     private Enum color;

     triplet(int x, int y, Enum color){
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