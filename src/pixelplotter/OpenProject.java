/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelplotter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author cberdin
 */
public class OpenProject {
    
    private String projectPathString;
    
    public String browseActionPerformed(JFrame parentFrame)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Project Location");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
                projectPathString = chooser.getSelectedFile().getAbsolutePath();
                PixelPlotterMain.status.setText("Existing project opened at " + projectPathString);
            }
        return projectPathString;
    }
}
