/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelplotter;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 *
 * @author cberdin
 */
public class CreateNewProject {
    
    private JFrame mainWindow;
    private JTextField projectName;
    private JTextField projectPath;
    private JButton browseDirectory;
    private JButton cancel;
    private JButton finish;
    private JPanel projectSettings;
    private JPanel projectButtons;
    private JSplitPane dividedLayout;
    private JLabel nameLabel;
    private JLabel locationLabel;
    
    private String projectPathString;
    private String projectNameString;
    
    public void createNewPojectWindow(JFrame parentFrame)
    {
        mainWindow = new JFrame("Create New Project");
        projectName = new JTextField("", 20);
        browseDirectory = new JButton("Browse");
        browseDirectory.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed();
            }
        });
        
        cancel = new JButton("Cancel");
        finish = new JButton("Create");
        finish.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectNameString = projectName.getText();
                projectPathString = projectPathString + "\\" + projectName.getText();
                File directory = new File(projectPathString);
                if (!directory.exists())
                {
                    directory.mkdir();
                    mainWindow.setVisible(false);
                    PixelPlotterMain.status.setText("New project " + projectName.getText() + " created");
                }
            }
        });
        projectPath = new JTextField("", 20);
        projectButtons = new JPanel();
        projectSettings = new JPanel();
        nameLabel = new JLabel("Project Name: ");
        locationLabel = new JLabel("Project Location: ");
        
        GroupLayout layout = new GroupLayout(projectSettings);
        projectSettings.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(locationLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(projectName)
                        .addComponent(projectPath))
                .addComponent(browseDirectory)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(projectName))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(locationLabel)
                    .addComponent(projectPath)
                    .addComponent(browseDirectory))
        );
        projectButtons.add(finish);
        projectButtons.add(cancel);
        dividedLayout = new JSplitPane(JSplitPane.VERTICAL_SPLIT, projectSettings, projectButtons);
        
        mainWindow.setLocationRelativeTo(parentFrame);
        mainWindow.add(dividedLayout);
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
    
    private void browseActionPerformed()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Project Location");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                projectPathString = chooser.getSelectedFile().getAbsolutePath();
                projectPath.setText(projectPathString);
        
                PixelPlotterMain mainWindow = new PixelPlotterMain();
                mainWindow.setWorkingDirectory(projectPathString);
            }
        
    }
    
    public String getWorkingDirectory()
    {
        return projectPathString;
    }
    
    public String getProjectName()
    {
        return projectNameString;
    }
}
