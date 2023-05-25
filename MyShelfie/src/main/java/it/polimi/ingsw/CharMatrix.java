package it.polimi.ingsw;
import java.util.ArrayList;

/**
 * CharMatrix is an array of strings, it is used as a view
 * @author Edoardo Gennaretti
 */
public class CharMatrix {
    private final ArrayList<String> m;

    public CharMatrix(){
        m = new ArrayList<>();
    }

    /**
     * print the CharMatrix object
     */
    public void printMatrix(){
        for(String s : m)
            System.out.println(s);
    }

    /**
     * print the CharMatrix object with a '|' at the end of each line
     */
    public void printMatrixWithAllineator(){
        for(String s : m)
            System.out.println(s + "|");
    }


    /**
     * print a specific line of the CharMatrix object
     * @param l line to be printed (must be between 0 and the number of lines)
     */
    public void printLine(int l){
        if(l < 0 || l >= m.size()){
            System.out.print("line error");
            return;
        }
        System.out.println(m.get(l));
    }

    /**
     * append a string s at the end of a specific line l
     * @param s string to be appended
     * @param l line (must be between 0 and the number of lines)
     * @return CharMatrix modified
     */
    public CharMatrix appendAtLine(String s, int l){
        if(l < 0 || l >= m.size()){
            System.out.print("errore linea");
            return this;
        }

        m.set(l,m.get(l).concat(s));
        return this;
    }

    /**
     * adds the string s as a new line at the bottom of the CharMatrix
     * @param s string to be appended
     * @return CharMatrix modified
     */
    public CharMatrix newLineAtBottom(String s){
        m.add(s);
        return this;
    }

    /**
     * adds the string s as a new line at the top of the CharMatrix
     * @param s string to be appended
     * @return CharMatrix modified
     */
    public CharMatrix newLineAtTop(String s){
        m.add(0,s);
        return this;
    }

    /**
     * adds rows and columns numbering
     * @param col number of columns of the matrix
     * @return CharMatrix modified
     */
    public CharMatrix addNumbering(int col){
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

    /**
     * adds columns numbering
     * @param col number of columns of the matrix
     * @return CharMatrix modified
     */
    public CharMatrix addColumnNumbering(int col){
        //adding headers for column
        String s = "";
        for(int i = 0; i < col; i++)
            s = s.concat(" "+ i + " ");
        m.add(0, s);

        return this;
    }

    /**
     * adds rows numbering
     * @return CharMatrix modified
     */
    public CharMatrix addRowsNumbering(){
        //adding headers for rows
        for(int j = 1; j < m.size(); j++)
            m.set(j, m.get(j).concat(" " + Constant.toString(j-1)));

        return this;
    }

    /**
     * concatenates two CharMatrix, adding another on the right
     * @param toBeAdded CharMatrix to be concatenated on the righr
     * @return two concatenated CharMatrix
     */
    public CharMatrix addOnRight(CharMatrix toBeAdded){
        if(toBeAdded.m.size() > m.size()) {
            System.out.println("errore");
            return this;
        }

        for(int i = 0; i < toBeAdded.m.size(); i++)
            this.appendAtLine(toBeAdded.m.get(i), i);

        return this;
    }

    /**
     * append a string at the end of each row
     * @param s string to be appended
     * @return CharMatrix modified
     */
    public CharMatrix appendToAllRows(String s){
        for(int i = 0; i < m.size(); i++){
            this.appendAtLine(s,i);
        }

        return this;
    }

    private int countNonSpecialCharInRow(int r){
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

    /**
     * aligns the column of the CharMatrix adding some spaces to shorter rows
     * @return CharMatrix modified
     */
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
