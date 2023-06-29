package it.polimi.ingsw.model;

import com.google.gson.annotations.Expose;
import it.polimi.ingsw.ItemEnum;

/**
 * This class contains the personal cards used in the game
 * @author Donato Fiore
 */
public final class PersonalCards {
     @Expose
     private final Card[] figure = new Card[12];

     //implementation of the 12 personal cards; each card has 6 triplets.
     public PersonalCards(){
          for(int i =0; i <12; i++)
               this.figure[i] = new Card();

          figure[0].addTriplet(0,2,0, ItemEnum.AZURE1);
          figure[0].addTriplet(1,4,1, ItemEnum.WHITE1);
          figure[0].addTriplet(2,2,2, ItemEnum.BLUE1);
          figure[0].addTriplet(3,4,2, ItemEnum.GREEN1);
          figure[0].addTriplet(4,3,3, ItemEnum.PURPLE1);
          figure[0].addTriplet(5,0,4, ItemEnum.YELLOW1);

          figure[1].addTriplet(0,2,0, ItemEnum.YELLOW1);
          figure[1].addTriplet(1,1,1, ItemEnum.WHITE1);
          figure[1].addTriplet(2,0,2, ItemEnum.PURPLE1);
          figure[1].addTriplet(3,3,2, ItemEnum.BLUE1);
          figure[1].addTriplet(4,5,3, ItemEnum.AZURE1);
          figure[1].addTriplet(5,4,4, ItemEnum.GREEN1);

          figure[2].addTriplet(0,0,0, ItemEnum.PURPLE1);
          figure[2].addTriplet(1,3,1, ItemEnum.YELLOW1);
          figure[2].addTriplet(2,0,2, ItemEnum.BLUE1);
          figure[2].addTriplet(3,5,2, ItemEnum.AZURE1);
          figure[2].addTriplet(4,2,3, ItemEnum.WHITE1);
          figure[2].addTriplet(5,1,4, ItemEnum.GREEN1);

          figure[3].addTriplet(0,1,0, ItemEnum.BLUE1);
          figure[3].addTriplet(1,5,0, ItemEnum.WHITE1);
          figure[3].addTriplet(2,3,1, ItemEnum.GREEN1);
          figure[3].addTriplet(3,2,2, ItemEnum.PURPLE1);
          figure[3].addTriplet(4,1,3, ItemEnum.YELLOW1);
          figure[3].addTriplet(5,3,4, ItemEnum.AZURE1);

          figure[4].addTriplet(0,5,0, ItemEnum.YELLOW1);
          figure[4].addTriplet(1,1,1, ItemEnum.AZURE1);
          figure[4].addTriplet(2,3,1, ItemEnum.BLUE1);
          figure[4].addTriplet(3,3,2, ItemEnum.WHITE1);
          figure[4].addTriplet(4,5,3, ItemEnum.GREEN1);
          figure[4].addTriplet(5,4,4, ItemEnum.PURPLE1);

          figure[5].addTriplet(0,5,0, ItemEnum.BLUE1);
          figure[5].addTriplet(1,4,1, ItemEnum.AZURE1);
          figure[5].addTriplet(2,0,2, ItemEnum.YELLOW1);
          figure[5].addTriplet(3,2,2, ItemEnum.GREEN1);
          figure[5].addTriplet(4,3,4, ItemEnum.WHITE1);
          figure[5].addTriplet(5,4,4, ItemEnum.PURPLE1);

          figure[6].addTriplet(0,4,1, ItemEnum.BLUE1);
          figure[6].addTriplet(1,0,4, ItemEnum.AZURE1);
          figure[6].addTriplet(2,1,1, ItemEnum.YELLOW1);
          figure[6].addTriplet(3,3,3, ItemEnum.GREEN1);
          figure[6].addTriplet(4,2,0, ItemEnum.WHITE1);
          figure[6].addTriplet(5,5,3, ItemEnum.PURPLE1);

          figure[7].addTriplet(0,2,2, ItemEnum.BLUE1);
          figure[7].addTriplet(1,3,3, ItemEnum.AZURE1);
          figure[7].addTriplet(2,4,4, ItemEnum.YELLOW1);
          figure[7].addTriplet(3,5,0, ItemEnum.GREEN1);
          figure[7].addTriplet(4,0,2, ItemEnum.WHITE1);
          figure[7].addTriplet(5,1,1, ItemEnum.PURPLE1);

          figure[8].addTriplet(0,1,3, ItemEnum.BLUE1);
          figure[8].addTriplet(1,3,0, ItemEnum.AZURE1);
          figure[8].addTriplet(2,4,4, ItemEnum.YELLOW1);
          figure[8].addTriplet(3,0,0, ItemEnum.GREEN1);
          figure[8].addTriplet(4,5,2, ItemEnum.WHITE1);
          figure[8].addTriplet(5,2,1, ItemEnum.PURPLE1);

          figure[9].addTriplet(0,0,4, ItemEnum.BLUE1);
          figure[9].addTriplet(1,2,2, ItemEnum.AZURE1);
          figure[9].addTriplet(2,5,3, ItemEnum.YELLOW1);
          figure[9].addTriplet(3,1,1, ItemEnum.GREEN1);
          figure[9].addTriplet(4,4,3, ItemEnum.WHITE1);
          figure[9].addTriplet(5,3,0, ItemEnum.PURPLE1);

          figure[10].addTriplet(0,4,3, ItemEnum.BLUE1);
          figure[10].addTriplet(1,0,2, ItemEnum.AZURE1);
          figure[10].addTriplet(2,4,1, ItemEnum.YELLOW1);
          figure[10].addTriplet(3,0,4, ItemEnum.GREEN1);
          figure[10].addTriplet(4,2,3, ItemEnum.WHITE1);
          figure[10].addTriplet(5,5,0, ItemEnum.PURPLE1);

          figure[11].addTriplet(0,5,4, ItemEnum.BLUE1);
          figure[11].addTriplet(1,4,3, ItemEnum.AZURE1);
          figure[11].addTriplet(2,2,2, ItemEnum.YELLOW1);
          figure[11].addTriplet(3,2,0, ItemEnum.GREEN1);
          figure[11].addTriplet(4,3,4, ItemEnum.WHITE1);
          figure[11].addTriplet(5,1,1, ItemEnum.PURPLE1);
     }

     public Card getCard(int i){
          return this.figure[i];
     }

}

class Triplet {
     @Expose
     private final int x;
     @Expose
     private final int y;
     @Expose
     private final ItemEnum color;

     public Triplet(int x, int y, ItemEnum color){
          this.x = x;
          this.y = y;
          this.color = color;
     }

     public int getX(){
          return this.x;
     }

     public int getY(){
          return this.y;
     }

     public ItemEnum getColor(){
          return this.color;
     }
}
/*
x e y are int; color is ItemEnum.
Card 1:   {x_1; y_1; color_1}
          {x_2; y_2; color_2}

array of length 12 card
*/