import java.util.ArrayList;
import java.util.List;

public class AI {
    public static void thinking(String[][] board, Storage store, String colour) {      //wish me luck
        Boolean processing = true;
        int Score = 0;
        ArrayList<ArrayList> moves = new ArrayList<>();
        while (processing) {
            playerInput pI = new playerInput();
            //this is going to hold all of the possible moves to make to rank them
            ArrayList<String> allPieces = new ArrayList<>();       //this gets all the white pieces on the board
            if (colour.equals("W")) {
                allPieces.addAll(store.currentWhitePiecesGet("horse"));
                allPieces.addAll(store.currentWhitePiecesGet("queen"));
                allPieces.addAll(store.currentWhitePiecesGet("king"));
                allPieces.addAll(store.currentWhitePiecesGet("bishop"));
                allPieces.addAll(store.currentWhitePiecesGet("castle"));
                allPieces.addAll(store.currentWhitePiecesGet("pawn"));

            } else {
                allPieces.addAll(store.currentBlackPiecesGet("pawn"));
                allPieces.addAll(store.currentBlackPiecesGet("horse"));
                allPieces.addAll(store.currentBlackPiecesGet("queen"));
                allPieces.addAll(store.currentBlackPiecesGet("king"));
                allPieces.addAll(store.currentBlackPiecesGet("bishop"));
                allPieces.addAll(store.currentBlackPiecesGet("castle"));
            }
            System.out.println(allPieces);
            for (int i = 0; allPieces.size() > i; i++) {           //this is getting all of the possible moves by the pieces
                ArrayList<String> moveDetails = new ArrayList<>();
                String[][] currentLayerMap = pI.getLayerMap(allPieces.get(i), board, store);
                for (int a = 0; 8 > a; a++) {
                    for (int b = 0; 8 > b; b++) {
                        if (Integer.parseInt(currentLayerMap[a][b]) == 1 || Integer.parseInt(currentLayerMap[a][b]) == 2) {
                            moveDetails.add(allPieces.get(i)); //the piece which is being analysed
                            moveDetails.add(currentLayerMap[a][b]); //gets the movement type
                            moveDetails.add("" + a);
                            moveDetails.add("" + b);
                        }
                    }
                }
                if (!moveDetails.isEmpty()) {
                    moves.add(moveDetails);
                }

            }
            System.out.println(moves);
            processing = false;
        }
        int scoreForPiece = 0;
        ArrayList<Double> scoreForMove = new ArrayList<>();
        for (int i = 0; moves.size() > i; i++) {
            ArrayList<String> list = moves.get(i);
            for(int a = 0; list.size() / 4 > a; a ++) {
                String pieceType = "" + list.get(4 * a).charAt(1);
                int tempScore = 0;
                switch (pieceType) {
                    case ("P") -> {
                        tempScore = store.pieceScoreGet("pawn");
                    }
                    case ("K") -> {
                        tempScore = store.pieceScoreGet("king");
                    }
                    case ("N") -> {
                        tempScore = store.pieceScoreGet("bishop");
                    }
                    case ("H") -> {
                        tempScore = store.pieceScoreGet("horse");
                    }
                    case ("C") -> {
                        tempScore = store.pieceScoreGet("castle");
                    }
                    case ("Q") -> {
                        tempScore = store.pieceScoreGet("queen");
                    }
                }
                double dampener = 1.0 / tempScore;
                int tempScore2 = 0;
                if (Integer.parseInt(list.get(4 * a + 1)) == 2) {
                    String potentialPiece = board[Integer.parseInt(list.get(4 * a +2))][Integer.parseInt(list.get(4 * a+ 3))];
                    pieceType = "" + potentialPiece.charAt(1);
                    switch (pieceType) {
                        case ("P") -> {
                            tempScore2 = store.pieceScoreGet("pawn");
                        }
                        case ("K") -> {
                            tempScore2 = store.pieceScoreGet("king");
                        }
                        case ("B") -> {
                            tempScore2 = store.pieceScoreGet("bishop");
                        }
                        case ("H") -> {
                            tempScore2 = store.pieceScoreGet("horse");
                        }
                        case ("C") -> {
                            tempScore2 = store.pieceScoreGet("castle");
                        }
                        case ("Q") -> {
                            tempScore2 = store.pieceScoreGet("queen");
                        }
                    }
                    dampener *= tempScore2;
                }
                dampener *= tempScore + tempScore2;
                scoreForMove.add(dampener);
            }
        }
        double highest = 0;
        int index = 0;
        for(int i = 0; scoreForMove.size() > i; i++){
            double possibleNewHigh = scoreForMove.get(i);
            if(possibleNewHigh > highest){
                highest = possibleNewHigh;
                index = i;
            }
        }

        int counter = 0 ;
        for(int i = 0; moves.size() > i; i++){
            ArrayList<String> list = moves.get(i);
            for(int a = 0; list.size() / 4> a; a++, counter++){
                if(counter == index){
                    System.out.println(list.get(4 * a));
                    System.out.println(list.get(4 * a + 2));
                    System.out.println(list.get(4 * a + 3));
                    String pieceOnMove = board[Integer.parseInt(list.get(4 * a + 2))][Integer.parseInt(list.get(4 * a + 3))];

                    if(!pieceOnMove.equals("E")) {
                        String pieceType = "" + pieceOnMove.charAt(1);

                        switch (pieceType) {
                            case ("B") -> {
                                store.amountOfWhiteBishop.remove(pieceOnMove);
                            }
                            case ("Q") -> {
                                store.amountOfWhiteQueen.remove(pieceOnMove);
                            }
                            case ("K") -> {
                                store.amountOfWhiteKing.remove(pieceOnMove);
                            }
                            case ("P") -> {
                                store.amountOfWhitePawn.remove(pieceOnMove);
                            }
                            case ("H") -> {
                                store.amountOfWhiteHorse.remove(pieceOnMove);
                            }
                            case ("C") -> {
                                store.amountOfWhiteCastle.remove(pieceOnMove);
                            }
                        }
                    }
                    String piecePosition = store.currentPiecePositionsGet(list.get(4 * a));
                    int piecePositionX = Integer.parseInt("" + piecePosition.charAt(0));
                    int piecePositionY = Integer.parseInt("" + piecePosition.charAt(1));
                    board[piecePositionX][piecePositionY] = "E";

                    board[Integer.parseInt(list.get(4 * a + 2))][Integer.parseInt(list.get(4 * a + 3))] = list.get(4 * a);
                    store.currentPiecePositionsSet(list.get(4 * a), "" + Integer.parseInt(list.get(4 * a + 2)) + Integer.parseInt(list.get(4 * a + 3)));
                    if(list.get(4 * a).charAt(1) == 'P'){
                        store.pawnsNotMoved.remove(list.get(4 * a));
                    }

                }
            }
        }

    }
}
