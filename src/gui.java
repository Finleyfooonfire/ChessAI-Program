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

public class gui {

    public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;
    public static void render() throws IOException {
        BufferedImage all = ImageIO.read(new File("E:\\School programs\\Chess Testing\\chess.png"));
        Image imgs[] = new Image[12];   //An image list that is holding all of the images to the pieces on the board
        int ind = 0;
        for(int y=0; y < 400; y += 200){
            for(int x=0; x < 1200; x +=200){
                imgs[ind]=all.getSubimage(x,y,200,200).getScaledInstance(64,64,BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }


        Piece BC1=new Piece(0, 7, false, "castle", ps);
        Piece BH1=new Piece(1, 7, false, "horse", ps);
        Piece BB1=new Piece(2, 7, false, "bishop", ps);
        Piece BQ1=new Piece(3, 7, false, "queen", ps);
        Piece BK1=new Piece(4, 7, false, "king", ps);
        Piece BB2=new Piece(5, 7, false, "bishop", ps);
        Piece BH2=new Piece(6, 7, false, "horse", ps);
        Piece BC2=new Piece(7, 7, false, "castle", ps);
        Piece BP1=new Piece(1, 6, false, "pawn", ps);
        Piece BP2=new Piece(2, 6, false, "pawn", ps);
        Piece BP3=new Piece(3, 6, false, "pawn", ps);
        Piece BP4=new Piece(4, 6, false, "pawn", ps);
        Piece BP5=new Piece(5, 6, false, "pawn", ps);
        Piece BP6=new Piece(6, 6, false, "pawn", ps);
        Piece BP7=new Piece(7, 6, false, "pawn", ps);
        Piece BP8=new Piece(0, 6, false, "pawn", ps);

        Piece WC1=new Piece(0, 0, true, "castle", ps);
        Piece WH1=new Piece(1, 0, true, "horse", ps);
        Piece WB1=new Piece(2, 0, true, "bishop", ps);
        Piece WQ1=new Piece(3, 0, true, "queen", ps);
        Piece WK1=new Piece(4, 0, true, "king", ps);
        Piece WB2=new Piece(5, 0, true, "bishop", ps);
        Piece WH2=new Piece(6, 0, true, "horse", ps);
        Piece WC2=new Piece(7, 0, true, "castle", ps);
        Piece WP1=new Piece(1, 1, true, "pawn", ps);
        Piece WP2=new Piece(2, 1, true, "pawn", ps);
        Piece WP3=new Piece(3, 1, true, "pawn", ps);
        Piece WP4=new Piece(4, 1, true, "pawn", ps);
        Piece WP5=new Piece(5, 1, true, "pawn", ps);
        Piece WP6=new Piece(6, 1, true, "pawn", ps);
        Piece WP7=new Piece(7, 1, true, "pawn", ps);
        Piece WP8=new Piece(0, 1, true, "pawn", ps);


        JFrame frame = new JFrame();
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

                selectedPiece = Objects.requireNonNull(getPiece(e.getX(), e.getY()));

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX()/64,e.getY()/64);
                frame.repaint();
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
