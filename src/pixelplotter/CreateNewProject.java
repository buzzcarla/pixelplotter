/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelplotter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
    
    public void createNewPojectWindow(JFrame parentFrame, Point coordinates, Dimension dimension)
    {
        mainWindow = new JFrame("Create New Project");
        mainWindow.setSize(dimension);
        mainWindow.setLocation(coordinates);
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        projectName = new JTextField("", 20);
        final Border border = projectName.getBorder();
        browseDirectory = new JButton("Browse");
        browseDirectory.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed();
            }
        });
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
            }
        });
        finish = new JButton("Create");
        finish.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(projectName.getText().isEmpty() || projectPath.getText().isEmpty()))
                {
                    if (new File(projectPath.getText()).exists())
                    {
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
                    else
                        projectPath.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                else
                {
                    if(projectName.getText().isEmpty() && projectPath.getText().isEmpty())
                    {
                        projectName.setBorder(BorderFactory.createLineBorder(Color.red));
                        projectPath.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                    else if(!(projectName.getText().isEmpty()) && projectPath.getText().isEmpty())
                    {
                        projectName.setBorder(border);
                        projectPath.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                    else
                    {
                        projectName.setBorder(BorderFactory.createLineBorder(Color.red));
                        projectPath.setBorder(border);
                    }
                        
                        
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
        
       
        
        mainWindow.add(dividedLayout);
        mainWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //mainWindow.pack();
        mainWindow.setResizable(false);
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
