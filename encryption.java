import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.awt.*; // for the UI layout
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

class editor extends JFrame implements ActionListener //Jframe gives basic windows features
{

    // Text component
    JTextArea t;

    // Frame
    JFrame f;

    // Constructor
    editor()
    {

        // Create a frame
        f = new JFrame("Encryter / Decrypter");
        try {
            // Set metl look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e) {
        }

        // Text component
        t = new JTextArea();
        t.setBackground(new Color	(47,79,79));
        t.setForeground(new Color	(224,255,255));
        t.setFont(new Font("consolas",0,16));

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        //setting the background colour
        mb.setBackground(new Color(102,205,170));

        // Create a menu for different options
        JMenu m1 = new JMenu("Operation");

        // Create menu items
        JMenuItem mi2 = new JMenuItem("Open file to encrypt/decrypt");
        JMenuItem mi3 = new JMenuItem("Save message");
        JMenuItem mi9 = new JMenuItem("Print_document");

        // Add action listener
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        // manually adding item to the window
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create amenu for menu
        JMenu m2 = new JMenu("Edit message");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("Exit application");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.show();
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();

        if (s.equals("cut")) {
            t.cut();
        }
        else if (s.equals("copy")) {
            t.copy();
        }
        else if (s.equals("paste")) {
            t.paste();
        }
        else if (s.equals("Save message")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(t.getText());

                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "you cancelled the operation");
        }
        else if (s.equals("Print_document")) {
            try {
                // print the file
                t.print();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if (s.equals("Open file to encrypt/decrypt")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initilize sl
                    sl = br.readLine();

                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text
                    t.setText(sl);
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(f, "you cancelled the operation");
        }
        else if (s.equals("Exit application")) {
            f.setVisible(false);
        }
    }

    // Main class
    public static void main(String args[])
    {
        editor e = new editor();
    }
}

class encrypt
{

  static String encrypted_msg="";
  encrypt(String msg,int key)
    {
      char a1,a2;
      int no,c,y;
      String ad="abcdefghijklmnopqrstuvwxyz";
      msg=msg.toLowerCase();
      int n[]=new int[26];

      for (y=0;y<26;y++) // [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26] formed
        n[y]=y+1;
      char[] ab= ad.toCharArray();

      for (int i=0;i<msg.length();i++)
        {
          no=0;
          c=-1;
          a1 = msg.charAt(i);

          for (char x:ab) // assigning numbers to each letter of the sent message
          {
            c+=1;
            if (a1==x)
              no=n[c];
          }

        if ((key+no)<=26)
          a2=(char)(a1+key);

        else
          a2=(char)(a1+key-26);
        encrypted_msg+=a2;
      }
  }

  void show()
  {
    System.out.print("\nthe encrypted message formed is\n");
    System.out.print(encrypted_msg);
  }
}

class save_message
{

  save_message(String mg)
  {
    	String FILENAME = "C:\\java_programs\\message.txt";
      BufferedWriter bw;

  		try
        {
          bw = new BufferedWriter(new FileWriter(FILENAME));
    			bw.write(mg);
          bw.close();
    		 }

      catch (Exception e)
        {
        	e.printStackTrace();
    		}
  }

  void tell()
  {
    System.out.println("\nfile saved\n");
  }
}

class read_message
{

  static String dec_msg;
  read_message()
  {
    	String FILENAME2 = "C:\\java_programs\\message.txt";
      BufferedReader br;

  		try
        {
          br = new BufferedReader(new FileReader(FILENAME2));
    			dec_msg=br.readLine();
          br.close();
    		 }

      catch (Exception e)
        {
        	e.printStackTrace();
    		}
  }

  void tell2()
  {
    System.out.println("\nfile read\n");
  }
}

class decrypt
{

  static String decrypted_msg;
  decrypt(String msg2,int dec_key)
    {

      decrypted_msg="";
      char b1,b2;
      int no2,c2,y2;
      String ad2="abcdefghijklmnopqrstuvwxyz";
      msg2=msg2.toLowerCase();
      int n2[]=new int[26];

      for (y2=0;y2<26;y2++) // [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26] formed
        n2[y2]=y2+1;
      char[] ab2= ad2.toCharArray();

      for (int j=0;j<msg2.length();j++)
        {
          no2=0;
          c2=-1;
          b1 = msg2.charAt(j);

          for (char x2:ab2) // assigning numbers to each letter of the recieved message
          {
            c2+=1;
            if (b1==x2)
              no2=n2[c2];
          }

        if ((no2-dec_key)>=0)
          b2=(char)(b1-dec_key);

        else
          b2=(char)(b1-dec_key+26);
        decrypted_msg+=b2;
      }
    }

    void show2()
    {
      System.out.print("\nthe decrypted message found is\n");
      System.out.print(decrypted_msg+"\n");
    }
}

class main
{

  public static void main(String[] args)
  {

    Scanner sc = new Scanner(System.in);
    System.out.print("enter your choice below\n");
    int ch=0;
    int key;

    while (ch!=3)
    {

      System.out.print(" 1:encrypt message\n 2:decrypt message\n 3:exit\n");
      ch=sc.nextInt();

      if (ch==1)
      {
        editor ed = new editor();
        System.out.print("enter the key you wish to use (1 to 9)\n");
        key=sc.nextInt();
        read_message obj=new read_message();
        String msg=read_message.dec_msg;
        encrypt ob=new encrypt(msg,key);
        String sv=encrypt.encrypted_msg;
        save_message ob2=new save_message(sv);
      }

      if (ch==2)
      {
        read_message ob3=new read_message();
        ob3.tell2();
        System.out.print("enter the key you recieved by the sender\n");
        System.out.print("(Warning :: original message would be lost if wrong key entered)\n");
        int dec_key=sc.nextInt();
        String rf=read_message.dec_msg;
        decrypt ob4=new decrypt(rf,dec_key);
        save_message ob5=new save_message(decrypt.decrypted_msg);
      }

    }
    System.out.print("\nthank you for using the encrypter/decrypter\n");
  }
}
