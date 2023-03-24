package it.polimi.ingsw;

public class CommonCard4 implements CommonCardStrategy {
    public boolean checkBookshelf(ItemEnum[][] b) {
        ItemEnum firstColor;
        ItemEnum secondColor=ItemEnum.WHITE;
        ItemEnum thirdColor=ItemEnum.WHITE;
        int counter=0;
        final int numberOfRows=b.length;
        final int numberOfColumns=b[0].length;
        int j;

        for (int i = numberOfRows-1; i >=0; i--) {
            j = 0;

            //colore1
            if (!b[i][j].equals(ItemEnum.BLANK)) {
                firstColor = b[i][j];
                do {j++;}
                while (j < numberOfColumns && b[i][j].equals(firstColor));

                //colore 2
                if(j!=numberOfColumns) {
                    secondColor = b[i][j];
                    do {j++;}
                    while (j < numberOfColumns
                            && (b[i][j].equals(firstColor) || b[i][j].equals(secondColor)));

                    //colore 3
                    if(j!=numberOfColumns) {
                        thirdColor = b[i][j];
                        do {j++;}
                        while (j < numberOfColumns
                                && (b[i][j].equals(firstColor) || b[i][j].equals(secondColor) || b[i][j].equals(thirdColor)));
                    }
                }
                if (j == numberOfColumns
                        && !firstColor.equals(ItemEnum.BLANK)
                        && !secondColor.equals(ItemEnum.BLANK)
                        && !thirdColor.equals(ItemEnum.BLANK)) counter++;
            }
            if (counter == 4)
                return true;
        }
        return false;
    }
}

