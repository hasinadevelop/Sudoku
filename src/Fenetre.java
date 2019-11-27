import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Hasina
 *
 */
//Class principale
public class Fenetre extends JFrame {
  //Variables
  private JMenuBar menubar = new JMenuBar();
  private JMenu fichier = new JMenu("Fichier");
  private JMenu jeu = new JMenu("Jeu");
  private JMenuItem fichier_nouveauJeu = new JMenuItem("Nouveau jeu");
  private JMenuItem fichier_resoudre = new JMenuItem("Resoudre");
  private JMenuItem fichier_quitter = new JMenuItem("Quitter");
  private JMenu jeu_difficulte = new JMenu("Difficulté");
  private JMenuItem jeu_meilleurTemps = new JMenuItem("Meilleur temps");
  private JMenuItem jeu_aide = new JMenuItem("Aide");
  private JMenuItem jeu_apropos = new JMenuItem("A propos");
  private JMenuItem jeu_difficulte_facile = new JMenuItem("Facile");
  private JMenuItem jeu_difficulte_moyenne = new JMenuItem("Moyenne");
  private JMenuItem jeu_difficulte_difficile = new JMenuItem("Difficile");
  private Case text[][] = new Case[9][9];
  private String difficulte = "facile";
  private Suduku sdk;
  private JButton valider = new JButton("Valider");
  private JButton resoudre = new JButton("Resoudre");
  private JButton retour = new JButton("Retour");
  private JButton nouveau = new JButton("Nouveau");
  private JLabel label_cases_a_complete;
  private JLabel label_temps;
  private JLabel label_coups;
  private int userSuduku[][] = new int[9][9];
  private EndDialog ed;
  private int caseModif = 0;
  private GereTemps gt = new GereTemps("GereTemps");
  private int temps = 0;
  //End variables

  //Constructeur
  public Fenetre () {
    //Parametrage de la fenetre
    this.setTitle("Sudoku Vita Malagasy");
    this.setSize(730, 730);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new BorderLayout());
    this.setJMenuBar(this.menubar);
    this.difficulteActif();
    //End parametrage

    //Mise en place des menus
    this.menubar.add(this.fichier);
    this.menubar.add(this.jeu);
    this.fichier.add(this.fichier_nouveauJeu);
    this.fichier.add(this.fichier_resoudre);
    this.fichier.add(this.fichier_quitter);
    this.jeu.add(this.jeu_difficulte);
    this.jeu.add(this.jeu_meilleurTemps);
    this.jeu.add(this.jeu_aide);
    this.jeu.add(this.jeu_apropos);
    this.jeu_difficulte.add(this.jeu_difficulte_facile);
    this.jeu_difficulte.add(this.jeu_difficulte_moyenne);
    this.jeu_difficulte.add(this.jeu_difficulte_difficile);
    //End menus

    //Mise en place des listeners sur les menus
    //fichier_nouveauJeu
    this.fichier_nouveauJeu.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        sdk = new Suduku(difficulte);
        init();
      }
    });
    //End fichier_nouveauJeu
    //fichier_resoudre
    this.fichier_resoudre.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        for(int i=0; i<9; i++){
          for(int j=0; j<9; j++){
            text[i][j].addValeurParDefaut(sdk.getListValeursDUneLigne(i)[j]);
            retour.setEnabled(true);
            ((JMenuItem)e.getSource()).setEnabled(false);
            resoudre.setEnabled(false);
          }
        }
        valider.setEnabled(false);
      }
    });
    //End fichier_resoudre
    //fichier_quitter
    this.fichier_quitter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.exit(0);
      }
    });
    //End fichier_quitter
    //jeu_meilleurTemps
    this.jeu_meilleurTemps.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        InfosDialog id = new InfosDialog(null, "Meilleur temps", true, "meilleur_temps");
        id.showDialog();
      }
    });
    //End jeu_meilleurTemps
    //jeu_aide
    this.jeu_aide.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        InfosDialog id = new InfosDialog(null, "Aide", true, "aide");
        id.showDialog();
      }
    });
    //End jeu_aide
    //jeu_apropos
    this.jeu_apropos.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        InfosDialog id = new InfosDialog(null, "A propos", true, "apropos");
        id.showDialog();
      }
    });
    //End jeu_apropos
    //jeu_difficulte_facile
    this.jeu_difficulte_facile.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        difficulte = "facile";
        difficulteActif();
        JOptionPane jop = new JOptionPane();
        int opt = jop.showConfirmDialog(null, "Voulez vous commencer une nouvelle partie ? ", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(opt == JOptionPane.NO_OPTION){

        }else{
          sdk = new Suduku(difficulte);
          init();
        }
      }
    });
    //End jeu_difficulte_facile
    //jeu_difficulte_moyenne
    this.jeu_difficulte_moyenne.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        difficulte = "moyenne";
        difficulteActif();
        JOptionPane jop = new JOptionPane();
        int opt = jop.showConfirmDialog(null, "Voulez vous commencer une nouvelle partie ? ", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(opt == JOptionPane.NO_OPTION){

        }else{
          sdk = new Suduku(difficulte);
          init();
        }
      }
    });
    //End jeu_difficulte_moyenne
    //jeu_difficulte_difficile
    this.jeu_difficulte_difficile.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        difficulte = "difficile";
        difficulteActif();
        JOptionPane jop = new JOptionPane();
        int opt = jop.showConfirmDialog(null, "Voulez vous commencer une nouvelle partie ? ", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(opt == JOptionPane.NO_OPTION){

        }else{
          sdk = new Suduku(difficulte);
          init();
        }
      }
    });
    //End jeu_difficulte_difficile
    //End Listeners sur les menus

    //Mise en place des composants sur l'actualités de la partie en cours
    JPanel pan_actu = new JPanel();
    pan_actu.setPreferredSize(new Dimension(730, 40));
    pan_actu.setBackground(Color.GRAY);
    JPanel pan_caseacompleter = new JPanel();
    pan_caseacompleter.setPreferredSize(new Dimension(230, 30));
    JPanel pan_coups = new JPanel();
    pan_coups.setPreferredSize(new Dimension(230, 30));
    JPanel pan_temps = new JPanel();
    pan_temps.setPreferredSize(new Dimension(230, 30));
    this.label_cases_a_complete = new JLabel("");
    this.label_temps = new JLabel("");
    this.label_coups = new JLabel("Modifications faites: "+this.caseModif);
    pan_caseacompleter.add(this.label_cases_a_complete);
    pan_coups.add(this.label_coups);
    pan_temps.add(this.label_temps);
    pan_actu.add(pan_caseacompleter);
    pan_actu.add(pan_coups);
    pan_actu.add(pan_temps);
    this.getContentPane().add(pan_actu, BorderLayout.NORTH);
    //End Composants d'actualité

    //Mise en place du contenu principal
    JPanel content_pan = new JPanel();
    JPanel pans[] = new JPanel[9];
    for(int i=0; i<9; i++){
      pans[i] = new JPanel();
      pans[i].setPreferredSize(new Dimension(730, 60));
      for(int j=0; j<9; j++){
        this.text[i][j] = new Case();
        if(i<3 && j<3){ this.text[i][j].setBackground(Color.GRAY); }
        if(i<3 && j>2 && j<6){ this.text[i][j].setBackground(Color.WHITE); }
        if(i<3 && j>5 && j<9){ this.text[i][j].setBackground(Color.GRAY); }
        if(i>2 && i<6 && j<3){ this.text[i][j].setBackground(Color.WHITE); }
        if(i>2 && i<6 && j>2 && j<6){ this.text[i][j].setBackground(Color.GRAY); }
        if(i>2 && i<6 && j>5 && j<9){ this.text[i][j].setBackground(Color.WHITE); }
        if(i>5 && i<9 && j<3){ this.text[i][j].setBackground(Color.GRAY); }
        if(i>5 && i<9 && j>2 && j<6){ this.text[i][j].setBackground(Color.WHITE); }
        if(i>5 && i<9 && j>5 && j<9){ this.text[i][j].setBackground(Color.GRAY); }
        this.text[i][j].addKeyListener(new KeyListener(){
          public void keyPressed(KeyEvent e){}
          public void keyReleased(KeyEvent e){
            if(e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3' ||
               e.getKeyChar() == '4' || e.getKeyChar() == '5' || e.getKeyChar() == '6' ||
               e.getKeyChar() == '7' || e.getKeyChar() == '8' || e.getKeyChar() == '9')
            {
              caseModif++;
              label_coups.setText("Modifications faites: "+caseModif);

            }
            else{
              if((((JTextField)e.getComponent()).getText()).equals("1") || (((JTextField)e.getComponent()).getText()).equals("2") || (((JTextField)e.getComponent()).getText()).equals("3") ||
                 (((JTextField)e.getComponent()).getText()).equals("4") || (((JTextField)e.getComponent()).getText()).equals("5") || (((JTextField)e.getComponent()).getText()).equals("6") ||
                 (((JTextField)e.getComponent()).getText()).equals("7") || (((JTextField)e.getComponent()).getText()).equals("8") || (((JTextField)e.getComponent()).getText()).equals("9"))
              {}else{
                ((JTextField)e.getComponent()).setText("");

              }
            }
            setCaseVide();
            userSuduku = recupereValeur();
            ecouteChamps();
          }
          public void keyTyped(KeyEvent e){}
        });
        pans[i].add(this.text[i][j]);
        content_pan.add(pans[i]);
      }
    }
    this.getContentPane().add(content_pan, BorderLayout.CENTER);
    this.init(); //On appelle la fonction d'initialisation des cases
    //End contenu

    //Mise en place des controlleurs
    JPanel pan_control = new JPanel();
    pan_control.setBackground(Color.GRAY);

    //mise en place du bouton valider et son listener
    pan_control.add(this.valider);
    this.valider.setBackground(Color.WHITE);
    this.valider.setForeground(Color.BLACK);
    this.valider.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        envoyeValeurs();
        boolean reponse = sdk.isValid();
        ed = new EndDialog(null, "Resultat", true, reponse, difficulte, temps);
        String valeurDeRetour = ed.showDialog();
        if(valeurDeRetour.equals("quitte")){ System.exit(0); }
        else if(valeurDeRetour.equals("recommence")){ sdk = new Suduku(difficulte); init(); }

      }
    });
    //ENd valider

    //mise en place du bouton resoudre et son listener
    pan_control.add(this.resoudre);
    this.resoudre.setBackground(Color.WHITE);
    this.resoudre.setForeground(Color.BLACK);
    this.resoudre.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        for(int i=0; i<9; i++){
          for(int j=0; j<9; j++){
            text[i][j].addValeurParDefaut(sdk.getListValeursDUneLigne(i)[j]);
            retour.setEnabled(true);
            ((JButton)e.getSource()).setEnabled(false);
            fichier_resoudre.setEnabled(false);
          }
        }
        valider.setEnabled(false);
      }
    });
    //ENd resoudre

    //mise en place du bouton retour et son listener
    pan_control.add(this.retour);
    this.retour.setBackground(Color.WHITE);
    this.retour.setForeground(Color.BLACK);
    this.retour.setEnabled(false);
    this.retour.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        for(int i=0; i<9; i++){
          for(int j=0; j<9; j++){
            String s = userSuduku[i][j] == 0 ? "" : ""+userSuduku[i][j];
            text[i][j].setText(s);
            resoudre.setEnabled(true);
            fichier_resoudre.setEnabled(true);
            ((JButton)e.getSource()).setEnabled(false);
            text[i][j].setEnabled(true);
          }
        }
        int taille = 75;
        if(difficulte.equals("facile")){taille = 75;}
        else if(difficulte.equals("moyenne")){taille = 40;}
        else if(difficulte.equals("difficile")){taille = 25;}
        int indexi[] = new int[taille];
        int indexj[] = new int[taille];
        int c=0;
        for( int i=0; i<sdk.getListPositionsDesValeursParDefaut().length; i++  ){
          indexi[c] = (int)(sdk.getListPositionsDesValeursParDefaut()[i] / 10);
          indexj[c] = sdk.getListPositionsDesValeursParDefaut()[i] % 10;
          c++;
        }
        c=0;
        for( int i=0; i<indexi.length; i++ ) {
          text[indexi[i]][indexj[c]].setEnabled(false);
          c++;
        }
        userSuduku = recupereValeur();
        ecouteChamps();
      }
    });
    //ENd retour

    //Mise en place du bouton nouveau et son listener
    pan_control.add(this.nouveau);
    this.nouveau.setBackground(Color.WHITE);
    this.nouveau.setForeground(Color.BLACK);
    this.getContentPane().add(pan_control, BorderLayout.SOUTH);
    this.nouveau.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        sdk = new Suduku(difficulte);
        init();
      }
    });
    //End nouveau
    //End controlleurs

    this.setVisible(true);
  }
  //End constructeur

  //La fonction qui permet d'initialiser les cases
  public void init(){
    this.resoudre.setEnabled(true);
    this.fichier_resoudre.setEnabled(true);
    this.retour.setEnabled(false);
    this.sdk = new Suduku(this.difficulte);
    int taille = 75;
    if ( this.difficulte.equals("facile") ) {
      taille = 75;
    }else if ( this.difficulte.equals("moyenne") ) {
      taille = 40;
    }else if ( this.difficulte.equals("difficile") ) {
      taille = 25;
    }
    int indexi[] = new int[taille];
    int indexj[] = new int[taille];
    int c=0;
    for( int i=0; i<sdk.getListPositionsDesValeursParDefaut().length; i++  ){
      indexi[c] = (int)(sdk.getListPositionsDesValeursParDefaut()[i] / 10);
      indexj[c] = sdk.getListPositionsDesValeursParDefaut()[i] % 10;
      c++;
    }
    this.effacerTout();
    c=0;
    for( int i=0; i<indexi.length; i++ ) {
      this.text[indexi[i]][indexj[c]].addValeurParDefaut(this.sdk.getListValeursDUneLigne(indexi[i])[indexj[c]]);
      c++;
    }
    this.userSuduku = this.recupereValeur();
    this.setCaseVide();
    this.setDureePartie();
    this.ecouteChamps();

  }
  //End init

  //La fonction qui permet d'effacer les valeurs dans les cases
  public void effacerTout(){
    this.caseModif = 0;
    this.label_coups.setText("Modifications faites: "+this.caseModif);
    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        this.text[i][j].addValeurParDefaut(0);
      }
    }
  }
  //End effacerTout

  //La fonction qui permet de savoir le nombre de case vide et met à jour le label correspondans
  public void setCaseVide(){
    this.envoyeValeurs();
    int casevide = 0;
    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        if(this.sdk.getUserSuduku()[i][j] == 0){
          casevide++;
        }
      }
    }
    this.label_cases_a_complete.setText("Cases à completer: "+casevide);
  }
  //End setCaseVide

  //La fonction qui permet de connaitre la durée de la partie
  public void setDureePartie(){
    this.gt.terminer();
    this.gt = new GereTemps("GereTemps");
    this.gt.start();
  }
  //End setDureePartie

  //La fonction qui permet de lancer l'écoute des champs
  public void ecouteChamps(){
    EcouteChamps ec = new EcouteChamps("EcouteChamps");
    ec.start();
  }
  //End ecouteChamps

  //La fonction qui permet de récuperer toutes les valeurs des cases
  public int[][] recupereValeur(){
    int tab[][] = new int[9][9];
    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        try{ tab[i][j] = (int)(Float.valueOf(this.text[i][j].getText()).floatValue()); }
        catch(NumberFormatException ex){
          tab[i][j] = 0;
        }
      }
    }
    return tab;
  }
  //End recupereValeur

  //La fonction qui permet d'envoyer les valeurs à notre objet suduku
  public void envoyeValeurs(){
    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        this.sdk.addValeurUser(i, j, this.recupereValeur()[i][j]);
      }
    }
  }
  //End envoyeValeurs

  //Fonction de coloration du menu de difficulte
  public void difficulteActif(){
    if(this.difficulte.equals("facile")){
      this.jeu_difficulte_facile.setBackground(Color.GRAY);
      this.jeu_difficulte_moyenne.setBackground(Color.WHITE);
      this.jeu_difficulte_difficile.setBackground(Color.WHITE);
    }
    if(this.difficulte.equals("moyenne")){
      this.jeu_difficulte_moyenne.setBackground(Color.GRAY);
      this.jeu_difficulte_facile.setBackground(Color.WHITE);
      this.jeu_difficulte_difficile.setBackground(Color.WHITE);
    }
    if(this.difficulte.equals("difficile")){
      this.jeu_difficulte_difficile.setBackground(Color.GRAY);
      this.jeu_difficulte_facile.setBackground(Color.WHITE);
      this.jeu_difficulte_moyenne.setBackground(Color.WHITE);
    }
  }
  //End difficulteActif

  //Fonction qui permet de retourner un temps en secondes
  public int tempsSeconde(int h, int m, int s){
    return ((((h * 60) + m) * 60) + s);
  }
  //End tempsSeconde

  public class GereTemps extends Thread{
    int s = 0;
    int m = 0;
    int h = 0;
    boolean lance = true;
    public GereTemps(String name){
      super(name);
    }
    public void run(){
      while(lance){
        s++;
        if(s == 60){
          m++;
          s = 0;
        }
        if(m == 60){
          h++;
          m = 0;
        }
        if(m == 0 && h == 0){
          label_temps.setText("Temps de jeu: "+s+"sec");
        }else if(h == 0){
          label_temps.setText("Temps de jeu: "+m+"min "+s+"sec");
        }else{
          label_temps.setText("Temps de jeu: "+h+"h "+m+"min "+s+"sec");
        }
        temps = tempsSeconde(h, m, s);
        try{this.sleep(1000);}
        catch(InterruptedException e){}
      }

    }
    public void terminer(){
      this.lance = false;
    }
  }

  public class EcouteChamps extends Thread{
    private boolean lance = true;
    public EcouteChamps(String name){
      super(name);
    }
    public void run(){
      for(int i=0; i<9; i++){
        for(int j=0; j<9; j++){
          if(userSuduku[i][j] == 0){
            lance = false;
          }
        }
      }
      if(!lance){ valider.setEnabled(false); }
      else{ valider.setEnabled(true); }
    }
  }
}
//End class

