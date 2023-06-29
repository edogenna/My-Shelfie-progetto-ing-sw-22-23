package it.polimi.ingsw.model.CommonCards;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.Utils;

/**
 * Contains the algorithms for the third common card:
 * Four groups each containing at least 4 tiles of the same type (non necessarily in the depicted shape). The tiles of one group can be different from those of another group.
 * @author Alessandro Fornara
 */
public class CommonCard03 implements CommonCardStrategy{
    private final int r=6;
    private final int c=5;
    private final int minGroups =4;
    private final int minTiles=4;
    public static final String constant3=
            "Card number 3   Description:\n"+
            "   |=|          Four groups each containing at least\n"+
            "   |=|  x4      4 tiles of the same type (non necessarily\n"+
            "   |=|          in the depicted shape). Tiles can be\n"+
            "   |=|          different between different groups.\n";

    public final int number = 3;

    @Override
    public boolean checkBookshelf(ItemEnum[][] b){
        int[][] m=new int[r][c];
        int[] groups=new int[15];
        int[] groupsID=new int[15];
        int[] tmp=new int[15];
        int counterGroups=0; //indice di vettore groups
        int counter=0;
        int i, j;

        Utils.setMatrix(m, -1);
        Utils.setArray(groups, 0);
        Utils.setArray(groupsID, 0);
        Utils.setArray(tmp, 0);

        for(int k=4; k>-5; k--){

            if(k>=0) {i=k; j=0;}
            else {i=0; j=-k;}

            while(i<r && j<c){

                if (j!=0 && !b[i][j].equals(ItemEnum.BLANK) && b[i][j].equals(b[i][j-1])) { //controlla a sinistra

                    if (m[i][j-1] == -1) {
                        m[i][j-1] = counterGroups;
                        m[i][j] = counterGroups;
                        groups[counterGroups] = 2;
                        groupsID[counterGroups]=counterGroups;
                        counterGroups++;
                    } else {
                        if(m[i][j]==-1) {
                            m[i][j] = m[i][j - 1];
                            groups[m[i][j]]++;
                        }
                        else {
                            if(groupsID[m[i][j-1]] <= groupsID[m[i][j]])
                                groupsID[m[i][j]]=groupsID[m[i][j-1]];
                            else
                                groupsID[m[i][j-1]]=groupsID[m[i][j]];
                        }
                    }
                }
                if (i!=r-1 && !b[i][j].equals(ItemEnum.BLANK) && b[i][j].equals(b[i+1][j])) { //controllo giù
                    if (m[i+1][j] == -1) {
                        m[i+1][j] = counterGroups;
                        m[i][j] = counterGroups;
                        groups[counterGroups] = 2;
                        groupsID[counterGroups]=counterGroups;
                        counterGroups++;
                    } else {
                        if(m[i][j]==-1) {
                            m[i][j] = m[i + 1][j];
                            groups[m[i][j]]++;
                        }
                        else {
                            if (groupsID[m[i + 1][j]] <= groupsID[m[i][j]])
                                groupsID[m[i][j]] = groupsID[m[i + 1][j]];
                            else
                                groupsID[m[i + 1][j]] = groupsID[m[i][j]];
                        }
                    }
                }
                i++; j++; //mi sposto in diagonale
            }
        }



        for(int k=0; k<15; k++)
            tmp[groupsID[k]]=tmp[groupsID[k]]+groups[k];

        /*Utils.printMatrix(m);
        System.out.println();
        Utils.printArray(groups);
        System.out.println();
        Utils.printArray(groupsID);
        System.out.println();
        Utils.printArray(tmp);*/

        for(int k=0; k<15; k++)
            if(tmp[k]>=minTiles) {
                counter++;
                if(counter== minGroups) return true;
            }

        return false;
    }

    @Override
    public String getCommonCardDesign() {
        return constant3;
    }

    @Override
    public int getNumber() {
        return number;
    }
}

