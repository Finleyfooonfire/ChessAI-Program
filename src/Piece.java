public class Piece {
    static String[][] baseMovementLayer = {     //This is the base "mesh" used to layer the piece movements
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
    };
    public static boolean empty(String[][] array, int x, int y){
        if(array[x][y] == "E") {
            return (true);
        }
        return(false);
    }
    public static String[][] movePiece(String[][] array, int StartX, int StartY, int EndX, int EndY){
        array[EndX][EndY] = array[StartX][StartY];
        array[StartX][StartY] = "E";


        return array;
    }

}
