/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Juju
 */
public class Accueil {
    public void pdf(String date, String nom, String prenom, String poste, String nb_abs, Integer montant) throws DocumentException, FileNotFoundException, IOException, SQLException
    {
        /***************************************************************************************************/
        String salaire_lettre="";
        
        switch(montant)
        {
            case 0:
                salaire_lettre = "(Zero mille Ariary)";
                break;
            case 10000:
                salaire_lettre = "(Dix mille Ariary)";
                break;
            case 20000:
                salaire_lettre = "(Vingt mille Ariary)";
                break;
            case 30000:
                salaire_lettre = "(Trente mille Ariary)";
                break;
            case 40000:
                salaire_lettre = "(Quarante mille Ariary)";
                break;
            case 50000:
                salaire_lettre = "(Cinquante mille Ariary)";
                break;
            case 60000:
                salaire_lettre = "(Soicante mille Ariary)";
                break;
            case 70000:
                salaire_lettre = "(Soisante dix mille Ariary)";
                break;
            case 80000:
                salaire_lettre = "(Quatre vingt mille Ariary)";
                break;
            case 90000:
                salaire_lettre = "(Quatre vingt dix mille Ariary)";
                break;
            case 100000:
                salaire_lettre = "(Cent mille Ariary)";
                break;
            case 110000:
                salaire_lettre = "(Cent dix mille Ariary)";
                break;
            case 120000:
                salaire_lettre = "(Cent vingt mille Ariary)";
                break;
            case 130000:
                salaire_lettre = "(Cent trente mille Ariary)";
                break;
            case 140000:
                salaire_lettre = "(Cent quarante mille Ariary)";
                break;
            case 150000:
                salaire_lettre = "(Cent cinquante mille Ariary)";
                break;
            case 160000:
                salaire_lettre = "(Cent soisante mille Ariary)";
                break;
            case 170000:
                salaire_lettre ="(Cent soisante dix mille Ariary)";
                break;
            case 180000:
                salaire_lettre = "(Cent quatre vingt mille Ariary)";
                break;
            case 190000:
                salaire_lettre = "(Cent quatre vingt dix mille Ariary)";
                break;
            case 200000:
                salaire_lettre = "(Deux cent mille Ariary)";
                break;
            case 210000:
                salaire_lettre = "(Deux cent dix mille Ariary)";
                break;
            case 220000:
                salaire_lettre = "(Deux cent vingt mille Ariary)";
                break;
            case 230000:
                salaire_lettre = "(Deux cent trente mille Ariary)";
                break;
            case 240000:
                salaire_lettre = "(Deux cent quarante mille Ariary)";
                break;
            case 250000:
                salaire_lettre = "(Deux cent cinquante mille Ariary)";
                break;
            case 260000:
                salaire_lettre = "(Deux cent soisante mille Ariary)";
                break;
            case 270000:
                salaire_lettre = "(Deux cent soisante dix mille Ariary)";
                break;
            case 280000:
                salaire_lettre = "(Deux cent quatre vingt mille Ariary)";
                break;
            case 290000:
                salaire_lettre = "(Deux cent quatre vingt dix mille Ariary)";
                break;
            case 300000:
                salaire_lettre = "(Trois cent mille Ariary)";
                break;
        }
        
        String salaire =" "+montant+"";
        /**********************************************************************************************/
        File folder = new File("documents");
        if(folder.exists())
        {folder.mkdir();}
        
        /*************************************************************************************************/
        String nom_fichier = "documents/fiche.pdf";
        Document document=  new Document();
        PdfWriter Pdf ; 
        PdfWriter pdf = PdfWriter.getInstance(document,new FileOutputStream(nom_fichier));
        document.open();
        document.addTitle(nom_fichier);
        document.addAuthor("fiche");
        Paragraph preface = new Paragraph();
        Paragraph titre = new Paragraph("Fiche de paie");
        
        /**********************************************************************************************************/
        Font titreF = new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.BLACK);
        Font reponseF = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.NORMAL,BaseColor.BLACK);
        
        /*************************************************************************************************/
        String l_titre= "Fiche de paie",l_date ="Date : ", l_nom = "Nom : ",l_prenom = "Prenom : ",l_nb_absence = "Nombre d'absences : ",l_poste = "Poste : ",l_salaire = "Salaire : ",l_l_salaire = "AR"+salaire_lettre;
        Chunk c_l_titre = new Chunk(l_titre,titreF);
        Chunk c_l_date = new Chunk(l_date,reponseF);
        Chunk c_l_nom = new Chunk(l_nom,reponseF);
        Chunk c_l_prenom = new Chunk(l_prenom,reponseF);
        Chunk c_l_poste = new Chunk(l_poste,reponseF);
        Chunk c_l_nb_absence = new Chunk(l_nb_absence,reponseF);
        Chunk c_l_salaire = new Chunk(l_salaire,reponseF);
        Chunk c_l_l_salaire = new Chunk(l_l_salaire,reponseF);
        
        /****************************************************************************************************////
        preface.add(c_l_titre);
        preface.add(new Paragraph());
        preface.add(new Paragraph());
        preface.add(c_l_date);
        preface.add(date);//eto miova
        preface.add(new Paragraph());
        preface.add(new Paragraph());
        preface.add(c_l_nom);
        preface.add(nom);//eto miova
        preface.add(new Paragraph());
        preface.add(new Paragraph());
        preface.add(c_l_prenom);
        preface.add(prenom);//eto miova
        preface.add(new Paragraph());
        preface.add(new Paragraph());
        preface.add(c_l_poste);
        preface.add(poste);//eto miova
        preface.add(new Paragraph());
        preface.add(new Paragraph());
        preface.add(c_l_nb_absence);
        preface.add(nb_abs);//eto miova
        preface.add(new Paragraph());
        preface.add(new Paragraph());
        preface.add(c_l_salaire);
        preface.add(salaire);
        preface.add(c_l_l_salaire);//eto miova
        
        
        document.add(preface);
        document.close();
        
        Desktop desk = Desktop.getDesktop();
        desk.open(folder);
        
    }
}
