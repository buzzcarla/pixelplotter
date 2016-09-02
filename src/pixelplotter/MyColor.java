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

public enum MyColor {
   BLACK(Color.black,"Black","b"), GRAY(Color.lightGray, "Gray","q");
   private Color color;
   private String name;
   private String shortName;

   private MyColor(Color color, String name, String shortName) {
      this.color = color;
      this.name = name;
      this.shortName = shortName;
   }

   public MyColor next() {
      int index = 0;
      for (int i = 0; i < MyColor.values().length; i++) {
         if (MyColor.values()[i] == this) {
            index = (i + 1) % MyColor.values().length;
         }
      }
      return MyColor.values()[index];
   }

   public Color getColor() {
      return color;
   }

   public String getName() {
      return name;
   }

   public String getShortName() {
      return shortName;
   }

   @Override
   public String toString() {
      return shortName;
   }

}