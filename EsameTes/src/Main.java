
import dao.DAO_Oggetto;
import dao.DBManager;
import corebusiness.oggettiCelesti.*;
import corebusiness.Exception.*;
import corebusiness.articoli.*;

import java.util.ArrayList;
import java.sql.*;
import ui.UIVisitatore;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		
	UIVisitatore ui=new UIVisitatore();
			ui.RicercaArticolo();
		
		
	}
		/*OggettoCeleste ogg=null;
		ArrayList<OggettoCeleste> listaOggetti=null;
		try{
	
	
				
				Connection c= DBManager.getConnection();
				PreparedStatement stat=c.prepareStatement("UPDATE ARTICOLI SET CORPO='Gli opposti' WHERE ID_ARTICOLO=2");
				PreparedStatement stat=c.prepareStatement("INSERT INTO OGGETTICELESTI(ID_OGGETTO,NOME,TIPO) VALUES (?,?,?)");
				stat.setInt(1, 6);
			    stat.setString(2,"SOLE");
				stat.setString(3,"STELLA");
				stat.executeUpdate();
				c.commit();
				stat=c.prepareStatement("INSERT INTO ARTICOLI(ID_ARTICOLO,TITOLO,OGGETTO,CORPO) VALUES (?,?,?,?)");
				stat.setInt(1, 7);
			    stat.setString(2,"IL SOLE");
				stat.setInt(3, 6);
				stat.setString(4, "Colui che ci riscalda");
			    stat.executeUpdate();
				c.commit();
				GestoreArticoli gest= new GestoreArticoli();
				ArrayList<String> listaTitoli=gest.listaTitoli();
			    ArrayList<Articolo> listaArticolo=gest.ricercaArticoli("","STELLA");
				ogg=DAO_Oggetto.read(1);
				System.out.println(ogg.ottieniTipo());
			    
			    System.out.println(listaArticolo.get(0).getTitolo());
				System.out.println(listaTitoli.get(0));
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(OggettoCelesteNotFound o)
		{	
			o.printErrorMessage();
		}
				
		
	
	}*/

}
