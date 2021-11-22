public class King extends Piece {
    public String[][] kingMain(String[][] array, int StartX, int StartY, Storage store){
        System.out.println("The king layer has been activated");
        String firstHalf = array[StartX][StartY].substring(0,2);
        String[][] boardOutput = null;
        if(firstHalf.equals("WK")){
            //System.out.println("There is a white king here");
            boardOutput = layerSystemKing(array, StartX, StartY, store);
        } else if (firstHalf.equals("BK")){
            //System.out.println("Black king here");
            boardOutput = layerSystemKing(array, StartX, StartY, store);
        }

        return boardOutput;
    }

    public static String[][] checkChecker(String[][] array, int StartX, int StartY, Storage store){
        String[][] avoidLossLayer = {
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
        };
        //This is part is just going to check if one of the kings is dead, if they are then it ends the game automatiically


        return avoidLossLayer;  //this will return the places that it needs to move towards to avoid loosing
    }

    public static int checkMateChecker(String[][] array, Storage store, String colour){
        //0 is no checkMate, 1 is WhiteKing is in checkmate, 2 is BlackKing is in checkmate
        //i need to generate a layer which has all of the possible moves of all the pieces
        playerInput pI = new playerInput();
        int checkMate = 1;
        if(store.currentWhitePiecesGet("king").size() == 0){
            checkMate = 3;
            return checkMate;
        }
        if(store.currentBlackPiecesGet("king").size() == 0){
            checkMate = 4;
            return checkMate;
        }
        if(colour.equals("W")){
            //need to generate the black occupation layer as it is the white piece that is being activated, therefore their movement layer will be affected
            String BK1position = store.currentPiecePositionsGet("BK1");
            int BK1X = Integer.parseInt("" + BK1position.charAt(0));
            int BK1Y = Integer.parseInt("" + BK1position.charAt(1));
            //need to generate a layer map of the current king that is being used
            String[][] kingLayer = layerSystemKing(array,BK1X, BK1Y, store);

            System.out.println("The white kings current position is X:" + BK1X +" Y:" +BK1Y);
            String[][] occupation = pI.boardOccupation(array, store, "B");
            System.out.println("This is the current occupation layer for black:\n");
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (occupation[i][a]);
                }
                System.out.println(line);
            }
            for (int i = 0; i < 8; i++) {       //all this is doing is checking if there is a checkmate, if there is then it ends the game
                for (int a = 0; a < 8; a++) {
                    if(kingLayer[i][a].equals("1") && occupation[i][a].equals("1")){
                        kingLayer[i][a] = "0";
                    }else if(kingLayer[i][a].equals("3") && occupation[i][a].equals("1")){
                        kingLayer[i][a] = "0";
                    }
                    for (int b = 0; b < 8; b++) {
                        for (int c = 0; c < 8; c++) {
                            if(Integer.parseInt(kingLayer[b][c]) > 0 ){
                                checkMate = 0;
                            }
                        }

                    }
                }
            }

        }else if(colour.equals("B")){
            String WK1position = store.currentPiecePositionsGet("WK1");
            int WK1X = Integer.parseInt("" + WK1position.charAt(0));
            int WK1Y = Integer.parseInt("" + WK1position.charAt(1));

            String[][] kingLayer = layerSystemKing(array,WK1X, WK1Y, store);


            System.out.println("The white kings current position is X:" + WK1X +" Y:" +WK1Y);
            String[][] occupation = pI.boardOccupation(array, store, "W");
            System.out.println("This is the current occupation layer for white:\n");
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (occupation[i][a]);
                }
                System.out.println(line);
            }
            for (int i = 0; i < 8; i++) {       //all this is doing is checking if there is a checkmate, if there is then it ends the game
                for (int a = 0; a < 8; a++) {
                    if(kingLayer[i][a].equals("1") && occupation[i][a].equals("1")){
                        kingLayer[i][a] = "0";
                    }else if(kingLayer[i][a].equals("3") && occupation[i][a].equals("1")){
                        kingLayer[i][a] = "0";
                    }
                    for (int b = 0; b < 8; b++) {
                        for (int c = 0; c < 8; c++) {
                            if(Integer.parseInt(kingLayer[b][c]) > 0 ){
                                checkMate = 0;
                            }
                        }

                    }
                }
            }

        }

        return checkMate;
    }


    public static String[][] layerSystemKing(String[][] array, int StartX, int StartY, Storage store){
        //instead i am going to call the checkmate checker every single time that the kingLayer Sysytem runs so that it can alawys try to avoid death.


        String [][] kingLayer = {     //This is the base "mesh" used to layer the piece movements
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
        Integer[][] movesKing = {      //these are movement vectors for the kings
                {1,0},
                {-1,0},
                {0,1},
                {0,-1},
                {1,1},
                {-1,1},
                {1,-1},
                {-1,-1}};
        for(int i = 0; movesKing.length > i; i++){      //This is the loop, to apply all the different movement vectors
            int newStartX = StartX + movesKing[i][0];   //These two are adding the movement vectors to the original coordinates
            int newStartY = StartY + movesKing[i][1];
            if(newStartX > 7 || newStartX < 0 || newStartY > 7 || newStartY < 0){   //boundary checking to make sure that it is wihtin range
                continue;  //this is not a useless contiue, as it means it resets the loop otherwise it will end up going for the else statement
            }else{
                String arrayLetter = "" + array[newStartX][newStartY].charAt(0);
                if(arrayLetter.equals(letter)){     //This is making sure that it is en enemy piece is being attacked.
                    kingLayer[newStartX][newStartY] = "2";      //This puts a 2 into the movement layer so that it can be seen that the piece can take here.
                    store.currentAvailableMovesTakeSet("king", (store.currentAvailableMovesTakeGet("king") +1 ));
                }if(arrayLetter.equals("E")){       //this is making sure that it can move here if the piece is empty
                    kingLayer[newStartX][newStartY] = "1";      //This puts a 1 into the movement layer, showing that it is possible to move here.
                    store.currentAvailableMovesSet("king", (store.currentAvailableMovesGet("king") +1 ));  //This is keeping track of all the possible moves for the king layer
                }
            }
        }
        kingLayer[StartX][StartY] = "3";


        if(store.debugGet() == 1) {
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (kingLayer[i][a]);
                }
                System.out.println(line);
            }
        }
        return kingLayer;
    }
}
