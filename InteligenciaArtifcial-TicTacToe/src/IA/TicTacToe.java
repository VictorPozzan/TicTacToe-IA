package IA;

import javax.swing.JOptionPane;

/**
 * @author Victor Augusto Pozzan
 */
public class TicTacToe extends javax.swing.JFrame {
    String x = "X", y = "O";
    int Tabuleiro[], VitoriaPlayer = 0, VitoriaComp = 0, Empates = 0, jogada=0, turno=0;
    boolean playerOn, computerOn;
    
    public TicTacToe(){
        initComponents();
        gameplay();
    }

    public int faz2(){
        if(Tabuleiro[5]==2){
            return 5;
        }else{
            for(int i=8; i>1; i=i-2){
                if(Tabuleiro[i]==2){
                    return i;
                }
            }
        }
        return 0;
    }
    /*ganha(p) verifica se o jogador passado ganha ou bloqueia 
    retorna o indice de qual posição deve ser jogada caso não retornada o retorno é 0 */
    public int ganha(boolean p){
        int i, j, r = 0, g = 0;
        if(p){//se for verdadeiro Computador é X
            g = 18;
        }else{
            g = 50;
        }
        //verifica as linhas
        for(i=1; i<10; i=i+3){
            r = Tabuleiro[i] * Tabuleiro[i+1] * Tabuleiro[i+2];
            if(r== g){
                if(Tabuleiro[i] == 2){ 
                    return i;
                }else if(Tabuleiro[i+1]==2){
                    return i+1;
                }else{
                    return i+2;
                }
            }
        }
        //verifica as colunas
        for(i=1; i<4; i++){
            r = Tabuleiro[i] * Tabuleiro[i+3] * Tabuleiro[i+6];
            if(r==g){
                if(Tabuleiro[i] == 2){
                    return i;
                }else if(Tabuleiro[i+3]==2){
                    return i+3;
                }else if(Tabuleiro[i+6]==2){
                    return i+6;
                }
            }
        }
        //verifica as diagonais
        int d1 = Tabuleiro[1] * Tabuleiro[5] * Tabuleiro[9];
        int d2 = Tabuleiro[3] * Tabuleiro[5] * Tabuleiro[7];
        if(d1==g){
            if(Tabuleiro[1]==2){
                return 1;
            }else if(Tabuleiro[5]==2){
                return 5;
            }else{
                return 9;
            }        
        }else if(d2== g){
            if(Tabuleiro[3]==2){
                return 3;
            }else if(Tabuleiro[5]==2){
                return 5;
            }else{
                return 7;
            }
        }else{
            return 0;
        }    
    }      
    
    /*jogueN(n) conta o as jogadas e marca no tabuleiro X=3 ou 5=O*/
    public void jogueN(int n){
        jogada++;
        if(jogada%2==0){//jogada é par O
            Tabuleiro[n] = 5;//O
        }else{
            Tabuleiro[n] = 3;//X   
        }
        for (int i=1; i<10; i++) {
             System.out.print(Tabuleiro[i] + " | ");
             if(i==3||i==6||i==9)
                System.out.println("");
        }
        System.out.println("-----------------------");
    }
    
    /*first player alterna qual jogado começa a jogar*/
    public boolean Firstplayer(){
       boolean retorno;
       if(turno%2 == 0){
            playerOn = true; //X
            computerOn = false; //O
            retorno = true;
        }else{
           playerOn = false; //O
           computerOn = true; //X
           retorno = false;
       }   
        turno++;
        return retorno;
    }
    
    /*inicializa o tabuleiro com 2 = posição vazia */
    private void initTabuleiro() {
        Tabuleiro = new int[10]; 
        for (int i=0; i<10; i++) {
             Tabuleiro[i] = 2;
        }
    }
   
    /*gameplay() inicia o jogo*/
    private void gameplay() {
        initTabuleiro();
        if(!Firstplayer()){
            strategyPc();
        }
    }
    
    /*strategyPC()implementa as estratégias do computador conforme a jogada++*/
    private void strategyPc(){
        int respX, respO;
        switch(jogada){
            case 0://jogada 1;
                B1.setText("X");
                jogueN(1);
                break;
            case 1://jogada 2;
                if(Tabuleiro[5] == 2){
                    B5.setText("O");
                    jogueN(5);
                }else{
                    B1.setText("O");
                    jogueN(1);   
                }
                break;
            case 2://jogado 3;
                if(Tabuleiro[9]==2){
                    B9.setText("X");
                    jogueN(9);
                }else{
                    B3.setText("X");
                    jogueN(3);
                }
                break;
            case 3://jogada  4;
                respX = ganha(playerOn);
                if(respX!=0){//se 
                    System.out.println("o Botão a ser preenchido é" + respX);
                    setTextBtn("O",respX);
                    jogueN(respX);
                }else{
                    setTextBtn("O", faz2());
                    jogueN(faz2());
                }
                break;
            case 4://jogada 5
                respX  = ganha(computerOn);
                respO = ganha(playerOn);
                if(respX != 0){
                    jogueN(respX);
                    setTextBtn("X", respX);
                }else if(respO != 0){
                    jogueN(respO);
                    setTextBtn("X", respO);
                }else if(Tabuleiro[7] == 2){
                    jogueN(7);
                    setTextBtn("X", 7);
                }else{
                    jogueN(3);
                    setTextBtn("X", 3);
                }
                break;
            case 5://jogada 6
                System.out.println("jogada 6");
                respX = ganha(playerOn);
                respO = ganha(computerOn);
                if(respO != 0){
                    jogueN(respO);
                    setTextBtn("O",respO);
                }else if(respX != 0){
                    jogueN(respX);
                    setTextBtn("O", respX);
                }else if(faz2() != 0){
                    setTextBtn("O",faz2());
                    jogueN(faz2());
                }//essa função falha quando o tabuleiro fica assim
                /*
                2 | 3 | 2 | 
                3 | 5 | 3 | 
                2 | 5 | 2 | 
                */
                else{
                    for(int i=1; i<=9; i++){
                        if(Tabuleiro[i]==2){
                            jogueN(i);
                            setTextBtn("O", i);
                            i = 9;
                        }
                    }
                }
                break;
            case 6://jogada 7
                respX = ganha(computerOn);
                respO = ganha(playerOn);
                if(respX != 0){
                    jogueN(respX);
                    setTextBtn("X",respX);
                }else if(respO != 0){
                    jogueN(respO);
                    setTextBtn("X", respO);
                }else{    
                    for(int i=1; i<=9; i++){
                            if(Tabuleiro[i]==2){
                                jogueN(i);
                                setTextBtn("X", i);
                                i = 9;
                        }
                    }
                }    
                break;
            case 7://jogada 8
                System.out.println("jogada 6");
                respO = ganha(computerOn);
                respX = ganha(playerOn);
                if(respO != 0){
                    jogueN(respO);
                    setTextBtn("O",respO);
                }else if(respX != 0){
                    jogueN(respX);
                    setTextBtn("O",respX);                    
                }else{
                    for(int i=1; i<=9; i++){
                        if(Tabuleiro[i]==2){
                            jogueN(i);
                            setTextBtn("O", i);
                            i = 9;
                        }
                    }
                }
                break;
            case 8://jogada 9
                respX = ganha(computerOn);
                respO = ganha(playerOn);
                if(respX != 0){
                    jogueN(respX);
                    setTextBtn("X",respX);
                }else if(respO != 0){
                    jogueN(respO);
                    setTextBtn("X", respO);
                }else{    
                    for(int i=1; i<=9; i++){
                            if(Tabuleiro[i]==2){
                                jogueN(i);
                                setTextBtn("X", i);
                                i = 9;
                        }
                    }
                }
                break;
        }
        int r = winner(true);
        if(r == 1 || r==2){
            clean();
        }
    }
    
    /*mostra em uma mensagem de qual jogador ganhou ou se deu empate*/
    public void displayWinner(String name){
        JOptionPane.showMessageDialog(null, ""+ name);
    }
    
    /*setTextBtn(c, nButton) qual a string(X ou O) e qual o botao deve ser ser marcado na inteface gráfica*/
    public void setTextBtn(String c,int nButton){ 
        switch (nButton) {
            case 1:
                B1.setText(c);
                break;
            case 2:
                B2.setText(c);
                break;
            case 3:
                B3.setText(c);
                break;
            case 4:
                B4.setText(c);
                break;
            case 5:
                B5.setText(c);
                break;
            case 6:
                B6.setText(c);
                break;    
            case 7:
                B7.setText(c);
                break;
            case 8:
                B8.setText(c);
                break;
            case 9:
                B9.setText(c);
        }
    }
    
    /*clean() limpa as variáveis utilizadas para que possa ser possível continuar o jogo*/
    public void clean(){
        jogada = 0;
        playerOn = false;
        computerOn = false;
        B1.setText("");
        B2.setText("");
        B3.setText("");
        B4.setText("");
        B5.setText("");
        B6.setText("");
        B7.setText("");
        B8.setText("");
        B9.setText("");
        gameplay();
    }
    
    /*winner(p) verifica se há algum ganhador e qual o jogador, também verifica se há empate
    retorna 1 ou 2 caso há vitória ou empate respectivamente
    retorna 0 caso não há nenhum vencedor ou empate*/
    private int winner(boolean p){
        int i, j, r = 0, g = 0;
        boolean win = false;
//se for true é o pc se for false é o player
        for(i=1; i<10; i=i+3){
            r = Tabuleiro[i] * Tabuleiro[i+1] * Tabuleiro[i+2];
            if(r==27 || r==125){
                win = true;
                System.out.println("We have a winner line");
                if(p){
                    VitoriaComp++;
                    NVitoriasComp.setText("Número de Vitórias: " + VitoriaComp);
                    displayWinner("Computador");
                }else{
                    VitoriaPlayer++;
                    NVitoriasPlayer.setText("Número de Vitórias: "+ VitoriaPlayer);
                    displayWinner("Player");
                }    
                return 1;
            }
        }        
        //verifica as colunas
        for(i=1; i<4; i++){
            r = Tabuleiro[i] * Tabuleiro[i+3] * Tabuleiro[i+6];
            if(r==27 || r==125){
                System.out.println("We have a winner colum");
                win = true;
                if(p){
                    VitoriaComp++;
                    NVitoriasComp.setText("Número de Vitórias: " + VitoriaComp);
                    displayWinner("Parabéns Computador");
                }else{
                    VitoriaPlayer++;
                    NVitoriasPlayer.setText("Número de Vitórias: "+ VitoriaPlayer);
                    displayWinner("Parabéns Player");
                }
                return 1;
            }
        }
        //verifica as diagonais
        int d1 = Tabuleiro[1] * Tabuleiro[5] * Tabuleiro[9];
        int d2 = Tabuleiro[3] * Tabuleiro[5] * Tabuleiro[7];
        
        if(d1==27 || d1==125){
            System.out.println("We have a winner diagonal 1");
                win = true;    
                if(p){
                    VitoriaComp++;
                    NVitoriasComp.setText("Número de Vitórias: " + VitoriaComp);
                    displayWinner("Parabéns Computador");
                }else{
                    VitoriaPlayer++;
                    NVitoriasPlayer.setText("Número de Vitórias: "+ VitoriaPlayer);
                    displayWinner("Parabéns Player");
                }
                return 1;
        }else if(d2==27 || d2==125){
                win = true;
                if(p){
                    VitoriaComp++;
                    NVitoriasComp.setText("Número de Vitórias: " + VitoriaComp);
                    displayWinner("Parabéns Computador");
                }else{
                    VitoriaPlayer++;
                    NVitoriasPlayer.setText("Número de Vitórias: "+ VitoriaPlayer);
                    displayWinner("Parabéns Player");
                }
            System.out.println("We have a winner diagonal 2");
            return 1;
        }
        int verifica=0;
        for(i=1; i<=9; i++){
            if(Tabuleiro[i]!=2){
                verifica++;
            }
        }
          
        if(verifica==9){
            Empates++;
            NEmpate.setText("Número de Empates: "+ Empates);
            displayWinner("Ixe! Deu empate");
            return 2;
        }
        return 0;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        B1 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        B9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NVitoriasPlayer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NVitoriasComp = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        NEmpate = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        NovoJogo = new javax.swing.JToggleButton();
        Exit = new javax.swing.JToggleButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setAutoRequestFocus(false);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Jogo"));

        B1.setBackground(new java.awt.Color(255, 255, 255));
        B1.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B1.setMinimumSize(new java.awt.Dimension(60, 60));
        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });

        B2.setBackground(new java.awt.Color(255, 255, 255));
        B2.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B2.setMinimumSize(new java.awt.Dimension(60, 60));
        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });

        B3.setBackground(new java.awt.Color(255, 255, 255));
        B3.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B3.setMinimumSize(new java.awt.Dimension(60, 60));
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });

        B4.setBackground(new java.awt.Color(255, 255, 255));
        B4.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B4.setMinimumSize(new java.awt.Dimension(60, 60));
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });

        B5.setBackground(new java.awt.Color(255, 255, 255));
        B5.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B5.setMinimumSize(new java.awt.Dimension(60, 60));
        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });

        B6.setBackground(new java.awt.Color(255, 255, 255));
        B6.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B6.setMinimumSize(new java.awt.Dimension(60, 60));
        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });

        B7.setBackground(new java.awt.Color(255, 255, 255));
        B7.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B7.setMinimumSize(new java.awt.Dimension(60, 60));
        B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B7ActionPerformed(evt);
            }
        });

        B8.setBackground(new java.awt.Color(255, 255, 255));
        B8.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B8.setMinimumSize(new java.awt.Dimension(60, 60));
        B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B8ActionPerformed(evt);
            }
        });

        B9.setBackground(new java.awt.Color(255, 255, 255));
        B9.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        B9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B9.setMinimumSize(new java.awt.Dimension(60, 60));
        B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {B1, B2, B3, B4, B5, B6, B7, B8, B9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {B1, B2, B3, B4, B5, B6, B7, B8, B9});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informações do Jogo TIC TAC TOE"));

        jLabel1.setText("Jogador");

        NVitoriasPlayer.setText("Número de Vitórias: 0");

        jLabel3.setText("Computador");

        NVitoriasComp.setText("Número de Vitórias: 0");

        NEmpate.setText("Número de Empates: 0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(NVitoriasPlayer)
                            .addComponent(jLabel3)
                            .addComponent(NVitoriasComp))
                        .addContainerGap(117, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(NEmpate)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NVitoriasPlayer)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NVitoriasComp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NEmpate, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Controle do Jogo"));
        jPanel4.setToolTipText("");

        NovoJogo.setText("Novo Jogo");
        NovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NovoJogoActionPerformed(evt);
            }
        });

        Exit.setText("Sair");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NovoJogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Exit, NovoJogo});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NovoJogo)
                    .addComponent(Exit))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("TIC-TAC-TOE");
        jLabel2.setFocusCycleRoot(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
        if(B2.getText().equals("")){
            if(playerOn){
                B2.setText("X");
            }else{
                B2.setText("O");
            }
            jogueN(2);
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }    
        }
    }//GEN-LAST:event_B2ActionPerformed

    private void NovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NovoJogoActionPerformed
        VitoriaPlayer = 0;
        VitoriaComp = 0;
        Empates = 0; 
        turno = 0;
        NVitoriasComp.setText("Número de Vitórias: " + VitoriaComp);
        NEmpate.setText("Número de Empates: " + Empates);
        NVitoriasPlayer.setText("Número de Vitórias: " + VitoriaPlayer);
        clean();
    }//GEN-LAST:event_NovoJogoActionPerformed

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        if(B4.getText().equals("")){
            if(playerOn){
                B4.setText("X");
            }else{
                B4.setText("O");
            }
            jogueN(4);
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
        
    }//GEN-LAST:event_B4ActionPerformed

    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B7ActionPerformed
        if(B7.getText().equals("")){
            if(playerOn){
                B7.setText("X");
            }else{
                B7.setText("O");
            }
            jogueN(7);//passa a posição do vetor a ser preechida
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
    }//GEN-LAST:event_B7ActionPerformed

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        if(B1.getText().equals("")){
            if(playerOn){
                B1.setText("X");
            }else{
                B1.setText("O");
            }
            jogueN(1);//passa a posição do vetor a ser preechida
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
    }//GEN-LAST:event_B1ActionPerformed

    private void B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B9ActionPerformed
        if(B9.getText().equals("")){
            if(playerOn){
                B9.setText("X");
            }else{
                B9.setText("O");
            }
            jogueN(9);//passa a posição do vetor a ser preechida
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
    }//GEN-LAST:event_B9ActionPerformed

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        if(B3.getText().equals("")){
            if(playerOn){
                B3.setText("X");
            }else{
                B3.setText("O");
            }
            jogueN(3);//passa a posição do vetor a ser preechida
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
    }//GEN-LAST:event_B3ActionPerformed

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
        if(B5.getText().equals("")){
            if(playerOn){
                B5.setText("X");
            }else{
                B5.setText("O");
            }
            jogueN(5);//passa a posição do vetor a ser preechida
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
    }//GEN-LAST:event_B5ActionPerformed

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        if(B6.getText().equals("")){
            if(playerOn){
                B6.setText("X");
            }else{
                B6.setText("O");
            }
            jogueN(6);//passa a posição do vetor a ser preechida
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
    }//GEN-LAST:event_B6ActionPerformed

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B8ActionPerformed
        if(B8.getText().equals("")){
            if(playerOn){
                B8.setText("X");
            }else{
                B8.setText("O");
            }
            jogueN(8);//passa a posição do vetor a ser preechida
            int r = winner(false);
            if(r == 1 || r == 2){
                clean();
            }else{
                strategyPc();
            }            }
    }//GEN-LAST:event_B8ActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
      
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B1;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton B9;
    private javax.swing.JToggleButton Exit;
    private javax.swing.JLabel NEmpate;
    private javax.swing.JLabel NVitoriasComp;
    private javax.swing.JLabel NVitoriasPlayer;
    private javax.swing.JToggleButton NovoJogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables


}
