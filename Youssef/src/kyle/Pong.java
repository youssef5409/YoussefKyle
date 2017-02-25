/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kyle;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sun.applet.Main;

/**
 *
 * @author kyle
 */
public class Pong extends JPanel implements KeyListener {

    private ArrayList<Integer> keysDown;
    //frame
    static JFrame myFrame = new JFrame("Pong");
    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    //variables
    int x = getWidth() / 2;
    int y = getHeight() / 2;
    int rectX = 100;
    int rect2X = 900;
    int rect2Y;
    int rectY;
    int speed = 5;
    int paddleSpeed = 5;
    int border = 30;
    int p1Score = 0;
    int p2Score = 0;
    int pongCounter = 0;
    boolean goal = false;
    boolean ballGoingDown = true;
    boolean ballGoingRight = true;
    boolean leftPaddleGoingUp = false;
    boolean leftPaddleGoingDown = false;
    boolean rightPaddleGoingUp = false;
    boolean rightPaddleGoingDown = false;
    boolean stop = false;

    private void moveBall() throws InterruptedException {
        if (stop == true) {
            if (p1Score >= 7) {
                JOptionPane.showMessageDialog(null, "Player One Wins!", "Results", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Player Two Wins", "Results", JOptionPane.ERROR_MESSAGE);
            }
            p1Score = 0;
            p2Score = 0;
            stop = false;

        } else {
            if (goal == true) {
                x = getWidth() / 2;
                y = getHeight() / 2;
                rectY = getHeight() / 2;
                rect2Y = getHeight() / 2;
                Thread.sleep(1000);
            }
            if (leftPaddleGoingUp == true) {
                rectY -= paddleSpeed;
            }
            if (leftPaddleGoingDown == true) {
                rectY += paddleSpeed;
            }
            if (rightPaddleGoingUp == true) {
                rect2Y -= paddleSpeed;
            }
            if (rightPaddleGoingDown == true) {
                rect2Y += paddleSpeed;
            }
            if (ballGoingDown == true) {
                //go down one pixel at a time
                y += speed;
            } else if (ballGoingDown == false) {
                //go up one pixel at a time
                y -= speed;
            }
            if (ballGoingRight == true) {
                //go right one pixel at a time
                x += speed;
            } else if (ballGoingRight == false) {
                //go left one pixel at a time
                x -= speed;
            }

            //check if ball is at bottom of screen
            if (y >= getHeight() - border) {
                ballGoingDown = false;
            }
            //check if ball is at top of the screen
            if (y <= 0) {
                ballGoingDown = true;
            }
            //check if ball is at right of screen
            if (x >= getWidth() - border) {
                try {
                    playSound("pong.wav");
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                ballGoingRight = false;
                goal = true;
                p1Score++;
            } else if (x <= 0) {  //check if ball is at left of screen
                try {
                    playSound("pong.wav");
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                ballGoingRight = true;
                goal = true;
                p2Score++;
            } else {
                goal = false;
            }
            //check if ball is touching left paddle
            if (x >= rectX + 25 && x <= rectX + 30 && y >= rectY - 30 && y <= rectY + 80) {
                ballGoingRight = true;
                pongCounter++;
                if (pongCounter == 1) {
                    try {
                        playSound("pong_2.wav");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pongCounter = 0;
                } else {
                    try {
                        playSound("pong_3.wav");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            //check if ball is touching right paddle
            if (x >= rect2X - 30 && x <= rect2X - 25 && y >= rect2Y - 30 && y <= rect2Y + 80) {
                ballGoingRight = false;
                pongCounter++;
                if (pongCounter == 1) {
                    try {
                        playSound("pong_2.wav");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pongCounter = 0;
                } else {
                    try {
                        playSound("pong_3.wav");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            //check if a player has won
            if (p1Score >= 7 || p2Score >= 7) {
                stop = true;
            } else {
                stop = false;
            }
            if (rectY < 0) {
                rectY = 0;
            } else if (rectY > 638) {
                rectY = 638;
            }
            if (rect2Y < 0) {
                rect2Y = 0;
            } else if (rect2Y > 638) {
                rect2Y = 638;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        keysDown = new ArrayList<Integer>();
        myFrame.addKeyListener(this);
        g.fillOval(x, y, 30, 30);
        g.fillRect(rectX, rectY, 30, 100);
        g.fillRect(rect2X, rect2Y, 30, 100);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("First to 7 Wins", 450, 30);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Player One: " + p1Score + "       Player Two: " + p2Score, 300, 75);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        //window code
        myFrame.setSize(1024, 738);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocation(dim.width / 2 - myFrame.getSize().width / 2, dim.height / 2 - myFrame.getSize().height / 2);
        //game code
        Pong game = new Pong();
        myFrame.add(game);
        JOptionPane.showMessageDialog(myFrame, "Start", "Commence", JOptionPane.ERROR_MESSAGE);

        while (true) {
            game.moveBall();
            game.repaint();
            Thread.sleep(10);
        }

    }

    //for importing sounds
    public static void playSound(String fileName) throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException {
        File url = new File(fileName);
        Clip clip = AudioSystem.getClip();

        AudioInputStream ais = AudioSystem.
                getAudioInputStream(url);
        clip.open(ais);
        clip.start();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (!keysDown.contains(ke.getKeyCode())) {
            keysDown.add(new Integer(ke.getKeyCode()));
        }
        for (Integer i : keysDown) {
            if (i == 87) {
                leftPaddleGoingUp = true;
            }
            if (i == 83) {
                leftPaddleGoingDown = true;
            }
            if (i == 38) {
                rightPaddleGoingUp = true;
            }
            if (i == 40) {
                rightPaddleGoingDown = true;
            }
        }
        //w = 87
        //s = 83
        //up = 38
        //down = 40

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == 87) {
            leftPaddleGoingUp = false;
        }
        if (ke.getKeyCode() == 83) {
            leftPaddleGoingDown = false;
        }
        if (ke.getKeyCode() == 38) {
            rightPaddleGoingUp = false;
        }
        if (ke.getKeyCode() == 40) {
            rightPaddleGoingDown = false;
        }
        keysDown.remove(new Integer(ke.getKeyCode()));
    }

}
