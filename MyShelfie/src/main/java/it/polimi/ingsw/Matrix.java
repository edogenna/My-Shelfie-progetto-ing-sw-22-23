package it.polimi.ingsw;

public class Matrix {
    private int r;
    private int c;
    private char m[][];

    public Matrix(int r, int c){
        this.r = r;
        this.c = c;
        m = new char[r][c];
    }

    public int getR() {return r;}
    public int getC() {return c;}
    public char getValue(int r, int c){return m[r][c];}
    public void setValue(int r, int c, char value){m[r][c] = value;}
    public Matrix addOnRight(Matrix toBeAdded){
        int newR = Math.max(r, toBeAdded.r);
        int newC = c + toBeAdded.c;

        Matrix newM = new Matrix(newR, newC);
        
        newM.copyMatrix(0,0,this);
        newM.copyMatrix(this.r, this.c, toBeAdded);

        return newM;
    }

    //TODO: gestione l'errore con una eccezione
    public void copyMatrix(int r, int c, Matrix toBeCopied){
        if(r + toBeCopied.r > this.r || c + toBeCopied.c > this.c || r < 0 || c < 0){
            System.out.println("Error index the matrix");
            return;
        }

        for(int i = 0; i < toBeCopied.r; i++)
            for(int j = 0; j < toBeCopied.c; j++)
                this.m[r+i][c+j] = toBeCopied.m[i][j];
    }
    
    
    public void printMatrix(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(m[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void printLine(int l){
        if(l < 0 || l >= r){
            System.out.print("errore linea");
            return;
        }
        for(int j = 0; j < c; j++){
            System.out.print(m[l][j]);
        }
        System.out.print("\n");
    }
}
