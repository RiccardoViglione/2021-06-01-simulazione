package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenze;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public void getAllGenes(Map<String,Genes>idMap){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
if(!idMap.containsKey(res.getString("GeneID"))) {
				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				idMap.put(genes.getGeneId(), genes);
			}
}
			res.close();
			st.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL ERROR");
			
		}
	}
	

	public List<Genes> getVertici(Map<String,Genes>idMap){
		String sql = "select distinct g.`GeneID` as id "
				+ "from genes g "
				+ "where g.`Essential`='Essential' "
				+ "order by g.GeneID";
		List<Genes>result=new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
result.add(idMap.get(res.getString("id")));

				
			
}
			res.close();
			st.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new RuntimeException("SQL ERROR");
			
		}
		return result;
	}
	
	public List<Adiacenze> getArchi(Map<String,Genes>idMap){
		String sql = "select distinct g.`GeneID`as id1 ,g2.`GeneID`as id2,ABS(i.`Expression_Corr`)as peso "
				+ "from genes g,genes g2,interactions i "
				+ "where g.`Essential`='Essential' and g2.`Essential`='Essential' and g.`GeneID`>g2.`GeneID` "
				+ "and (g.`GeneID`=i.`GeneID1`||g.`GeneID`=i.`GeneID2`)and (g2.`GeneID`=i.`GeneID1`||g2.`GeneID`=i.`GeneID2`)  "
				+ "group by g.GeneID,g2.GeneID,peso ";
		List<Adiacenze>result=new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

result.add(new Adiacenze(idMap.get(res.getString("id1")),idMap.get(res.getString("id2")),res.getDouble("peso")));
				
			
}
			res.close();
			st.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL ERROR");
			
		}
		return result;
	}
	

	
}
