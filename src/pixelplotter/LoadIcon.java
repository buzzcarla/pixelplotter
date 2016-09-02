/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelplotter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author cberdin
 */
public class LoadIcon {
    
    
    public ColorGrid loadIconFileChooserLauncher(String workingDirectory, JFrame parentFrame, ColorGrid mainPanel)
    {
        ColorGrid newColorGrid = null;
        String fileName = null;
        BufferedReader reader = null;
        int x = 0, w = 0;
        int icon_rows, icon_columns;
        
        JFileChooser chooser = new JFileChooser(new File(workingDirectory));
        int status = chooser.showOpenDialog(parentFrame);
        if (status == JFileChooser.APPROVE_OPTION) 
        {
            File file = chooser.getSelectedFile();
            if (file == null) {
                    //return;
            }
            fileName = chooser.getSelectedFile().getAbsolutePath();
        }
        File file = new File(fileName);
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            icon_columns = Integer.parseInt(reader.readLine());
            icon_rows = Integer.parseInt(reader.readLine());
            System.out.println(icon_rows);
            System.out.println(icon_columns);
            int [][] parsedInput = new int [icon_rows][icon_columns];
            while ((text = reader.readLine()) != null) 
            {
                char [] myvalues = text.toCharArray();
                for(int y = 0; y < myvalues.length; y++)
                {
                    parsedInput [x][y] = Integer.parseInt(String.valueOf(myvalues[y]));
                }   
                x++;
            }
            newColorGrid = new ColorGrid(icon_rows, icon_columns, parsedInput);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  newColorGrid;
    }
}
