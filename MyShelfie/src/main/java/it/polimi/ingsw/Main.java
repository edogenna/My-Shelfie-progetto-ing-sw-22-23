package it.polimi.ingsw;

public class Main {
    public static void main( String[] args ){
        System.out.println(Constant.MY_SHELFIE_TITLE);

        Board b = new Board(2);
        ItemEnum.generateCharMatrix(b.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addHeaders(Board.BOARD_SIZE).printMatrix();



    }

}

