package it.polimi.ingsw;
import java.util.ArrayList;

public class CharMatrix {
    private ArrayList<String> m;

    public CharMatrix(){
        m = new ArrayList<>();
    }

    public void printMatrix(){
        for(String s : m)
            System.out.println(s);
    }

    public void printMatrixWithAllineator(){
        for(String s : m)
            System.out.println(s + "|");
    }


    //TODO: gestione con eccezione
    public void printLine(int l){
        if(l < 0 || l >= m.size()){
            System.out.print("errore linea");
            return;
        }
        System.out.println(m.get(l));
    }

    //TODO: gestione con eccezione
    public CharMatrix appendAtLine(String s, int l){
        if(l < 0 || l >= m.size()){
            System.out.print("errore linea");
            return this;
        }

        m.set(l,m.get(l).concat(s));
        return this;
    }

    public CharMatrix addNewLine(String s){
        m.add(s);
        return this;
    }

    public CharMatrix addHeaders(int col){
        //adding headers for column
        String s = "";
        for(int i = 0; i < col; i++)
            s = s.concat(" "+ i + " ");
        m.add(0, s);

        //adding headers for rows
        for(int j = 1; j < m.size(); j++)
            m.set(j, m.get(j).concat(" " + Constant.toString(j-1)));

        return this;
    }

    public CharMatrix addOnRight(CharMatrix toBeAdded){
        //TODO modificare questa situazione aggiungento in this.m una righa di spazi allineata
        if(toBeAdded.m.size() > m.size()) {
            System.out.println("errore");
            return this;
        }

        for(int i = 0; i < toBeAdded.m.size(); i++)
            this.appendAtLine(toBeAdded.m.get(i), i);

        return this;
    }

    public CharMatrix appendToAllRows(String s){
        for(int i = 0; i < m.size(); i++){
            this.appendAtLine(s,i);
        }

        return this;
    }

    public int countNonSpecialCharInRow(int r){
        if(r < 0 || r >= m.size())
            return -1;

        int count = 0;

        for(int i = 0; i < m.get(r).length(); i++){
            if(m.get(r).charAt(i) == ItemEnum.RESET.charAt(0)
                || m.get(r).charAt(i) == ItemEnum.BLANK.label.charAt(0))
                i += 11;
            count++;
        }

        return count;
    }

    //TODO: migliorare l'implementazione salvando la lunghezza dei caratteri non speciali in un array ??
    public CharMatrix alignColumn(){
        if(m.size() == 0) //matrix is empty
            return this;

        int maxCountChar = countNonSpecialCharInRow(0);
        int n;

        for(int i = 1; i < m.size(); i++){
            n = countNonSpecialCharInRow(i);
            if(n > maxCountChar)
                maxCountChar = n;
        }


        int diff;
        String spaces;
        for(int i = 0; i < m.size(); i++){
            diff = maxCountChar - countNonSpecialCharInRow(i);
            if(diff > 0){
                spaces = "";
                for(int j = 0; j < diff; j++)
                    spaces = spaces.concat(" ");

                appendAtLine(spaces, i);
            }
        }

        return this;
    }




}
