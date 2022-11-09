package notedtr;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NotEdtr {

    public static void main(String[] args) {

        JFrame f = new JFrame("Text Editor");
        JMenuBar menuBar;

        JButton btngeri, btnileri, italic, bld;
        JTextArea area;
        JComboBox cmb, renk;
        area = new JTextArea();
        area.setBounds(20, 20, 450, 450);

        menuBar = new JMenuBar();
        f.setJMenuBar(menuBar);

        String Fnt[] = {"İtalic", "CENTER_BASELINE", "PLAIN", "HANGING_BASELINE"};

        cmb = new JComboBox<>(Fnt);
        

        String Renkler[] = {"Mavi", "Kırmızı", "Yeşil", "Gri", "Beyaz","sarı"};

        renk = new JComboBox<>(Renkler);

        btnileri = new JButton("İleri");
        btnileri.setBounds(20, 10, 80, 30);

        btngeri = new JButton("Geri");
        btngeri.setBounds(120, 10, 80, 30);

        bld = new JButton("Bold");
        bld.setBounds(320, 10, 80, 30);

        değişken dgşkn = new değişken();//Değişken tanımlmak için sınıf

        Font fn = new Font("", Font.BOLD, 30);

        Stack redo = new Stack();
        Stack undo = new Stack();

        btngeri.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (undo.empty() != true) {

                    dugum sonEleman = (dugum) undo.pop();

                    redo.push(sonEleman);

                    String yazi = area.getText();

                    String sonYazi = yazi.substring(0, sonEleman.index);

                    area.setText(sonYazi);
                }
            }

        });
        area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ev) {
                if (ev.isControlDown() && ev.getKeyCode() == KeyEvent.VK_Z) {

                    if (undo.empty() != true) {

                        dugum sonEleman = (dugum) undo.pop();

                        redo.push(sonEleman);

                        String yazi = area.getText();

                        String sonYazi = yazi.substring(0, sonEleman.index);

                        area.setText(sonYazi);
                    }
                }
                if (ev.isControlDown() && ev.getKeyCode() == KeyEvent.VK_Y) {
                    if (redo.empty() != true) {

                        String yazi = area.getText();
                        dugum sonEleman = (dugum) redo.pop();

                        yazi = yazi + " " + sonEleman.kelime;

                        area.setText(yazi);
                    }
                }

            }

        });

        area.addKeyListener(new KeyAdapter() {

            @Override
            

            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyChar() == ' ') {
                    String yazi = area.getText();

                    String kelime = yazi.substring(dgşkn.sonİndex, yazi.length());

                    undo.push(new dugum(dgşkn.i, kelime));

                    dgşkn.sonİndex = (dgşkn.i) + 1;
                }

                (dgşkn.i)++;
            }

        });

        btnileri.addActionListener(new ActionListener() {
            
            @Override
            
            public void actionPerformed(ActionEvent e) {
                if (redo.empty() != true) {
                    
                    String yazi = area.getText();
                    dugum sonEleman = (dugum) redo.pop();
                    
                    yazi = yazi + " " + sonEleman.kelime;

                    area.setText(yazi);
                }
            }

        }
        );

        bld.addActionListener(new ActionListener() {
            private Object font;

            @Override
            public void actionPerformed(ActionEvent e) {

                area.setFont(fn);
            }
        }
        );

        cmb.addActionListener(new ActionListener() {
            private Object font;

            @Override
            public void actionPerformed(ActionEvent e) {
                String test = (String) cmb.getItemAt(cmb.getSelectedIndex());
                switch (test) {
                    case "İtalic" -> {
                        Font fnt1 = new Font("İtalic", Font.ITALIC, 25);
                        area.setFont(fnt1);
                    }

                    case "PLAIN" -> {
                        Font fn3 = new Font("", Font.PLAIN, 20);
                        area.setFont(fn3);
                    }
                    case "HANGING_BASELINE" -> {
                        Font fn4 = new Font("", Font.HANGING_BASELINE, 15);
                        area.setFont(fn4);
                    }
                    case "CENTER_BASELINE" -> {
                        Font fn5 = new Font("", Font.CENTER_BASELINE, 35);
                        area.setFont(fn5);
                    }

                }

            }
        });
        renk.addActionListener(new ActionListener() {
            private Object font;

            @Override
            public void actionPerformed(ActionEvent e) {
                String test = (String) renk.getItemAt(renk.getSelectedIndex());
                switch (test) {
                    case "Kırmızı" -> {

                        area.setBackground(Color.red);
                    }

                    case "Mavi" -> {

                        area.setBackground(Color.BLUE);
                    }
                    case "Yeşil" -> {

                        area.setBackground(Color.GREEN);
                    }
                    case "Gri" -> {

                        area.setBackground(Color.GRAY);
                    }
                    case "Beyaz" -> {

                        area.setBackground(Color.white);
                    }
                    case "sarı" -> {

                        area.setBackground(Color.yellow);
                    }

                }

            }
        });
       
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        cmb.setSize(10, 40);
        menuBar.add(btnileri);
        menuBar.add(btngeri);
        menuBar.add(bld);
        menuBar.add(renk);
        menuBar.add(cmb);
        f.add(area);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLayout(null);

        f.setVisible(true);

    }

}
