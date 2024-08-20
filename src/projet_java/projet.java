/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projet_java;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import static org.joda.time.format.ISODateTimeFormat.date;

/**
 *
 * @author Juju
 */
public class projet extends javax.swing.JFrame {

    int selected_row_employe;
    int selected_row_pointage;
    int selected_row_conge;
    int selected_row_tout;
    
    public projet() {
        initComponents();
        select_tout();
        select_employe();
        select_pointage();
        select_conge();
    }
    ///////declaration***********
    ResultSet Rs=null;
    PreparedStatement Ps=null;
    
    public void select_tout()
    {/*******************************connection a la base de donne************************************************************/
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        /***************************************Affichage de la reponse*******************************************************/
        try{
        String reqete = "SELECT employe.nom, employe.prenom,employe.poste,employe.salaire, COUNT(pointage.num_pointage) AS nombre_absence,employe.salaire-COUNT(pointage.num_pointage)*10000 as salaire_restant FROM pointage JOIN employe ON (pointage.num_employe=employe.num_employe) WHERE pointage.pointage='non' GROUP BY employe.num_employe ORDER BY employe.num_employe ASC";
        Ps = conn.prepareStatement(reqete);
        Rs = Ps.executeQuery();
        
        tout.setModel(DbUtils.resultSetToTableModel(Rs));
        }catch(Exception e)
        {
            e.getMessage();
        }
    }
    
    
    
    
    
    public void select_employe()
    {
        Connection conn = null;
        try
        {///////////////////connection a la base de donnée***************************/
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        /*************************************affichage de la resultat***********************************/
        try{
        String reqete = "SELECT num_employe as N°EMPLOYE,nom as NOM, prenom as PRENOM, poste as POSTE,salaire as SALAIRE FROM employe ORDER BY num_employe ASC";
        Ps = conn.prepareStatement(reqete);
        Rs = Ps.executeQuery();
        
        tab_employe.setModel(DbUtils.resultSetToTableModel(Rs));
        }catch(Exception e)
        {
            e.getMessage();
        }
    }
    
    
    
    
    
    public void select_pointage()
    {
        Connection conn = null;
        try
        {
            //*******************************base de donnée***************************************/
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        /********************************affichage de la resultat************************************/
        try{
        String reqete = "SELECT num_pointage as N°POINTAGE,date_pointage AS DATE,num_employe as N°EMPLOYE,pointage as POINTAGE  FROM pointage ORDER BY num_pointage ASC";
        Ps = conn.prepareStatement(reqete);
        Rs = Ps.executeQuery();
        
        tab_pointage.setModel(DbUtils.resultSetToTableModel(Rs));
        }catch(Exception e)
        {
            e.getMessage();
        }
    }
    
    
    
    
    public void select_conge()
    {
        Connection conn = null;
        try
        {
            /*****************************base de donne*********************************/
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        /************************************affichage de la resultat********************************************/
        try{
            //String reqete ="SELECT * FROM pointage";
        String reqete = "SELECT num_conge as N°CONGE,num_employe as N°EMPLOYE,motif as MOTIF,date_demande as DEMANDE, date_retour as RETOUR,nb_absence as jour_conge FROM conge ORDER BY num_conge ASC";
        Ps = conn.prepareStatement(reqete);
        Rs = Ps.executeQuery();
        
        tab_conge.setModel(DbUtils.resultSetToTableModel(Rs));
        }catch(Exception e)
        {
            e.getMessage();
        }
    }
    
public void select_reste_conge()
{
    Connection conn = null;
        try
        {/**************************************Base de donne********************************************/
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        /****************************************Affichage resultat*********************************************/
        try{
        String reqete = "SELECT employe.nom, employe.prenom, 30-SUM(conge.nb_absence) AS conge_restant FROM conge JOIN employe ON (conge.num_employe=employe.num_employe) GROUP BY employe.num_employe ORDER BY employe.num_employe ASC";
        Ps = conn.prepareStatement(reqete);
        Rs = Ps.executeQuery();
        
        tout.setModel(DbUtils.resultSetToTableModel(Rs));
        
        }catch(Exception e)
        {
            System.out.println(e);
        }
}
    


public void recherche()
{
        String rech = E_txt_recherche.getText();
        String requete = "SELECT num_employe as N°EMPLOYE,nom as NOM, prenom as PRENOM, poste as POSTE,salaire as SALAIRE FROM employe WHERE nom LIKE'%"+rech+"%' OR prenom LIKE '%"+rech+"%' ORDER BY num_employe ASC";
        
        Connection conn = null;
        try
        {
            //manova ny class de driver
            Class.forName("org.postgresql.Driver");
            
            //creer l'objet de connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            Ps = conn.prepareStatement(requete);
            Rs = Ps.executeQuery();
            
            tab_employe.setModel(DbUtils.resultSetToTableModel(Rs));
        }
        catch(Exception e)
        {}
    }
    java.sql.Date date,date_p,date1,date2, date_ap;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        A_date = new com.toedter.calendar.JDateChooser();
        A_nom = new javax.swing.JTextField();
        A_prenom = new javax.swing.JTextField();
        A_poste = new javax.swing.JTextField();
        A_nb_abs = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tout = new javax.swing.JTable();
        pdf = new javax.swing.JButton();
        reste_conge = new javax.swing.JButton();
        A_montant = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_employe = new javax.swing.JTable();
        E_txt_recherche = new javax.swing.JTextField();
        E_ajouter = new javax.swing.JButton();
        E_employe = new javax.swing.JButton();
        E_supprimer = new javax.swing.JButton();
        E_salaire = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        E_poste = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        E_prenom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        E_nom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        E_numero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_pointage = new javax.swing.JTable();
        P_date_absence = new com.toedter.calendar.JDateChooser();
        P_absent = new javax.swing.JButton();
        P_ajouter = new javax.swing.JButton();
        P_modifier = new javax.swing.JButton();
        P_supprimer = new javax.swing.JButton();
        p = new javax.swing.JButton();
        P_date = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        P_numero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        P_numero_employe = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        P_pointage = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tab_conge = new javax.swing.JTable();
        C_modifer = new javax.swing.JButton();
        C_supprimer = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        C_date_retour = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        C_date_demande = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        C_nb_jour = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        C_motif = new javax.swing.JTextField();
        C_numero_employe = new javax.swing.JTextField();
        C_numero = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        actualiser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(8, 9, 66));

        jLabel1.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\PROJET\\L2\\projet_java\\search.png")); // NOI18N
        jLabel1.setText("   GESTION DE POINTAGE ET CONGE PERSONNEL");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jTabbedPane1.setBackground(new java.awt.Color(8, 9, 66));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(8, 9, 66));

        A_nom.setBackground(new java.awt.Color(8, 9, 66));
        A_nom.setForeground(new java.awt.Color(255, 255, 255));
        A_nom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        A_prenom.setBackground(new java.awt.Color(8, 9, 66));
        A_prenom.setForeground(new java.awt.Color(255, 255, 255));
        A_prenom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        A_poste.setBackground(new java.awt.Color(8, 9, 66));
        A_poste.setForeground(new java.awt.Color(255, 255, 255));
        A_poste.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        A_nb_abs.setBackground(new java.awt.Color(8, 9, 66));
        A_nb_abs.setForeground(new java.awt.Color(255, 255, 255));
        A_nb_abs.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        tout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toutMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tout);

        pdf.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        pdf.setForeground(new java.awt.Color(8, 9, 66));
        pdf.setText("PDF");
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });

        reste_conge.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        reste_conge.setForeground(new java.awt.Color(8, 9, 66));
        reste_conge.setText("Reste conge");
        reste_conge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reste_congeActionPerformed(evt);
            }
        });

        A_montant.setBackground(new java.awt.Color(8, 9, 66));
        A_montant.setForeground(new java.awt.Color(255, 255, 255));
        A_montant.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel18.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Information pour le pdf");

        jLabel19.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Date :");

        jLabel20.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Nom:");

        jLabel21.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Prenom:");

        jLabel22.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Nombre d'absence:");

        jLabel23.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Poste");

        jLabel24.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Salaire :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(A_nom, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(A_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23)
                                    .addComponent(A_poste, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(A_montant, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(A_prenom)
                                    .addComponent(A_nb_abs))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reste_conge)
                        .addGap(275, 275, 275))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(A_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel20)
                                .addGap(20, 20, 20)
                                .addComponent(A_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addGap(26, 26, 26)
                                .addComponent(A_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23)
                                .addGap(26, 26, 26)
                                .addComponent(A_nb_abs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addGap(22, 22, 22)
                                .addComponent(A_poste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(A_montant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(21, 142, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reste_conge, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))))
        );

        jTabbedPane1.addTab("ACCEUIL", new javax.swing.ImageIcon("D:\\PROJET\\L2\\projet_java\\reception_24px.png"), jPanel2); // NOI18N

        jPanel3.setBackground(new java.awt.Color(8, 9, 66));

        tab_employe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_employe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_employeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_employe);

        E_txt_recherche.setBackground(new java.awt.Color(8, 9, 66));
        E_txt_recherche.setForeground(new java.awt.Color(255, 255, 255));
        E_txt_recherche.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        E_txt_recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                E_txt_rechercheKeyPressed(evt);
            }
        });

        E_ajouter.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        E_ajouter.setForeground(new java.awt.Color(8, 9, 66));
        E_ajouter.setText("Ajouter");
        E_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_ajouterActionPerformed(evt);
            }
        });

        E_employe.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        E_employe.setForeground(new java.awt.Color(8, 9, 66));
        E_employe.setText("Modifier");
        E_employe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_employeActionPerformed(evt);
            }
        });

        E_supprimer.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        E_supprimer.setForeground(new java.awt.Color(8, 9, 66));
        E_supprimer.setText("Supprimer");
        E_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_supprimerActionPerformed(evt);
            }
        });

        E_salaire.setBackground(new java.awt.Color(8, 9, 66));
        E_salaire.setForeground(new java.awt.Color(255, 255, 255));
        E_salaire.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        E_salaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_salaireActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(8, 9, 66));
        jLabel6.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Salaire");

        E_poste.setBackground(new java.awt.Color(8, 9, 66));
        E_poste.setForeground(new java.awt.Color(255, 255, 255));
        E_poste.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel5.setBackground(new java.awt.Color(8, 9, 66));
        jLabel5.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Poste");

        E_prenom.setBackground(new java.awt.Color(8, 9, 66));
        E_prenom.setForeground(new java.awt.Color(255, 255, 255));
        E_prenom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        E_prenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_prenomActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(8, 9, 66));
        jLabel4.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Prenom");

        E_nom.setBackground(new java.awt.Color(8, 9, 66));
        E_nom.setForeground(new java.awt.Color(255, 255, 255));
        E_nom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        E_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_nomActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(8, 9, 66));
        jLabel3.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nom");

        E_numero.setBackground(new java.awt.Color(8, 9, 66));
        E_numero.setForeground(new java.awt.Color(255, 255, 255));
        E_numero.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel2.setBackground(new java.awt.Color(8, 9, 66));
        jLabel2.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Numero employe");

        jLabel17.setBackground(new java.awt.Color(8, 9, 66));
        jLabel17.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Recherche");

        jLabel25.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Information pour l'employé:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(E_ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(242, 242, 242)
                        .addComponent(E_employe, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(E_supprimer)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(E_prenom)
                            .addComponent(E_poste)
                            .addComponent(E_nom)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(E_numero, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(E_salaire)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(E_txt_recherche))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(E_txt_recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel25)))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E_poste, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E_salaire, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(E_ajouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(E_employe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(E_supprimer, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("EMPLOYE", new javax.swing.ImageIcon("D:\\PROJET\\L2\\projet_java\\EMPLOYx.png"), jPanel3); // NOI18N

        jPanel4.setBackground(new java.awt.Color(8, 9, 66));

        tab_pointage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_pointage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_pointageMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_pointage);

        P_date_absence.setBackground(new java.awt.Color(8, 9, 66));

        P_absent.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        P_absent.setForeground(new java.awt.Color(8, 9, 66));
        P_absent.setText("Absent");
        P_absent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_absentActionPerformed(evt);
            }
        });

        P_ajouter.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        P_ajouter.setForeground(new java.awt.Color(8, 9, 66));
        P_ajouter.setText("Ajouter");
        P_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_ajouterActionPerformed(evt);
            }
        });

        P_modifier.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        P_modifier.setForeground(new java.awt.Color(8, 9, 66));
        P_modifier.setText("Modifier");
        P_modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_modifierActionPerformed(evt);
            }
        });

        P_supprimer.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        P_supprimer.setForeground(new java.awt.Color(8, 9, 66));
        P_supprimer.setText("Supprimer");
        P_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_supprimerActionPerformed(evt);
            }
        });

        p.setText("pointer");
        p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date pointage : ");

        P_numero.setBackground(new java.awt.Color(8, 9, 66));
        P_numero.setForeground(new java.awt.Color(255, 255, 255));
        P_numero.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        P_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_numeroActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Numero pointage :");

        jLabel9.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Numero employe :");

        P_numero_employe.setBackground(new java.awt.Color(8, 9, 66));
        P_numero_employe.setForeground(new java.awt.Color(255, 255, 255));
        P_numero_employe.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel10.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pointage");

        P_pointage.setBackground(new java.awt.Color(8, 9, 66));
        P_pointage.setForeground(new java.awt.Color(255, 255, 255));
        P_pointage.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel26.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Information pointage :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(P_numero)
                            .addComponent(jLabel9)
                            .addComponent(P_pointage)
                            .addComponent(P_numero_employe)
                            .addComponent(jLabel10)
                            .addComponent(P_date, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel26)))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(P_date_absence, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(P_absent, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P_supprimer))
                .addGap(30, 30, 30))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170)
                .addComponent(P_ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182)
                .addComponent(P_modifier)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel26)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(P_date_absence, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(P_absent, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P_numero_employe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(23, 23, 23)
                        .addComponent(P_pointage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(33, 33, 33)
                        .addComponent(P_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P_ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P_modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P_supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("POINTAGE", new javax.swing.ImageIcon("D:\\PROJET\\L2\\projet_java\\POINTAGE.png"), jPanel4); // NOI18N

        jPanel5.setBackground(new java.awt.Color(8, 9, 66));

        tab_conge.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_conge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_congeMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tab_conge);

        C_modifer.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        C_modifer.setForeground(new java.awt.Color(8, 9, 66));
        C_modifer.setText("Modifier");
        C_modifer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_modiferActionPerformed(evt);
            }
        });

        C_supprimer.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        C_supprimer.setForeground(new java.awt.Color(8, 9, 66));
        C_supprimer.setText("Supprimer");
        C_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_supprimerActionPerformed(evt);
            }
        });

        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(8, 9, 66));
        jLabel16.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Date de retour :");

        jLabel15.setBackground(new java.awt.Color(8, 9, 66));
        jLabel15.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Date de demande :");

        C_nb_jour.setBackground(new java.awt.Color(8, 9, 66));
        C_nb_jour.setForeground(new java.awt.Color(255, 255, 255));
        C_nb_jour.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel14.setBackground(new java.awt.Color(8, 9, 66));
        jLabel14.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nombre de jour :");

        C_motif.setBackground(new java.awt.Color(8, 9, 66));
        C_motif.setForeground(new java.awt.Color(255, 255, 255));
        C_motif.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        C_numero_employe.setBackground(new java.awt.Color(8, 9, 66));
        C_numero_employe.setForeground(new java.awt.Color(255, 255, 255));
        C_numero_employe.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        C_numero.setBackground(new java.awt.Color(8, 9, 66));
        C_numero.setForeground(new java.awt.Color(255, 255, 255));
        C_numero.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel11.setBackground(new java.awt.Color(8, 9, 66));
        jLabel11.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Numero conge :");

        jLabel12.setBackground(new java.awt.Color(8, 9, 66));
        jLabel12.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Numero employé :");

        jLabel13.setBackground(new java.awt.Color(8, 9, 66));
        jLabel13.setFont(new java.awt.Font("MS Gothic", 2, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Motif :");

        jLabel27.setFont(new java.awt.Font("MS Gothic", 2, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Information conge : ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C_modifer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230)
                .addComponent(C_supprimer)
                .addGap(66, 66, 66))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel27)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(C_numero)
                        .addComponent(C_numero_employe)
                        .addComponent(C_motif)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel13)
                                .addComponent(C_date_retour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addComponent(C_date_demande, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(C_nb_jour, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel27)
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(C_numero_employe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(C_motif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(32, 32, 32)
                        .addComponent(C_nb_jour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(C_date_demande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(C_date_retour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C_modifer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C_supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("CONGE", jPanel5);

        actualiser.setText("Actualiser");
        actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualiserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(actualiser)
                .addGap(23, 23, 23))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(actualiser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void C_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_supprimerActionPerformed
        // TODO add your handling code here:
        if(C_numero.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"entrer le numero du conge");
        }
        else
        {
            String id_conge = C_numero.getText();
            int id = Integer.parseInt(id_conge);
            Conge supprimer = new Conge();
            supprimer.Supprimer(id);
            JOptionPane.showMessageDialog(this,"suppression avec succès!");
            select_conge();
        }
    }//GEN-LAST:event_C_supprimerActionPerformed

    private void C_modiferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_modiferActionPerformed
        // TODO add your handling code here:
        String numero_c,numero_e,motif,nb,date_d,date_r;
        Date dat_d,dat_r;
        if(C_numero.getText().isEmpty() || C_numero_employe.getText().isEmpty() || C_motif.getText().isEmpty() || C_nb_jour.getText().isEmpty()|| C_date_demande.getDate()==null || C_date_retour.getDate()==null)
        {
            JOptionPane.showMessageDialog(this, "Completer tout les champs");
        }
        else{
            numero_c = C_numero.getText();
            numero_e = C_numero_employe.getText();
            motif = C_motif.getText();
            nb = C_nb_jour.getText();
            dat_d = C_date_demande.getDate();
            dat_r = C_date_retour.getDate();
            date1 = new java.sql.Date(dat_d.getTime());
            date2 = new java.sql.Date(dat_r.getTime());
            int num_c = Integer.parseInt(numero_c);
            int num_e = Integer.parseInt(numero_e);
            int nb_j = Integer.parseInt(nb);
            Conge modifier = new Conge();
            modifier.Modifier(num_c, num_e, motif, nb_j,  date1, date2);
        }

        select_conge();
    }//GEN-LAST:event_C_modiferActionPerformed

    private void tab_congeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_congeMouseClicked
        // TODO add your handling code here:
        DefaultTableModel conge = (DefaultTableModel)tab_conge.getModel();
        
        int index = tab_conge.getSelectedRow();
        selected_row_conge = tab_conge.getSelectedRow();
        C_numero.setText(conge.getValueAt(index, 0).toString());
        C_numero_employe.setText(conge.getValueAt(index, 1).toString());
        C_motif.setText(conge.getValueAt(index, 2).toString());
        C_nb_jour.setText(conge.getValueAt(index, 5).toString());

        selected_row_conge = tab_conge.getSelectedRow();
    }//GEN-LAST:event_tab_congeMouseClicked

    private void P_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_supprimerActionPerformed
        // TODO add your handling code here:
        
        if(P_numero.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"entrer le numero du pointage");
        }
        else
        {
            String id_pointage = P_numero.getText();
            int id = Integer.parseInt(id_pointage);
            Pointage supprimer = new Pointage();
            supprimer.Supprimer(id);
            String pointage = P_pointage.getText();
            
            /*int i = Integer.parseInt(id_pointage);
            if(pointage.equals("non"))
            {
                Employe absent = new Employe();
                absent.Modifier_e(i);
            }*/
            JOptionPane.showMessageDialog(this,"suppression avec succès!");
            select_pointage();
            select_tout();
        }
    }//GEN-LAST:event_P_supprimerActionPerformed

    private void P_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_modifierActionPerformed
        // TODO add your handling code here:
        if(P_numero.getText().isEmpty() || P_numero_employe.getText().isEmpty() || P_pointage.getText().isEmpty()|| P_date.getDate()==null)
        {
            JOptionPane.showMessageDialog(this, "Complete tout les champs");
        }
        else
        {
            String num_pointage,numEmploye,pointage;
            Date Fdate;
            num_pointage = P_numero.getText();
            numEmploye = P_numero_employe.getText();
            pointage = P_pointage.getText();
            Fdate = P_date.getDate();
            date_p = new java.sql.Date(Fdate.getTime());

            int n_p = Integer.parseInt(num_pointage);
            int n_e = Integer.parseInt(numEmploye);
            Pointage modifier = new Pointage();
            modifier.Modifier(n_p,date_p,n_e,pointage);
        }
        select_pointage();
        select_tout();
    }//GEN-LAST:event_P_modifierActionPerformed

    private void P_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_ajouterActionPerformed
        // TODO add your handling code here:
        if(P_numero_employe.getText().isEmpty()|| P_date.getDate()==null)
        {
            JOptionPane.showMessageDialog(this, "completer le champ date de pointage et numero de l'employé");
        }
        else
        {
            String num_pointage,numEmploye,pointage;
            Date fdate= P_date.getDate();
            numEmploye = P_numero_employe.getText();
            date_ap = new java.sql.Date(fdate.getTime());
            int n_e = Integer.parseInt(numEmploye);
            Pointage ajout = new Pointage();
            try {
                ajout.Ajouter_p(n_e,date_ap);
            } catch (SQLException ex) {
                Logger.getLogger(projet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /*Employe present = new Employe();
            present.Modifier_p(n_e);*/
        }
        select_pointage(); 
        select_tout();                                       

    }//GEN-LAST:event_P_ajouterActionPerformed

    private void P_absentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_absentActionPerformed
        // TODO add your handling code here:
        if(P_date_absence.getDate()==null)
        {
            JOptionPane.showMessageDialog(this, "Entrer la date");
        }else{
        Date dat =  P_date_absence.getDate();
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        String req = "SELECT employe.nom,employe.prenom, date_pointage as date FROM employe JOIN pointage ON (employe.num_employe=pointage.num_employe) WHERE date_pointage=? and pointage='non'";
        try
        {
            Date dt = P_date_absence.getDate();
            date = new java.sql.Date(dt.getTime());
            Ps = conn.prepareStatement(req);
            Ps.setDate(1, date);
            Rs = Ps.executeQuery();

            tab_pointage.setModel(DbUtils.resultSetToTableModel(Rs));
        }
        catch(Exception e)
        {}}
    }//GEN-LAST:event_P_absentActionPerformed

    private void tab_pointageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_pointageMouseClicked
        // TODO add your handling code here:
        DefaultTableModel poin = (DefaultTableModel)tab_pointage.getModel();
        int index = tab_pointage.getSelectedRow();
        P_numero.setText(poin.getValueAt(index, 0).toString());
        P_numero_employe.setText(poin.getValueAt(index, 2).toString());
        P_pointage.setText(poin.getValueAt(index, 3).toString());
        selected_row_pointage = tab_pointage.getSelectedRow();
    }//GEN-LAST:event_tab_pointageMouseClicked

    private void P_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P_numeroActionPerformed

    private void E_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_supprimerActionPerformed
        // TODO add your handling code here:
        if(E_numero.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"entrer le numero de l'employe");
        }
        else
        {
            String id_employe = E_numero.getText();
            int id = Integer.parseInt(id_employe);
            Employe supprimer = new Employe();
            supprimer.Supprimer(id);
            JOptionPane.showMessageDialog(this,"suppression avec succès!");
            select_employe();
        }
    }//GEN-LAST:event_E_supprimerActionPerformed

    private void E_employeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_employeActionPerformed
        // TODO add your handling code here:
        if(E_numero.getText().isEmpty() || E_nom.getText().isEmpty() || E_prenom.getText().isEmpty() || E_poste.getText().isEmpty() || E_salaire.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Completer tout les champs");
        }
        else
        {
            String numero,nom,prenom,poste,salaire;
            numero = E_numero.getText();
            nom = E_nom.getText();
            prenom = E_prenom.getText();
            poste = E_poste.getText();
            salaire = E_salaire.getText();
            int num = Integer.parseInt(numero);
            int sal = Integer.parseInt(salaire);

            Employe modifier = new Employe();
            modifier.Modifier(num, nom, prenom, poste, sal);
            select_employe();
        }
    }//GEN-LAST:event_E_employeActionPerformed

    private void E_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_ajouterActionPerformed
        // TODO add your handling code here:
        String numero,nom,prenom,poste,salaire;
        if( E_nom.getText().isEmpty() || E_prenom.getText().isEmpty() || E_poste.getText().isEmpty() || E_salaire.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Completer tous les champs");
        }
        else{
            try {
                nom = E_nom.getText();
                prenom = E_prenom.getText();
                poste = E_poste.getText();
                salaire = E_salaire.getText();

                int sal = Integer.parseInt(salaire);

                Employe ajouter = new Employe();
                ajouter.Ajouter_e(nom, prenom, poste, sal);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(projet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        select_employe();
    }//GEN-LAST:event_E_ajouterActionPerformed

    private void E_txt_rechercheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_txt_rechercheKeyPressed
        // TODO add your handling code here:
        recherche();
    }//GEN-LAST:event_E_txt_rechercheKeyPressed

    private void tab_employeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_employeMouseClicked
        // TODO add your handling code here:

        DefaultTableModel emp = (DefaultTableModel)tab_employe.getModel();
        int index = tab_employe.getSelectedRow();
        E_numero.setText(emp.getValueAt(index, 0).toString());
        E_nom.setText(emp.getValueAt(index, 1).toString());
        E_prenom.setText(emp.getValueAt(index, 2).toString());
        E_poste.setText(emp.getValueAt(index, 3).toString());
        selected_row_employe = tab_employe.getSelectedRow();
    }//GEN-LAST:event_tab_employeMouseClicked

    private void reste_congeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reste_congeActionPerformed
        // TODO add your handling code here:
        
            select_reste_conge();
    }//GEN-LAST:event_reste_congeActionPerformed

    private void pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActionPerformed
        // TODO add your handling code here:
        String dateA,nom,prenom,poste,nb,sal;
        Date dat;
        Integer montant,nb_abs;if( A_nom.getText().isEmpty() || A_prenom.getText().isEmpty() || A_poste.getText().isEmpty() || A_montant.getText().isEmpty()|| A_date.getDate()==null)
        {
            JOptionPane.showMessageDialog(this, "Completer le champ");
        }
        else{
        dat = A_date.getDate();
        date = new java.sql.Date(dat.getTime());

        dateA =""+date+"";
        nom = A_nom.getText();
        prenom = A_prenom.getText();
        poste = A_poste.getText();
        nb = A_nb_abs.getText();
        sal = A_montant.getText();

        //nb_abs = Integer.parseInt(n_abs);
        montant= Integer.parseInt(sal);
        Accueil pdf = new Accueil();
        try {
            pdf.pdf(dateA, nom, prenom, poste, nb, montant);
        } catch (DocumentException ex) {
            Logger.getLogger(projet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(projet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(projet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }//GEN-LAST:event_pdfActionPerformed

    private void toutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toutMouseClicked
        // TODO add your handling code here:
        DefaultTableModel toute = (DefaultTableModel)tout.getModel();
        int index = tout.getSelectedRow();
        selected_row_tout = tout.getSelectedRow();
        A_nom.setText(tout.getValueAt(index, 0).toString());
        A_prenom.setText(tout.getValueAt(index, 1).toString());
        A_poste.setText(tout.getValueAt(index, 2).toString());
        A_nb_abs.setText(tout.getValueAt(index, 4).toString());
        A_montant.setText(tout.getValueAt(index, 5).toString());
        selected_row_tout = tout.getSelectedRow();
    }//GEN-LAST:event_toutMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String numero_e,motif,nb,date_d,date_r;
        Date dat_d,dat_r;
        if( C_numero_employe.getText().isEmpty() || C_motif.getText().isEmpty() || C_nb_jour.getText().isEmpty() || C_date_demande.getDate()==null || C_date_retour.getDate()==null )
        {
            JOptionPane.showMessageDialog(this, "Completer tout les champs");
        }
        else{
            try {
                numero_e = C_numero_employe.getText();
                motif = C_motif.getText();
                nb = C_nb_jour.getText();
                dat_d = C_date_demande.getDate();
                dat_r = C_date_retour.getDate();
                
                date1 = new java.sql.Date(dat_d.getTime());
                date2 = new java.sql.Date(dat_r.getTime());

                int num_e = Integer.parseInt(numero_e);
                int nb_j = Integer.parseInt(nb);

                Conge ajouter = new Conge();
                ajouter.Ajouter_c(num_e, motif,  date1, date2, nb_j);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(projet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        select_conge();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pActionPerformed
        // TODO add your handling code here:
        if( P_date.getDate()==null)
        {
            JOptionPane.showMessageDialog(this, "Entrer la date de pointage");
        }
        else{
        DefaultTableModel emp = (DefaultTableModel)tab_employe.getModel();
        int index = tab_employe.getRowCount();
        Date dat = P_date.getDate();
        date_p = new java.sql.Date(dat.getTime());
        for (int i=1; i<= index+2; i++)
        {
            Pointage ajout = new Pointage();
            try {
                ajout.Ajouter_p1(i,date_p);
            } catch (SQLException ex) {
                Logger.getLogger(projet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /*Employe absent = new Employe();
            absent.Modifier_e(i);*/
        }
        select_pointage();
        select_tout();}
    }//GEN-LAST:event_pActionPerformed

    private void E_prenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_prenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_prenomActionPerformed

    private void E_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_nomActionPerformed

    private void E_salaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_salaireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_E_salaireActionPerformed

    private void actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualiserActionPerformed
        // TODO add your handling code here:
        select_tout();
        select_employe();
        select_pointage();
        select_conge();
    }//GEN-LAST:event_actualiserActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new projet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser A_date;
    private javax.swing.JTextField A_montant;
    private javax.swing.JTextField A_nb_abs;
    private javax.swing.JTextField A_nom;
    private javax.swing.JTextField A_poste;
    private javax.swing.JTextField A_prenom;
    private com.toedter.calendar.JDateChooser C_date_demande;
    private com.toedter.calendar.JDateChooser C_date_retour;
    private javax.swing.JButton C_modifer;
    private javax.swing.JTextField C_motif;
    private javax.swing.JTextField C_nb_jour;
    private javax.swing.JTextField C_numero;
    private javax.swing.JTextField C_numero_employe;
    private javax.swing.JButton C_supprimer;
    private javax.swing.JButton E_ajouter;
    private javax.swing.JButton E_employe;
    private javax.swing.JTextField E_nom;
    private javax.swing.JTextField E_numero;
    private javax.swing.JTextField E_poste;
    private javax.swing.JTextField E_prenom;
    private javax.swing.JTextField E_salaire;
    private javax.swing.JButton E_supprimer;
    private javax.swing.JTextField E_txt_recherche;
    private javax.swing.JButton P_absent;
    private javax.swing.JButton P_ajouter;
    private com.toedter.calendar.JDateChooser P_date;
    private com.toedter.calendar.JDateChooser P_date_absence;
    private javax.swing.JButton P_modifier;
    private javax.swing.JTextField P_numero;
    private javax.swing.JTextField P_numero_employe;
    private javax.swing.JTextField P_pointage;
    private javax.swing.JButton P_supprimer;
    private javax.swing.JButton actualiser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton p;
    private javax.swing.JButton pdf;
    private javax.swing.JButton reste_conge;
    private javax.swing.JTable tab_conge;
    private javax.swing.JTable tab_employe;
    private javax.swing.JTable tab_pointage;
    private javax.swing.JTable tout;
    // End of variables declaration//GEN-END:variables
}
