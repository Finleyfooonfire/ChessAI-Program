import java.util.ArrayList;
import java.util.List;

public class AI {
    public static void thinking(String[][] board, Storage store, String colour){      //wish me luck
        Boolean processing = true;
        int Score = 0;
            while(processing){
                playerInput pI = new playerInput();
                ArrayList<ArrayList> moves = new ArrayList<>();
                //this is going to hold all of the possible moves to make to rank them
                ArrayList<String> allPieces = new ArrayList<>();       //this gets all the white pieces on the board
                if(colour.equals("W")){
                    allPieces.addAll(store.currentWhitePiecesGet("horse"));
                    allPieces.addAll(store.currentWhitePiecesGet("queen"));
                    allPieces.addAll(store.currentWhitePiecesGet("king"));
                    allPieces.addAll(store.currentWhitePiecesGet("bishop"));
                    allPieces.addAll(store.currentWhitePiecesGet("castle"));
                    allPieces.addAll(store.currentWhitePiecesGet("pawn"));

                }else{
                    allPieces.addAll(store.currentBlackPiecesGet("pawn"));
                    allPieces.addAll(store.currentBlackPiecesGet("horse"));
                    allPieces.addAll(store.currentBlackPiecesGet("queen"));
                    allPieces.addAll(store.currentBlackPiecesGet("king"));
                    allPieces.addAll(store.currentBlackPiecesGet("bishop"));
                    allPieces.addAll(store.currentBlackPiecesGet("castle"));
                }


                System.out.println(allPieces);
                for(int i = 0; allPieces.size() > i;i++){           //this is getting all of the possible moves by the pieces
                    ArrayList<String> moveDetails = new ArrayList<>();
                    String[][] currentLayerMap = pI.getLayerMap(allPieces.get(i), board, store);
                    for(int a = 0; 8 > a; a++){
                        for(int b = 0; 8 > b; b++) {
                            if (Integer.parseInt(currentLayerMap[a][b]) == 1 || Integer.parseInt(currentLayerMap[a][b]) == 2) {
                                moveDetails.add(allPieces.get(i)); //the piece which is being analysed
                                moveDetails.add(currentLayerMap[a][b]);
                                moveDetails.add("" + a);
                                moveDetails.add("" + b);
                            }
                        }
                    }
                    moves.add(moveDetails);
                }
                System.out.println(moves);




                    processing = false;
                }




    }
}
