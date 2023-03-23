package it.polimi.ingsw;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;

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

    public CharMatrix appendEmptyLine(int numberSpaces){
        String s = "";
        for(int i = 0; i < numberSpaces; i++)
            s = s.concat(" ");
        m.add(s);
        return this;
    }

    public CharMatrix addOnRight(CharMatrix sec){
        return this;
    }
}
