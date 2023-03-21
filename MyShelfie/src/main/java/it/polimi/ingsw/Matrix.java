package it.polimi.ingsw;

public class Matrix {
    private int r;
    private int c;
    private char m[][];

    public Matrix(int r, int c){m = new char[r][c];}

    public int getR() {return r;}
    public int getC() {return c;}

    public void setValue(int r, int c, char value){m[r][c] = value;}
}
