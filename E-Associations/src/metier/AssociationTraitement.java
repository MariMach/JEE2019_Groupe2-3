package metier;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import dao.SingletonConnection;
import web.AssociationModel;
import web.CategorieModel;

public class AssociationTraitement {
	
	private static Connection conx = SingletonConnection.getConnection();

	public static void addassociation(AssociationModel assoc,String s) throws FileNotFoundException{
	    InputStream img = new FileInputStream(new File(s));
	

		

		try {
			PreparedStatement ps;
			ps = (PreparedStatement) conx.prepareStatement("insert into association values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			    ps.setInt(1,assoc.getId_assoc());
		     	ps.setString(2,assoc.getNom_assoc());
		     	ps.setString(3,assoc.getDate_creation());
		     	ps.setString(4,assoc.getTele_assoc());
		     	ps.setString(5,assoc.getPresident_assoc());
		     	ps.setString(6,assoc.getDescription_assoc());
		     	ps.setInt(7,assoc.getEffectif());
		     	ps.setString(8,assoc.getFax_assoc());
		     	ps.setString(9,assoc.getSite_web());
		     	ps.setString(10,assoc.getEmail_assoc());
		     	ps.setInt(11, assoc.getId_authentif());
		     	ps.setInt(12,assoc.getId_categorie());
                ps.setBinaryStream(13,img, (int)(new File(s).length()));   
                System.out.println("wow");


		     

	            ps.executeUpdate();
	            ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public ArrayList<AssociationModel> getAllAssociations(){
		java.sql.PreparedStatement ps;
		ArrayList<AssociationModel> AM = new ArrayList<AssociationModel> ();
	
		try {
			ps = (PreparedStatement) conx.prepareStatement("select * from association");
			ResultSet rs;
			rs = ps.executeQuery();
            while(rs.next()){
             AssociationModel c=new AssociationModel();
             c.setNom_assoc(rs.getString("nom_assoc")); 
             c.setPresident_assoc(rs.getString("president_assoc"));   
             c.setDescription_assoc(rs.getString("description_assoc")); 
             c.setDate_creation(rs.getString("date_creation"));
             c.setLogo(rs.getBytes("logo_assoc"));  
             c.setEmail_assoc(rs.getString("email_assoc"));  
             c.setTele_assoc(rs.getString("tele_assoc"));
             c.setFax_assoc(rs.getString("fax_assoc")); 
             c.setEffectif(rs.getInt("effectif"));
             AM.add(c);
            }
            return AM ;
            }
			
			
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AM;
} 
	public ArrayList<AssociationModel> getAllAssociations_Cat(int id_categorie){
		java.sql.PreparedStatement ps;
		ArrayList<AssociationModel> AM = new ArrayList<AssociationModel> ();
	
		try {
			ps = (PreparedStatement) conx.prepareStatement("select * from association where id_categorie ="+id_categorie);
			ResultSet rs;
			rs = ps.executeQuery();
            while(rs.next()){
             AssociationModel c=new AssociationModel();
             c.setNom_assoc(rs.getString("nom_assoc")); 
             c.setPresident_assoc(rs.getString("president_assoc"));   
             c.setDescription_assoc(rs.getString("description_assoc")); 
             c.setDate_creation(rs.getString("date_creation"));
             c.setLogo(rs.getBytes("logo_assoc"));  
             c.setEmail_assoc(rs.getString("email_assoc"));  
             c.setTele_assoc(rs.getString("tele_assoc"));
             c.setFax_assoc(rs.getString("fax_assoc")); 
             c.setEffectif(rs.getInt("effectif"));
             AM.add(c);
            }
            return AM ;
            }
			
			
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return AM;
} 
	
	
public  AssociationModel  Association(int id_assoc) {
		
		java.sql.ResultSet rs;
		java.sql.PreparedStatement ps;
		AssociationModel Cl=new AssociationModel();
		   int l = 0;

		
		try {
			ps = (PreparedStatement) conx.prepareStatement("select * from association where id_assoc ="+id_assoc);
			   ps.executeQuery();
			   rs=ps.getResultSet();
				AssociationModel am =new AssociationModel();
			  while(rs.next()){
				  
				  am.setNom_assoc(rs.getString("nom_assoc")); 
				  am.setPresident_assoc(rs.getString("president_assoc"));   
				  am.setDescription_assoc(rs.getString("description_assoc")); 
				  am.setDate_creation(rs.getString("date_creation"));
				  am.setLogo(rs.getBytes("logo_assoc"));  
				  am.setEmail_assoc(rs.getString("email_assoc"));  
				  am.setTele_assoc(rs.getString("tele_assoc"));
				  am.setFax_assoc(rs.getString("fax_assoc")); 
				  am.setEffectif(rs.getInt("effectif"));
				  
			  } 
			  
			  ps.close();
			  return am;
			  
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  return Cl;

			}
		 }

public void upadatAssociation(AssociationModel assoc ,int id_assoc,String s) throws FileNotFoundException {
		
		java.sql.PreparedStatement ps;
	
		AssociationTraitement at = new AssociationTraitement();
	    InputStream img = new FileInputStream(new File(s));


		/*
		 * 
		 * id_assoc 	nom_assoc 	date_creation 	tele_assoc 	president_assoc 
		 * 	description_assoc 	effectif 	fax_assoc 	site_web 	
		 * email_assoc 	id_authentif 	id_categorie  
		 * 
		 * 
		 * 
		 */
		
		try {
			
			ps = (PreparedStatement) conx.prepareStatement("UPDATE association SET nom_assoc= ? ,date_creation=? , tele_assoc = ?"
					+ " , president_assoc= ? ,description_assoc =? ,effectif= ? , fax_assoc = ? , site_web = ? "
					+ ",email_assoc = ? , logo_assoc= ? WHERE id_assoc= ?");
		
			
	     	ps.setString(1,assoc.getNom_assoc());
	     	ps.setString(2,assoc.getDate_creation());
	     	ps.setString(3,assoc.getTele_assoc());
	     	ps.setString(4,assoc.getPresident_assoc());
	     	ps.setString(5,assoc.getDescription_assoc());
	     	ps.setInt(6,assoc.getEffectif());
	     	ps.setString(7,assoc.getFax_assoc());
	     	ps.setString(8,assoc.getSite_web());
	     	ps.setString(9,assoc.getEmail_assoc());
            ps.setBinaryStream(10,img, (int)(new File(s).length()));  
            ps.setInt(11, id_assoc);
			ps.executeUpdate(); 
			ps.close();}

		         catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

}
	
	

	
	public static void main(String[] args) throws FileNotFoundException {
		
		AssociationModel am = new AssociationModel();
		AssociationTraitement at = new AssociationTraitement();
		am.setDate_creation("333");
		am.setDescription_assoc("333");
		am.setEffectif(4);
		am.setEmail_assoc("333");
		am.setFax_assoc("333");
		am.setId_assoc(2);
		am.setId_categorie(1);
		am.setNom_assoc("333");
		am.setPresident_assoc("333");
		am.setSite_web("333");
		am.setTele_assoc("333");
		//at.addassociation(am, "logo_CINDH.png");
		at.upadatAssociation(am, 2, "logo_CINDH.png");
		/*am=at.Association(2);	
		System.out.println(am);*/
		
	}
}
