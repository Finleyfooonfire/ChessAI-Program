import java.util.LinkedList;

public class Piece {

    //all the following things are to do with the GUI, this does not change the operating of the program or how it works
    int xp; //this is the x coordinate on the board
    int yp; //this is the y coordinate on the board

    int x;  //this is the actual x and y
    int y;

    boolean isWhite;
    LinkedList<Piece> ps;
    String name; //this is the type of piece
    public Piece(int xp, int yp, boolean isWhite, String n, LinkedList<Piece> ps){

        this.xp = xp;
        this.yp = yp;

        x=xp*64;
        y=yp*64;
        this.isWhite = isWhite;
        this.ps=ps;
        name=n;
        ps.add(this);
    }

    public String pieceDetails(){
        return(gui.getPiece(xp*64, yp*64).name + ","+xp+","+yp);
    }

    public String move(int xp, int yp){
        if(gui.getPiece(xp*64, yp*64)!=null){
            if(gui.getPiece(xp*64, yp*64).isWhite!=isWhite){
                gui.getPiece(xp*64,yp*64).kill();       //kills the piece if there is already something there
            }else{
                x=this.xp*64;
                y=this.yp*64;
                return "";
            }


        }

        this.xp=xp;
        this.yp=yp;
        x=xp*64;
        y=yp*64;


        return(gui.getPiece(xp*64, yp*64).name + ","+xp+","+yp);
    }
    public void kill(){
        ps.remove(this);
    }


}
