import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ArrayList;

public class gui {

    public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;
    public static String pieceToMove = "";
    public static int pieceToMoveX = 0;
    public static int pieceToMoveY = 0;
    public static Piece BC1=new Piece(0, 7, false, "castle", ps);
    public static Piece BH1=new Piece(1, 7, false, "horse", ps);
    public static Piece BB1=new Piece(2, 7, false, "bishop", ps);
    public static Piece BQ1=new Piece(3, 7, false, "queen", ps);
    public static Piece BK1=new Piece(4, 7, false, "king", ps);
    public static Piece BB2=new Piece(5, 7, false, "bishop", ps);
    public static Piece BH2=new Piece(6, 7, false, "horse", ps);
    public static Piece BC2=new Piece(7, 7, false, "castle", ps);
    public static Piece BP1=new Piece(1, 6, false, "pawn", ps);
    public static  Piece BP2=new Piece(2, 6, false, "pawn", ps);
    public static Piece BP3=new Piece(3, 6, false, "pawn", ps);
    public static  Piece BP4=new Piece(4, 6, false, "pawn", ps);
    public static Piece BP5=new Piece(5, 6, false, "pawn", ps);
    public static Piece BP6=new Piece(6, 6, false, "pawn", ps);
    public static Piece BP7=new Piece(7, 6, false, "pawn", ps);
    public static Piece BP8=new Piece(0, 6, false, "pawn", ps);

    public static Piece WC1=new Piece(0, 0, true, "castle", ps);
    public static Piece WH1=new Piece(1, 0, true, "horse", ps);
    public static Piece WB1=new Piece(2, 0, true, "bishop", ps);
    public static Piece WQ1=new Piece(3, 0, true, "queen", ps);
    public static Piece WK1=new Piece(4, 0, true, "king", ps);
    public static Piece WB2=new Piece(5, 0, true, "bishop", ps);
    public static Piece WH2=new Piece(6, 0, true, "horse", ps);
    public static Piece WC2=new Piece(7, 0, true, "castle", ps);
    public static Piece WP1=new Piece(1, 1, true, "pawn", ps);
    public static Piece WP2=new Piece(2, 1, true, "pawn", ps);
    public static Piece WP3=new Piece(3, 1, true, "pawn", ps);
    public static Piece WP4=new Piece(4, 1, true, "pawn", ps);
    public static Piece WP5=new Piece(5, 1, true, "pawn", ps);
    public static Piece WP6=new Piece(6, 1, true, "pawn", ps);
    public static Piece WP7=new Piece(7, 1, true, "pawn", ps);
    public static Piece WP8=new Piece(0, 1, true, "pawn", ps);
    public static JFrame frame = new JFrame();

    public static void repaint(Storage store){


        int x;
        int y;
        String moves;
        moves = store.currentPiecePositionsGet("WC1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteCastle.contains("WC1")) {
            WC1.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WC2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteCastle.contains("WC2")) {
            WC2.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WH1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteHorse.contains("WH1")) {
            WH1.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WH2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteHorse.contains("WH2")) {
            WH2.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WB1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteBishop.contains("WB1")) {
            WB1.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WB2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteBishop.contains("WB2")) {
            WB2.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WK1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteKing.contains("WK1")) {
            WK1.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WQ1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhiteQueen.contains("WQ1")) {
            WQ1.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP1")) {
            WP1.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP2")) {
            WP2.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP3");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP3")) {
            WP3.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP4");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP4")) {
            WP4.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP5");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP5")) {
            WP5.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP6");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP6")) {
            WP6.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP7");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP7")) {
            WP7.move(y, x);
        }
        moves = store.currentPiecePositionsGet("WP8");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfWhitePawn.contains("WP8")) {
            WP8.move(y, x);
        }



        //now black pieces
        moves = store.currentPiecePositionsGet("BC1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackCastle.contains("BC1")) {
            BC1.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BC2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackCastle.contains("BC2")) {
            BC2.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BH1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackHorse.contains("BH1")) {
            BH1.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BH2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackHorse.contains("BH2")) {
            BH2.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BB1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackBishop.contains("BB1")) {
            BB1.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BB2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackBishop.contains("BB2")) {
            BB2.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BK1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackKing.contains("BK1")) {
            BK1.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BQ1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackQueen.contains("BQ1")) {
            BQ1.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BP1");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP1")) {
            BP1.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BP2");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP2")) {
            BP2.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BP3");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP3")) {
            BP3.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BP4");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP4")) {
            BP4.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BP5");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP5")) {
            BP5.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BP6");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP6")) {
            BP6.move(y, x);
        }

        moves = store.currentPiecePositionsGet("BP7");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP7")) {
            BP7.move(y, x);
        }
        moves = store.currentPiecePositionsGet("BP8");
        x = Integer.parseInt("" + moves.charAt(0));
        y = Integer.parseInt(""+ moves.charAt(1));
        if(store.amountOfBlackPawn.contains("BP8")) {
            BP8.move(y, x);
        }

        frame.repaint();
    }


    public static void render(Storage store, String[][] board) throws IOException {
        String[][] newBoard = board;

        BufferedImage all = ImageIO.read(new File("E:\\School programs\\Chess Testing\\chess.png"));
        Image imgs[] = new Image[12];   //An image list that is holding all of the images to the pieces on the board
        int ind = 0;
        for(int y=0; y < 400; y += 200){
            for(int x=0; x < 1200; x +=200){
                imgs[ind]=all.getSubimage(x,y,200,200).getScaledInstance(64,64,BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }



        int x;
        int y;
        String moves;
        //initialising



        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn =new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if(white){
                            g.setColor(new Color(235, 235, 208));
                        }else{
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x*64,y*64, 64, 64);
                        white=!white;
                    }
                    white=!white;
                }
                    for(Piece p: ps){   //this is indexing all of the pieces
                        int ind = 0;
                        if(p.name.equalsIgnoreCase("king")){
                            ind=0;
                        }
                        if(p.name.equalsIgnoreCase("queen")){
                            ind=1;
                        }
                        if(p.name.equalsIgnoreCase("bishop")){
                            ind=2;
                        }
                        if(p.name.equalsIgnoreCase("horse")){
                            ind=3;
                        }
                        if(p.name.equalsIgnoreCase("castle")){
                            ind=4;
                        }
                        if(p.name.equalsIgnoreCase("pawn")){
                            ind=5;
                        }
                        if(!p.isWhite){
                            ind+=6;
                        }
                        g.drawImage(imgs[ind], p.x, p.y, this);
                    }


            }


        };

        frame.add(pn);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(getPiece(e.getX(), e.getY()) != null && Objects.requireNonNull(getPiece(e.getX(), e.getY())).isWhite) {
                    selectedPiece = Objects.requireNonNull(getPiece(e.getX(), e.getY()));       //this is responsible for getting the piece you just clicked
                    String details = selectedPiece.pieceDetails();     //this just pulls the piece details
                    String[] parts = details.split(",");
                    String pieceName = parts[0];
                    int xCord = Integer.parseInt(parts[1]);
                    int yCord = Integer.parseInt(parts[2]);
                    System.out.println("X: " + xCord);
                    System.out.println("X: " + yCord);
                    pieceToMove = newBoard[yCord][xCord];
                    store.previousPosition = "" + yCord + xCord; //updating the piece that is moving and where it moved from



                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                String moved = selectedPiece.move(e.getX() / 64, e.getY() / 64);
                System.out.println(moved);
                String[] parts = moved.split(",");
                String pieceName = parts[0];
                pieceToMoveX = Integer.parseInt(parts[1]);
                pieceToMoveY = Integer.parseInt(parts[2]);
                store.currentPosition = "" + pieceToMoveY + pieceToMoveX; //updating the current position
                frame.repaint();
                //this is the only indication that a move has happened
                System.out.println("This happens");
                store.inputRecieved = 1;    //meaning there is input that has been recieved



            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece!=null){
                    selectedPiece.x=e.getX()-32;
                    selectedPiece.y=e.getY()-32;
                    frame.repaint();

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        //System.out.println("I get here");
        store.currentPiecePositionsSet(pieceToMove, "" +pieceToMoveY +pieceToMoveX);
    }

    public static Piece getPiece(int x, int y){
         int xp =x/64;
         int yp = y/64;
         for(Piece p: ps){
             if(p.xp==xp&&p.yp==yp){
                 return p;
             }
         }
         return null;
    }
}
