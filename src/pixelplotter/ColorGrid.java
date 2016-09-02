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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ColorGrid extends JPanel {
   private MyColor[][] myColors;
   private JLabel[][] myLabels;
   private Border whiteborder;
   public static int [][] globalStatus;
   private int flagger_mouse_click = 0; //0 if left
   private int temp_row = 0, temp_col = 0;
   private int cellWidth = 40;
   public ColorGrid(int rows, int cols) {
      myColors = new MyColor[rows][cols];
      myLabels = new JLabel[rows][cols];
      globalStatus = new int [rows][cols];
      whiteborder = BorderFactory.createLineBorder(Color.WHITE);
      MyMouseListener myListener = new MyMouseListener(this);
      Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
      setLayout(new GridLayout(rows, cols));
      for (int row = 0; row < myLabels.length; row++) {
         for (int col = 0; col < myLabels[row].length; col++) {
            JLabel myLabel = new JLabel();
            myLabel = new JLabel();
            myLabel.setOpaque(true);
            myLabel.setBorder(whiteborder);
            //myLabel.
            MyColor myColor = MyColor.GRAY;
            myColors[row][col] = myColor;
            myLabel.setBackground(myColor.getColor());
            myLabel.addMouseListener(myListener);
            
            myLabel.setPreferredSize(labelPrefSize);
            add(myLabel);
            globalStatus[row][col] = 0;
            myLabels[row][col] = myLabel;
         }
      }
   }
   //overload grid
   public ColorGrid(int rows, int cols, int [][] toDecode) {
      myColors = new MyColor[rows][cols];
      myLabels = new JLabel[rows][cols];
      globalStatus = new int [rows][cols];
      whiteborder = BorderFactory.createLineBorder(Color.WHITE);
      MyMouseListener myListener = new MyMouseListener(this);
      Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
      setLayout(new GridLayout(rows, cols));
      for (int row = 0; row < myLabels.length; row++) {
         for (int col = 0; col < myLabels[row].length; col++) {
            JLabel myLabel = new JLabel();
            myLabel = new JLabel();
            myLabel.setOpaque(true);
            myLabel.setBorder(whiteborder);
            //myLabel.
            if(toDecode[row][col] == 0){
                MyColor myColor = MyColor.GRAY;
                myColors[row][col] = myColor;
                myLabel.setBackground(myColor.getColor());
                myLabel.addMouseListener(myListener);
                globalStatus[row][col] = 0;
            }
            else{
                MyColor myColor = MyColor.BLACK;
                myColors[row][col] = myColor;
                myLabel.setBackground(myColor.getColor());
                myLabel.addMouseListener(myListener);
                globalStatus[row][col] = 1;
            }
            
            myLabel.setPreferredSize(labelPrefSize);
            add(myLabel);
            
            myLabels[row][col] = myLabel;
         }
      }
   }

   public MyColor[][] getMyColors() {
      return myColors;
   }

   public void labelPressed(JLabel label, int left_or_right) {
      if(left_or_right == 0){  
       for (int row = 0; row < myLabels.length; row++) {
           for (int col = 0; col < myLabels[row].length; col++) {
              if (label == myLabels[row][col]) {
                 MyColor myColor = myColors[row][col].next();
                 myColors[row][col] = myColor;
                 myLabels[row][col].setBackground(myColor.getColor());
                 globalStatus[row][col] = globalStatus[row][col]^1;
                 //System.out.print(globalStatus[row][col]);
              }
           }
        }
      }
      else{ //right
       //System.out.println("right");
       for (int row = 0; row < myLabels.length; row++) {
           for (int col = 0; col < myLabels[row].length; col++) {
              if (label == myLabels[row][col]) {
                  if(flagger_mouse_click == 0){
                    MyColor myColor = myColors[row][col].next();
                    myColors[row][col] = myColor;
                    myLabels[row][col].setBackground(myColor.getColor());
                    globalStatus[row][col] = globalStatus[row][col]^1;
                    temp_row = row;
                    temp_col = col;
                    //System.out.print(globalStatus[row][col]);
                  }
                  else{
                  int y = temp_col+1;
                     for(;temp_row<= row; temp_row++){
                         for(;y<=col;y++){
                            MyColor myColor = myColors[temp_row][y].next();
                            myColors[temp_row][y] = myColor;
                            myLabels[temp_row][y].setBackground(myColor.getColor());
                            globalStatus[temp_row][y] = globalStatus[temp_row][y]^1;
                         }
                         y = temp_col;
                     }
                  }
              }
           }
        }  
        flagger_mouse_click = flagger_mouse_click^1;
      }
   }
   
   public void labelHovered(JLabel label)
   {
       for (int row = 0; row < myLabels.length; row++) {
           for (int col = 0; col < myLabels[row].length; col++) {
              if (label == myLabels[row][col]) {
                 myLabels[row][col].setToolTipText(col+1 + "," + (row+1));
              }
           }
        }
   }
}
