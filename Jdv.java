
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.TimerTask;
import java.util.Timer;

public class Jdv extends JPanel
{

    //******************************************************************************
    //  MEMBERS
    //******************************************************************************
    //***************************************
    //  variables GUI
    private JButton button_step_by_step;
    private JButton button_play;
    private JButton button_stop;
    private JButton button_init;
    private JButton button_clear;
    private JdvPanel panel;
    private JPanel panel_button;

    //***************************************    
    //  variables de gestion
    private JdvAlgo algo;
    private JdvTimerTask timerBalle; // objet qui exécute une méthode à intervalle régulier
    private Timer timer; // gestion du temps
    private boolean animated;

    //**************************************************************************
    //  CONSTRUCTORS
    //**************************************************************************
    public Jdv()
    {
        super();
        
        
        //***************************************
        //  initialisation des variables de gestion
        algo = new JdvAlgo();
        animated = false;

        //***************************************
        //  initialisation GUI
        this.setLayout(new BorderLayout());
        
        //panels
        panel = new JdvPanel(algo);
        panel_button = new JPanel();
        panel_button.setLayout(new GridLayout(1, 5));

        //buttons
        button_step_by_step = new JButton("Step by step");
        button_play = new JButton("Play");
        button_stop = new JButton("Stop");
        button_init = new JButton("Initialisation");
        button_clear = new JButton("Clear");

        BoutonListener listener = new BoutonListener();
        this.button_step_by_step.addActionListener(listener);
        this.button_init.addActionListener(listener);
        this.button_play.addActionListener(listener);
        this.button_stop.addActionListener(listener);
        this.button_clear.addActionListener(listener);

        panel_button.add(button_step_by_step);
        panel_button.add(button_play);
        panel_button.add(button_stop);
        panel_button.add(button_init);
        panel_button.add(button_clear);


        //Add panels to frame
        this.add(panel_button, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.CENTER);

        //create timer
        timerBalle = new JdvTimerTask(algo);
        timer = new Timer();
        timer.schedule(timerBalle, 1, 100);

    }

    //**************************************************************************
    //  PRIVATE CLASS
    //**************************************************************************
    private class BoutonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent arg0)
        {
            if(arg0.getSource() == button_play){
                button_play.setEnabled(false);
                button_stop.setEnabled(true);
                animated = true;
            }else if(arg0.getSource() == button_stop){
                button_play.setEnabled(true);
                button_stop.setEnabled(false);
                animated = false;
            }else if(arg0.getSource() == button_init){
                algo.initGrille();
                panel.repaint();
            }else if(arg0.getSource() == button_step_by_step){
                button_play.setEnabled(true);
                button_stop.setEnabled(false);
                animated = false;
                algo.generationSuivante();
                panel.repaint();
            }else if(arg0.getSource() == button_clear){
                algo.clear();
                panel.repaint();
            }
        }

    }

    private class JdvTimerTask extends TimerTask
    {

        private JdvAlgo moteur;

        JdvTimerTask(JdvAlgo m)
        {
            moteur = m;
        }

        public void run()
        {
            if(animated){
                moteur.generationSuivante();
                panel.repaint();
            }
        }

    }
}
