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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

public class PixelPlotterMain {

    /**
     * @param args the command line arguments
     */
    private JSplitPane  splitPaneV;
    private JSplitPane  splitPaneH;
    private JButton generateCanvasBtn;
    private JButton loadIconBtn;
    private JTextField numOfRows;
    private JTextField numOfColumns;
    private ColorGrid mainPanel;
    private int rows = 20;
    private int columns = 20;
    private JFrame frame;
    private String projectPath;
    private String workingDirectory;
    
    public static JLabel status;
    
    private CreateNewProject create;
    
    
    public void createAndShowGui() {
    mainPanel = new ColorGrid(rows, columns);
    frame = new JFrame("Pixel Plotter v2.1");
      numOfColumns = new JTextField("",4);  
      numOfColumns.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveIconActionPerformed();
            }
        });
      numOfRows = new JTextField("",4);  
      numOfRows.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveIconActionPerformed();
            }
        });
      
      
      JPanel panel = new JPanel();
      panel.add(new JLabel("Rows: "));
      panel.add(numOfRows);
      panel.add(new JLabel("Columns: "));
      panel.add(numOfColumns);

      generateCanvasBtn = new JButton("Generate Canvas");
      generateCanvasBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateCanvasBtnActionPerformed(evt);
            }
        });
      generateCanvasBtn.addKeyListener(new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
              System.out.print("enter");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
      
      loadIconBtn = new JButton("Load Icon");
      loadIconBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadIconBtnActionPerformed(evt);
            }
        });
      loadIconBtn.addKeyListener(new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
              System.out.print("enter");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
      });
      
      mainPanel.addKeyListener(new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
              saveIconActionPerformed();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
      JPanel buttons = new JPanel();
      buttons.add(generateCanvasBtn);
      buttons.add(loadIconBtn);
      
      JPanel topPanel = new JPanel();
      topPanel.setLayout( new BorderLayout());
      frame.getContentPane().add(topPanel);
      splitPaneV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
      topPanel.add( splitPaneV, BorderLayout.CENTER );
      splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
      splitPaneH.setLeftComponent(panel);
      splitPaneH.setRightComponent(buttons);
      
      /*STATUS BAR*/
      JPanel projectStatus = new JPanel();
      projectStatus.setBorder(new BevelBorder (BevelBorder.LOWERED));
      frame.add(projectStatus, BorderLayout.SOUTH);
      status = new JLabel("Create or Open a Project");
      status.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
      status.setHorizontalAlignment(SwingConstants.LEFT);
      status.setFont(new Font("Arial", Font.ITALIC, 12));
      status.setForeground(Color.red);
      projectStatus.add(status, BorderLayout.WEST);
      
      splitPaneV.setLeftComponent(splitPaneH);
      splitPaneV.setRightComponent(mainPanel);
        
      
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      //frame.getContentPane().add(mainPanel);
      frame.addKeyListener(new KeyListener(){
      @Override
               public void keyPressed(KeyEvent e) {
                          System.out.println("hello");    
               }
      @Override
          public void keyTyped(KeyEvent e) {
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public void keyReleased(KeyEvent e) {
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
      });
    JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem new_project = new JMenuItem("New Project");
        new_project.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                createNewProject();
            }
        });
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
             System.exit(0);
        }
        });
        
        JMenuItem openItem = new JMenuItem("Open Project");
        openItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            openExistingProject();
        }
        });
        menu.add(new_project);
        menu.add(openItem);
        menu.add(item);
        frame.setJMenuBar(menuBar);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
      
   }
    
    private void generateCanvasBtnActionPerformed(java.awt.event.ActionEvent evt)
    {
        System.out.println("Btn Pressed");
        rows = Integer.parseInt(numOfRows.getText());
        columns = Integer.parseInt(numOfColumns.getText());
        mainPanel = new ColorGrid(rows, columns);
        splitPaneV.setRightComponent(mainPanel);
        
//        mainPanel.revalidate();
//        mainPanel.repaint();
    }
    
    private void createNewProject()
    {
        create = new CreateNewProject();
        create.createNewPojectWindow(frame);
    }
    
    private void openExistingProject()
    {
        OpenProject open = new OpenProject();
        workingDirectory = open.browseActionPerformed(frame);
        
    }
    
    private void loadIconBtnActionPerformed(java.awt.event.ActionEvent evt)
    {
        String fileName = null;
        BufferedReader reader = null;
        int x = 0, w = 0;
        int icon_rows, icon_columns;
        if (workingDirectory == null) 
        {
            workingDirectory = create.getWorkingDirectory();
        }
        JFileChooser chooser = new JFileChooser(new File(workingDirectory));
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                if (file == null) {
                    return;
                }
                fileName = chooser.getSelectedFile().getAbsolutePath();
            }
        File file = new File(fileName);
        try {
        reader = new BufferedReader(new FileReader(file));
        String text = null;
        icon_columns = Integer.parseInt(reader.readLine());
        icon_rows = Integer.parseInt(reader.readLine());
        System.out.println(icon_rows);
        System.out.println(icon_columns);
        int [][] parsedInput = new int [icon_rows][icon_columns];
        while ((text = reader.readLine()) != null) {
            char [] myvalues = text.toCharArray();
            for(int y = 0; y < myvalues.length; y++){
                parsedInput [x][y] = Integer.parseInt(String.valueOf(myvalues[y]));
            }
            x++;
        }
        mainPanel = new ColorGrid(icon_rows, icon_columns, parsedInput);
        splitPaneV.setRightComponent(mainPanel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveIconActionPerformed()    
    {
        String name = JOptionPane.showInputDialog(frame, "Name of icon or font", null);
        SaveIcon addIconToProject = new SaveIcon();
        if (workingDirectory == null) 
        {
            workingDirectory = create.getWorkingDirectory();
        }
        addIconToProject.saveIconInWorkingDirectory(workingDirectory, name, rows, columns, create);
    }

    public void createAndShowGui(final int rows, final int cols, int [][] toDecode) {
      int cellWidth = 20;
      ColorGrid mainPanel = new ColorGrid(rows, cols, toDecode);
      final JFrame frame = new JFrame("Pixel Plotter v2.0");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.addKeyListener(new KeyListener(){
             @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String name = JOptionPane.showInputDialog(frame,
                    "Name of icon or font", null);
                        try
                        {
                            String filename= "C:\\Users\\CBerdin\\Desktop\\sample.txt";
                            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                            fw.write("\n//"+ name +"\nnew int[,]\n{\n");
                            for(int row = 0; row < rows; row++)
                           {
                               fw.write("\t{");
                               for(int col = 0; col <cols; col++){
                                   fw.write(""+ColorGrid.globalStatus[row][col]);
                                   if(col != cols - 1) fw.write(", ");
                               }
                               if(row != rows - 1) fw.write("},\n");
                               else fw.write("}\n");
                           }
                            fw.write("},\n");
                            fw.close();
                        }
                        catch(IOException ioe)
                        {
                            System.err.println("IOException: " + ioe.getMessage());
                        }
                    }
                }
          @Override
          public void keyTyped(KeyEvent e) {
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public void keyReleased(KeyEvent e) {
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

        });
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
      
   }
    
    public void setWorkingDirectory(String workingDirectoryPath)
    {
        workingDirectory = workingDirectoryPath;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            //welcome_page start = new welcome_page();
           // start.setVisible(true);
            PixelPlotterMain generateGrid = new PixelPlotterMain();
            generateGrid.createAndShowGui();
            //createAndShowGui();
         }
      });
    }
        
}
