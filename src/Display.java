import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;

public class Display extends JFrame implements MouseListener {
    private Game game;
    JPanel chess_board = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    ArrayList<Spot> spots = new ArrayList<>();
    int expectedCellSize;
    ImageIcon brownIcon;
    ImageIcon whiteIcon;
    ImageIcon movementDot;
    Color highlight;
    int expectedPieceSize;
    int clickCounter = 0;
    int chessBoardSize;
    Piece currentPiece;

    public Display(Game game) {
        chess_board.addMouseListener(this);
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(123,58,30));
        this.game = game;

        chessBoardSize = Math.min(getWidth(),getHeight()) - 200;
        expectedCellSize = chessBoardSize / 8;
        expectedPieceSize = chessBoardSize / 10;

        highlight = new Color(255,255,0);

        movementDot = new ImageIcon(
                new ImageIcon(getClass().getResource("/MovementDot.png")).getImage()
                        .getScaledInstance(expectedCellSize,expectedCellSize,Image.SCALE_SMOOTH)
        );

        brownIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/BrownTile.png")).getImage()
                        .getScaledInstance(expectedCellSize,expectedCellSize,Image.SCALE_SMOOTH)
        );

        whiteIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/WhiteTile.png")).getImage()
                        .getScaledInstance(expectedCellSize,expectedCellSize,Image.SCALE_SMOOTH)
        );


        setResizable(false);
    }


    public void clear() {
        this.getContentPane().removeAll();
    }
    public void drawBoard(ChessBoard board) {
        this.clear();
        chess_board.removeAll();

        ImageIcon settings = new ImageIcon(
                new ImageIcon(getClass().getResource("/SettingsIcon.png")).getImage()
                        .getScaledInstance(expectedCellSize,expectedCellSize,Image.SCALE_SMOOTH)
        );

        //add in the settings button to the top right as well as logic
        int heightPadding = (getHeight() - chessBoardSize) / 2 - 20;
        int padding = (getWidth() - chessBoardSize) / 2;
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton user_settings = new JButton(settings);
        topPanel.add(user_settings, BorderLayout.EAST);
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(padding, heightPadding));
        this.add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(padding, padding));
        this.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(padding, padding));
        this.add(rightPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(padding, heightPadding));
        this.add(bottomPanel, BorderLayout.SOUTH);



        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Create a square panel with OverlayLayout (stack children)
                JPanel squarePanel = new JPanel();
                squarePanel.setLayout(new OverlayLayout(squarePanel));
                squarePanel.setPreferredSize(new Dimension(expectedCellSize, expectedCellSize));

                // Tile label
                JLabel tileLabel = new JLabel();
                tileLabel.setIcon((row + col) % 2 == 0 ? brownIcon : whiteIcon);
                tileLabel.setAlignmentX(0.5f);
                tileLabel.setAlignmentY(0.5f);

                JLabel movementLabel = new JLabel();
                movementLabel.setIcon(movementDot);
                movementLabel.setAlignmentX(0.5f);
                movementLabel.setAlignmentY(0.5f);

                // Piece label if any
                JLabel pieceLabel = new JLabel();
                Piece p = board.getSpot(row, col).getPiece();
                if (p != null) {
                    pieceLabel = p.draw(p.isBlack(), expectedPieceSize);
                    pieceLabel.setAlignmentX(0.5f);
                    pieceLabel.setAlignmentY(0.5f);
                }

                squarePanel.add(pieceLabel);

                if(clickCounter == 0 && spots != null) {
                    for(int i = 0; i < spots.size(); i++) {
                        Spot validSpot = board.getSpot(spots.get(i).getX(), spots.get(i).getY());
                        if(col == validSpot.getY() && row == validSpot.getX()) {
                            squarePanel.add(movementLabel);
                        }
                    }
                }


                if(game.currentPlayer != null && game.currentPlayer.isInCheck()) {
                    if(game.currentPlayer.isBlackSide()) {
                        Spot checkSpot = board.findSpotOnBoard(board.pieceListBlack.get(board.getKing(board.pieceListBlack)));
                        if(checkSpot.getX() == row && checkSpot.getY() == col) {
                            JPanel check = new JPanel();
                            check.setPreferredSize(new Dimension(expectedCellSize, expectedCellSize));
                            check.setOpaque(true);
                            check.setBackground(new Color(255, 0, 0));
                            check.repaint();

                            squarePanel.add(check);
                        }
                    }
                    else if(!game.currentPlayer.isBlackSide()) {
                        Spot checkSpot = board.findSpotOnBoard(board.pieceListWhite.get(board.getKing(board.pieceListWhite)));
                        if(checkSpot.getX() == row && checkSpot.getY() == col) {
                            JPanel check = new JPanel();
                            check.setPreferredSize(new Dimension(expectedCellSize, expectedCellSize));
                            check.setOpaque(true);
                            check.setBackground(new Color(255, 0, 0));
                            check.repaint();

                            squarePanel.add(check);
                        }
                    }
                }


                // Add tile first, then piece (piece is on top)
                if(clickCounter == 0 && p!= null && p.equals(currentPiece)) {
                    JPanel highlighter = new JPanel();
                    highlighter.setPreferredSize(new Dimension(expectedCellSize, expectedCellSize));
                    highlighter.setOpaque(true);

                    JLabel highlight = new JLabel();
                    highlight.setBackground(this.highlight);
                    highlight.repaint();
                    squarePanel.add(highlight);

                }
                else {
                    squarePanel.add(tileLabel);
                }

                gbc.gridx = row;
                gbc.gridy = col;
                chess_board.add(squarePanel, gbc);
            }
        }
        chess_board.setPreferredSize(new Dimension(chessBoardSize,chessBoardSize));
        chess_board.setSize(new Dimension(chessBoardSize,chessBoardSize));
        chess_board.setOpaque(true);
        this.add(chess_board);



        user_settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().repaint();

                JPanel settingsPanel = new JPanel(new BorderLayout());
                settingsPanel.setBackground(new Color(123, 58, 30));

                JLabel settingsText = new JLabel("Settings:");
                settingsText.setFont(new Font("Arial", Font.BOLD, 50));
                settingsText.setHorizontalAlignment(SwingConstants.CENTER);
                settingsPanel.add(settingsText, BorderLayout.NORTH);

                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                centerPanel.setOpaque(false);
                centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                String[] sizes = {
                        "800 x 600",
                        "1024 x 768",
                        "1280 x 720",
                        "1920 x 1080",
                        "2560 x 1440"
                };

                JComboBox<String> screenSizes = new JComboBox<>(sizes);
                screenSizes.setMaximumSize(new Dimension(150, 30)); // restrict width

                JLabel currentSize = new JLabel("Screen Size is currently: " + sizes[2]);
                currentSize.setFont(new Font("Arial", Font.BOLD, 24));
                currentSize.setAlignmentX(Component.CENTER_ALIGNMENT);

                centerPanel.add(Box.createVerticalStrut(20));
                centerPanel.add(currentSize);
                centerPanel.add(Box.createVerticalStrut(10));
                centerPanel.add(screenSizes);

                settingsPanel.add(centerPanel, BorderLayout.CENTER);

                JButton exit = new JButton("Back To Game");
                JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                bottomPanel.setOpaque(false);
                bottomPanel.add(exit);
                settingsPanel.add(bottomPanel, BorderLayout.SOUTH);

                getContentPane().add(settingsPanel);
                revalidate();
                repaint();

                screenSizes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String select = (String) screenSizes.getSelectedItem();
                        currentSize.setText("Screen Size is currently: " + select);
                        String[] windowSizes = select.split(" x ");
                        setSize(Integer.parseInt(windowSizes[0]), Integer.parseInt(windowSizes[1]));
                    }
                });

                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getContentPane().removeAll();
                        drawBoard(board);
                    }
                });
            }
        });


        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(clickCounter % 2 == 0) {
            int startSpotx = e.getX() / expectedCellSize;
            int startSpoty = e.getY() / expectedCellSize;
            spots = game.HandlePress(startSpotx,startSpoty);
            currentPiece = game.board.getSpot(startSpotx, startSpoty).getPiece();
            drawBoard(game.board);
            clickCounter++;
        }
        else {
            int endSpotx = e.getX() / expectedCellSize;
            int endSpoty = e.getY() / expectedCellSize;
            game.HandleRelease(endSpotx, endSpoty);
            drawBoard(game.board);
            clickCounter = 0;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}