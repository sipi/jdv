
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author clement
 */
public class Start {
    public static void main(String[] args){
        JFrame f = new JFrame("JDV");
        f.setSize(800,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Jdv());
        f.setVisible(true);
    }
}
