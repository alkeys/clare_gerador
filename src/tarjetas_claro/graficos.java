package tarjetas_claro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class graficos extends JFrame {

    public graficos() {
        setTitle("claro ");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension pant = getToolkit().getScreenSize();
        setLocation(pant.width / 4, pant.height / 4);
        panels xda = new panels();
        add(xda, BorderLayout.NORTH);
        texto lol = new texto();
        getContentPane().add(lol, BorderLayout.CENTER);
        pack();

    }
}

 class panels extends JPanel {

   protected Image fondo;

    public panels() {
        setBackground(Color.lightGray);
        try {
            fondo = ImageIO.read(new File("src//tarjetas_claro//fodo.jpg"));
        } catch (IOException e) {
        }
        setLayout(new BorderLayout());
        botones xd = new botones();
        add(xd, BorderLayout.NORTH);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 1, 5, 500, 450, this);
    }

}

class botones extends JPanel {

   protected String cant_numer = "0339";
  protected  JTextArea area = new JTextArea("0339");
  protected  JLabel cont = new JLabel();

    public botones() {
        setLayout(new GridLayout(2, 2, 25, 10));
        JButton xda = new JButton(new eventos("generar", 0));
        add(xda, BorderLayout.NORTH);
        add(new JButton(new eventos("borrar", 1)), BorderLayout.NORTH);
        add(new JLabel());
        area.addCaretListener(new carater());
        ImageIcon ic;

        ic = new ImageIcon("ico.jpeg");
        add(new JLabel(ic));
        add(area, BorderLayout.SOUTH);
        add(cont, BorderLayout.SOUTH);
    }

    private class carater implements CaretListener {

        @Override
        public void caretUpdate(CaretEvent e) {
            /*caretes actualisacion */
            cant_numer = area.getText();
            cont.setText(" N =" + cant_numer.length());
        }

    }

    private class eventos extends AbstractAction {

        public eventos(String nombre, int tipo) {
            putValue(javax.swing.Action.NAME, nombre);
            putValue("tipo", tipo);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = Integer.parseInt(String.valueOf(getValue("tipo")));
            switch (i) {
                case 0 -> {
                    String aux_total = "", aux = "", aux_cant;
                    int au = 0;
                    aux_cant = cant_numer;
                    int aux_cont = 0;
                    int t = 0;
                    if (aux_cant.length() < 16) {
                        if (aux_cant.length() == 10) {
                            for (int q = 0; q < 10; q++) {
                                if (aux_cont < 10) {
                                    aux += aux_cont;
                                    aux_cont++;
                                } else {
                                    aux_cont = 0;
                                }
                                //bucle para generar los numero de 10 diguitos
                                for (int j = 0; j < 4; j++) {
                                    do {
                                        t = (int) (Math.random() * 100);
                                    } while (t > 9);
                                    aux += t;

                                }
                                aux_total += cant_numer + aux + "\n" + "\n";
                                aux = "";
                            }
                        } else {
                            aux = "";
                            for (int q = 0; q < 10; q++) {
                                for (int j = 0; j < (15 - aux_cant.length()); j++) {
                                    do {
                                        t = (int) (Math.random() * 100);
                                    } while (t > 9);
                                    aux += t;
                                }
                               aux_total += cant_numer + aux + "\n" + "\n";
                               aux = "";
                            }
                        }
                        texto.text.setText(aux_total);
                        aux = "";
                        texto.text.selectAll();
                        texto.text.setForeground(Color.MAGENTA);

                    } else {
                        JOptionPane.showMessageDialog(null, "sobrepasa la cantidad de digitos");
                    }
                }
                case 1 -> {
                    texto.text.setText("");
                    area.setText("0339");
                }
            }
        }

    }

}

class texto extends JPanel {

    static JTextArea text = new JTextArea(20, 25);

    public texto() {
        text.setFont(new Font("ariel", 30, 18));
        add(text);
    }

}
