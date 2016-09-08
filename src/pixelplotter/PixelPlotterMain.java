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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class PixelPlotterMain {

    /**
     * @param args the command line arguments
     */
    private JSplitPane  splitPaneV;
    private JSplitPane  splitPaneH;
    private JSplitPane splitProjects;
    
    private JButton generateCanvasBtn;
    private JButton loadIconBtn;
    private JButton saveBtn;
    private JTextField numOfRows;
    private JTextField numOfColumns;
    private ColorGrid mainPanel;
    private int rows = 20;
    private int columns = 20;
    private JFrame frame;
    private String projectPath;
    private String workingDirectory;
    
    private Boolean projectOpened = false;
    public static JLabel status;
    public static Boolean projectCreated = false;
    
    private CreateNewProject create;
    
    
    public void createAndShowGui() throws IOException {
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
      
      
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel.add(new JLabel("Rows: "));
    panel.add(numOfRows);
    panel.add(new JLabel("Columns: "));
    panel.add(numOfColumns);
    generateCanvasBtn = new JButton();
    ImageIcon img_ic = new ImageIcon(System.getProperty("user.dir") + "//generate_grid.png");
    generateCanvasBtn.setIcon(img_ic);
   
    generateCanvasBtn.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
            generateCanvasBtnActionPerformed(evt);
        }
    });
        generateCanvasBtn.setEnabled(false);
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
      
    loadIconBtn = new JButton();
    ImageIcon img2_ic = new ImageIcon(System.getProperty("user.dir") + "//new_proj.png");
    loadIconBtn.setIcon(img2_ic);
    loadIconBtn.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
            createNewProject();
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
    
    JButton openIconBtn = new JButton();
    ImageIcon img3_ic = new ImageIcon(System.getProperty("user.dir") + "//open_proj.png");
    openIconBtn.setIcon(img3_ic);
    
    saveBtn = new JButton();
    ImageIcon img4_ic = new ImageIcon(System.getProperty("user.dir") + "//save_icon.png");
    saveBtn.setIcon(img4_ic);
    saveBtn.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
           saveIconActionPerformed();
        }
    });
    
    JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
    //buttons.add(generateCanvasBtn);
    buttons.add(loadIconBtn);
    buttons.add(openIconBtn);
    openIconBtn.addActionListener(new java.awt.event.ActionListener() 
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) 
        {
            openExistingProject();
        }
    });
    panel.add(generateCanvasBtn);
    panel.add(saveBtn);
    /*PROJECTS TAB*/
    splitProjects = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
    JPanel projectsPanel = new JPanel();
    projectsPanel.add(new JLabel("Sample Project"));
      
    JPanel topPanel = new JPanel();
    topPanel.setLayout( new BorderLayout());
    frame.getContentPane().add(topPanel);
    splitPaneV = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
    topPanel.add( splitProjects, BorderLayout.CENTER );
    splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
    splitPaneH.setLeftComponent(buttons);
    splitPaneH.setRightComponent(panel);
    splitPaneH.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
      
    splitPaneH.setEnabled(false);
    //splitPaneV.setEnabled(false);
    splitPaneV.setBorder((BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY)));
    splitProjects.setEnabled(false);
    
    /*STATUS BAR*/
    JPanel projectStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
    //projectStatus.setBorder((BorderFactory)));
    frame.add(projectStatus, BorderLayout.SOUTH);
    status = new JLabel("Create or Open a Project");
    status.setFont(new Font("Arial", Font.ITALIC, 12));
    status.setForeground(Color.red);
    //status.setBorder((BorderFactory.createMatteBorder(1, 1, 0, 1, Color.GRAY)));
    projectStatus.add(status);
      
    boolean projectExists = checkForProjects();
    if(projectExists)
    {
        //splitPaneV.setRightComponent(mainPanel);
        JPanel existingProjects = new JPanel();
        existingProjects = existingProjectsPanel();
        splitPaneV.setRightComponent(existingProjects);
    }
    else
    {
        JPanel welcome = new JPanel();
        JLabel message = new JLabel("Welcome. Please create a Project.");
        message.setFont(new Font("Serif", Font.BOLD, 20));
        welcome.add(message);
        welcome.setSize(1024, 768);
         splitPaneV.setRightComponent(welcome);
    }
    splitPaneV.setLeftComponent(projectsPanel);
    projectsPanel.setPreferredSize(new Dimension(300,768));
    
    splitProjects.setTopComponent(splitPaneH);
    splitProjects.setBottomComponent(splitPaneV);
    projectsPanel.setBorder((BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY)));
    
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
    //frame.pack();
    frame.setLocationByPlatform(true);
    ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "//pixelplotter.png");
    frame.setIconImage(img.getImage());
    //status.setText(System.getProperty("user.dir"));
    
    if(!projectOpened)
    {
        numOfRows.setEnabled(false);
        numOfColumns.setEnabled(false);
        generateCanvasBtn.setEnabled(false);
        saveBtn.setEnabled(false);
    }
    frame.setSize(1028, 720);
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
        create = new CreateNewProject();
        create.createNewPojectWindow(frame, newCoordinates, newDimension);
        
        generateCanvasBtn.setEnabled(true);
        numOfRows.setEnabled(true);
        numOfColumns.setEnabled(true);
        saveBtn.setEnabled(true);
    }
    
    public void setWorkingDirectory(String directory)
    {
        workingDirectory = directory;
    }
    
    private JPanel existingProjectsPanel()
    {
        BufferedReader reader = null;
        JPanel existingProjects = new JPanel();
        JLabel titleBanner = new JLabel("Recent Projects");
        titleBanner.setFont(new Font("Serif", Font.BOLD, 30));
         
        existingProjects.add(titleBanner);
        existingProjects.add(new JLabel(""));
        existingProjects.setBackground(Color.LIGHT_GRAY);
        existingProjects.setLayout(new BoxLayout(existingProjects, BoxLayout.Y_AXIS));
        existingProjects.add(Box.createRigidArea(new Dimension(0,2)));
        

        File file = new File(System.getProperty("user.dir") + "//pixelplotter.prjs");
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null) 
            {
                String [] separatedText = text.split(",");
                existingProjects.add(new JLabel(separatedText[0]));
                existingProjects.add(new JLabel("          " + separatedText[1]));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return existingProjects;
    }
    
    
    private boolean checkForProjects()
    {
        int x = 0;
        BufferedReader reader = null;
        File file = new File(System.getProperty("user.dir") + "//pixelplotter.prjs");
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null) 
            {
                x++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (x == 0)
            return false;
        else
            return true;
    }
    
    private void openExistingProject()
    {
        OpenProject open = new OpenProject();
        workingDirectory = open.browseActionPerformed(frame);
        /*UPDATE PROJECT TAB*/
        updateProjectTab();
        numOfRows.setEnabled(true);
        numOfColumns.setEnabled(true);
        generateCanvasBtn.setEnabled(true);
        saveBtn.setEnabled(true);
    }
    
    public void updateProjectTab()
    {
        JPanel workingProject = new JPanel();
        workingProject.setSize(50, 50);
        workingProject.setLayout(new BoxLayout(workingProject, BoxLayout.PAGE_AXIS));
         int x = 0;
        String names = "";
        ImageIcon image = new ImageIcon(System.getProperty("user.dir") + "//bar.png");;
        BufferedReader reader = null;
        File file = new File(workingDirectory + "//_project.conf");
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null) 
            {
                names = names + "," + text;
                x++;
            }
            
            JLabel [] projectFiles = new JLabel[x];
            final String [] projectNames = names.split(",");
            if(x > 1)
            {
                workingProject.add(new JLabel(projectNames[1]));
                for(int y = 1;  y < x  ; y++)
                {
                    projectFiles[y] = new JLabel(projectNames[y + 1], image, JLabel.CENTER);
                    final int k = y;
                    projectFiles[y].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            loadIcon(projectNames[k + 1]);
                        }

                    });
                    workingProject.add(projectFiles[y]);
                }
            }
            else
            workingProject.add(new JLabel("HELLO"));
            System.out.println(text);
            workingProject.setPreferredSize(new Dimension(300,768));
            splitPaneV.setLeftComponent(workingProject);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadIcon (String icon)
    {
        LoadIcon loadIconLaunchWindow = new LoadIcon();
        mainPanel = loadIconLaunchWindow.loadIconFileChooserLauncher(workingDirectory + "//" + icon + ".ppi", frame, mainPanel);
        splitPaneV.setRightComponent(mainPanel);
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
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
                     UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

                    } catch (Exception e) {
                      System.err.println("Look and feel not set.");
                    }
            PixelPlotterMain generateGrid = new PixelPlotterMain();
             try {
                 generateGrid.createAndShowGui();
             } catch (IOException ex) {
                 Logger.getLogger(PixelPlotterMain.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
      });
    }
        
}
