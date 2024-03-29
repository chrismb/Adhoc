import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;


public class LeboncoinTest {

	public LeboncoinTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Statement stmt;
		ResultSet rslt = null;
        Class.forName("org.h2.Driver");// charge le driver pour la jdbc
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "christophermb", "jeanjean"); // Cree une connection avec la bd
        
        stmt = conn.createStatement();//Cree un stmt pour la bd correspondant a la connexion conn
/*        
        stmt.execute("Drop table Ut");
        
        String str = "CREATE TABLE Ut(U_ID int,nom varchar(255),PRIMARY KEY(U_ID))";
        stmt.execute(str);
        
        str = "INSERT INTO Ut VALUES (1,'Ivan'),(2,'Qing')";
        stmt.execute(str);
*/
/*        
//Obtenir la description de tous les produits dont le prix de depart est 10.
        rslt = stmt.executeQuery("SELECT * FROM Produit WHERE prixDepart=10");
        //rslt.beforeFirst();
        while  (rslt.next()){
        System.out.println("La description de "+rslt.getString("nom")+" est : "+rslt.getString("description"));
        }
        
//Obtenir le nom et la description de tous les objets vendus par Nada (en supposant qu'il n'y a qu'une seule Nada)
       	rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE nom='Nada'");
        int sought_ID=0;
        while  (rslt.next()){
        	sought_ID = rslt.getInt("U_ID");
        }
        rslt = stmt.executeQuery("SELECT * FROM Produit WHERE V_ID="+sought_ID);
        rslt.beforeFirst();
        System.out.println("Nada Vend :");
        while  (rslt.next()){
        System.out.println(rslt.getString("nom")+" : "+rslt.getString("description"));
        }
        
 */       
        
        
        
        String str="";
        Scanner user_input = new Scanner( System.in );
       
//Ajout d'une nouvelle offre
        System.out.println("Ajout d'une offre");
        user_input = new Scanner( System.in );
        
        // Fixe la date
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        ft.format(date);
        // Fixe le statut
        String statut = "Attente";
        //Fixe le nom
        System.out.print("Votre nom : ");//Pour l'instant on ne teste pas si il appartient a la BD
        String nom = user_input.next( );

        // Fixele produit
        System.out.print("Quel produit voulez vous acheter ? ");//Pour l'instant on ne teste pas si il appartient a la BD
        String produit = user_input.next( );

        // Fixe le montant
        System.out.print("A combien ? ");
        double montant = user_input.nextDouble( );
        // Fixe l'O_ID
        rslt = stmt.executeQuery("SELECT * FROM Offre");
        rslt.last();
        int nbreOffres = rslt.getRow();
        // Recherche l'U_ID du nom entre   
        rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE nom='"+nom+"'");
        int Acht_ID=0;
        while  (rslt.next()){
        	Acht_ID = rslt.getInt("U_ID");
        }
        // Recherche leP_ID du produit
        rslt = stmt.executeQuery("SELECT * FROM Produit WHERE nom='"+produit+"'");
        int Produit_ID=0;
        while  (rslt.next()){
        	Produit_ID = rslt.getInt("P_ID");
        }
          
        str = "INSERT INTO Offre VALUES (" + (nbreOffres+1) + "," + 
        								montant + ",'" + 
        								ft.format(date) + "','" + 
        								statut + "'," + 
        								Acht_ID + "," + 
        								(Produit_ID) + ")";
        stmt.execute(str);
    
        
//Ajout d'un nouvel utilisateur
        System.out.println("Ajout d'un utilisateur");
        user_input = new Scanner( System.in );
        //Fixe le nom
        System.out.print("Votre nom : ");//Pour l'instant on ne teste pas si il appartient a la BD
        String U_nom = user_input.next( );
        
        //Fixe le mail
        System.out.print("Votre mail : ");//Pour l'instant on ne teste pas si il appartient a la BD
        String mail = user_input.next( );

        // Fixe la note
        float note = (float) U_nom.charAt(0) - (float) 'A' +1;
        
        // Fixe l'O_ID
        rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
        rslt.last();
        int nbreUtilisateurs = rslt.getRow();
        
        str = "INSERT INTO Utilisateur VALUES (" + (nbreUtilisateurs+1) + ",'" + 
        								U_nom + "','" + 
        								mail + "'," + 
        								note + ")";
        stmt.execute(str);

       
        
//Ajout d'un nouveau produit
        System.out.println("Ajout d'un produit");
        user_input = new Scanner( System.in );
        
        //Fixe le nom
        System.out.print("Votre nom : ");//Pour l'instant on ne teste pas si il appartient a la BD
        String V_nom = user_input.next( );
        
        //Fixe le nom
        System.out.print("Nom du produit : ");
        String P_nom = user_input.next( );
        
        //Fixe la description
        System.out.print("Description du produit : ");
        user_input.nextLine( );
        String description = user_input.nextLine( );

        System.out.print("A combien ? ");
        double prixDepart = user_input.nextDouble( );
     
        // Fixe le P_ID
        rslt = stmt.executeQuery("SELECT * FROM Produit");
        rslt.last();
        int nbreProduits = rslt.getRow();
        
        // Recherche l'U_ID du nom entre   
        rslt = stmt.executeQuery("SELECT * FROM Utilisateur WHERE nom='"+V_nom+"'");
        int Vend_ID=0;
        while  (rslt.next()){
        	Vend_ID = rslt.getInt("U_ID");
        }
        
        
        str = "INSERT INTO Produit VALUES (" + (nbreProduits+1) + ",'" + 
        								P_nom + "'," + 
        								prixDepart + ",'" + 
        								description + "'," + 
        								Vend_ID + ")";
        stmt.execute(str);  


 


        
 //Affichage des Utilisateurs :
        rslt = stmt.executeQuery("SELECT * FROM Utilisateur");
    	System.out.println("ID\tnom\t\tmail\t\t\tnote");
    	for(int i=0;i<60;i++){
    		System.out.print("-");
    	}
    	System.out.println();
    	while  (rslt.next()){
    			
        	System.out.print(rslt.getInt("U_ID") + "\t");
        	System.out.print(rslt.getString("nom"));
        	
        	for (int i = 2-(rslt.getString("nom").length()/8);i>0 ;i--){
        		System.out.print("\t");
        	}

        	System.out.print(rslt.getString("mail"));
        	
        	for (int i = 3-(rslt.getString("mail").length()/8);i>0 ;i--){
        			System.out.print("\t");
        	}
        	System.out.print(rslt.getFloat("note")+"\n");
        }
        
    	
    	System.out.println();
//Affichage des Offres :
        rslt = stmt.executeQuery("SELECT * FROM Offre");
    	System.out.println("ID\tmontant\tdate\t\tstatut\t\tA_ID\tP_ID");
    	for(int i=0;i<80;i++){
    		System.out.print("-");
    	}
    	System.out.println();

    	while  (rslt.next()){
    			
        	System.out.print(rslt.getInt("O_ID") + "\t");
        	System.out.print(rslt.getDouble("montant") + "\t");
        	System.out.print(rslt.getString("date") + "\t");
        	System.out.print(rslt.getString("statut") + "\t");
        	for (int i = 1-(rslt.getString("statut").length()/8);i>0 ;i--){
    			System.out.print("\t");
        	}
        	System.out.print(rslt.getString("A_ID") + "\t");
        	System.out.print(rslt.getString("P_ID") + "\n");

        } 
    	
     	System.out.println();
     	
//Affichage des Produits :
        rslt = stmt.executeQuery("SELECT * FROM Produit");
    	System.out.println("ID\tnom\t\tprixDepart\tdescription\t\t\t\t\tV_ID");
    	for(int i=0;i<100;i++){
    		System.out.print("-");
    	}
    	System.out.println();
    	while  (rslt.next()){
    			
        	System.out.print(rslt.getInt("P_ID") + "\t");
        	System.out.print(rslt.getString("nom"));
        	
        	for (int i = 2-(rslt.getString("nom").length()/8);i>0 ;i--){
        		System.out.print("\t");
        	}

        	System.out.print(rslt.getString("prixDepart") + "\t\t");
        	
        	System.out.print(rslt.getString("description"));
        	
        	for (int i = 6-(rslt.getString("description").length()/8);i>0 ;i--){
        			System.out.print("\t");
        	}
        	System.out.print(rslt.getInt("V_ID")+"\n");
        }
        
    	
    	
    	//Just a test to check egit changes
    	
        
        conn.close();
	}

}
