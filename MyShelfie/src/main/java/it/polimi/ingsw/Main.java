package it.polimi.ingsw;

public class Main {
    public static void main( String[] args ){
        System.out.println(Constant.MY_SHELFIE_TITLE);

        Board b = new Board(2);
        ItemEnum.generateCharMatrix(b.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addHeaders(Board.BOARD_SIZE).printMatrix();


        System.out.println("\n\n");
        Board b2 = new Board(2);
        ItemEnum.generateCharMatrix(b2.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addHeaders(Board.BOARD_SIZE).printMatrix();


        System.out.println("\n\n");
        ItemEnum.generateCharMatrix(b.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addHeaders(Board.BOARD_SIZE).appendToAllRows("   ").addOnRight(ItemEnum.generateCharMatrix(b2.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                        .addHeaders(Board.BOARD_SIZE)).printMatrix();


        System.out.println("\nALLINEATO:\n");


        System.out.println("\n\n");
        ItemEnum.generateCharMatrix(b.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addHeaders(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn().addOnRight(ItemEnum.generateCharMatrix(b2.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                        .addHeaders(Board.BOARD_SIZE)).printMatrix();



    }

}

