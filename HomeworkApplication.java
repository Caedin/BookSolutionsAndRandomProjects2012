// Keith Dyer, February 20, 2013

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

// -----------------------------------------------

// Class HomeworkProject will contain the main applet and will be the core program of the project.

public class HomeworkApplication extends JFrame {
   // Panels
   PaintPanel topPanel = new PaintPanel(); // This panel will be the overall panel
   PaintPanel northPanel = new PaintPanel(); // This panel will be north
   PaintPanel centerPanel = new PaintPanel(); // This panel will be center
   PaintPanel southPanel = new PaintPanel(); // This panel will be south
   MainMenu main = new MainMenu();
   PaintPanel currentPanel = new PaintPanel();

   // Layouts
   BorderLayout topLayout = new BorderLayout();

   // ImageIcon for displaying neccessary graphics
   ImageIcon graphics = new ImageIcon();
   JLabel graphicsLabel = new JLabel();

   // Scroll Pane
   JScrollPane scrollPane;

   // Variables for description
   JLabel description = new JLabel();

   // Is there an app in the center panel?
   boolean app = false;
   PaintApplet currentApplet = new PaintApplet();

   public HomeworkApplication() throws IOException {
      // Setup applet window
      setSize(825, 825);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setLayout(new GridLayout(1, 0));

      // Create panels for each problem solution
      createProblemObjects();

      // Create Panels for each area
      createNorthPanel();
      createCenterPanel();
      createTopPanel();

      // Display
      add(topPanel);
      setVisible(true);
   }

   private void createNorthPanel() {
      JLabel header = new JLabel("COSC 1437 Homework/Projects");
      JButton mainMenuButton = new JButton("Main Menu"); // This button loads the main menu into the center panel.
      mainMenuButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (app == false)
               updateCenterPanel(main);
            else {
               updateCenterPanel(main);
               topPanel.removeAll();
               createTopPanel();
               topPanel.repaint();
               topPanel.revalidate();
            }

         }
      });
      mainMenuButton.setToolTipText("Return to the main menu.");

      JButton problemButton = new JButton(" Problem Description "); // This button loads the description for the problem
                                                                    // loaded into the center
      problemButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (app == false)
               JOptionPane.showMessageDialog(null, currentPanel.getDescription());
            else
               JOptionPane.showMessageDialog(null, currentApplet.getDescription());
         }
      });
      problemButton.setToolTipText("Reveals the problem description from the text book.");

      // buttonPanel is to prevent the button from stretching to the size of its cell
      // in northPanel
      JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 5, 5));
      JPanel headerWrapper = new JPanel();
      buttonPanel.add(wrap(mainMenuButton));
      buttonPanel.add(wrap(problemButton));
      headerWrapper.add(header);

      northPanel.setLayout(new GridLayout(2, 0));
      northPanel.add(headerWrapper);
      northPanel.add(buttonPanel);
   }

   private JPanel wrap(JComponent c) {
      JPanel wrapper = new JPanel();
      wrapper.add(c);
      return wrapper;
   }

   private void createTopPanel() {
      topPanel.setLayout(topLayout);
      topPanel.add(northPanel, BorderLayout.NORTH);

      scrollPane = new JScrollPane(centerPanel);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());

      topPanel.add(scrollPane, BorderLayout.CENTER);
   }

   private void createCenterPanel() {
      centerPanel = new PaintPanel();
      centerPanel.add(main);
   }

   private void updateCenterPanel(PaintPanel panel) {
      app = false;
      centerPanel.removeAll();
      currentPanel = panel;
      centerPanel.add(panel);
      topPanel.repaint();
      topPanel.revalidate();
   }

   private void updateCenterPanel(PaintApplet app) {
      this.app = true;
      currentApplet = app;

      centerPanel.removeAll();
      topPanel.remove(scrollPane);
      topPanel.add(app, BorderLayout.CENTER);
      app.init();
      app.start();
      topPanel.repaint();
      topPanel.revalidate();
   }

   private void updateCenterPanelProject(Container panel) {
      app = false;
      centerPanel.removeAll();
      // currentPanel = panel;
      centerPanel.add(panel);
      topPanel.repaint();
      topPanel.revalidate();
   }

   private void createProblemObjects() {
      main = new MainMenu();
      currentPanel = main;
   }

   public void paint(Graphics g) {
      super.paint(g);

   }

   public void update(Graphics g) {
      paint(g);
   }

   private class MainMenu extends PaintPanel {
      JPanel topPanel = new JPanel();
      JPanel chapterPanel = new JPanel();
      JPanel problemPanel = new JPanel();
      JPanel wrapperPanel = new JPanel();
      JButton button = new JButton();

      int numberOfProblems = 0;

      public MainMenu() {
         createChapterPanel();
         buildPanel();
         add(topPanel);
         setVisible(true);
      }

      @Override
      public String getDescription() {
         return "This is the main menu, where you can select which problem you would like to look at.";
      }

      private void createChapterPanel() {
         topPanel.setLayout(new BorderLayout());
         chapterPanel.setLayout(new GridLayout(8, 0));

         for (int i = 9; i < 15; i++) {
            wrapperPanel = new JPanel();
            button = new JButton("Chapter " + (i));
            button.addActionListener(new buttonResponses());
            wrapperPanel.add(button);
            chapterPanel.add(wrapperPanel);
         }

         wrapperPanel = new JPanel();
         button = new JButton("Projects");
         button.addActionListener(new buttonResponses());
         wrapperPanel.add(button);
         chapterPanel.add(wrapperPanel);

         wrapperPanel = new JPanel();
         button = new JButton("Extra");
         button.addActionListener(new buttonResponses());
         wrapperPanel.add(button);
         chapterPanel.add(wrapperPanel);

         chapterPanel.setBorder(BorderFactory.createTitledBorder("Chapters"));
         createProblemPanel();
      }

      private void createProblemPanel() {
         problemPanel.setLayout(new GridLayout(4, 4));
         setProblemButtons(10, 9);
      }

      private void setProblemButtons(int numProb, int numChap) {
         problemPanel.removeAll();
         problemPanel.setBorder(BorderFactory.createTitledBorder("Chapter " + numChap));

         for (int i = 1; i <= numProb; i++) {
            String s = numChap + "." + i;
            wrapperPanel = new JPanel();
            button = new JButton("Problem " + s);
            button.addActionListener(new buttonResponses());
            wrapperPanel.add(button);
            problemPanel.add(wrapperPanel);
         }
         for (int i = 0; i < (16 - numProb); i++) {
            wrapperPanel = new JPanel();
            problemPanel.add(wrapperPanel);
         }
         problemPanel.repaint();
         revalidate();
      }

      private void buildPanel() {
         wrapperPanel = new JPanel();
         wrapperPanel.add(problemPanel);
         topPanel.add(wrapperPanel, BorderLayout.CENTER);
         wrapperPanel = new JPanel();
         wrapperPanel.add(chapterPanel);
         topPanel.add(wrapperPanel, BorderLayout.WEST);
      }

      private class buttonResponses implements ActionListener {
         public void chapters(String choice) {

            // Chapters
            if (choice.equals("Chapter 9")) {
               setProblemButtons(10, 9);

            }
            if (choice.equals("Chapter 10")) {
               setProblemButtons(16, 10);

            }
            if (choice.equals("Chapter 11")) {
               setProblemButtons(10, 11);

            }
            if (choice.equals("Chapter 12")) {
               setProblemButtons(10, 12);

            }
            if (choice.equals("Chapter 13")) {
               setProblemButtons(8, 13);

            }
            if (choice.equals("Chapter 14")) {
               setProblemButtons(9, 14);

            }

            if (choice.equals("Extra")) {
               int numExtra = 7;

               problemPanel.removeAll();
               problemPanel.setBorder(BorderFactory.createTitledBorder("Extra"));

               wrapperPanel = new JPanel();
               button = new JButton("Problem 7.4");
               button.addActionListener(new buttonResponses());
               wrapperPanel.add(button);
               problemPanel.add(wrapperPanel);

               wrapperPanel = new JPanel();
               button = new JButton("Problem 7.6");
               button.addActionListener(new buttonResponses());
               wrapperPanel.add(button);
               problemPanel.add(wrapperPanel);

               wrapperPanel = new JPanel();
               button = new JButton("Problem 8.5");
               button.addActionListener(new buttonResponses());
               wrapperPanel.add(button);
               problemPanel.add(wrapperPanel);

               wrapperPanel = new JPanel();
               button = new JButton("Problem 8.7");
               button.addActionListener(new buttonResponses());
               wrapperPanel.add(button);
               problemPanel.add(wrapperPanel);

               wrapperPanel = new JPanel();
               button = new JButton("Collision Engine");
               button.addActionListener(new buttonResponses());
               wrapperPanel.add(button);
               problemPanel.add(wrapperPanel);

               wrapperPanel = new JPanel();
               button = new JButton("LOS Demo");
               button.addActionListener(new buttonResponses());
               wrapperPanel.add(button);
               problemPanel.add(wrapperPanel);

               wrapperPanel = new JPanel();
               button = new JButton("ConnectFour");
               button.addActionListener(new buttonResponses());
               wrapperPanel.add(button);
               problemPanel.add(wrapperPanel);

               for (int i = 0; i < (16 - numExtra); i++) {
                  wrapperPanel = new JPanel();
                  problemPanel.add(wrapperPanel);
               }
               problemPanel.repaint();
               revalidate();
            }

            if (choice.equals("Projects")) {
               int numProjects = 8;
               problemPanel.removeAll();
               problemPanel.setBorder(BorderFactory.createTitledBorder("Projects"));

               JButton button = new JButton("Greer & Poche");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               button = new JButton("Michael & Eric");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               button = new JButton("Stupka & Reyes");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               button = new JButton("Salazar & Meyer");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               button = new JButton("Mike and Roel");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               button = new JButton("Will & Jolynn");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               button = new JButton("Peter & Elyssa");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               button = new JButton("Unnamed");
               button.addActionListener(new buttonResponses());
               problemPanel.add(KDUtil.wrap(button));

               for (int i = 0; i < (16 - numProjects); i++) {
                  wrapperPanel = new JPanel();
                  problemPanel.add(wrapperPanel);
               }

               problemPanel.repaint();
               revalidate();
            }

         }

         public void chapter9(String choice) throws IOException {
            // Chapter 9 problems
            if (choice.equals("Problem 9.9")) {
               Problem9_9 x = new Problem9_9();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.10")) {
               Problem9_10 x = new Problem9_10();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.8")) {
               Problem9_8 x = new Problem9_8();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.7")) {
               Problem9_7 x = new Problem9_7();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 9.6")) {
               Problem9_6 x = new Problem9_6();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.3")) {
               Problem9_3 x = new Problem9_3();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.4")) {
               Problem9_4 x = new Problem9_4();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.5")) {
               Problem9_5 x = new Problem9_5();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.1")) {
               Problem9_1 x = new Problem9_1();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 9.2")) {
               Problem9_2 x = new Problem9_2();
               updateCenterPanel(x);
            }

         }

         public void extras(String choice) {
            // Extra Problems
            if (choice.equals("Problem 7.4")) {
               Problem7_4 x = new Problem7_4();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 7.6")) {
               Problem7_6 x = new Problem7_6();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 8.7")) {
               Problem8_7 x = new Problem8_7();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 8.5")) {
               Problem8_5 x = new Problem8_5();
               updateCenterPanel(x);

            }

            if (choice.equals("Collision Engine")) {
               CollisionEngine2 x = new CollisionEngine2();
               updateCenterPanel(x);

            }

            if (choice.equals("LOS Demo")) {
               LOSDemo x = new LOSDemo();
               updateCenterPanel(x);
            }

            if (choice.equals("ConnectFour")) {
               ConnectFour x = new ConnectFour();
               updateCenterPanel(x);
            }

         }

         public void chapter10(String choice) {
            // Chapter 10 problems
            if (choice.equals("Problem 10.1")) {
               Problem10_1 x = new Problem10_1();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.2")) {
               Problem10_2 x = new Problem10_2();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.4")) {
               Problem10_4 x = new Problem10_4();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.3")) {
               Problem10_3 x = new Problem10_3();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.5")) {
               Problem10_5 x = new Problem10_5();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.6")) {
               Problem10_6 x = new Problem10_6();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.7")) {
               Problem10_7 x = new Problem10_7();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.8")) {
               Problem10_8 x = new Problem10_8();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.9")) {
               Problem10_9 x = new Problem10_9();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.10")) {
               Problem10_10 x = new Problem10_10();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.11")) {
               Problem10_11 x = new Problem10_11();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.12")) {
               Problem10_12 x = new Problem10_12();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.13")) {
               Problem10_13 x = new Problem10_13();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.14")) {
               Problem10_14 x = new Problem10_14();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.15")) {
               Problem10_15 x = new Problem10_15();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 10.16")) {
               Problem10_16 x = new Problem10_16();
               updateCenterPanel(x);
            }
         }

         public void chapter11(String choice) {
            // Chapter 11 problems

            if (choice.equals("Problem 11.1")) {
               Problem11_1 x = new Problem11_1();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 11.2")) {
               Problem11_2 x = new Problem11_2();
               updateCenterPanel(x);
            }

            if (choice.equals("Problem 11.3")) {
               Problem11_3 x = new Problem11_3();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 11.4")) {
               Problem11_4 x = new Problem11_4();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 11.5")) {
               Problem11_5 x = new Problem11_5();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 11.6")) {
               Problem11_6 x = new Problem11_6();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 11.7")) {
               Problem11_7 x = new Problem11_7();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 11.8")) {
               Problem11_8 x = new Problem11_8();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 11.9")) {
               Problem11_9 x = new Problem11_9();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 11.10")) {
               Problem11_10 x = new Problem11_10();
               updateCenterPanel(x);
            }

         }

         public void chapter12(String choice) {
            if (choice.equals("Problem 12.1")) {
               Problem12_1 x = new Problem12_1();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.2")) {
               Problem12_2 x = new Problem12_2();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.3")) {
               Problem12_3 x = new Problem12_3();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.4")) {
               Problem12_4 x = new Problem12_4();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.5")) {
               Problem12_5 x = new Problem12_5();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.6")) {
               Problem12_6 x = new Problem12_6();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.7")) {
               Problem12_7 x = new Problem12_7();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.8")) {
               Problem12_8 x = new Problem12_8();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.9")) {
               Problem12_9 x = new Problem12_9();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 12.10")) {
               Problem12_10 x = new Problem12_10();
               updateCenterPanel(x);
            }
         }

         public void chapter13(String choice) {
            if (choice.equals("Problem 13.1")) {
               Problem13_1 x = new Problem13_1();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 13.2")) {
               Problem13_2 x = new Problem13_2();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 13.3")) {
               Problem13_3 x = new Problem13_3();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 13.4")) {
               Problem13_4 x = new Problem13_4();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 13.5")) {
               Problem13_5 x = new Problem13_5();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 13.6")) {
               Problem13_6 x = new Problem13_6();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 13.7")) {
               Problem13_7 x = new Problem13_7();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 13.8")) {
               Problem13_8 x = new Problem13_8();
               updateCenterPanel(x);
            }

         }

         public void chapter14(String choice) {
            if (choice.equals("Problem 14.1")) {
               Problem14_1 x = new Problem14_1();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.2")) {
               Problem14_2 x = new Problem14_2();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.3")) {
               Problem14_3 x = new Problem14_3();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.4")) {
               Problem14_4 x = new Problem14_4();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.5")) {
               Problem14_5 x = new Problem14_5();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.6")) {
               Problem14_6 x = new Problem14_6();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.7")) {
               Problem14_7 x = new Problem14_7();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.8")) {
               Problem14_8 x = new Problem14_8();
               updateCenterPanel(x);
            }
            if (choice.equals("Problem 14.9")) {
               Problem14_9 x = new Problem14_9();
               updateCenterPanel(x);
            }
         }

         public void actionPerformed(ActionEvent e) {
            try {
               String choice = e.getActionCommand();

               chapters(choice);
               extras(choice);

               chapter9(choice);
               chapter10(choice);
               chapter11(choice);
               chapter12(choice);
               chapter13(choice);
               chapter14(choice);
            } catch (IOException exception) {
               System.out.println("Error reading or writing to file.");
            }

         }
      }
   }
}
