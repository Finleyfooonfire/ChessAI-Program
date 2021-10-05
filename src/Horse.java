import java.util.ArrayList;
public class Horse extends Piece {

    public String[][] horseMain(String[][] array, int StartX , int StartY, Storage store){
        String firstHalf = array[StartX][StartY].substring(0,2);
        String[][] boardOutput = array;
        if(firstHalf.equals("WH")){         //remember that only pawns can be passed please, you absolute pillock
            //System.out.println("There is a white horse here");
            boardOutput = layerSystemHorse(array, StartX, StartY, store);

        } else if (firstHalf.equals("BH")){

            //System.out.println("Black horse here");
            boardOutput = layerSystemHorse(array, StartX, StartY, store);
        }
        return boardOutput;

    }

    public static String[][] layerSystemHorse(String[][] array, int StartX, int StartY, Storage store){

        String[][] horseLayer = {     //This is the base "mesh" used to layer the piece movements
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

        //needs to check different moves in different areas
        Integer[][] moves = {
                {-2,-1},
                {-2,1},
                {-1,2},
                {1,2},
                {2,1},
                {2,-1},
                {-1,-2},
                {1,-2},};

        for(int i = 0; moves.length > i; i++){
            int newStartX = StartX + moves[i][0];
            int newStartY = StartY + moves[i][1];
            if(newStartX > 7 || newStartX < 0 || newStartY > 7 || newStartY < 0){   //boundary checking to make sure that it is wihtin range
                continue;
            }else{
                String arrayLetter = "" + array[newStartX][newStartY].charAt(0);
                if(arrayLetter.equals(letter)){
                    horseLayer[newStartX][newStartY] = "2";
                    store.currentAvailableMovesTakeSet("horse", (store.currentAvailableMovesTakeGet("horse") +1 ));
                }if(arrayLetter.equals("E")){
                    horseLayer[newStartX][newStartY] = "1";
                    store.currentAvailableMovesSet("horse", (store.currentAvailableMovesGet("horse") +1 ));
                }
            }
        }

        horseLayer[StartX][StartY] = "3";
        if(store.debugGet() == 1) {
            for (int i = 0; i < 8; i++) {
                String line = "";
                for (int a = 0; a < 8; a++) {
                    line = line + " " + (horseLayer[i][a]);
                }
                System.out.println(line);
            }
        }
        return horseLayer;
    }
}
