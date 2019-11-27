import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * @author Hasina
 *
 */
//Class principale
class InfosDialog extends JDialog{
  //Variables
  private String infos = "";
  private JTextArea text = new JTextArea("");
  private JPanel pan_content = new JPanel();
  //End variables

  //Constructeur
  public InfosDialog(JFrame parent, String title, boolean modal, String infos){
    //Parametrage de notre dialog
    super(parent, title, modal);
    this.setSize(500, 200);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.infos = infos;
    this.init();
    //End parametrage
  }

  public void init(){
    this.getContentPane().add(this.pan_content, BorderLayout.CENTER);
    if(this.infos.equals("aide")){
      JLabel l1 = new JLabel("Pour jouer, entrer un chiffre entre 1 et 9 dans les");
      JLabel l2 = new JLabel("cases de telle sorte que chaque chiffre ne doit s'");
      JLabel l3 = new JLabel("afficher qu'une seule fois sur une ligne, sur une");
      JLabel l4 = new JLabel("colonne et sur une case de 9 chiffres (de meme couleur)");
      l1.setHorizontalAlignment(JLabel.CENTER);
      l1.setBackground(Color.WHITE);
      l1.setForeground(Color.BLACK);
      l2.setHorizontalAlignment(JLabel.CENTER);
      l2.setBackground(Color.WHITE);
      l2.setForeground(Color.BLACK);
      l3.setHorizontalAlignment(JLabel.CENTER);
      l3.setBackground(Color.WHITE);
      l3.setForeground(Color.BLACK);
      l4.setHorizontalAlignment(JLabel.CENTER);
      l4.setBackground(Color.WHITE);
      l4.setForeground(Color.BLACK);
      JPanel p1 = new JPanel();
      p1.add(l1);
      p1.setPreferredSize(new Dimension(500, 30));
      JPanel p2 = new JPanel();
      p2.add(l2);
      p2.setPreferredSize(new Dimension(500, 30));
      JPanel p3 = new JPanel();
      p3.add(l3);
      p3.setPreferredSize(new Dimension(500, 30));
      JPanel p4 = new JPanel();
      p4.add(l4);
      p4.setPreferredSize(new Dimension(500, 30));
      this.pan_content.add(p1);
      this.pan_content.add(p2);
      this.pan_content.add(p3);
      this.pan_content.add(p4);

    }else if(this.infos.equals("apropos")){
      JLabel l1 = new JLabel("C'est une application développé par");
      JLabel l2 = new JLabel("ANDRIANTSEHENO RASOAMAHARO Ny Hasina Liantsoa");
      JLabel l3 = new JLabel("le 21 décembre 2018.");
      JLabel l4 = new JLabel("Version de l'application: 1.1.0");
      JLabel l5 = new JLabel("(c) copyright 2018.");
      l1.setHorizontalAlignment(JLabel.CENTER);
      l1.setBackground(Color.WHITE);
      l1.setForeground(Color.BLACK);
      l2.setHorizontalAlignment(JLabel.CENTER);
      l2.setBackground(Color.WHITE);
      l2.setForeground(Color.BLACK);
      l3.setHorizontalAlignment(JLabel.CENTER);
      l3.setBackground(Color.WHITE);
      l3.setForeground(Color.BLACK);
      l4.setHorizontalAlignment(JLabel.CENTER);
      l4.setBackground(Color.WHITE);
      l4.setForeground(Color.BLACK);
      l5.setHorizontalAlignment(JLabel.CENTER);
      l5.setBackground(Color.WHITE);
      l5.setForeground(Color.BLACK);
      JPanel p1 = new JPanel();
      p1.add(l1);
      p1.setPreferredSize(new Dimension(500, 30));
      JPanel p2 = new JPanel();
      p2.add(l2);
      p2.setPreferredSize(new Dimension(500, 30));
      JPanel p3 = new JPanel();
      p3.add(l3);
      p3.setPreferredSize(new Dimension(500, 30));
      JPanel p4 = new JPanel();
      p4.add(l4);
      p4.setPreferredSize(new Dimension(500, 30));
      JPanel p5 = new JPanel();
      p5.add(l5);
      p5.setPreferredSize(new Dimension(500, 30));
      this.pan_content.add(p1);
      this.pan_content.add(p2);
      this.pan_content.add(p3);
      this.pan_content.add(p4);
      this.pan_content.add(p5);
    }else if(this.infos.equals("meilleur_temps")){
      String mtf = "";
      String mtm = "";
      String mtd = "";
      FileReader frf, frm, frd;

      try{
        frf = new FileReader(new File("meilleurtempsfacile.txt"));
        frm = new FileReader(new File("meilleurtempsmoyenne.txt"));
        frd = new FileReader(new File("meilleurtempsdifficile.txt"));

        int i = 0;
        while((i = frf.read()) != -1){
          mtf += (char)i;
        }
        i = 0;
        while((i = frm.read()) != -1){
          mtm += (char)i;
        }
        i = 0;
        while((i = frd.read()) != -1){
          mtd += (char)i;
        }
      }
      catch(FileNotFoundException e){System.out.print("File not found");}
      catch(IOException e){}

      int imtf = 0;
      int imtm = 0;
      int imtd = 0; 
      
      try{
    	  imtf = (int)Float.valueOf(mtf).floatValue();
          imtm = (int)Float.valueOf(mtm).floatValue();
          imtd = (int)Float.valueOf(mtd).floatValue();  
      }catch(NumberFormatException e){
    	  System.out.print(mtf+", "+mtm+", "+mtd);
      }
      

      int mmtf = (int)(imtf / 60);
      int hmtf = (int)(mmtf / 60);
      mmtf = mmtf % 60;
      int smtf = imtf % 60;

      int mmtm = (int)(imtm / 60);
      int hmtm = (int)(mmtm / 60);
      mmtm = mmtm % 60;
      int smtm = imtm % 60;

      int mmtd = (int)(imtd / 60);
      int hmtd = (int)(mmtd / 60);
      mmtd = mmtd % 60;
      int smtd = imtd % 60;

      String repf = "";
      String repm = "";
      String repd = "";

      if(mmtf == 0 && hmtf == 0){
        repf = smtf+" sec";
      }else if(hmtf == 0){
        repf = mmtf+" min "+smtf+" sec";
      }else{
        repf = hmtf+" h "+mmtf+" min "+smtf+"sec";
      }

      if(mmtm == 0 && hmtm == 0){
        repm = smtm+" sec";
      }else if(hmtm == 0){
        repm = mmtm+" min "+smtm+" sec";
      }else{
        repm = hmtm+" h "+mmtm+" min "+smtm+"sec";
      }

      if(mmtd == 0 && hmtd == 0){
        repd = smtd+" sec";
      }else if(hmtd == 0){
        repd = mmtd+" min "+smtd+" sec";
      }else{
        repd = hmtd+" h "+mmtd+" min "+smtd+"sec";
      }

      JPanel pan_difficulte = new JPanel();
      JPanel pan_meilleur_temps = new JPanel();
      pan_difficulte.setPreferredSize(new Dimension(190, 30));
      pan_meilleur_temps.setPreferredSize(new Dimension(290, 30));
      JLabel l_difficulte = new JLabel("Difficulté");
      JLabel l_meilleur_temps = new JLabel("Meilleur temps");
      l_difficulte.setHorizontalAlignment(JLabel.CENTER);
      l_meilleur_temps.setHorizontalAlignment(JLabel.CENTER);
      pan_difficulte.add(l_difficulte);
      pan_meilleur_temps.add(l_meilleur_temps);

      JPanel pan_facile = new JPanel();
      JPanel pan_meilleur_temps_facile = new JPanel();
      pan_facile.setPreferredSize(new Dimension(190, 30));
      pan_meilleur_temps_facile.setPreferredSize(new Dimension(290, 30));
      JLabel l_facile = new JLabel("Facile");
      JLabel l_meilleur_temps_facile = new JLabel(repf);
      l_facile.setHorizontalAlignment(JLabel.CENTER);
      l_meilleur_temps_facile.setHorizontalAlignment(JLabel.CENTER);
      pan_facile.add(l_facile);
      pan_meilleur_temps_facile.add(l_meilleur_temps_facile);

      JPanel pan_moyenne = new JPanel();
      JPanel pan_meilleur_temps_moyenne = new JPanel();
      pan_moyenne.setPreferredSize(new Dimension(190, 30));
      pan_meilleur_temps_moyenne.setPreferredSize(new Dimension(290, 30));
      JLabel l_moyenne = new JLabel("Moyenne");
      JLabel l_meilleur_temps_moyenne = new JLabel(repm);
      l_moyenne.setHorizontalAlignment(JLabel.CENTER);
      l_meilleur_temps_moyenne.setHorizontalAlignment(JLabel.CENTER);
      pan_moyenne.add(l_moyenne);
      pan_meilleur_temps_moyenne.add(l_meilleur_temps_moyenne);

      JPanel pan_difficile = new JPanel();
      JPanel pan_meilleur_temps_difficile = new JPanel();
      pan_difficile.setPreferredSize(new Dimension(190, 30));
      pan_meilleur_temps_difficile.setPreferredSize(new Dimension(290, 30));
      JLabel l_difficile = new JLabel("Difficile");
      JLabel l_meilleur_temps_difficile = new JLabel(repd);
      l_difficile.setHorizontalAlignment(JLabel.CENTER);
      l_meilleur_temps_difficile.setHorizontalAlignment(JLabel.CENTER);
      pan_difficile.add(l_difficile);
      pan_meilleur_temps_difficile.add(l_meilleur_temps_difficile);

      this.pan_content.add(pan_difficulte);
      this.pan_content.add(pan_meilleur_temps);

      this.pan_content.add(pan_facile);
      this.pan_content.add(pan_meilleur_temps_facile);

      this.pan_content.add(pan_moyenne);
      this.pan_content.add(pan_meilleur_temps_moyenne);

      this.pan_content.add(pan_difficile);
      this.pan_content.add(pan_meilleur_temps_difficile);
    }
  }

  public void showDialog(){
    this.setVisible(true);
  }

}
//End class