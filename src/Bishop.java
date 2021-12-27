public class Bishop{
    public String[][] bishopMain(String[][] array, int StartX , int StartY, Storage store){
        int debugValue = store.debugGet();
        if(debugValue == 1) {
            System.out.println("The bishop layer has been activated");
        }
        String firstHalf = array[StartX][StartY].substring(0,2);
        String[][] boardOutput = null;
        if(firstHalf.equals("WB")){         //remember that only pawns can be passed please, you absolute pillock
            //System.out.println("There is a white bishop here");
            boardOutput = layerSystemBishop(array, StartX, StartY, store);

        } else if (firstHalf.equals("BB")){
            //System.out.println("Black bishop here");
            boardOutput = layerSystemBishop(array, StartX, StartY, store);
        }
        return boardOutput;

    }


    public static String[][] layerSystemBishop(String[][] array, int StartX, int StartY, Storage store){
        String[][] bishopLayer = {     //This is the base "mesh" used to layer the piece movements
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
        if(letter.equals(letter2)){
            letter = "B";
        }else{
            letter = "W";
        }
        //letter is now giving the opposing colour, the one that is the opposite of the one being inputted
        //This is going to going into the top left

        for(int i = 1; 8 > i; i++){
            int newStartY = StartY - i;
            int newStartX = StartX - i;
            if(newStartX > 7 || newStartY > 7 || newStartX < 0 || newStartY < 0){
                break;
            }
            String currentStartLetter = "" + array[newStartX][newStartY].charAt(0);
            if(currentStartLetter.equals(letter)){
                bishopLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("bishop", (store.currentAvailableMovesTakeGet("bishop") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            bishopLayer[newStartX][newStartY] = "1";
            store.currentAvailableMovesSet("bishop", (store.currentAvailableMovesGet("bishop") +1 ));

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
                bishopLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("bishop", (store.currentAvailableMovesTakeGet("bishop") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            bishopLayer[newStartX][newStartY] = "1";
            store.currentAvailableMovesSet("bishop", (store.currentAvailableMovesGet("bishop") +1 ));


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
                bishopLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("bishop", (store.currentAvailableMovesTakeGet("bishop") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            bishopLayer[newStartX][newStartY] = "1";
            store.currentAvailableMovesSet("bishop", (store.currentAvailableMovesGet("bishop") +1 ));

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
                bishopLayer[newStartX][newStartY] = "2";
                store.currentAvailableMovesTakeSet("bishop", (store.currentAvailableMovesTakeGet("bishop") +1 ));
                break;
            }
            if(!currentStartLetter.equals("E")){
                break;
            }
            bishopLayer[newStartX][newStartY] = "1";
            store.currentAvailableMovesSet("bishop", (store.currentAvailableMovesGet("bishop") +1 ));
        }


        bishopLayer[StartX][StartY] = "3";
        if(store.debugGet() == 1) {
            System.out.println("BishopLayer Output:\n");
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (bishopLayer[i][a]);
                }
                System.out.println(line);
            }
        }
        return bishopLayer;
    }
}

