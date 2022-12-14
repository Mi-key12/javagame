
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import object.OBJ_Pizza;
import object.OBJ_Pencil;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage pizzaImage;
    BufferedImage pencilImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public boolean gameOver = false;
    
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Pizza pizza = new OBJ_Pizza();
        OBJ_Pencil pencil = new OBJ_Pencil();
        pizzaImage = pizza.image;
        pencilImage = pencil.image;
    }
    
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics g2) {
        if (gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x;
            int y;
            text = "Got your notes";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80B);
            g2.setColor(Color.blue);
            text = "Congratulations";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);
            
            gp.gameThread = null;
            
        }
        else if (gameOver) {
            g2.setFont(arial_80B);
            g2.setColor(Color.black);
            String text;
            int textLength;
            int x;
            int y;
            text = "Game Over";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);
            gp.gameThread = null;
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(pizzaImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.lives , 76, 63);
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
            if (gp.player.hasPencil) {
                g2.drawImage(pencilImage, gp.tileSize/2, (gp.tileSize/2) + 45, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
