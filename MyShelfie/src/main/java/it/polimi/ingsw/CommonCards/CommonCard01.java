package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.CharMatrix;
import it.polimi.ingsw.ItemEnum;

/**
 * Contains the algorithms for the first common card:
 * Six groups each containing at least 2 tiles of the same type (non necessarily in the depicted shape). The tiles of one group can be different from those of another group.
 * @author Alessandro Fornara
 */
public class CommonCard01 implements CommonCardStrategy{
    private final int r=6;
    private final int c=5;
    private final int minGroups =6;
    private final int minTiles=2;
    private static final String constant1=
            "Card number 1   Description:\n"+
            "   |=|          Six groups each containing at least\n"+
            "   |=|   x6     2 tiles of the same type (non necessarily\n"+
            "                in the depicted shape). Tiles can be\n"+
            "                different between different groups.\n";

    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        int[][] m=new int[r][c];
        int[] groups=new int[15];
        int counterGroups=0; //indice di vettore groups
        int counter=0;
        int i, j;

        setMatrix(m, -1);
        setArray(groups, 0);

        for(int k=4; k>-5; k--){

            if(k>=0) {i=k; j=0;}
            else {i=0; j=-k;}

            while(i<r && j<c){

                if (j!=0 && !b[i][j].equals(ItemEnum.BLANK) && b[i][j].equals(b[i][j-1])) { //controlla a sinistra

                    if (m[i][j-1] == -1) {
                        m[i][j-1] = counterGroups;
                        m[i][j] = counterGroups;
                        groups[counterGroups] = 2;
                        counterGroups++;
                    } else {
                        if(m[i][j]==-1) {
                            m[i][j] = m[i][j - 1];
                            groups[m[i][j]]++;
                        }
                    }
                }
                if (i!=r-1 && !b[i][j].equals(ItemEnum.BLANK) && b[i][j].equals(b[i+1][j])) { //controllo giù
                    if (m[i+1][j] == -1) {
                        m[i+1][j] = counterGroups;
                        m[i][j] = counterGroups;
                        groups[counterGroups] = 2;
                        counterGroups++;
                    } else {
                        if(m[i][j]==-1) {
                            m[i][j] = m[i + 1][j];
                            groups[m[i][j]]++;
                        }
                    }
                }
                i++; j++; //mi sposto in diagonale
            }
        }
        for(int k=0; k<14; k++)
            if(groups[k]>=minTiles) {
                counter++;
                if(counter== minGroups) return true;
            }

        return false;
    }

    @Override
    public void printCommonCard() {
        System.out.println(constant1);
    }

    public CharMatrix printCommonCardMatrix(){

        //TODO: finere e sitemare le print di tutte
        return  new CharMatrix()
                .addNewLine("Card number 1   Description:")
                .addNewLine("   |=|          Six groups each containing at least")
                .addNewLine("   |=|   x6     2 tiles of the same type (non necessarily");
    }

    /**
     * Sets all cells in a matrix to the indicated value
     * @param m Matrix
     * @param value Number to be assigned
     */
    private void setMatrix(int[][] m, int value){
        for(int i=0; i<r; i++)
            for (int j = 0; j < c; j++)
                m[i][j]=value;
    }

    /**
     * Sets all cells in an array to the indicated value
     * @param a Array
     * @param value Number to be assigned
     */
    private void setArray(int[] a, int value){
        for(int i=0; i<a.length; i++)
            a[i]=value;
    }
}
