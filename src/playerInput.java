import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class playerInput {

    public String inputTranslator(String coordinate){   //this is translating the inputs that are being made
        String converted = "";
        if(Character.isDigit(coordinate.charAt(0))) {
            switch (coordinate.charAt(0)) {
                case '0' -> converted += "a";
                case '1' -> converted += "b";
                case '2' -> converted += "c";
                case '3' -> converted += "d";
                case '4' -> converted += "e";
                case '5' -> converted += "f";
                case '6' -> converted += "g";
                case '7' -> converted += "h";
                default -> converted += "9";
            }
            if(Character.isLetter(coordinate.charAt(1)) || Character.isDigit(coordinate.charAt(1))){
                converted = "99";  //this throws a different loop path when the value is given back
                return converted;
            }
            converted += coordinate.charAt(1);
        }else if(Character.isLetter(coordinate.charAt(0))){     //this converts the user input into computer data
            switch(coordinate.charAt(0)){
                case 'a' -> converted += "0";
                case 'b' -> converted += "1";
                case 'c' -> converted += "2";
                case 'd' -> converted += "3";
                case 'e' -> converted += "4";
                case 'f' -> converted += "5";
                case 'g' -> converted += "6";
                case 'h' -> converted += "7";
                default -> converted += "9";
            }
            if(Character.isLetter(coordinate.charAt(1))){
                converted = "99";  //this throws a different loop path when the value is given back
                return converted;
            }
            converted += coordinate.charAt(1);
        }
        return converted;
    }

    public static String getPlayerInput(String type) {       //all the validation to player input is here, it will be done later
        Scanner sc = new Scanner(System.in);
        String playerInput = "";
        if (type.equals("coordinates")) {
            while (true) {
                playerInput = sc.nextLine();
                if (Character.isDigit(playerInput.charAt(0))) {
                    System.out.println("Please write it in the proper format");
                } else {  //if all other conditions are met then it outputs the right stuff
                    break;
                }
            }
        } else if (type.equals("pieceChoice")){
            playerInput = sc.nextLine();
        }
        return (playerInput);
    }
    public static String[][] boardOccupation(String[][] array, Storage store, String colour){
        String[][] spaceOccupation = store.getSpaceOccupation();
        playerInput pI = new playerInput();
        ArrayList<String> allPiecesWhite = new ArrayList<>();
        //this is adding all the white pieces so it can cycle through
        allPiecesWhite.addAll(store.currentWhitePiecesGet("pawn"));
        allPiecesWhite.addAll(store.currentWhitePiecesGet("horse"));
        allPiecesWhite.addAll(store.currentWhitePiecesGet("queen"));
        allPiecesWhite.addAll(store.currentWhitePiecesGet("king"));
        allPiecesWhite.addAll(store.currentWhitePiecesGet("bishop"));
        allPiecesWhite.addAll(store.currentWhitePiecesGet("castle"));

        String [] blackPieces = {"BC1","BC2","BP1","BP2","BP3","BP4","BP5","BP6","BP7","BP8","BB1","BB2","BH1","BH2","BQ1","BK1"};

        ArrayList<String> allPiecesBlack = new ArrayList<>();
        //this is adding all the white pieces so it can cycle through
        allPiecesWhite.addAll(store.currentBlackPiecesGet("pawn"));
        allPiecesWhite.addAll(store.currentBlackPiecesGet("horse"));
        allPiecesWhite.addAll(store.currentBlackPiecesGet("queen"));
        allPiecesWhite.addAll(store.currentBlackPiecesGet("king"));
        allPiecesWhite.addAll(store.currentBlackPiecesGet("bishop"));
        allPiecesWhite.addAll(store.currentBlackPiecesGet("castle"));

        if(colour.equals("W")) {
            for (int i = 0; i < 16; i++) {
                //The array part is the active map that is being worked on
                String [][] layerMap = pI.getLayerMap(allPiecesWhite.get(i), array, store);
                for(int a = 0;a < 8; a++){
                    for(int b = 0;b < 8; b++){
                        //System.out.println("space "+spaceOccupation[a][b]);
                        //System.out.println("layer "+ layerMap[a][b]);
                        if((Integer.parseInt(spaceOccupation[a][b]) + Integer.parseInt(layerMap[a][b]) )>= 1){
                            spaceOccupation[a][b] = "1";
                        }
                    }
                }
            }
            store.setSpaceOccupation(spaceOccupation);
        }else if(colour.equals("B")){

        }

        return spaceOccupation;

    }

    public String[][] getLayerMap(String piece, String[][] array, Storage store){
        System.out.println("The getLayerMap method has been called");
        Horse horse = new Horse();
        King king = new King();
        Queen queen = new Queen();
        Bishop bishop = new Bishop();
        Castle castle = new Castle();
        Pawn pawn = new Pawn();
        String type = "";
        switch(piece.charAt(1)){
            case 'H' -> type = "horse";
            case 'Q' -> type = "queen";
            case 'K' -> type = "king";
            case 'B' -> type = "bishop";
            case 'P' -> type = "pawn";
            case 'C' -> type = "castle";

        }
        String position = store.currentPiecePositionsGet(piece);
        int coordinateX = Integer.parseInt(""+position.charAt(0));
        int coordinateY = Integer.parseInt(""+position.charAt(1));
        System.out.println("The layer map for "+piece+" is being generated");
        System.out.println("This piece's coordinates are X:"+coordinateX+" Y:"+coordinateY);


        String[][] layer = null;
        switch(type){
            case "castle" -> layer = castle.castleMain(array, coordinateX, coordinateY, store);
            case "pawn" ->  layer = pawn.pawnMain(array, coordinateX, coordinateY, store);
            case "bishop" -> layer = bishop.bishopMain(array, coordinateX, coordinateY, store);
            case "queen" -> layer = queen.queenMain(array, coordinateX, coordinateY, store);
            case "king" -> layer = king.kingMain(array, coordinateX, coordinateY, store);
            case "horse" -> layer = horse.horseMain(array, coordinateX, coordinateY, store);
        }
        System.out.println("The activated movement system from getting the LayerMap is " +type);
        System.out.println("Their layer map is:\n");
        for (int i = 0; i < 8; i++) {
            String line = "";
            for (int a = 0; a < 8; a++) {
                line = line + " " + (layer[i][a]);
            }
            System.out.println(line);
        }

        store.currentAvailableMovesSet("king", 0);   //this is needed, this caused me so much pain
        store.currentAvailableMovesTakeSet("king", 0);  //Th
        store.currentAvailableMovesSet("pawn", 0);   //this is needed, this caused me so much pain
        store.currentAvailableMovesTakeSet("pawn", 0);  //Th
        store.currentAvailableMovesSet("bishop", 0);   //this is needed, this caused me so much pain
        store.currentAvailableMovesTakeSet("bishop", 0);  //Th
        store.currentAvailableMovesSet("queen", 0);   //this is needed, this caused me so much pain
        store.currentAvailableMovesTakeSet("queen", 0);  //Th
        store.currentAvailableMovesSet("castle", 0);   //this is needed, this caused me so much pain
        store.currentAvailableMovesTakeSet("castle", 0);  //Th
        store.currentAvailableMovesSet("horse", 0);   //this is needed, this caused me so much pain
        store.currentAvailableMovesTakeSet("horse", 0);  //Th
        return(layer);


    }

    public String[][] playerMove(String[][] board, Storage storage, String colour, int startX, int startY, int endX, int endY, String moveType){
        /*
        All the things that are needed
        It needs to know the place that is going to be moved from
        It needs to know the place that is going to be move to
        It needs to know the colour of the piece
        It needs to have access to the storage part of the program
        It needs to have access to the board so that it can modify it.
         */
        //as everything is already validated so there needs to be no validation on input with movement layers
        String movingPiece = board[startX][startY];     //this is the current piece that is being moved.
        System.out.println(startX);
        System.out.println(startY);
          //this is setting the place it moved from as empty
        if(moveType.equals("1")){
            board[endX][endY] = movingPiece;
            board[startX][startY] = "E";
            System.out.println("Current Moving Piece is " + movingPiece + "...");
            storage.currentPiecePositionsSet(movingPiece, "" + endX + "" + endY);
            if(board[endX][endY].charAt(1) == 'P'){
                ArrayList<String> movablePawns = storage.currentPawnsAllowedMoveGet();
                movablePawns.remove(movingPiece);
            }
        }else if(moveType.equals("2")){

            String takenPiece = board[endX][endY];
            String takenPieceChar = "" + board[endX][endY].charAt(1);
            board[startX][startY] = "E";
            switch (takenPieceChar) {
                case ("P") -> {
                    ArrayList<String> currentPieces = storage.currentBlackPiecesGet("pawn");
                    currentPieces.remove(takenPiece);
                }
                case ("C") -> {
                    ArrayList<String> currentPieces = storage.currentBlackPiecesGet("castle");
                    currentPieces.remove(takenPiece);
                }
                case ("B") -> {
                    ArrayList<String> currentPieces = storage.currentBlackPiecesGet("bishop");
                    currentPieces.remove(takenPiece);
                }
                case ("Q") -> {
                    ArrayList<String> currentPieces = storage.currentBlackPiecesGet("queen");
                    currentPieces.remove(takenPiece);
                }
                case ("K") -> {
                    ArrayList<String> currentPieces = storage.currentBlackPiecesGet("king");
                    currentPieces.remove(takenPiece);
                }
                case ("H") -> {
                    ArrayList<String> currentPieces = storage.currentBlackPiecesGet("horse");
                    currentPieces.remove(takenPiece);
                }
            }
        }
        return board;
    }

}
