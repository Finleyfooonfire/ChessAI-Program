import javax.sql.rowset.FilteredRowSet;

public class Queen extends Piece {
    public String[][] queenMain(String[][] array, int StartX, int StartY, Storage store){
        String firstHalf = array[StartX][StartY].substring(0,2);

        String[][] boardOutput = array;
        if(firstHalf.equals("WQ")){
           // System.out.println("There is a white queen here");
            boardOutput = layerSystemQueen(array, StartX, StartY, store);
        } else if (firstHalf.equals("BQ")){
       // System.out.println("Black bishop here");
        boardOutput = layerSystemQueen(array, StartX, StartY, store);
        }

        return boardOutput;
        }

    public static String[][] layerSystemQueen(String[][] array, int StartX, int StartY, Storage store){
        String [][] queenLayer = {     //This is the base "mesh" used to layer the piece movements
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
        };
        String letter = "" + array[StartX][StartY].charAt(0);   //this is a system that is getting the opposing piece, makes sure it knows which to attack
        String letter2 = "W";
        if(letter.equals(letter2)){
            letter = "B";
        }else{
            letter = "W";
        }
        //the following is copying from the Bishop piece, this is to make sure that the queen can move in those directions to what tile she currently is on
        for(int i = 1; 8 > i; i++){
            int newStartY = StartY - i;
            int newStartX = StartX - i;
            if(newStartX > 7 || newStartY > 7 || newStartX < 0 || newStartY < 0){
                break;
            }
            String currentStartLetter = "" + array[newStartX][newStartY].charAt(0);
            if(currentStartLetter.equals(letter)){
                queenLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));  //This updates the amount of pieces by 1
            queenLayer[newStartX][newStartY] = "1";

        }
        //This is going in the bottom left direction

        for(int i = 1; 8 > i; i++){
            int newStartY = StartY - i;
            int newStartX = StartX + i;
            if(newStartX > 7 || newStartY > 7 || newStartX < 0 || newStartY < 0){
                break;
            }
            String currentStartLetter = "" + array[newStartX][newStartY].charAt(0);
            if(currentStartLetter.equals(letter)){
                queenLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));
            queenLayer[newStartX][newStartY] = "1";


        }
        //This goes in the top right direction

        for(int i = 1; 8 > i; i++){
            int newStartY = StartY + i;
            int newStartX = StartX - i;
            if(newStartX > 7 || newStartY > 7 || newStartX < 0 || newStartY < 0){
                break;
            }

            String currentStartLetter = "" + array[newStartX][newStartY].charAt(0);
            if(currentStartLetter.equals(letter)){
                queenLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));
            queenLayer[newStartX][newStartY] = "1";

        }
        //This goes in the bottom right direction

        for(int i = 1; 8 > i; i++){
            int newStartY = StartY + i;
            int newStartX = StartX + i;
            if(newStartX > 7 || newStartY > 7 || newStartX < 0 || newStartY < 0){
                break;
            }

            String currentStartLetter = "" + array[newStartX][newStartY].charAt(0);
            if(currentStartLetter.equals(letter)){
                queenLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));
            queenLayer[newStartX][newStartY] = "1";
        }


        //the following has been copied and slightly modified from the castle piece, this means that it has the same parts as that

        for(int i = StartX + 1; i < 8; i++){            //Traces the moves in the down direction
            String s = "" + array[i][StartY].charAt(0);
            if(s.equals(letter) && !s.equals("E")){     //This is seeing if the piece it is colliding with is the opposite colour, if it is then it says that the piece is "taken" or can be
                queenLayer[i][StartY] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }else if(!array[i][StartY].equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));
            queenLayer[i][StartY] = "1";
        }
        for(int i = StartX - 1; i >= 0; i--){       //Traces the moves in the up direction
            String s = "" + array[i][StartY].charAt(0);
            if(s.equals(letter) && !s.equals("E")) {
                queenLayer[i][StartY] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }if(!array[i][StartY].equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));
            queenLayer[i][StartY] = "1";
        }
        for(int i = StartY + 1; i < 8;i++){         //Traces the moves in the right direction
            String s = "" + array[StartX][i].charAt(0);
            if(s.equals(letter) && !s.equals("E")){
                queenLayer[StartX][i] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }if(!array[StartX][i].equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));
            queenLayer[StartX][i] = "1";
        }
        for(int i = StartY - 1; i >= 0; i--){   //Traces the moves in the left direction
            String s = "" + array[StartX][i].charAt(0);
            if(s.equals(letter) && !s.equals("E")) {
                queenLayer[StartX][i] = "2";
                store.currentAvailableMovesTakeSet("queen", (store.currentAvailableMovesTakeGet("queen") +1 ));
                break;
            }if(!array[StartX][i].equals("E")){
                break;
            }
            store.currentAvailableMovesSet("queen", (store.currentAvailableMovesGet("queen") +1 ));
            queenLayer[StartX][i] = "1";
        }
        queenLayer[StartX][StartY] = "3";
        if(store.debugGet() == 1) {
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (queenLayer[i][a]);
                }
                System.out.println(line);
            }
        }


        return queenLayer;
    }
}
