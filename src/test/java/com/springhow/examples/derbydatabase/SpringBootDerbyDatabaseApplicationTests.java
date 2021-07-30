package com.springhow.examples.derbydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

//@SpringBootTest
class SpringBootDerbyDatabaseApplicationTests {
	/*
	 * EntityManagerFactory emFactory;
	 * 
	 * @Test void contextLoads() { //Creating Entity Manager emFactory =
	 * Persistence.createEntityManagerFactory("TestingDB"); em =
	 * emFactory.createEntityManager(); }
	 */
	private static final Logger log = Logger.getLogger(SpringBootDerbyDatabaseApplicationTests.class.getName());

	private static Connection conn;
	private static Statement stat;

	@BeforeAll
	static void testEmbeddedDataSource() throws SQLException {
		EmbeddedDataSource dataSource = new EmbeddedDataSource();
		dataSource.setDatabaseName("TestingDB");
		dataSource.setCreateDatabase("create");
		conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		stat = conn.createStatement();		
		//stat = conn.createStatement();
		String schema = "CREATE TABLE b ("
				+ " ID INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ " TITLE VARCHAR(255) , AUTHOR VARCHAR(255) )";
		stat.executeUpdate(schema);
		log.info("Stat: " + stat);
		//conn.close();
	}
	
	@Test
	void testInsertQueryWithDriverManager() throws SQLException {
		PreparedStatement prep = conn.prepareStatement("insert into b (TITLE, AUTHOR) values (?, ?)");
		prep.setString(1, "Il mago del nilo");
		prep.setString(2, "Christian Jacq");
		prep.addBatch();

		conn.setAutoCommit(false);
		prep.executeBatch();
		conn.setAutoCommit(false);

		// Data retrieve
		ResultSet rs = stat.executeQuery("select ID, TITLE, AUTHOR from b");

		Map<String, List<String>> actualBookMap = new HashMap<String, List<String>>();
		while (rs.next()) {
			List<String> bookCol = new ArrayList<String>();
			bookCol.add(rs.getString(2));
			bookCol.add(rs.getString(3));

			actualBookMap.put(rs.getString(1), bookCol);
		}
		log.info("actualBookMap: " + actualBookMap.toString());
		log.info("End");

		String expectedBookMap = "{1=[Il mago del nilo, Christian Jacq]}";
		Assertions.assertEquals(expectedBookMap, actualBookMap.toString());
		rs.close();
		/*
		 * DataSource dataSource = new
		 * EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY)
		 * .addScript("classpath:schema.sql") .addScript("classpath:data.sql") .build();
		 */
	}
	
	@AfterAll
	static void dropTableCreated() throws SQLException {
		//stat = conn.createStatement();
		String drop = "DROP TABLE b";
		stat.executeUpdate(drop);
		log.info("Stat: " + stat);
		conn.commit();
		conn.close();
	}

	/*@AfterAll
	void closeConnection() {
		
	}*/
}
