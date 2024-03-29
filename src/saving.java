import java.util.ArrayList;
import java.util.Random;

public class saving {
    public static void randomPart(String[][] board, Storage storage){
        Horse horse = new Horse();
        King king = new King();
        Queen queen = new Queen();
        Bishop bishop = new Bishop();
        Castle castle = new Castle();
        Pawn pawn = new Pawn();
        int whichTurn = 0;   //This is a variable that is going to be a flip flop, it is going to keep track of whose turn it is
        Random random = new Random();
        whichTurn = random.nextInt(2);
        int times = 0;
        whichTurn  = 0;

        while(times <= 1000) {       //this is the start of the random loop
            for(int i = 0; i < 8; i++){
                String line = "";
                for(int a = 0; a < 8; a++){
                    line = line + " " +(board[i][a]);
                }
                System.out.println(line);
            }
            System.out.println("\n\n");


            if (whichTurn == 0) {
                int pieceType = random.nextInt(6);     //this is a random part for the pieces, getting the piece that is going to be used
                int amountOfPiece = 0;   //initilising the variable
                String pieceName = "";
                switch (pieceType) {
                    case (0) -> {
                        amountOfPiece = (storage.currentWhitePiecesGet("king")).size();       //this gets the amount of pieces
                        if(amountOfPiece == 0){
                            break;
                        }
                        System.out.println(amountOfPiece);
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentWhitePiecesGet("king");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate
                        System.out.println(startX);
                        System.out.println(startY);
                        String[][] movementLayer = king.kingMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("king") + storage.currentAvailableMovesTakeGet("king");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("king", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("king", 0);  //This is doing the same as the line before
                        if(currentMoves == 0 ){
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made

                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        switch(takenPieceChar){
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

                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }

                            }
                        }
                        whichTurn  = 1;
                    }
                    case (1) -> {
                        amountOfPiece = (storage.currentWhitePiecesGet("pawn")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentWhitePiecesGet("pawn");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = pawn.pawnMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("pawn") + storage.currentAvailableMovesTakeGet("pawn");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("pawn", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("pawn", 0);  //This is doing the same as the line before
                        if(currentMoves == 0){
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make
                                        ArrayList<String> movablePawns = storage.currentPawnsAllowedMoveGet();
                                        movablePawns.remove(whatPiece);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
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
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 1;
                    }
                    case (2) -> {
                        amountOfPiece = (storage.currentWhitePiecesGet("bishop")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentWhitePiecesGet("bishop");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate
                        System.out.println(startX);
                        System.out.println(startY);

                        String[][] movementLayer = bishop.bishopMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("bishop") + storage.currentAvailableMovesTakeGet("bishop");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("bishop", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("bishop", 0);  //This is doing the same as the line before
                        if(currentMoves == 0 ){
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
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
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 1;
                    }
                    case (3) -> {

                        amountOfPiece = (storage.currentWhitePiecesGet("horse")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentWhitePiecesGet("horse");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        System.out.println(startX);
                        System.out.println(startY);
                        String[][] movementLayer = horse.horseMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("horse") + storage.currentAvailableMovesTakeGet("horse");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("horse", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("horse", 0);  //This is doing the same as the line before
                        if(currentMoves == 0 ){
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
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
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 1;
                    }
                    case (4) -> {
                        amountOfPiece = (storage.currentWhitePiecesGet("queen")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentWhitePiecesGet("queen");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = queen.queenMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("queen") + storage.currentAvailableMovesTakeGet("queen");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("queen", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("queen", 0);  //This is doing the same as the line before
                        if(currentMoves == 0 ){
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made

                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
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
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 1;
                    }
                    case (5) -> {
                        amountOfPiece = (storage.currentWhitePiecesGet("castle")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentWhitePiecesGet("castle");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = castle.castleMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("castle") + storage.currentAvailableMovesTakeGet("castle");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("castle", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("castle", 0);  //This is doing the same as the line before
                        if(currentMoves == 0 ){
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
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
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 1;
                    }
                }



            } else {
                //System.out.println("It is Blacks turn");
                int pieceType = random.nextInt(6);     //this is a random part for the pieces, getting the piece that is going to be used
                int amountOfPiece = 0;
                String pieceName = "";
                switch (pieceType) {
                    case (0) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("king")).size();       //this gets the amount of
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("king");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = king.kingMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("king") + storage.currentAvailableMovesTakeGet("king");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("king", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("king", 0);  //This is doing the same as the line before
                        if(currentMoves == 0 ){
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);  //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {

                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1000;//this breaks the loop
                                    }
                                    position += 1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + board[i][a].charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        //this is now dealing with if a piece has been takne and what piece it is
                                        switch(takenPieceChar){
                                            case ("P") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("pawn");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("C") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("castle");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("B") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("bishop");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("Q") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("queen");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("K") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("king");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("H") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("horse");
                                                currentPieces.remove(takenPiece);
                                            }
                                        }
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1000; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 0;
                        break;
                    }
                    case (1) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("pawn")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("pawn");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate
                        System.out.println(startX);
                        System.out.println(startY);

                        String[][] movementLayer = pawn.pawnMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("pawn") + storage.currentAvailableMovesTakeGet("pawn");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("pawn", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("pawn", 0);  //This is doing the same as the line before
                        if(currentMoves == 0){
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make
                                        ArrayList<String> movablePawns = storage.currentPawnsAllowedMoveGet();
                                        movablePawns.remove(whatPiece);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        switch (takenPieceChar) {
                                            case ("P") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("pawn");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("C") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("castle");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("B") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("bishop");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("Q") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("queen");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("K") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("king");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("H") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("horse");
                                                currentPieces.remove(takenPiece);
                                            }
                                        }
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 0;
                        break;
                    }
                    case (2) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("bishop")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("bishop");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate
                        System.out.println(startX);
                        System.out.println(startY);
                        String[][] movementLayer = bishop.bishopMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("bishop") + storage.currentAvailableMovesTakeGet("bishop");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("bishop", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("bishop", 0);  //This is doing the same as the line before
                        if(currentMoves == 0){
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }

                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        System.out.println(moveToMake);
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        switch (takenPieceChar) {
                                            case ("P") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("pawn");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("C") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("castle");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("B") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("bishop");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("Q") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("queen");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("K") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("king");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("H") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("horse");
                                                currentPieces.remove(takenPiece);
                                            }
                                        }
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 0;
                        break;
                    }
                    case (3) -> {

                        amountOfPiece = (storage.currentBlackPiecesGet("horse")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("horse");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        System.out.println(startX);
                        System.out.println(startY);
                        String[][] movementLayer = horse.horseMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("horse") + storage.currentAvailableMovesTakeGet("horse");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("horse", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("horse", 0);  //This is doing the same as the line before
                        if(currentMoves == 0){
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + board[i][a].charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        switch(takenPieceChar){
                                            case ("P") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("pawn");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("C") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("castle");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("B") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("bishop");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("Q") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("queen");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("K") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("king");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("H") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("horse");
                                                currentPieces.remove(takenPiece);
                                            }
                                        }
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop

                                    }
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 0;
                        break;
                    }
                    case (4) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("queen")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("queen");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = queen.queenMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("queen") + storage.currentAvailableMovesTakeGet("queen");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("queen", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("queen", 0);  //This is doing the same as the line before
                        if(currentMoves == 0){
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        switch (takenPieceChar) {
                                            case ("P") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("pawn");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("C") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("castle");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("B") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("bishop");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("Q") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("queen");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("K") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("king");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("H") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("horse");
                                                currentPieces.remove(takenPiece);
                                            }
                                        }
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 0;
                        break;
                    }
                    case (5) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("castle")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if(amountOfPiece == 0){
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("castle");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt( "" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = castle.castleMain(board, startX, startY, storage); //this is building the layer map

                        int currentMoves = storage.currentAvailableMovesGet("castle") + storage.currentAvailableMovesTakeGet("castle");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("castle", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("castle", 0);  //This is doing the same as the line before
                        if(currentMoves == 0 ){
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for(int i = 0; i < 8; i++){
                            for(int a = 0; a < 8; a++){
                                if(movementLayer[i][a].equals("1")) {
                                    if(position == moveToMake){     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position+=1;
                                }
                                if(movementLayer[i][a].equals("2")){        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if(position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + takenPiece.charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        switch (takenPieceChar) {
                                            case ("P") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("pawn");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("C") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("castle");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("B") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("bishop");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("Q") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("queen");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("K") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("king");
                                                currentPieces.remove(takenPiece);
                                            }
                                            case ("H") -> {
                                                ArrayList<String> currentPieces = storage.currentWhitePiecesGet("horse");
                                                currentPieces.remove(takenPiece);
                                            }
                                        }
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position +=1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn  = 0;
                        break;
                    }
                }


            }

            System.out.println(storage.currentWhitePiecesGet("pawn"));
            System.out.println(storage.currentWhitePiecesGet("castle"));
            System.out.println(storage.currentWhitePiecesGet("bishop"));
            System.out.println(storage.currentWhitePiecesGet("king"));
            System.out.println(storage.currentWhitePiecesGet("queen"));
            System.out.println(storage.currentWhitePiecesGet("horse"));

            System.out.println(storage.currentBlackPiecesGet("pawn"));
            System.out.println(storage.currentBlackPiecesGet("castle"));
            System.out.println(storage.currentBlackPiecesGet("bishop"));
            System.out.println(storage.currentBlackPiecesGet("king"));
            System.out.println(storage.currentBlackPiecesGet("queen"));
            System.out.println(storage.currentBlackPiecesGet("horse"));



            times+=1;
        }

    }
}
