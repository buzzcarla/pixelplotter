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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
import javax.swing.ImageIcon;
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
    frame = new JFrame("Pixel Plotter Beta");
    numOfColumns = new JTextField("20",4);  
    numOfColumns.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
            saveIconActionPerformed();
        }
    });
    numOfRows = new JTextField("20",4);  
    numOfRows.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
            saveIconActionPerformed();
        }
    });
      
      
    JPanel panel = new JPanel();
    panel.add(new JLabel("Rows: "));
    panel.add(numOfRows);
    panel.add(new JLabel("Columns: "));
    panel.add(numOfColumns);

    generateCanvasBtn = new JButton("Generate Canvas");
    generateCanvasBtn.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
            generateCanvasBtnActionPerformed(evt);
        }
    });
    generateCanvasBtn.addKeyListener(new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
              System.out.print("enter");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
      
    loadIconBtn = new JButton("Load Icon");
    loadIconBtn.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
            loadIconBtnActionPerformed(evt);
        }
    });
    loadIconBtn.addKeyListener(new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent e) 
        {
            
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
              System.out.print("enter");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
      
    mainPanel.addKeyListener(new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent e) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
              saveIconActionPerformed();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) 
        {
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
    frame.addKeyListener(new KeyListener()
    {
        @Override
        public void keyPressed(KeyEvent e) 
        {
            System.out.println("hello");    
        }
        @Override
        public void keyTyped(KeyEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public void keyReleased(KeyEvent e) 
        {
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menuBar.add(menu);
    JMenuItem new_project = new JMenuItem("New Project");
    new_project.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            createNewProject();
        }
    });
    JMenuItem item = new JMenuItem("Exit");
    item.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
             System.exit(0);
        }
    });
        
    JMenuItem openItem = new JMenuItem("Open Project");
    openItem.addActionListener(new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            openExistingProject();
        }
    });
    menu.add(new_project);
    menu.add(openItem);
    menu.add(item);
    frame.setJMenuBar(menuBar);
    frame.pack();
    frame.setLocationByPlatform(true);
    ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "//Icon.png");
    frame.setIconImage(img.getImage());
    //status.setText(System.getProperty("user.dir"));
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
        /*Get coordinated and dimension*/
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.getWidth() + "," + screenSize.getHeight());
        Point topLeftCoordinates = frame.getLocation();
        System.out.println(topLeftCoordinates.x + "," + topLeftCoordinates.y);
        Rectangle dimension = frame.getBounds();
        int newWidth = (int)((dimension.width * 0.5) - (dimension.width * 0.8 / 2));
        int newHeight = (int)((dimension.height * 0.5) - 78);
        System.out.println(newWidth+ "," + newHeight);
        Dimension newDimension = new Dimension((int)(dimension.width * 0.8), 156);
        Point newCoordinates = new Point(topLeftCoordinates.x + newWidth, topLeftCoordinates.y + newHeight);
        frame.setFocusableWindowState(false);
        frame.setEnabled(false);
        create = new CreateNewProject();
        create.createNewPojectWindow(frame, newCoordinates, newDimension);
    }
    
    private void openExistingProject()
    {
        OpenProject open = new OpenProject();
        workingDirectory = open.browseActionPerformed(frame);
        
    }
    
    private void loadIconBtnActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (workingDirectory == null) 
        {
            workingDirectory = create.getWorkingDirectory();
        }
        LoadIcon loadIconLaunchWindow = new LoadIcon();
        mainPanel = loadIconLaunchWindow.loadIconFileChooserLauncher(workingDirectory, frame, mainPanel);
        splitPaneV.setRightComponent(mainPanel);
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
