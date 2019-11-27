import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;


/**
 * @author Hasina
 *
 */
//class principale
public class Case extends JTextField{
  //Variables
  private Font police = new Font("Arial", Font.BOLD, 32);

  //End variables

  //Constructeur
  public Case () {
    this.setFont(this.police);
    this.setHorizontalAlignment(JTextField.CENTER);
    this.setPreferredSize(new Dimension(50,50));
    this.setText("");
  }
  //End contrusteur

  //Une fonction qui permet d'ajouter une valeur par defaut
  public void addValeurParDefaut(int val){
    this.setEnabled(true);
    String text = val == 0 ? "" : ""+val;
    if(val != 0){ this.setEnabled(false); }
    this.setText(text);

  }
  //End addValeurParDefaut
}

//End class
