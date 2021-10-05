public class King extends Piece {
    public String[][] kingMain(String[][] array, int StartX, int StartY, Storage store){
        String firstHalf = array[StartX][StartY].substring(0,2);
        String[][] boardOutput = array;
        if(firstHalf.equals("WK")){
            //System.out.println("There is a white king here");
            boardOutput = layerSystemKing(array, StartX, StartY, store);
        } else if (firstHalf.equals("BK")){
            //System.out.println("Black king here");
            boardOutput = layerSystemKing(array, StartX, StartY, store);
        }

        return boardOutput;
    }

    public static String[][] checkMateChecker(String[][] array, int StartX, int StartY, Storage store){
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



        return avoidLossLayer;  //this will return the places that it needs to move towards to avoid loosing
    }

    public static String[][] layerSystemKing(String[][] array, int StartX, int StartY, Storage store){
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
