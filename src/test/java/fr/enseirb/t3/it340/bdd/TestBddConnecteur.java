package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;

public class TestBddConnecteur {
	@Test
	public void throwsExceptionWhenNegativeNumbersAreGiven() {

    	JdbcConnectionPool pool = JdbcConnectionPool.create("jdbc:h2:" + "~/production", "wrong","wrong");
        try {
        	pool.getConnection();
            fail();
        } catch (SQLException e) {
        	System.out.println(e);
        }
	}
}
