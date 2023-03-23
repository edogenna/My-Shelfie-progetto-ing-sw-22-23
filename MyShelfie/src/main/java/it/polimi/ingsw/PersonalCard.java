package it.polimi.ingsw;

//TODO: change names to "PersonalCard" and "Card" classes

public final class PersonalCard {
     private final Card[] figure = new Card[12];

     //implementation of the 12 personal cards; each card has 6 triplets.
     PersonalCard(){
          for(int i =0; i <12; i++)
               this.figure[i] = new Card(i);

          figure[0].addTriplet(0,2,0, ItemEnum.AZURE);
          figure[0].addTriplet(1,4,1, ItemEnum.WHITE);
          figure[0].addTriplet(2,2,2, ItemEnum.BLUE);
          figure[0].addTriplet(3,4,2, ItemEnum.GREEN);
          figure[0].addTriplet(4,3,3, ItemEnum.PURPLE);
          figure[0].addTriplet(5,0,4, ItemEnum.YELLOW);

          figure[1].addTriplet(0,2,0, ItemEnum.YELLOW);
          figure[1].addTriplet(1,1,1, ItemEnum.WHITE);
          figure[1].addTriplet(2,0,2, ItemEnum.PURPLE);
          figure[1].addTriplet(3,3,2, ItemEnum.BLUE);
          figure[1].addTriplet(4,5,3, ItemEnum.AZURE);
          figure[1].addTriplet(5,4,4, ItemEnum.GREEN);

          figure[2].addTriplet(0,0,0, ItemEnum.PURPLE);
          figure[2].addTriplet(1,3,1, ItemEnum.YELLOW);
          figure[2].addTriplet(2,0,2, ItemEnum.BLUE);
          figure[2].addTriplet(3,5,2, ItemEnum.AZURE);
          figure[2].addTriplet(4,2,3, ItemEnum.WHITE);
          figure[2].addTriplet(5,1,4, ItemEnum.GREEN);

          figure[3].addTriplet(0,1,0, ItemEnum.BLUE);
          figure[3].addTriplet(1,5,0, ItemEnum.WHITE);
          figure[3].addTriplet(2,3,1, ItemEnum.GREEN);
          figure[3].addTriplet(3,2,2, ItemEnum.PURPLE);
          figure[3].addTriplet(4,1,3, ItemEnum.YELLOW);
          figure[3].addTriplet(5,3,4, ItemEnum.AZURE);

          figure[4].addTriplet(0,5,0, ItemEnum.YELLOW);
          figure[4].addTriplet(1,1,1, ItemEnum.AZURE);
          figure[4].addTriplet(2,3,1, ItemEnum.BLUE);
          figure[4].addTriplet(3,3,2, ItemEnum.WHITE);
          figure[4].addTriplet(4,5,3, ItemEnum.GREEN);
          figure[4].addTriplet(5,4,4, ItemEnum.PURPLE);

          figure[5].addTriplet(0,5,0, ItemEnum.BLUE);
          figure[5].addTriplet(1,4,1, ItemEnum.AZURE);
          figure[5].addTriplet(2,0,2, ItemEnum.YELLOW);
          figure[5].addTriplet(3,2,2, ItemEnum.GREEN);
          figure[5].addTriplet(4,3,4, ItemEnum.WHITE);
          figure[5].addTriplet(5,4,4, ItemEnum.PURPLE);
     }

}

class Card{
     int IdCard, i;
     private Triplet[] dataTriad = new Triplet[6];

     Card(int IdCard){
          this.IdCard = IdCard;
     }
     void addTriplet(int idTriplet,int x, int y, ItemEnum color){
          dataTriad[idTriplet] = new Triplet(x, y, color);
     }

     public Triplet getTriplet(int i){
          Triplet tris;

          tris = dataTriad[i];

          return tris;
     }
}

class Triplet {
     private int x, y;
     private ItemEnum color;

     Triplet(int x, int y, ItemEnum color){
          this.x = x;
          this.y = y;
          this.color = color;
     }
}
/*
x e y are int; color is ENUM.
Card 1:   {x_1; y_1; color_1}
          {x_2; y_2; color_2}

array of length 12 card
*/