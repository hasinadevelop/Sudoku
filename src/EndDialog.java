import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Hasina
 *
 */
//Class principale
class EndDialog extends JDialog{
  //Variables
  private boolean reponse;
  private String valeurDeRetour = "";
  private FileWriter fw;
  private FileReader fr;
  private File f;
  private String difficulte = "";
  private int meilleurtemps = 0;
  private JLabel text = new JLabel("");
  private Font police = new Font("Arial", Font.BOLD, 16);
  private JLabel infos = new JLabel("");
  private JLabel infos2 = new JLabel("");
  private JLabel infos3 = new JLabel("");
  private JButton retour = new JButton("Retour");
  private JButton recommencer = new JButton("Recommencer");
  private JButton quitter = new JButton("Quitter");
  //End variables

  //Constructeur
  public EndDialog(JFrame parent, String title, boolean modal, boolean reponse, String difficulte, int temps){
    //Parametrage de notre dialog
    super(parent, title, modal);
    this.setSize(500, 300);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.setResizable(false);
    this.reponse = reponse;
    this.difficulte = difficulte;
    if(this.difficulte.equals("facile")){
      this.f = new File("meilleurtempsfacile.txt");
    }else if(this.difficulte.equals("moyenne")){
      this.f = new File("meilleurtempsmoyenne.txt");
    }else if(this.difficulte.equals("this.difficile")){
      this.f = new File("meilleurtempsdifficile.txt");
    }
    //End parametrage

    //Mise en place de nos contenus
    this.text.setFont(this.police);
    if(this.reponse){
      this.text.setText("Bravo, vous avez résolu avec succés le sudoku");
      this.infos.setText("Cliquer sur Recommencer pour faire une nouvelle partie.");
      this.infos2.setText("Sur Quitter pour quitter le jeu.");

      try{
        this.fr = new FileReader(this.f);
        String rep = "";
        int i = 0;
        while((i = this.fr.read()) != -1){
          rep += (char)i;
        }
        this.meilleurtemps = (int)(Float.valueOf(rep).floatValue());
        if(temps < this.meilleurtemps){
          this.meilleurtemps = temps;
          this.fw = new FileWriter(this.f);
          this.fw.write(""+temps);
          this.fw.close();
        }
      }
      catch(FileNotFoundException e){
        try{
          this.fw = new FileWriter(this.f);
          this.fw.write(""+temps);
          this.fw.close();
        }catch(FileNotFoundException ex){}catch(IOException ex){}

      }
      catch(IOException e){
        e.printStackTrace();
      }

    }else{
      this.text.setText("Désolé, vous avez fait quelques érreurs");
      this.infos.setText("Cliquer sur Retour pour poursuivre la partie.");
      this.infos2.setText("Sur Recommencer pour faire une nouvelle partie.");
      this.infos3.setText("Sur Quitter pour quitter le jeu.");
    }

    JPanel pan_content = new JPanel();

    JPanel pan_text = new JPanel();
    pan_text.setPreferredSize(new Dimension(500, 100));
    this.text.setPreferredSize(new Dimension(495, 30));
    this.text.setBackground(Color.WHITE);
    this.text.setForeground(Color.BLACK);
    this.text.setHorizontalAlignment(JLabel.CENTER);
    pan_text.add(this.text);
    pan_content.add(pan_text);

    JPanel pan_infos = new JPanel();
    pan_infos.setPreferredSize(new Dimension(500, 170));
    this.infos.setPreferredSize(new Dimension(495, 30));
    this.infos.setBackground(Color.WHITE);
    this.infos.setForeground(Color.BLACK);
    this.infos.setHorizontalAlignment(JLabel.CENTER);

    this.infos2.setPreferredSize(new Dimension(495, 30));
    this.infos2.setBackground(Color.WHITE);
    this.infos2.setForeground(Color.BLACK);
    this.infos2.setHorizontalAlignment(JLabel.CENTER);

    this.infos3.setPreferredSize(new Dimension(495, 30));
    this.infos3.setBackground(Color.WHITE);
    this.infos3.setForeground(Color.BLACK);
    this.infos3.setHorizontalAlignment(JLabel.CENTER);

    pan_infos.add(this.infos);
    pan_infos.add(this.infos2);
    pan_infos.add(this.infos3);

    pan_content.add(pan_infos);

    this.getContentPane().add(pan_content, BorderLayout.CENTER);
    //End contenus

    //Mise en place de nos controlleurs
    JPanel pan_control = new JPanel();

    this.retour.setBackground(Color.WHITE);
    this.recommencer.setBackground(Color.WHITE);
    this.quitter.setBackground(Color.WHITE);
    this.retour.setForeground(Color.BLACK);
    this.recommencer.setForeground(Color.BLACK);
    this.quitter.setForeground(Color.BLACK);

    if(!this.reponse){ pan_control.add(this.retour); }
    pan_control.add(this.recommencer);
    pan_control.add(this.quitter);

    this.getContentPane().add(pan_control, BorderLayout.SOUTH);
    this.getContentPane().setBackground(Color.WHITE);

    this.retour.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        setVisible(false);
      }
    });

    this.quitter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        setVisible(false);
        valeurDeRetour = "quitte";
      }
    });

    this.recommencer.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        setVisible(false);
        valeurDeRetour = "recommence";
      }
    });
    //End controlleurs
  }
  //End constructeur

  //La fonction qui permet d'afficher le dialog et qui retourne la reponse
  public String showDialog(){
    this.setVisible(true);
    return this.valeurDeRetour;
  }
  //End showDialog
}
//End class