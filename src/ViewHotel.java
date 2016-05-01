//add view hotel function with GUI to display pictures of the hotel

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;


public class ViewHotel extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton jButton1;
    private JButton jButton4;
    private JLabel jLabel2;
    private JButton jButton3;
    private JButton jButton2;
    private JTextField jTextField1;
    private JLabel jLabel1;
    private int count = 0;
    private int num = 0;
    private ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewHotel inst = new ViewHotel();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public ViewHotel() {
        super();
        initGUI();
    }

    private void initGUI() {
        try {
            GroupLayout thisLayout = new GroupLayout((JComponent) getContentPane());
            getContentPane().setLayout(thisLayout);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("View Hotel");
            {
                jLabel1 = new JLabel();
                jLabel1.setText("Path");
            }
            {
                jTextField1 = new JTextField();
            }
            {
                jButton1 = new JButton();
                jButton1.setText("Open");
                jButton1.addActionListener(this);
            }
            {
                jButton4 = new JButton();
                jButton4.setText("Add to favorite");
                jButton4.addActionListener(this);
            }
            {
                jLabel2 = new JLabel();
                jLabel2.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
                jLabel2.setSize(600, 450);
            }
            {
                jButton2 = new JButton();
                jButton2.setText("Previous");
                jButton2.addActionListener(this);
            }
            {
                jButton3 = new JButton();
                jButton3.setText("Next");
                jButton3.addActionListener(this);
            }
            thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            //.addComponent(jButton4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(thisLayout.createParallelGroup()
                            .addComponent(jLabel2, GroupLayout.Alignment.LEADING, 0, 496, Short.MAX_VALUE)
                            .addGroup(thisLayout.createSequentialGroup()
                                    .addGap(210)
                                    .addGroup(thisLayout.createParallelGroup()
                                            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
                                                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 11, Short.MAX_VALUE))
                                            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
                                                    .addGap(11)
                                                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                    .addGap(253)))
                    .addContainerGap(24, 24));
            thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
                    .addGroup(thisLayout.createParallelGroup()
                            .addComponent(jButton2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
                                    .addGap(24)
                                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(thisLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
                                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 608, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
                                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
                                    .addGap(28)
                                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                    .addGap(19)
                                    .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 20, Short.MAX_VALUE))));
            pack();
            this.setSize(800, 600);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == jButton1) {
            JFileChooser fc = new JFileChooser();
            int returnval = fc.showOpenDialog(this);
            if (returnval == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                String fileName = f.getName();
                String filePath = fc.getSelectedFile().getAbsolutePath();
                jTextField1.setText(filePath);
                //System.out.println(filePath);
                this.setTitle(fileName + "View Hotel");
                ImageIcon image = new ImageIcon(filePath);
                int width = image.getIconWidth();
                int height = image.getIconHeight();
                if (width > height) {
                    Image scaledImage = image.getImage().getScaledInstance(600, 450, Image.SCALE_DEFAULT);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    jLabel2.setIcon(scaledIcon);
                    jLabel2.setHorizontalAlignment(0);
                    jLabel2.setVerticalAlignment(0);
                } else {
                    Image scaledImage = image.getImage().getScaledInstance(300, 450, Image.SCALE_DEFAULT);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    jLabel2.setIcon(scaledIcon);
                    jLabel2.setHorizontalAlignment(0);
                    jLabel2.setVerticalAlignment(0);
                }
                File[] fileList = f.getParentFile().listFiles();
                //System.out.println(fileList.length);
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isFile()) {
                        String[] part = fileList[i].getName().split("\\.");
                        if (part[1].equals("jpg") || part[1].equals("JPG") || part[1].equals("GIF") || part[1].equals("gif") || part[1].equals("png") || part[1].equals("PNG") || part[1].equals("bmp") || part[1].equals("BMP")) {
                            //System.out.println(part[1]);
                            list.add(fileList[i].getAbsolutePath());
                            num++;
                            //System.out.println(fileList[i].getAbsoluteFile());
                            if (fileList[i].getAbsolutePath().equals(filePath)) {
                                count = num;
                            }
                        }
                    }
                }
                //System.out.println(num+"\t"+count);
            }
        } else if (e.getSource() == jButton2) {
            if (count >= 1) {
                String path = list.get(count - 1);
                //System.out.println(path);
                ImageIcon image = new ImageIcon(path);
                int width = image.getIconWidth();
                int height = image.getIconHeight();
                if (width > height) {
                    Image scaledImage = image.getImage().getScaledInstance(600, 450, Image.SCALE_DEFAULT);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    jLabel2.setIcon(scaledIcon);
                    jLabel2.setHorizontalAlignment(0);
                    jLabel2.setVerticalAlignment(0);

                } else {
                    Image scaledImage = image.getImage().getScaledInstance(300, 450, Image.SCALE_DEFAULT);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    jLabel2.setIcon(scaledIcon);
                    jLabel2.setHorizontalAlignment(0);
                    jLabel2.setVerticalAlignment(0);
                }
                jTextField1.setText(path);
                File file = new File(path);
                this.setTitle(file.getName() + "View Hotel");
                count--;
                //System.out.println(path+"\t"+count);
            } else {
                JOptionPane.showMessageDialog(null, "Reach the first picture");
            }
        } else if (e.getSource() == jButton3) {
            if (count < list.size() - 1) {
                //System.out.println(list.get(0));
                String path = list.get(count + 1);
                ImageIcon image = new ImageIcon(path);
                int width = image.getIconWidth();
                int height = image.getIconHeight();
                if (width > height) {
                    Image scaledImage = image.getImage().getScaledInstance(600, 450, Image.SCALE_DEFAULT);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    jLabel2.setIcon(scaledIcon);
                    jLabel2.setHorizontalAlignment(0);
                    jLabel2.setVerticalAlignment(0);
                } else {
                    Image scaledImage = image.getImage().getScaledInstance(300, 450, Image.SCALE_DEFAULT);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    jLabel2.setIcon(scaledIcon);
                    jLabel2.setHorizontalAlignment(0);
                    jLabel2.setVerticalAlignment(0);
                }
                jTextField1.setText(path);
                File file = new File(path);
                this.setTitle(file.getName() + "View Hotel");
                count++;
                //System.out.println(path+"\t"+count);
            } else {
                JOptionPane.showMessageDialog(null, "Reach the last picture");
            }
        } else if (e.getSource() == jButton4) {
            if (jTextField1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please open a picture");
            } else {
                try {
                    FileInputStream fin = new FileInputStream(jTextField1.getText());
                    BufferedInputStream bin = new BufferedInputStream(fin);
                    String filename = (new File(jTextField1.getText())).getName();
                    FileOutputStream fou = new FileOutputStream("d://favorite//" + filename);
                    BufferedOutputStream bou = new BufferedOutputStream(fou);
                    byte[] buff = new byte[1024 * 10];
                    int len;
                    while ((len = bin.read(buff)) != -1) {
                        bou.write(buff, 0, len);
                    }
                    bou.flush();
                    bou.close();
                    bin.close();
                    fou.close();
                    fin.close();
                    (new File(jTextField1.getText())).delete();
                    list.remove(count);
                    JOptionPane.showMessageDialog(null, "Add to favorite successfully！");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "File read error");
                }

            }
        }


    }
}
