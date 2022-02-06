
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JdvPanel extends JPanel
{

    //******************************************************************************
    //  MEMBERS
    //******************************************************************************
    private JdvAlgo algo;
    private int largeur,  hauteur;
    private int margin_top,  margin_left;

    //******************************************************************************
    //  CONSTRUCTORS
    //******************************************************************************
    public JdvPanel(JdvAlgo vie)
    {
        this.algo = vie;
        this.addMouseListener(new SourisListener());
    }


    @Override
    public void paintComponent(Graphics g)
    {
        this.calculate_cells_size(this.getWidth(), this.getHeight());
        
        //draw background
        g.setColor(Color.gray);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //draw terrain
        g.setColor(Color.black);
        g.fillRect(margin_left, margin_top, largeur * (algo.getTerrain().length - 2), hauteur * (algo.getTerrain()[0].length - 2));

        g.setColor(Color.white);
        for(int l = 1; l < algo.getTerrain()[0].length - 1; l++){
            for(int c = 1; c < algo.getTerrain().length - 1; c++){
                if(algo.getTerrain()[c][l] != 0){
                    g.fillRect(margin_left + (c - 1) * largeur, margin_top + (l - 1) * hauteur, largeur, hauteur);
                }
            }
        }

    }

    //******************************************************************************
    //  PRIVATE METHODS
    //******************************************************************************
    private void calculate_cells_size(int width, int height)
    {
        largeur = (int) (((float) this.getWidth()) / ((float) (algo.getTerrain().length - 2)));
        hauteur = (int) (((float) this.getHeight()) / ((float) (algo.getTerrain()[0].length - 2)));

        margin_top = (int) ((this.getHeight() - (hauteur * (algo.getTerrain()[0].length - 2))) / 2);
        margin_left = (int) ((this.getWidth() - (largeur * (algo.getTerrain().length - 2))) / 2);
    }

    //******************************************************************************
    //  PRIVATE CLASSES
    //******************************************************************************
    private class SourisListener implements MouseListener
    {

        public void mousePressed(MouseEvent e)
        {   
            if(((e.getY() - margin_top) / hauteur) + 1 < algo.getTerrain()[0].length - 1 && ((e.getX() - margin_left) / largeur) + 1 < algo.getTerrain().length - 1){
                if(algo.getTerrain()[((e.getX() - margin_left) / largeur) + 1][((e.getY() - margin_top) / hauteur) + 1 ] == 0){
                    //+1 car tout le terrain n'est pas afficher
                    algo.getTerrain()[((e.getX() - margin_left) / largeur) + 1][((e.getY() - margin_top) / hauteur) + 1] = 1;
                }else{
                    algo.getTerrain()[((e.getX() - margin_left) / largeur) + 1][((e.getY() - margin_top) / hauteur) + 1] = 0;
                }
                repaint();
            }
        }

        public void mouseClicked(MouseEvent e)
        {
        }

        public void mouseEntered(MouseEvent e)
        {
        }

        public void mouseExited(MouseEvent e)
        {
        }

        public void mouseReleased(MouseEvent e)
        {
        }

    }
}   
