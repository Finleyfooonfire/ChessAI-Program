import java.util.ArrayList;
import java.util.Arrays;


public class Storage {

         //i am doing this as the ArrayList cannot be updated or changed just in a class, it needs to be in a method
        ArrayList<String> amountOfWhiteCastle = new ArrayList<>(Arrays.asList("WC1","WC2"));
        ArrayList<String> amountOfWhitePawn = new ArrayList<>(Arrays.asList("WP1","WP2","WP3","WP4","WP5","WP6","WP7","WP8"));
        ArrayList<String> amountOfWhiteBishop = new ArrayList<>(Arrays.asList("WB1","WB2"));
        ArrayList<String> amountOfWhiteHorse = new ArrayList<>(Arrays.asList("WH1","WH2"));
        ArrayList<String> amountOfWhiteQueen = new ArrayList<>(Arrays.asList("WQ1"));
        ArrayList<String> amountOfWhiteKing = new ArrayList<>(Arrays.asList("WK1"));

        //this is now the black pieces

        ArrayList<String> amountOfBlackCastle = new ArrayList<>(Arrays.asList("BC1","BC2"));
        ArrayList<String> amountOfBlackPawn = new ArrayList<>(Arrays.asList("BP1","BP2","BP3","BP4","BP5","BP6","BP7","BP8"));
        ArrayList<String> amountOfBlackBishop = new ArrayList<>(Arrays.asList("BB1","BB2"));
        ArrayList<String> amountOfBlackHorse = new ArrayList<>(Arrays.asList("BH1","BH2"));
        ArrayList<String> amountOfBlackQueen = new ArrayList<>(Arrays.asList("BQ1"));
        ArrayList<String> amountOfBlackKing = new ArrayList<>(Arrays.asList("BK1"));


    int debug = 0;

    int castlePieceScore = 4;
    int pawnPieceScore = 1;
    int bishopPieceScore= 5;
    int horsePieceScore = 6;
    int queenPieceScore = 10;
    int kingPieceScore = 10000;


    int currentAvailableMovesCastle = 0;
    int currentAvailableMovesPawn = 0;
    int currentAvailableMovesBishop = 0;
    int currentAvailableMovesHorse = 0;
    int currentAvailableMovesQueen = 0;
    int currentAvailableMovesKing = 0;

    int currentAvailableMovesCastleTake = 0;
    int currentAvailableMovesPawnTake = 0;
    int currentAvailableMovesBishopTake = 0;
    int currentAvailableMovesHorseTake = 0;
    int currentAvailableMovesQueenTake = 0;
    int currentAvailableMovesKingTake = 0;

    //This is now all of the current positions of pieces on the board

    String currentPositionOfWP1 = "10";
    String currentPositionOfWP2 = "11";
    String currentPositionOfWP3 = "12";
    String currentPositionOfWP4 = "13";
    String currentPositionOfWP5 = "14";
    String currentPositionOfWP6 = "15";
    String currentPositionOfWP7 = "16";
    String currentPositionOfWP8 = "17";
    String currentPositionOfWC1 = "00";
    String currentPositionOfWC2 = "07";
    String currentPositionOfWH1 = "01";
    String currentPositionOfWH2 = "06";
    String currentPositionOfWB1 = "02";
    String currentPositionOfWB2 = "05";
    String currentPositionOfWQ1 = "04";
    String currentPositionOfWK1 = "03";

    String currentPositionOfBP1 = "60";
    String currentPositionOfBP2 = "61";
    String currentPositionOfBP3 = "62";
    String currentPositionOfBP4 = "63";
    String currentPositionOfBP5 = "64";
    String currentPositionOfBP6 = "65";
    String currentPositionOfBP7 = "66";
    String currentPositionOfBP8 = "67";
    String currentPositionOfBC1 = "70";
    String currentPositionOfBC2 = "77";
    String currentPositionOfBH1 = "71";
    String currentPositionOfBH2 = "76";
    String currentPositionOfBB1 = "72";
    String currentPositionOfBB2 = "75";
    String currentPositionOfBQ1 = "74";
    String currentPositionOfBK1 = "73";

    Boolean randomPersonActive= false;
    int inputRecieved = 0;

    //this is now the stuff for previous and changing moves
    String previousPosition = "00";
    String currentPosition = "00";


    //this is going to be the lists that have the pawns which have not moved

    ArrayList<String> pawnsNotMoved = new ArrayList<>(Arrays.asList("BP1","BP2","BP3","BP4","BP5","BP6","BP7","BP8","WP1","WP2","WP3","WP4","WP5","WP6","WP7","WP8"));
    //This the array that is holding all the data on the pawns moved. If it is in the list then that pawn hasnt been moved.

    String [][] spaceOccupation = {     //this is used to save the space occupations
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0"},
    };

    public int debugGet(){
        return debug;
    }

    public void pieceScoreSet(String piece, int value){
        switch (piece) {
            case "castle" -> castlePieceScore = value;
            case "pawn" -> pawnPieceScore = value;
            case "bishop" -> bishopPieceScore = value;
            case "horse" -> horsePieceScore = value;
            case "queen" -> queenPieceScore = value;
            case "king" -> kingPieceScore = value;
        }
    }

    public int pieceScoreGet(String piece){
        return switch (piece) {
            case "castle" -> castlePieceScore;
            case "pawn" -> pawnPieceScore;
            case "bishop" -> bishopPieceScore;
            case "horse" -> horsePieceScore;
            case "queen" -> queenPieceScore;
            case "king" -> kingPieceScore;
            default -> 0;
        };


    }

    //This is the start of the current white pieces that are on the board setter and getter methods
    public ArrayList<String> currentWhitePiecesGet(String piece){     //This gets the details on how many pieces are on the board at once
        ArrayList<String> nothing = new ArrayList<>(Arrays.asList("nothing"));
        return switch (piece) {
            case "castle" -> amountOfWhiteCastle;
            case "pawn" -> amountOfWhitePawn;
            case "bishop" -> amountOfWhiteBishop;
            case "horse" -> amountOfWhiteHorse;
            case "queen" -> amountOfWhiteQueen;
            case "king" -> amountOfWhiteKing;
            default -> nothing;
        };
    }
    public void currentWhitePiecesSet(String piece, ArrayList<String> value){     //This is the method that is setting the value of how many whites are on the board
        switch (piece) {                                    //I was wondering why these arent used and its beceause i modify the actual white piece arrays, not the setter methods
            case "castle" -> amountOfWhiteCastle = value;
            case "pawn" -> amountOfWhitePawn = value;
            case "bishop" -> amountOfWhiteBishop = value;
            case "horse" -> amountOfWhiteHorse = value;
            case "queen" -> amountOfWhiteQueen = value;
            case "king" -> amountOfWhiteKing = value;
        }
    }
    //This is the getter and setter method for the amount of black pieces currently on the board of each class
    public ArrayList<String> currentBlackPiecesGet(String piece){     //This gets the details on how many pieces are on the board at once
        ArrayList<String> nothing = new ArrayList<>(Arrays.asList("nothing"));
        return switch (piece) {
            case "castle" -> amountOfBlackCastle;
            case "pawn" -> amountOfBlackPawn;
            case "bishop" -> amountOfBlackBishop;
            case "horse" -> amountOfBlackHorse;
            case "queen" -> amountOfBlackQueen;
            case "king" -> amountOfBlackKing;
            default -> nothing;
        };
    }
    public void currentBlackPiecesSet(String piece, ArrayList<String> value){     //This is the method that is setting the value of how many whites are on the board
        switch (piece) {
            case "castle" -> amountOfBlackCastle = value;
            case "pawn" -> amountOfBlackPawn = value;
            case "bishop" -> amountOfBlackBishop = value;
            case "horse" -> amountOfBlackHorse = value;
            case "queen" -> amountOfBlackQueen = value;
            case "king" -> amountOfBlackKing = value;
        }
    }

    //The next part is a little weird, it is for the amount of places on the movement layer after one instance. I just realised i needed
    //it so im adding it, quite innefficent i know but there is a reference issue with static objects that i cant be bothered getting around

    public int currentAvailableMovesGet(String piece){
        return switch(piece){
            case "castle" -> currentAvailableMovesCastle;
            case "pawn" -> currentAvailableMovesPawn;
            case "bishop" -> currentAvailableMovesBishop;
            case "horse" -> currentAvailableMovesHorse;
            case "queen" -> currentAvailableMovesQueen;
            case "king" -> currentAvailableMovesKing;
            default -> 0;
        };
    }

    public void currentAvailableMovesSet(String piece, int value){     //This is the method that is setting the value of how many whites are on the board
        switch (piece) {
            case "castle" -> currentAvailableMovesCastle = value;
            case "pawn" -> currentAvailableMovesPawn = value;
            case "bishop" -> currentAvailableMovesBishop = value;
            case "horse" -> currentAvailableMovesHorse = value;
            case "queen" -> currentAvailableMovesQueen = value;
            case "king" -> currentAvailableMovesKing = value;
        }
    }

    //this is the part of the current pieces which are the takeable pieces, these are the ones that can be taken

    public int currentAvailableMovesTakeGet(String piece){
        return switch(piece){
            case "castle" -> currentAvailableMovesCastleTake;
            case "pawn" -> currentAvailableMovesPawnTake;
            case "bishop" -> currentAvailableMovesBishopTake;
            case "horse" -> currentAvailableMovesHorseTake;
            case "queen" -> currentAvailableMovesQueenTake;
            case "king" -> currentAvailableMovesKingTake;
            default -> 0;
        };
    }

    public void currentAvailableMovesTakeSet(String piece, int value){     //This is the setter method for the takeable pieces
        switch (piece) {
            case "castle" -> currentAvailableMovesCastleTake = value;
            case "pawn" -> currentAvailableMovesPawnTake = value;
            case "bishop" -> currentAvailableMovesBishopTake = value;
            case "horse" -> currentAvailableMovesHorseTake = value;
            case "queen" -> currentAvailableMovesQueenTake = value;
            case "king" -> currentAvailableMovesKingTake = value;
        }
    }

    public ArrayList<String> currentPawnsAllowedMoveGet(){
        return(pawnsNotMoved);
    }
    /*Once again, ive thought of something else that is going to be needed, this is going to be the positions of all of the pieces.
    Let me explain, there need to be positions of all of the pieces as this means in the long run it will save a lot of time as having to
    search for these pieces would take a long time as it is a n^3 operation. Therefore just having them in getter and setter methods
    makes life a lot easier and more organised.
    */

    public String currentPiecePositionsGet(String piece){
        return switch(piece){
            case "WP1" -> currentPositionOfWP1;
            case "WP2" -> currentPositionOfWP2;
            case "WP3" -> currentPositionOfWP3;
            case "WP4" -> currentPositionOfWP4;
            case "WP5" -> currentPositionOfWP5;
            case "WP6" -> currentPositionOfWP6;
            case "WP7" -> currentPositionOfWP7;
            case "WP8" -> currentPositionOfWP8;
            case "WC1" -> currentPositionOfWC1;
            case "WC2" -> currentPositionOfWC2;
            case "WH1" -> currentPositionOfWH1;
            case "WH2" -> currentPositionOfWH2;
            case "WB1" -> currentPositionOfWB1;
            case "WB2" -> currentPositionOfWB2;
            case "WK1" -> currentPositionOfWK1;
            case "WQ1" -> currentPositionOfWQ1;

            case "BP1" -> currentPositionOfBP1;
            case "BP2" -> currentPositionOfBP2;
            case "BP3" -> currentPositionOfBP3;
            case "BP4" -> currentPositionOfBP4;
            case "BP5" -> currentPositionOfBP5;
            case "BP6" -> currentPositionOfBP6;
            case "BP7" -> currentPositionOfBP7;
            case "BP8" -> currentPositionOfBP8;
            case "BC1" -> currentPositionOfBC1;
            case "BC2" -> currentPositionOfBC2;
            case "BH1" -> currentPositionOfBH1;
            case "BH2" -> currentPositionOfBH2;
            case "BB1" -> currentPositionOfBB1;
            case "BB2" -> currentPositionOfBB2;
            case "BK1" -> currentPositionOfBK1;
            case "BQ1" -> currentPositionOfBQ1;
            default -> "";
        };
    }
    public void currentPiecePositionsSet(String piece, String value){     //This is the setter method for the pieces current positions
        // The black pieces
        switch (piece) {
            case "WP1" -> currentPositionOfWP1 = value;
            case "WP2" -> currentPositionOfWP2 = value;
            case "WP3" -> currentPositionOfWP3 = value;
            case "WP4" -> currentPositionOfWP4 = value;
            case "WP5" -> currentPositionOfWP5 = value;
            case "WP6" -> currentPositionOfWP6 = value;
            case "WP7" -> currentPositionOfWP7 = value;
            case "WP8" -> currentPositionOfWP8 = value;
            case "WC1" -> currentPositionOfWC1 = value;
            case "WC2" -> currentPositionOfWC2 = value;
            case "WH1" -> currentPositionOfWH1 = value;
            case "WH2" -> currentPositionOfWH2 = value;
            case "WB1" -> currentPositionOfWB1 = value;
            case "WB2" -> currentPositionOfWB2 = value;
            case "WK1" -> currentPositionOfWK1 = value;
            case "WQ1" -> currentPositionOfWQ1 = value;


            case "BP1" -> currentPositionOfBP1 = value;
            case "BP2" -> currentPositionOfBP2 = value;
            case "BP3" -> currentPositionOfBP3 = value;
            case "BP4" -> currentPositionOfBP4 = value;
            case "BP5" -> currentPositionOfBP5 = value;
            case "BP6" -> currentPositionOfBP6 = value;
            case "BP7" -> currentPositionOfBP7 = value;
            case "BP8" -> currentPositionOfBP8 = value;
            case "BC1" -> currentPositionOfBC1 = value;
            case "BC2" -> currentPositionOfBC2 = value;
            case "BH1" -> currentPositionOfBH1 = value;
            case "BH2" -> currentPositionOfBH2 = value;
            case "BB1" -> currentPositionOfBB1 = value;
            case "BB2" -> currentPositionOfBB2 = value;
            case "BK1" -> currentPositionOfBK1 = value;
            case "BQ1" -> currentPositionOfBQ1 = value;
        }
    }

    public String[][] getSpaceOccupation(){
        return(spaceOccupation);
    }

    public void setSpaceOccupation(String[][] array){
        spaceOccupation = array;
    }

}
