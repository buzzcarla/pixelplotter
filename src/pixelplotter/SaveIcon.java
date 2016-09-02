/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelplotter;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author cberdin
 */
public class SaveIcon {
    
    public int saveIconInWorkingDirectory(String workingDirectory, String iconName, int rows, int columns, CreateNewProject create)
    {
        try
        {
            String filename= workingDirectory + "\\" + create.getProjectName() + "_Project.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write("\n//"+ iconName +"\nnew int[,]\n{");
            for(int row = 0; row < rows; row++)
            {
                fw.write("\t{");
                for(int col = 0; col <columns; col++)
                {
                    fw.write(""+ColorGrid.globalStatus[row][col]);
                    if(col != columns - 1) fw.write(", ");
                }
            if(row != rows - 1) fw.write("},\n");
            else fw.write("}\n");
            }
            fw.write("},\n");
            fw.close();                 
            filename = workingDirectory + "\\" + iconName + ".ppi";
            fw = new FileWriter(filename,true);
            fw.write(columns + "\n" + rows + "\n");
            for(int row = 0; row < rows; row++)
            {
                for(int col = 0; col <columns; col++)
                {
                    fw.write(""+ColorGrid.globalStatus[row][col]);
                    //if(col != columns - 1) fw.write("");
                }
                fw.write("\n");
            }
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
            return 1;
        }
        return 0;
    }
}
