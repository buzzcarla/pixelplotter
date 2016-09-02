/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelplotter;

/**
 *
 * @author cberdin
 */
import static pixelplotter.ColorGrid.globalStatus;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class MyMouseListener extends MouseAdapter {
   private ColorGrid colorGrid;

   public MyMouseListener(ColorGrid colorGrid) {
      this.colorGrid = colorGrid;
   }

   @Override
   public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
         colorGrid.labelPressed((JLabel)e.getSource(),0); //left
      } else if (e.getButton() == MouseEvent.BUTTON3) {
         colorGrid.labelPressed((JLabel)e.getSource(),1);
      }
   }
   
   @Override
   public void mouseEntered(MouseEvent e)
   {
       colorGrid.labelHovered((JLabel)e.getSource());
   }
}
