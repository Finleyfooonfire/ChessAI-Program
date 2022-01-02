import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws IOException {
        //All of the constructors
        Horse horse = new Horse();
        King king = new King();
        Queen queen = new Queen();
        Bishop bishop = new Bishop();
        Castle castle = new Castle();
        Pawn pawn = new Pawn();
        Storage store = new Storage();  //Making the main storage class
        gui gi = new gui();

        //this is making sure that all the arrays are initilised from the storage class
        String[][] board = {
                {"WC1", "WH1", "WB1", "WK1", "WQ1", "WB2", "WH2", "WC2"},
                {"WP1", "WP2", "WP3", "WP4", "WP5", "WP6", "WP7", "WP8"},
                {"E", "E", "E", "E", "E", "E", "E", "E"},
                {"E", "E", "E", "E", "E", "E", "E", "E"},
                {"E", "E", "E", "E", "E", "E", "E", "E"},
                {"E", "E", "E", "E", "E", "E", "E", "E"},
                {"BP1", "BP2", "BP3", "BP4", "BP5", "BP6", "BP7", "BP8"},
                {"BC1", "BH1", "BB1", "BK1", "BQ1", "BB2", "BH2", "BC2"},
        };
        for (int i = 0; i < 8; i++) {
            String line = "";
            for (int a = 0; a < 8; a++) {
                line = line + " " + (board[i][a]);
            }
            System.out.println(line);
        }

        boolean gameRunning = true;

        while (gameRunning){
            Scanner sc = new Scanner(System.in);
            System.out.println("Please state what you want to do:");
            String input = sc.nextLine();
            if (input.equals("random")) {
                String colour = "W";
                gui.render(store, board);
                for (int b = 0; b < 100; b++) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("On the " + b+ "th iteration of random moves");
                    if (colour.equals("W")) {
                        System.out.println("It is whites move now");
                        board = randomPart(board, store, colour);


                        for (int i = 0; i < 8; i++) {
                            String line = "";
                            for (int a = 0; a < 8; a++) {
                                if(board[i][a].equals("E")){
                                    line = line + "   " + (board[i][a]);
                                }else{
                                    line = line + " " + (board[i][a]);
                                }
                            }
                            System.out.println(line);
                        }
                        int isThereCheck = King.checkMateChecker(board,store, colour);
                        switch (isThereCheck) {
                            case 1 -> {
                                System.out.println("There was checkmate, thank you for playing");
                                //System.exit(0);
                            }
                            case 3 -> {
                                System.out.println("Black has won the game, congratulations");
                                //System.exit(0);
                            }
                            case 4 -> {
                                System.out.println("White has won the game, congratulations");
                                //System.exit(0);
                            }
                        }

                        colour = "B";
                        System.out.println("Move completed");


                    } else if (colour.equals("B")) {
                        board = randomPart(board, store, colour);

                        for (int i = 0; i < 8; i++) {
                            String line = "";
                            for (int a = 0; a < 8; a++) {
                                if(board[i][a].equals("E")){
                                    line = line + "   " + (board[i][a]);
                                }else{
                                    line = line + " " + (board[i][a]);
                                }

                            }
                            System.out.println(line);
                        }
                        int isThereCheck = King.checkMateChecker(board,store, colour);
                        switch (isThereCheck) {
                            case 1 -> {
                                System.out.println("There was checkmate, thank you for playing");
                                gameRunning = false;
                            }
                            case 3 -> {
                                System.out.println("Black has won the game, congratulations");
                                gameRunning = false;
                            }
                            case 4 -> {
                                System.out.println("White has won the game, congratulations");
                                gameRunning = false;
                            }
                        }

                        colour = "W";
                        System.out.println("Move completed");

                    }
                    gui.repaint(store); //repaints the GUI
                }


            } else if (input.equals("AI")) {
                AI.thinking(board, store, "W");

                try {
                    gui.render(store, board);
                    System.out.println("This is the position: " + store.currentPiecePositionsGet("WP1"));


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }else if(input.equals("random person")){
                gui.render(store, board);
                store.randomPersonActive = true;
                String colour = "W";
                while(gameRunning){
                    try {       //needs to wait otherwise it doesnt work. Its like it makes the program stop and think. I like it
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(colour.equals("W")){
                        if(store.inputRecieved == 1){
                            store.inputRecieved = 0;
                            colour = "B";
                            //need to now validate the moves
                            String currentPosition = store.currentPosition;
                            int currentPositionX = Integer.parseInt("" + currentPosition.charAt(0));
                            int currentPositionY = Integer.parseInt("" + currentPosition.charAt(1));
                            String previousPosition = store.previousPosition;
                            int previousPositionX = Integer.parseInt("" + previousPosition.charAt(0));
                            int previousPositionY = Integer.parseInt("" + previousPosition.charAt(1));
                            String pieceOnMove = board[currentPositionX][currentPositionY];

                            if(!pieceOnMove.equals("E")){
                                String pieceType =  "" + pieceOnMove.charAt(1);

                                switch (pieceType){
                                    case("B") -> {
                                        store.amountOfBlackBishop.remove(pieceOnMove);
                                    }case("Q") ->{
                                        store.amountOfBlackQueen.remove(pieceOnMove);
                                    }case("K") ->{
                                        store.amountOfBlackKing.remove(pieceOnMove);
                                    }case("P") ->{
                                        store.amountOfBlackPawn.remove(pieceOnMove);
                                    }case("H") ->{
                                        store.amountOfBlackHorse.remove(pieceOnMove);
                                    }case("C") ->{
                                        store.amountOfBlackCastle.remove(pieceOnMove);
                                    }
                                }
                                String pieceName = board[previousPositionX][previousPositionY];
                                board[previousPositionX][previousPositionY] = "E";
                                board[currentPositionX][currentPositionY] = pieceName;
                                store.currentPiecePositionsSet(pieceName, currentPosition);
                                gui.repaint(store);
                            }else{
                                String pieceName = board[previousPositionX][previousPositionY];
                                board[previousPositionX][previousPositionY] = "E";
                                board[currentPositionX][currentPositionY] = pieceName;

                                store.currentPiecePositionsSet(pieceName, currentPosition);
                                gui.repaint(store);
                            }
                        }

                    }else if(colour.equals("B")){
                        try {       //needs to wait otherwise it doesnt work. Its like it makes the program stop and think. I like it
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        randomPart(board, store, colour);
                        gui.repaint(store);     //repaints the GUI
                        colour = "W";

                        //checkmate checker
                        int isThereCheck = King.checkMateChecker(board,store, colour);
                        switch (isThereCheck) {
                            case 1 -> {
                                System.out.println("There was checkmate, thank you for playing");
                                gameRunning = false;
                            }
                            case 3 -> {
                                System.out.println("Black has won the game, congratulations");
                                gameRunning = false;
                            }
                            case 4 -> {
                                System.out.println("White has won the game, congratulations");
                                gameRunning = false;
                            }
                        }
                    }




                }

            } else if (input.equals("random person console")) {        //this is the part that will just simulate random move with player input
                playerInput player = new playerInput();
                String colour = "W";
                int times = 0;
                gui.render(store, board);

                while (times < 100) {
                    if (colour.equals("B")) {
                        randomPart(board, store, colour);//makes a random move for black
                        System.out.println("\n\n");
                        for (int i = 0; i < 8; i++) {
                            String line = "";
                            for (int a = 0; a < 8; a++) {
                                line = line + " " + (board[i][a]);
                            }
                            System.out.println(line);
                        }
                        gui.repaint(store);
                        colour = "W";
                    } else if (colour.equals("W")) {     //to make life eaiser the player is always going to be white, they wont have an option
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Please input the piece you would want to move");
                        String pieceChoice = scan.nextLine();
                        String[][] layerMap = player.getLayerMap(pieceChoice, board, store);
                        System.out.println("These are the possible moves that can be taken");
                        for (int i = 0; i < 8; i++) {
                            String line = "";
                            for (int a = 0; a < 8; a++) {
                                line = line + " " + (layerMap[i][a]);
                            }
                            System.out.println(line);
                        }
                        //declaring the coordinate varialbes
                        int coordinateX;
                        int coordinateY;
                        while (true) {
                            String placeToMove;
                            while (true) {
                                System.out.println("Please pick the coordinates to move to");
                                placeToMove = scan.nextLine();
                                if (placeToMove.charAt(0) == '9') {
                                    System.out.println("That is not valid, please try again");
                                } else {
                                    break;
                                }
                            }
                            String placeToMoveTranslated = player.inputTranslator(placeToMove);
                            coordinateX = Integer.parseInt("" + placeToMoveTranslated.charAt(0));
                            coordinateY = Integer.parseInt("" + placeToMoveTranslated.charAt(1));
                            if (store.debugGet() == 1) {  //part of the debug values
                                System.out.println("X: " + coordinateX); //this is where they are going to move to
                                System.out.println("Y: " + coordinateY); //this is where they aregoing to move to.
                            }
                            if (!layerMap[coordinateX][coordinateY].equals("0") && !layerMap[coordinateX][coordinateY].equals("3")) {
                                break;
                            } else {
                                System.out.println("Please pick a place that you can move to");
                                for (int i = 0; i < 8; i++) {
                                    String line = "";
                                    for (int a = 0; a < 8; a++) {
                                        line = line + " " + (layerMap[i][a]);
                                    }
                                    System.out.println(line);
                                }
                            }
                        }//now all the validation has finished, it can now go onto doing the move
                        //need to get the coordinates of the piece that is being moved
                        String moveType = layerMap[coordinateX][coordinateY];
                        String initialMove = store.currentPiecePositionsGet(pieceChoice);
                        System.out.println(initialMove);    //this is the place where the piece that is being moved is stored
                        int initialMoveX = Integer.parseInt("" + initialMove.charAt(0));    //this is seperating it into seperate parts
                        int initialMoveY = Integer.parseInt("" + initialMove.charAt(1));
                        player.playerMove(board, store, colour, initialMoveX, initialMoveY, coordinateX, coordinateY, moveType);  //this is what deals with the user input and changing the values
                        gui.repaint(store);
                        colour = "B";
                    }
                }
            }else if(input.equals("exit")){
                System.out.println("Thank you for playing");
                System.exit(0);
            }
       }
    }

    //this is a method that will generate a map of all of the free spaces on the board.


    public static String[][] randomPart(String[][] board, Storage storage, String colour) throws IOException {  //this is a fully working, fully validated random move picker, i am proud of my baby
        Horse horse = new Horse();
        King king = new King();
        Queen queen = new Queen();
        Bishop bishop = new Bishop();
        Castle castle = new Castle();
        Pawn pawn = new Pawn();

        int whichTurn = 0;   //This is a variable that is going to be a flip flop, it is going to keep track of whose turn it is
        Random random = new Random();
        int times = 0;
               //this is the start of the random loop
            if (colour == "W") {

                while(true) {
                    int currentMoves = 0 ;
                    int pieceType = random.nextInt(6);     //this is a random part for the pieces, getting the piece that is going to be used
                    int amountOfPiece = 0;   //initilising the variable
                    String pieceName = "";
                    switch (pieceType) {
                        case (0) -> {
                            amountOfPiece = (storage.currentWhitePiecesGet("king")).size();       //this gets the amount of pieces
                            if (amountOfPiece == 0) {
                                break;
                            }
                            if(storage.debugGet() == 1) {   //part of debug data
                                System.out.println("Amount of king piece " + amountOfPiece);
                            }
                            // System.out.println("The amount of pieces are " + amountOfPiece);
                            int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                            ArrayList<String> movingPieces = storage.currentWhitePiecesGet("king");  //this is the array that has all of the horse pieces
                            String whatPiece = movingPieces.get(whatPieceNumber);

                            //now that the piece to move has been decided, i need to get its coordinates
                            String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                            int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                            int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate
                            System.out.println(startX);
                            System.out.println(startY);
                            String[][] movementLayer = king.kingMain(board, startX, startY, storage); //this is building the layer map

                            currentMoves = storage.currentAvailableMovesGet("king") + storage.currentAvailableMovesTakeGet("king");      //this is getting all of the amount of moves that can happen
                            storage.currentAvailableMovesSet("king", 0);   //this is resetting the values
                            storage.currentAvailableMovesTakeSet("king", 0);  //This is doing the same as the line before
                            if (currentMoves == 0) {
                                break;
                            }
                            int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made

                            int position = 0;
                            for (int i = 0; i < 8; i++) {
                                for (int a = 0; a < 8; a++) {
                                    if (movementLayer[i][a].equals("1")) {
                                        if (position == moveToMake) {     //it should have now found a move to make
                                            board[i][a] = whatPiece;    //this is the place the piece is moving to
                                            board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                            //now the positions need to be updated in the storage
                                            storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                            position = 1111; //this breaks the loop
                                        }
                                        position += 1;
                                    }
                                    if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                        if (position == moveToMake) {
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

                                }
                            }
                            whichTurn = 1;
                        }
                        case (1) -> {
                            amountOfPiece = (storage.currentWhitePiecesGet("pawn")).size();       //this gets the amount of pieces
                            System.out.println(amountOfPiece);
                            if (amountOfPiece == 0) {
                                break;
                            }
                            // System.out.println("The amount of pieces are " + amountOfPiece);
                            int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                            ArrayList<String> movingPieces = storage.currentWhitePiecesGet("pawn");  //this is the array that has all of the horse pieces
                            String whatPiece = movingPieces.get(whatPieceNumber);

                            //now that the piece to move has been decided, i need to get its coordinates
                            String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                            int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                            int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                            String[][] movementLayer = pawn.pawnMain(board, startX, startY, storage); //this is building the layer map

                            currentMoves = storage.currentAvailableMovesGet("pawn") + storage.currentAvailableMovesTakeGet("pawn");      //this is getting all of the amount of moves that can happen
                            storage.currentAvailableMovesSet("pawn", 0);   //this is resetting the values
                            storage.currentAvailableMovesTakeSet("pawn", 0);  //This is doing the same as the line before
                            if (currentMoves == 0) {
                                break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                            }
                            int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                            int position = 0;
                            for (int i = 0; i < 8; i++) {
                                for (int a = 0; a < 8; a++) {
                                    if (movementLayer[i][a].equals("1")) {
                                        if (position == moveToMake) {     //it should have now found a move to make
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
                                    if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                        if (position == moveToMake) {
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
                            whichTurn = 1;
                        }
                        case (2) -> {
                            amountOfPiece = (storage.currentWhitePiecesGet("bishop")).size();       //this gets the amount of pieces
                            System.out.println(amountOfPiece);
                            if (amountOfPiece == 0) {
                                break;
                            }
                            // System.out.println("The amount of pieces are " + amountOfPiece);
                            int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                            ArrayList<String> movingPieces = storage.currentWhitePiecesGet("bishop");  //this is the array that has all of the horse pieces
                            String whatPiece = movingPieces.get(whatPieceNumber);

                            //now that the piece to move has been decided, i need to get its coordinates
                            String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                            int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                            int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate
                            System.out.println(startX);
                            System.out.println(startY);

                            String[][] movementLayer = bishop.bishopMain(board, startX, startY, storage); //this is building the layer map

                            currentMoves = storage.currentAvailableMovesGet("bishop") + storage.currentAvailableMovesTakeGet("bishop");      //this is getting all of the amount of moves that can happen
                            storage.currentAvailableMovesSet("bishop", 0);   //this is resetting the values
                            storage.currentAvailableMovesTakeSet("bishop", 0);  //This is doing the same as the line before
                            if (currentMoves == 0) {
                                break;
                            }
                            int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                            int position = 0;
                            for (int i = 0; i < 8; i++) {
                                for (int a = 0; a < 8; a++) {
                                    if (movementLayer[i][a].equals("1")) {
                                        if (position == moveToMake) {     //it should have now found a move to make

                                            board[i][a] = whatPiece;    //this is the place the piece is moving to
                                            board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                            //now the positions need to be updated in the storage
                                            storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                            position = 1111; //this breaks the loop
                                        }
                                        position += 1;
                                    }
                                    if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                        if (position == moveToMake) {
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
                            whichTurn = 1;
                        }
                        case (3) -> {

                            amountOfPiece = (storage.currentWhitePiecesGet("horse")).size();       //this gets the amount of pieces
                            System.out.println(amountOfPiece);
                            if (amountOfPiece == 0) {
                                break;
                            }
                            // System.out.println("The amount of pieces are " + amountOfPiece);
                            int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                            ArrayList<String> movingPieces = storage.currentWhitePiecesGet("horse");  //this is the array that has all of the horse pieces
                            String whatPiece = movingPieces.get(whatPieceNumber);

                            //now that the piece to move has been decided, i need to get its coordinates
                            String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                            int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                            int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                            System.out.println(startX);
                            System.out.println(startY);
                            String[][] movementLayer = horse.horseMain(board, startX, startY, storage); //this is building the layer map

                            currentMoves = storage.currentAvailableMovesGet("horse") + storage.currentAvailableMovesTakeGet("horse");      //this is getting all of the amount of moves that can happen
                            storage.currentAvailableMovesSet("horse", 0);   //this is resetting the values
                            storage.currentAvailableMovesTakeSet("horse", 0);  //This is doing the same as the line before
                            if (currentMoves == 0) {
                                break;
                            }
                            int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                            int position = 0;
                            for (int i = 0; i < 8; i++) {
                                for (int a = 0; a < 8; a++) {
                                    if (movementLayer[i][a].equals("1")) {
                                        if (position == moveToMake) {     //it should have now found a move to make

                                            board[i][a] = whatPiece;    //this is the place the piece is moving to
                                            board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                            //now the positions need to be updated in the storage
                                            storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                            position = 1111; //this breaks the loop
                                        }
                                        position += 1;
                                    }
                                    if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                        if (position == moveToMake) {
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
                            whichTurn = 1;
                        }
                        case (4) -> {
                            amountOfPiece = (storage.currentWhitePiecesGet("queen")).size();       //this gets the amount of pieces
                            System.out.println(amountOfPiece);
                            if (amountOfPiece == 0) {
                                break;
                            }
                            // System.out.println("The amount of pieces are " + amountOfPiece);
                            int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                            ArrayList<String> movingPieces = storage.currentWhitePiecesGet("queen");  //this is the array that has all of the horse pieces
                            String whatPiece = movingPieces.get(whatPieceNumber);

                            //now that the piece to move has been decided, i need to get its coordinates
                            String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                            int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                            int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                            String[][] movementLayer = queen.queenMain(board, startX, startY, storage); //this is building the layer map

                           currentMoves = storage.currentAvailableMovesGet("queen") + storage.currentAvailableMovesTakeGet("queen");      //this is getting all of the amount of moves that can happen
                            storage.currentAvailableMovesSet("queen", 0);   //this is resetting the values
                            storage.currentAvailableMovesTakeSet("queen", 0);  //This is doing the same as the line before
                            if (currentMoves == 0) {
                                break;
                            }
                            int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made

                            int position = 0;
                            for (int i = 0; i < 8; i++) {
                                for (int a = 0; a < 8; a++) {
                                    if (movementLayer[i][a].equals("1")) {
                                        if (position == moveToMake) {     //it should have now found a move to make

                                            board[i][a] = whatPiece;    //this is the place the piece is moving to
                                            board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                            //now the positions need to be updated in the storage
                                            storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                            position = 1111; //this breaks the loop
                                        }
                                        position += 1;
                                    }
                                    if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                        if (position == moveToMake) {
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
                            whichTurn = 1;
                        }
                        case (5) -> {
                            amountOfPiece = (storage.currentWhitePiecesGet("castle")).size();       //this gets the amount of pieces
                            System.out.println(amountOfPiece);
                            if (amountOfPiece == 0) {
                                break;
                            }
                            // System.out.println("The amount of pieces are " + amountOfPiece);
                            int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                            ArrayList<String> movingPieces = storage.currentWhitePiecesGet("castle");  //this is the array that has all of the horse pieces
                            String whatPiece = movingPieces.get(whatPieceNumber);

                            //now that the piece to move has been decided, i need to get its coordinates
                            String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                            int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                            int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                            String[][] movementLayer = castle.castleMain(board, startX, startY, storage); //this is building the layer map

                            currentMoves = storage.currentAvailableMovesGet("castle") + storage.currentAvailableMovesTakeGet("castle");      //this is getting all of the amount of moves that can happen
                            storage.currentAvailableMovesSet("castle", 0);   //this is resetting the values
                            storage.currentAvailableMovesTakeSet("castle", 0);  //This is doing the same as the line before
                            if (currentMoves == 0) {
                                break;
                            }
                            int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                            int position = 0;
                            for (int i = 0; i < 8; i++) {
                                for (int a = 0; a < 8; a++) {
                                    if (movementLayer[i][a].equals("1")) {
                                        if (position == moveToMake) {     //it should have now found a move to make

                                            board[i][a] = whatPiece;    //this is the place the piece is moving to
                                            board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                            //now the positions need to be updated in the storage
                                            storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                            position = 1111; //this breaks the loop
                                        }
                                        position += 1;
                                    }
                                    if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                        if (position == moveToMake) {
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
                            whichTurn = 1;
                        }
                    }

                    if(currentMoves != 0){
                        break;
                    }
                }
            } else {
                //System.out.println("It is Blacks turn");
                while(true){
                    int currentMoves = 0 ;
                int pieceType =  random.nextInt(6);     //this is a random part for the pieces, getting the piece that is going to be used
                int amountOfPiece = 0;
                String pieceName = "";
                switch (pieceType) {
                    case (0) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("king")).size();       //this gets the amount of
                        System.out.println("The amount of black kings on the board: "+ amountOfPiece);
                        if (amountOfPiece == 0) {
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("king");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = king.kingMain(board, startX, startY, storage); //this is building the layer map

                        currentMoves = storage.currentAvailableMovesGet("king") + storage.currentAvailableMovesTakeGet("king");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("king", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("king", 0);  //This is doing the same as the line before
                        if (currentMoves == 0) {
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);  //this is the move that is going to be made
                        int position = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (movementLayer[i][a].equals("1")) {
                                    if (position == moveToMake) {     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1000;//this breaks the loop
                                    }
                                    position += 1;
                                }
                                if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if (position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + board[i][a].charAt(1);
                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";
                                        //this is now dealing with if a piece has been takne and what piece it is
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
                                        position = 1000; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn = 0;
                        break;
                    }
                    case (1) -> {

                        amountOfPiece = (storage.currentBlackPiecesGet("pawn")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if (amountOfPiece == 0) {
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("pawn");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate
                        System.out.println(startX);
                        System.out.println(startY);

                        String[][] movementLayer = pawn.pawnMain(board, startX, startY, storage); //this is building the layer map

                        currentMoves = storage.currentAvailableMovesGet("pawn") + storage.currentAvailableMovesTakeGet("pawn");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("pawn", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("pawn", 0);  //This is doing the same as the line before
                        if (currentMoves == 0) {
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }

                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (movementLayer[i][a].equals("1")) {
                                    if (position == moveToMake) {     //it should have now found a move to make
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
                                if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if (position == moveToMake) {
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
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn = 0;
                        break;
                    }
                    case (2) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("bishop")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if (amountOfPiece == 0) {
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("bishop");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate
                        System.out.println(startX);
                        System.out.println(startY);
                        String[][] movementLayer = bishop.bishopMain(board, startX, startY, storage); //this is building the layer map

                        currentMoves = storage.currentAvailableMovesGet("bishop") + storage.currentAvailableMovesTakeGet("bishop");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("bishop", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("bishop", 0);  //This is doing the same as the line before
                        if (currentMoves == 0) {
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }

                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        System.out.println(moveToMake);
                        int position = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (movementLayer[i][a].equals("1")) {
                                    if (position == moveToMake) {     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if (position == moveToMake) {
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
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn = 0;
                        break;
                    }
                    case (3) -> {

                        amountOfPiece = (storage.currentBlackPiecesGet("horse")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if (amountOfPiece == 0) {
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("horse");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                        System.out.println(startX);
                        System.out.println(startY);
                        String[][] movementLayer = horse.horseMain(board, startX, startY, storage); //this is building the layer map

                        currentMoves = storage.currentAvailableMovesGet("horse") + storage.currentAvailableMovesTakeGet("horse");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("horse", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("horse", 0);  //This is doing the same as the line before
                        if (currentMoves == 0) {
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (movementLayer[i][a].equals("1")) {
                                    if (position == moveToMake) {     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if (position == moveToMake) {
                                        System.out.println("im being taken");
                                        String takenPiece = board[i][a];
                                        String takenPieceChar = "" + board[i][a].charAt(1);
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
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn = 0;
                        break;
                    }
                    case (4) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("queen")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if (amountOfPiece == 0) {
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("queen");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = queen.queenMain(board, startX, startY, storage); //this is building the layer map

                        currentMoves = storage.currentAvailableMovesGet("queen") + storage.currentAvailableMovesTakeGet("queen");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("queen", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("queen", 0);  //This is doing the same as the line before
                        if (currentMoves == 0) {
                            break;   //this will need to be refined at a later date and ill need to make sure that it doesnt count this move
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (movementLayer[i][a].equals("1")) {
                                    if (position == moveToMake) {     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if (position == moveToMake) {
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
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }
                        whichTurn = 0;
                        break;
                    }
                    case (5) -> {
                        amountOfPiece = (storage.currentBlackPiecesGet("castle")).size();       //this gets the amount of pieces
                        System.out.println(amountOfPiece);
                        if (amountOfPiece == 0) {
                            break;
                        }
                        // System.out.println("The amount of pieces are " + amountOfPiece);
                        int whatPieceNumber = random.nextInt(amountOfPiece);        //this gets the piece that is going to be moved
                        ArrayList<String> movingPieces = storage.currentBlackPiecesGet("castle");  //this is the array that has all of the horse pieces
                        String whatPiece = movingPieces.get(whatPieceNumber);

                        //now that the piece to move has been decided, i need to get its coordinates
                        String coordinates = storage.currentPiecePositionsGet(whatPiece); //this gets the piece position and sets it to a variable
                        int startX = Integer.parseInt("" + coordinates.charAt(0));  //the x position coordinate
                        int startY = Integer.parseInt("" + coordinates.charAt(1));     //the y position coordinate

                        String[][] movementLayer = castle.castleMain(board, startX, startY, storage); //this is building the layer map

                        currentMoves = storage.currentAvailableMovesGet("castle") + storage.currentAvailableMovesTakeGet("castle");      //this is getting all of the amount of moves that can happen
                        storage.currentAvailableMovesSet("castle", 0);   //this is resetting the values
                        storage.currentAvailableMovesTakeSet("castle", 0);  //This is doing the same as the line before
                        if (currentMoves == 0) {
                            break;
                        }
                        int moveToMake = random.nextInt(currentMoves);     //this is the move that is going to be made
                        int position = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int a = 0; a < 8; a++) {
                                if (movementLayer[i][a].equals("1")) {
                                    if (position == moveToMake) {     //it should have now found a move to make

                                        board[i][a] = whatPiece;    //this is the place the piece is moving to
                                        board[startX][startY] = "E";      // this is where the piece was, and is setting it empty

                                        //now the positions need to be updated in the storage
                                        storage.currentPiecePositionsSet(whatPiece, "" + i + "" + a);
                                        position = 1111; //this breaks the loop
                                    }
                                    position += 1;
                                }
                                if (movementLayer[i][a].equals("2")) {        //this needs to be seperate as it is taking a piece, so i need to update that in an array
                                    if (position == moveToMake) {
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
                                    position += 1;
                                }
                                //if it wasnt the random position it adds one
                            }
                        }

                        break;
                    }
                }
                    if(currentMoves != 0){
                        break;
                    }
            }
            }
/*
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
*/


            times+=1;
            return board;
    }

}
