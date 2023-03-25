package it.polimi.ingsw.CommonCards;

import it.polimi.ingsw.ItemEnum;
public class CommonCard1 implements CommonCardStrategy{
    private final int r=6;
    private final int c=5;
    private final int minGroups =6;
    private final int minTiles=2;
    public boolean checkBookshelf(ItemEnum[][] b){
        int[][] m=new int[r][c];
        int[] groups=new int[14];
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

    private void setMatrix(int[][] m, int value){
        for(int i=0; i<r; i++)
            for (int j = 0; j < c; j++)
                m[i][j]=value;
    }

    private void setArray(int[] a, int value){
        for(int i=0; i<a.length; i++)
            a[i]=value;
    }
}
