import java.util.ArrayList;

public class Pawn{
    public String[][] pawnMain(String[][] array, int StartX , int StartY, Storage store){
        int debugValue = store.debugGet();
        if(debugValue == 1) {
            System.out.println("The pawn layer has been activated");
        }
        String firstHalf = array[StartX][StartY].substring(0,2);
        String[][] boardOutput = null;

        if(firstHalf.equals("WP")){         //remember that only pawns can be passed please, you absolute pillock
            if(debugValue == 1) {
                System.out.println("A white pawn movement layer is being generated..");
            }
            boardOutput = layerSystemPawn(array, StartX, StartY, store);
            return boardOutput;

        } else if (firstHalf.equals("BP")){
            if(debugValue == 1) {
                System.out.println("A black pawn movement layer is being generated..");
            }
            //System.out.println("Enemy pawn here");
            boardOutput = layerSystemPawn(array, StartX, StartY, store);
            return boardOutput;
        }else {
            if(debugValue == 1) {
                System.out.println("There was no pawn at the activated location of X:" + StartX + " Y:" + StartY + " with the attempted piece being " + array[StartX][StartY]);
            }
            return boardOutput;
        }
    }
    public static String[][] layerSystemPawn(String[][] array, int StartX, int StartY, Storage store){
        String[][] pawnLayer = {     //This is the base "mesh" used to layer the piece movements
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
        };
        String piece = "" + array[StartX][StartY];      //this is getting the piece in question
        String letter = "" + piece.charAt(0);   //This is the starting letter
        String letter2 = "W";       //this is the base just in case there isnt a change
        if(letter.equals("W")){
            letter2 = "B";  //flips the initial letter if its not the same
        }


        if(letter.equals("W")){ //if the piece is white it goes here
            ArrayList<String> movablePawns = store.currentPawnsAllowedMoveGet();
            if(movablePawns.contains(piece)){       //this is checking that if the piece has already had its first move
                if(array[StartX + 1][StartY].charAt(0) == 'E') {    //this checking that that is actually a piece infront of the pawn
                    pawnLayer[StartX + 1][StartY] = "1";    //puts a movement infront of the piece
                    store.currentAvailableMovesSet("pawn", (store.currentAvailableMovesGet("pawn") +1 ));   //updates something in the storage to keep track of how many moves made
                    if(array[StartX + 2][StartY].charAt(0) == 'E') {    //does the same thing as before, just in the piece infront instead
                        pawnLayer[StartX + 2][StartY] = "1";
                        store.currentAvailableMovesSet("pawn", (store.currentAvailableMovesGet("pawn") +1 ));
                    }
                }
            }
            else{   //this is the branch if it has been moved then it goes to here
                if(StartX + 1 > -1 && StartX + 1 < 8 ) {    //this is just the basic move forward if there is nothing else to do
                    if (array[StartX + 1][StartY].charAt(0) == 'E') {   //making sure its not empty
                        pawnLayer[StartX + 1][StartY] = "1";
                        store.currentAvailableMovesSet("pawn", (store.currentAvailableMovesGet("pawn") + 1));   //adds a possible move to the variable in storage Will be used for random
                    }
                }
            }
            Integer[][] possibleMoves = {{1,1},{1,-1}};     //instead of having seperate, these are the vectors for the possible moves for the white piece
            for(int i = 0; i < 2;i ++){     //this loop only lasts for 2 times
                if(StartX + possibleMoves[i][0] < 8 && StartY + possibleMoves[i][1] < 8 && StartY + possibleMoves[i][1] > -1 ){     //this is the boundary check, making sure everything is in range
                    if (array[StartX + possibleMoves[i][0]][StartY + possibleMoves[i][1]].charAt(0) == 'B') {   //this is for taking pieces, and making sure that the piece is black
                        pawnLayer[StartX + possibleMoves[i][0]][StartY + possibleMoves[i][1]] = "2";      //changing on the movement layer to show a piece can be taken
                        store.currentAvailableMovesTakeSet("pawn", (store.currentAvailableMovesTakeGet("pawn") +1 ));   //adding to the storage of how many possible moves there are
                    }

                }
            }
        }else if(letter.equals("B")){
            ArrayList<String> movablePawns = store.currentPawnsAllowedMoveGet();
            if(movablePawns.contains(piece)){       //this deals with if it hasnt moved then it does this
                if(array[StartX - 1][StartY].charAt(0) == 'E') {
                    pawnLayer[StartX - 1][StartY] = "1";
                    store.currentAvailableMovesSet("pawn", (store.currentAvailableMovesGet("pawn") +1 ));
                    if(array[StartX - 2][StartY].charAt(0) == 'E') {
                        pawnLayer[StartX - 2][StartY] = "1";
                        store.currentAvailableMovesSet("pawn", (store.currentAvailableMovesGet("pawn") +1 ));
                    }
                }
            }
            else{
                if(StartX - 1 > -1 && StartX -1 < 8 ) {
                    if (array[StartX - 1][StartY].charAt(0) == 'E') {
                        pawnLayer[StartX - 1][StartY] = "1";
                        store.currentAvailableMovesSet("pawn", (store.currentAvailableMovesGet("pawn") + 1));
                    }
                }
            }
            Integer[][] possibleMoves = {{-1,1},{-1,-1}};
            for(int i = 0; i < 2;i ++){
                if(StartX + possibleMoves[i][0] > -1 && StartY + possibleMoves[i][1] < 8 && StartY + possibleMoves[i][1] > -1 ){
                    if (array[StartX + possibleMoves[i][0]][StartY + possibleMoves[i][1]].charAt(0) == 'W') {
                        pawnLayer[StartX + possibleMoves[i][0]][StartY + possibleMoves[i][1]] = "2";
                        store.currentAvailableMovesTakeSet("pawn", (store.currentAvailableMovesTakeGet("pawn") +1 ));
                    }

                }
            }
        }

        pawnLayer[StartX][StartY] = "3";
        if(store.debugGet() == 1) {
            System.out.println("PawnLayer Output:\n");
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (pawnLayer[i][a]);
                }
                System.out.println(line);
            }
        }
        return pawnLayer;
    }
}

