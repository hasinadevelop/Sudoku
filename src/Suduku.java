/**
 * 
 */

/**
 * @author Hasina
 * C'est une class suduku qui comose toutes les differentes méthodes de manipulation
 * du suduku
 */

class Suduku{

  //Variables
  private String difficulte = "facile";
  private int listPositionsDesValeursParDefaut[];
  private int userSuduku[][] = new int[9][9];
  private int valeursParLigne[][]   = new int[9][9];
  private int valeursParColonne[][] = new int[9][9];
  private int valeursParCase[][]    = new int[9][9];
  //End Variables

  //Constructeur par defaut
  public Suduku(){
    if ( this.difficulte.equals("facile") ) {
      this.listPositionsDesValeursParDefaut = new int[75];
    }else if ( this.difficulte.equals("moyenne") ) {
      this.listPositionsDesValeursParDefaut = new int[40];
    }else if( this.difficulte.equals("difficile") ) {
      this.listPositionsDesValeursParDefaut = new int[25];
    }
    this.misazero();
    this.generatePositionsDesValeursParDefaut();
    this.generateSuduku();
  }
  //End Constructeur par defaut

  //Constructeur avec paramètre difficulte
  public Suduku(String difficulte){
    this.difficulte = difficulte;
    if ( this.difficulte.equals("facile") ) {
      this.listPositionsDesValeursParDefaut = new int[75];
    }else if ( this.difficulte.equals("moyenne") ) {
      this.listPositionsDesValeursParDefaut = new int[40];
    }else if( this.difficulte.equals("difficile") ) {
      this.listPositionsDesValeursParDefaut = new int[25];
    }
    this.misazero();
    this.generatePositionsDesValeursParDefaut();
    this.generateSuduku();
  }
  //End Constructeur avec paramètre

  //Une fonction qui permet de mettre à zéro les valeurs de notre suduku
  private void misazero () {
    for (int i=0; i<9; i++) {
      for(int j=0; j<9; j++) {
        this.valeursParLigne[i][j] = 0;
      }
    }
  }
  //End misazero

  //La fonction qui permet de generer les grilles du suduku
  public void generateSuduku(){
    this.ajoutValeursAleatoire();
    essayeValeurs(0, 0);
  }
  //End generateSuduku

  //La fonction qui permet d'essayer les valeurs possibles sur une case
  public boolean essayeValeurs(int indexl, int indexcl){
    boolean rep = false;
    if(this.valeursParLigne[indexl][indexcl] == 0){
      int vp[] = this.valeursPossibles(indexl, indexcl);
      if(vp.length == 0){ rep = false; }
      else{
        int i = 0;
        while(!rep) {
          if(this.caseSuivante(indexl, indexcl)[0] != 10){
            this.addValeur(indexl, indexcl, vp[i]);
            rep = this.essayeValeurs(this.caseSuivante(indexl, indexcl)[0], this.caseSuivante(indexl, indexcl)[1]);
            i++;
            if(i == vp.length){ break; }
          }else{
            rep = true;
            this.addValeur(indexl, indexcl, vp[i]);
            i++;
            if(i == vp.length){ break; }
          }
          if(rep){ break; }
        }
        if(!rep){ this.addValeur(indexl, indexcl, 0); }
      }
    }else{
      if(this.caseSuivante(indexl, indexcl)[0] != 10){
        rep = this.essayeValeurs(this.caseSuivante(indexl, indexcl)[0], this.caseSuivante(indexl, indexcl)[1]);
      }else{
        rep = true;
      }
    }
    return rep;
  }
  //End essayeValeurs

  //Fonction qui permet de generer 10 nombres aléatoires et les place dans des positions aléatoires
  public void ajoutValeursAleatoire(){
    for(int i=0; i<10; i++){
      int pi = this.randSansNeuf();
      int pj = this.randSansNeuf();
      this.addValeur(pi, pj, this.randIntelligent(pi, pj));
    }
  }
  //End ajoutValeursAleatoire




  //Fonction de generation de la liste des positions des valeurs pa defaut
  private void generatePositionsDesValeursParDefaut () {
    if(this.difficulte.equals("facile")){
      int indexi[] = new int[75];
      int indexj[] = new int[75];
      String s[] = new String[75];
      for(int i=0; i<75; i++){
        indexi[i] = this.randSansNeuf();
        indexj[i] = this.randSansNeuf();
        s[i] = indexi[i]+""+indexj[i];
        this.listPositionsDesValeursParDefaut[i] = (int)Float.valueOf(s[i]).floatValue();
      }

    }else if(this.difficulte.equals("moyenne")){
      int indexi[] = new int[40];
      int indexj[] = new int[40];
      String s[] = new String[40];
      for(int i=0; i<40; i++){
        indexi[i] = this.randSansNeuf();
        indexj[i] = this.randSansNeuf();
        s[i] = indexi[i]+""+indexj[i];
        this.listPositionsDesValeursParDefaut[i] = (int)Float.valueOf(s[i]).floatValue();
      }
    }else if(this.difficulte.equals("difficile")){
      int indexi[] = new int[25];
      int indexj[] = new int[25];
      String s[] = new String[25];
      for(int i=0; i<25; i++){
        indexi[i] = this.randSansNeuf();
        indexj[i] = this.randSansNeuf();
        s[i] = indexi[i]+""+indexj[i];
        this.listPositionsDesValeursParDefaut[i] = (int)Float.valueOf(s[i]).floatValue();
      }
    }
  }
  //End generatePositionsDesValeursParDefaut

  //Fonction d'ajout de valeur dans parLigne
  public void addValeur (int indexi, int indexj, int val){
    this.valeursParLigne[indexi][indexj] = val;
    this.remplissageParColonneSelonParLigne();
    this.remplissageParCaseSelonParLigneParColonne();
  }
  //End addValeurDansParLigne

  //Fonction de Remplissage de valeursParColonne selon valeursParColonne
  public void remplissageParColonneSelonParLigne () {
    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        this.valeursParColonne[i][j] = this.valeursParLigne[j][i];
      }
    }
  }
  //End remplissageParColonneSelonParLigne

  //Fonction de remplissageParCaseSelonParLigneParColonne
  public void remplissageParCaseSelonParLigneParColonne () {
    for ( int i=0; i<9; i++ ) {
      if ( i == 0 ) {
        int il=0; int ic=0;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic > 2){ ic=0; }
        }
      }else if ( i == 1 ) {
        int il=0; int ic=3;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic > 5){ ic=3; }
        }
      }else if ( i == 2 ) {
        int il=0; int ic=6;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic > 8){ic=6;}
        }
      }else if ( i == 3 ) {
        int il=3; int ic=0;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic > 2){ ic=0; }
        }
      }else if ( i == 4 ) {
        int il=3; int ic=3;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic > 5){ ic=3; }
        }
      }else if ( i == 5 ) {
        int il=3; int ic=6;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic>8){ic=6;}
        }
      }else if ( i == 6 ) {
        int il=6; int ic=0;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic > 2){ ic=0; }
        }
      }else if ( i == 7 ) {
        int il=6; int ic=3;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic > 5){ ic=3; }
        }
      }else if ( i == 8 ) {
        int il=6; int ic=6;
        for(int j=0; j<9; j++){
          this.valeursParCase[i][j] = this.valeursParLigne[il][ic];
          if(j == 2){ il++; }
          if(j == 5){ il++; }
          ic++;
          if(ic>8){ic=6;}
        }
      }
    }

  }
  //End remplissageParCaseSelonParLigneParColonne

  //Fonction d'ajout de valeur dans le suduku de l'utilisateur
  public void addValeurUser(int indexi, int indexj, int val){
    this.userSuduku[indexi][indexj] = val;
  }
  //End addValeurUser

  //Fonction qui permet de retourner la valeur user
  public int[][] getUserSuduku(){
    return this.userSuduku;
  }
  //End getUserSuduku

  //Fonction pour savoir si le suduku est valide
  public boolean isValid(){
    boolean reponse = true;
    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        if(this.userSuduku[i][j] != this.getListValeursDUneLigne(i)[j]){ reponse = false; break;}
        else{
          reponse = true;
        }
      }
      if(!reponse){ break; }
    }
    return reponse;
  }
  //End isValid

  //Getters des valeurs parLigne
  public int[][] getListValeursParLigne () {
    return this.valeursParLigne;
  }
  //End getListValeursParLigne

  //Getters des valeurs parColonne
  public int[][] getListValeursParColonne () {
    return this.valeursParColonne;
  }
  //End getListValeursParColonne

  //Getters des valeurs parCase
  public int[][] getListValeursParCase () {
    return this.valeursParCase;
  }
  //End getListValeursParCase

  //Getters de la liste des positions des valeurs par defaut
  public int[] getListPositionsDesValeursParDefaut () {
    return this.listPositionsDesValeursParDefaut;
  }
  //End getListPositionsDesValeursParDefaut

  //Getters des valeurs d'une ligne
  public int[] getListValeursDUneLigne (int indexLigne){
    int tab[] = new int[9];
    for(int i=0; i<9; i++){
      tab[i] = this.valeursParLigne[indexLigne][i];
    }
    return tab;
  }
  //End getListValeursDUneLigne

  //Getters des valeurs d'une colonne
  public int[] getListValeursDUneColonne (int indexColonne){
    int tab[] = new int[9];
    for(int i=0; i<9; i++){
      tab[i] = this.valeursParColonne[indexColonne][i];
    }
    return tab;
  }
  //End getListValeursDUneColonne

  //Getters des valeurs d'une case
  public int[] getListValeursDUneCase (int indexCase){
    int tab[] = new int[9];
    for(int i=0; i<9; i++){
      tab[i] = this.valeursParCase[indexCase][i];
    }
    return tab;
  }
  //End getListValeursDUneCase

  //Fonction de recherche de l'index de la case selon l'index ligne et colonne
  public int getIndexCase(int indexl, int indexcl){
    int indexc = 0;
    if(indexl < 3) {
      if(indexcl < 3){ indexc = 0; }
      else if(indexcl > 2 && indexcl < 6){ indexc = 1; }
      else if(indexcl > 5){ indexc = 2; }
    }else if(indexl > 2 && indexl < 6){
      if(indexcl < 3){ indexc = 3; }
      else if(indexcl > 2 && indexcl < 6){ indexc = 4; }
      else if(indexcl > 5){ indexc = 5; }
    }else if(indexl > 5){
      if(indexcl < 3){ indexc = 6; }
      else if(indexcl > 2 && indexcl < 6){ indexc = 7; }
      else if(indexcl > 5){ indexc = 8; }
    }
    return indexc;
  }
  //End getIndexCase

  //Fonction qui retourne la case suivante
  public int[] caseSuivante(int indexl, int indexcl){
    int tab[] = new int[2];
    if(indexcl != 8){
      tab[0] = indexl;
      tab[1] = indexcl+1;
    }else{
      if(indexl != 8){
        tab[0] = indexl+1;
        tab[1] = 0;
      }else{
        tab[0] = 10;
        tab[1] = 10;
      }

    }
    return tab;
  }
  //End caseSuivante

  //Fonction qui retourne les valeurs possibles d'une case
  public int[] valeursPossibles(int indexl, int indexcl){
    if(this.getListValeursDUneLigne(indexl)[indexcl] == 0){
      int indexc = this.getIndexCase(indexl, indexcl);
      int tabl[] = this.getListValeursDUneLigne(indexl);
      int tabcl[] = this.getListValeursDUneColonne(indexcl);
      int tabc[] = this.getListValeursDUneCase(indexc);
      int tab[];
      int taille = 0;
      for(int i=1; i<=9; i++){
        if (i != tabl[0] && i != tabl[1] && i != tabl[2] && i != tabl[3] &&
        i != tabl[4] && i != tabl[5] && i != tabl[6] && i != tabl[7] &&
        i != tabl[8] && i != tabcl[0] && i != tabcl[1] && i != tabcl[2] &&
        i != tabcl[3] && i != tabcl[4] && i != tabcl[5] && i != tabcl[6] &&
        i != tabcl[7] && i != tabcl[8] && i != tabc[0] && i != tabc[1] &&
        i != tabc[2] && i != tabc[3] && i != tabc[4] && i != tabc[5] &&
        i != tabc[6] && i != tabc[7] && i != tabc[8] )
        {
          taille++;
        }
      }
      tab = new int[taille];
      for(int i=1; i<=9; i++){
        if (i != tabl[0] && i != tabl[1] && i != tabl[2] && i != tabl[3] &&
        i != tabl[4] && i != tabl[5] && i != tabl[6] && i != tabl[7] &&
        i != tabl[8] && i != tabcl[0] && i != tabcl[1] && i != tabcl[2] &&
        i != tabcl[3] && i != tabcl[4] && i != tabcl[5] && i != tabcl[6] &&
        i != tabcl[7] && i != tabcl[8] && i != tabc[0] && i != tabc[1] &&
        i != tabc[2] && i != tabc[3] && i != tabc[4] && i != tabc[5] &&
        i != tabc[6] && i != tabc[7] && i != tabc[8] )
        {
          for(int j=0; j<taille; j++){
            if(tab[j] == 0){ tab[j] = i; break; }
          }
        }
      }
      return tab;
    }else{ int tab[] = new int[0]; return tab; }
  }
  //End valeursPossibles






  //Fonction de generation d'un nombre aléatoire entre 0 et 9
  public int rand () {
    return (int)Math.floor(Math.random() * 10);
  }
  //End rand

  //Fonction de generation d'un nombre aléatoire entre 0 et 8
  public int randSansNeuf () {
    int rand = this.rand();
    while ( rand == 9 ) { rand = this.rand(); }
    return rand;
  }
  //End randSansNeuf

  //Fonction de generation d'un nombre aléatoire entre 1 et 9
  public int randSansZero () {
    int rand = this.rand();
    while ( rand == 0 ) { rand = this.rand(); }
    return rand;
  }
  //End randSansZero

  //Fonction de generation d'un nombre aléatoire en tenat compte de l'environement
  public int randIntelligent (int indexl, int indexcl) {
    int tabl[] = this.getListValeursDUneLigne(indexl);
    int tabcl[] = this.getListValeursDUneColonne(indexcl);
    int rand = this.randSansZero();
    int indexc = this.getIndexCase(indexl, indexcl);

    int tabc[] = this.getListValeursDUneCase(indexc);

    int compteur = 0;
    while ( rand == tabl[0] || rand == tabl[1] || rand == tabl[2] || rand == tabl[3] ||
            rand == tabl[4] || rand == tabl[5] || rand == tabl[6] || rand == tabl[7] ||
            rand == tabl[8] || rand == tabcl[0] || rand == tabcl[1] || rand == tabcl[2] ||
            rand == tabcl[3] || rand == tabcl[4] || rand == tabcl[5] || rand == tabcl[6] ||
            rand == tabcl[7] || rand == tabcl[8] || rand == tabc[0] || rand == tabc[1] ||
            rand == tabc[2] || rand == tabc[3] || rand == tabc[4] || rand == tabc[5] ||
            rand == tabc[6] || rand == tabc[7] || rand == tabc[8] )
    { rand = this.randSansZero(); compteur++; if(compteur == 100000){ rand = 0; break; }  }
    return rand;
  }
  //End randSansZero


}
