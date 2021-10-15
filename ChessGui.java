import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChessGui extends JFrame {

    
    public static void main(String[] args) {
        new ChessGui().run();
    }
    
    
    
    private JFrame f;
    private Board jboard;
    final JPanel statusPanel;

    public ChessGui() {
        this.f = new JFrame("Chess");
        this.f.setLayout(new BorderLayout());

        this.jboard = new Board();
        this.f.add(this.jboard, BorderLayout.CENTER);

        this.f.setSize(600, 600);
        this.f.setVisible(true);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.statusPanel = new JPanel();
        JButton instructions = new JButton("Instructions");
        this.statusPanel.add(instructions);
        f.add(statusPanel, BorderLayout.SOUTH);
        f.validate();

        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome to Chess!\n"
                        + "Pieces move the same as in traditional chess.  To move a piece\n"
                        + "click on the piece you want to move and the spot you want to \n"
                        + "move it to.  Only legal moves will be allowed.  Turns alternate\n"
                        + "starting with white, and a valid move must be made each turn.\n"
                        + "The goal is to put your opponent in checkmate.\n\nGood Luck!");
            }
        });

    }

    public void promotionSelection(boolean white) {
        JButton queen, rook, knight, bishop;
        if (white) {
            queen = new JButton(Spot.wqueen);
            rook = new JButton(Spot.wrook);
            knight = new JButton(Spot.wknight);
            bishop = new JButton(Spot.wbishop);
            queen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Queen(true));
                }
            });
            rook.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Rook(true));
                }
            });
            knight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Knight(true));
                }
            });
            bishop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Bishop(true));
                }
            });
        } else {
            queen = new JButton(Spot.bqueen);
            rook = new JButton(Spot.brook);
            knight = new JButton(Spot.bknight);
            bishop = new JButton(Spot.bbishop);
            queen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Queen(false));
                }
            });
            rook.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Rook(false));
                }
            });
            knight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Knight(false));
                }
            });
            bishop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jboard.promotePawn(new Bishop(false));
                }
            });
        }
        JButton[] options = new JButton[] { queen, rook, knight, bishop };
        JOptionPane.showOptionDialog(null, "Select piece to replace pawn", "Promotion", JOptionPane.CANCEL_OPTION, 1,
                null, options, queen);
    }

    public void run() {
        while (!jboard.gameover()) {
            jboard.update();
            if (jboard.pawnToPromote()) {
                if (jboard.getPawnColor()) {
                    this.promotionSelection(true);
                } else {
                    this.promotionSelection(false);
                }
            }
        }
        if (jboard.gameover()) {
            if (jboard.checkmate(true)) {
                JOptionPane.showMessageDialog(null, "Checkmate\nBlack Wins!");
            }
            if (jboard.checkmate(false)) {
                JOptionPane.showMessageDialog(null, "Checkmate\nWhite Wins!");
            }
            if (jboard.stalemate(true)) {
                JOptionPane.showMessageDialog(null, "White in Stalemate\nIt is a tie");
            }
            if (jboard.stalemate(false)) {
                JOptionPane.showMessageDialog(null, "Black in Stalemate\nIt is a tie");
            }
        }

    }

}