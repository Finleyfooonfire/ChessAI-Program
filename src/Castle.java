public class Castle extends Piece{
    public String[][] castleMain(String[][] array, int StartX, int StartY, Storage store){
        System.out.println("The castle layer has been activated");
        String firstHalf = array[StartX][StartY].substring(0,2);
        String[][] boardOutput = null;
        if(firstHalf.equals("WC")){
            boardOutput = layerSystemCastle(array, StartX, StartY, store);

        }else if(firstHalf.equals("BC")){
            //System.out.println("Enemy castle");
            boardOutput = layerSystemCastle(array, StartX, StartY, store);
        }
        else{
            //System.out.println("\n\nthere is no castle piece here\n\n");
            return(array);
        }
        return boardOutput;
    }




    public static String[][] layerSystemCastle(String[][] array ,int StartX, int StartY, Storage store){        //This is making the movement layer for a castle
        String[][] castleLayer = {     //This is the base "mesh" used to layer the piece movements
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
                {"0","0","0","0","0","0","0","0"},
        };
        String letter = "" + array[StartX][StartY].charAt(0);
        String letter2 = "W";
        if(letter.equals("W")){      //this automatically gets the opposing piece, so the program can know what is the current "enemy"
            letter2 = "B";
        }else if(letter.equals("B")){
            letter2 = "W";
        }

        for(int i = StartX + 1; i < 8; i++){            //Traces the moves in the down direction
            String s = "" + array[i][StartY].charAt(0);
            if(s.equals(letter2) && !s.equals("E")){     //This is seeing if the piece it is colliding with is the opposite colour, if it is then it says that the piece is "taken" or can be
                castleLayer[i][StartY] = "2";
                store.currentAvailableMovesTakeSet("castle", (store.currentAvailableMovesTakeGet("castle") +1 ));
                break;
            }else if(!array[i][StartY].equals("E")){
                break;
            }
            store.currentAvailableMovesSet("castle", (store.currentAvailableMovesGet("castle") +1 ));
            castleLayer[i][StartY] = "1";

        }
        for(int i = StartX - 1; i >= 0; i--){       //Traces the moves in the up direction
            String s = "" + array[i][StartY].charAt(0);
            if(s.equals(letter2) && !s.equals("E")) {
                castleLayer[i][StartY] = "2";
                store.currentAvailableMovesTakeSet("castle", (store.currentAvailableMovesTakeGet("castle") +1 ));
                break;
            }if(!array[i][StartY].equals("E")){
                break;
            }
            store.currentAvailableMovesSet("castle", (store.currentAvailableMovesGet("castle") +1 ));
            castleLayer[i][StartY] = "1";
        }
        for(int i = StartY + 1; i < 8;i++){         //Traces the moves in the right direction
            String s = "" + array[StartX][i].charAt(0);
            if(s.equals(letter2) && !s.equals("E")){
                castleLayer[StartX][i] = "2";
                store.currentAvailableMovesTakeSet("castle", (store.currentAvailableMovesTakeGet("castle") +1 ));
                break;
            }if(!array[StartX][i].equals("E")){
                break;
            }
            store.currentAvailableMovesSet("castle", (store.currentAvailableMovesGet("castle") +1 ));
            castleLayer[StartX][i] = "1";
        }
        for(int i = StartY - 1; i >= 0; i--){   //Traces the moves in the left direction
            String s = "" + array[StartX][i].charAt(0);
            if(s.equals(letter2) && !s.equals("E")) {
                castleLayer[StartX][i] = "2";
                store.currentAvailableMovesTakeSet("castle", (store.currentAvailableMovesTakeGet("castle") +1 ));
                break;
            }if(!array[StartX][i].equals("E")){
                break;
            }

            castleLayer[StartX][i] = "1";
            store.currentAvailableMovesSet("castle", (store.currentAvailableMovesGet("castle") +1 ));
        }



        castleLayer[StartX][StartY] = "3";
        if(store.debugGet() == 1) {
            System.out.println("CastleLayer Output:\n");
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (castleLayer[i][a]);
                }
                System.out.println(line);
            }
        }
        return castleLayer;

    }
}
