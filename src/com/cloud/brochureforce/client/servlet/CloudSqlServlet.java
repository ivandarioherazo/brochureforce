/**
 * 
 */
package com.cloud.brochureforce.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

//import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.cloud.brochureforce.client.jsf.ExampleBean;

/**
 * Cloud SQL API example.
 * 
 * @author <a href=
 *         "https://cloud.google.com/appengine/docs/java/cloud-sql/">Google</a>
 */
@SuppressWarnings("serial")
public class CloudSqlServlet extends HttpServlet {
	//@Inject
	//public ExampleBean jsfbExample; // CDI didn't work at all!!.
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		/*
		try {
			this.jsfbExample.setInjectedProperty("CloudSqlServlet - YAY! CDI WORKS!");
		} catch (Exception e) {
			throw new ServletException("CloudSqlServlet - I'M SAAAAAD! CDI IS NOT WORKING!");
		}
		*/
		
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}
		// store only the first two octets of a users ip address
		String userIp = req.getRemoteAddr();
		InetAddress address = InetAddress.getByName(userIp);
		if (address instanceof Inet6Address) {
			// nest indexOf calls to find the second occurrence of a character
			// in a string
			// an alternative is to use Apache Commons Lang:
			// StringUtils.ordinalIndexOf()
			userIp = userIp.substring(0, userIp.indexOf(":", userIp.indexOf(":") + 1)) + ":*:*:*:*:*:*";
		} else if (address instanceof Inet4Address) {
			userIp = userIp.substring(0, userIp.indexOf(".", userIp.indexOf(".") + 1)) + ".*.*";
		}

		final String createTableSql = "CREATE TABLE IF NOT EXISTS visits ( visit_id BIGINT NOT NULL "
				+ "AUTO_INCREMENT, user_ip VARCHAR(46) NOT NULL, timestamp DATETIME NOT NULL, "
				+ "PRIMARY KEY (visit_id) )";
		final String createVisitSql = "INSERT INTO visits (user_ip, timestamp) VALUES (?, ?)";
		final String selectSql = "SELECT user_ip, timestamp FROM visits ORDER BY timestamp DESC " + "LIMIT 10";

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		String url;
		if (System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
			// Check the System properties to determine if we are running on
			// appengine or not
			// Google App Engine sets a few system properties that will reliably
			// be present on a remote
			// instance.
			//url = System.getProperty("ae-cloudsql.cloudsql-database-url");
			url = System.getProperty("cloud.db.url") + "?user=" +
				  System.getProperty("cloud.db.user") + "&password=" +
				  System.getProperty("cloud.db.password");
			try {
				// Load the class that provides the new "jdbc:google:mysql://"
				// prefix.
				Class.forName(System.getProperty("cloud.db.driver"));
				log(System.getProperty("cloud.db.driver") + " HAS BEEN LOADED!!");
			} catch (ClassNotFoundException e) {
				throw new ServletException("Error loading Google JDBC Driver: " + e.getMessage());
			}
		} else {
			// Set the url with the local MySQL database connection url when running locally
			//url = System.getProperty("ae-cloudsql.local-database-url");
			url = System.getProperty("dev.db.url") + "?user=" +
				  System.getProperty("dev.db.user") + "&password="+
				  System.getProperty("dev.db.password");
		}
		log("connecting to: " + url);
		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement statementCreateVisit = conn.prepareStatement(createVisitSql)) {
			conn.createStatement().executeUpdate(createTableSql);
			statementCreateVisit.setString(1, userIp);
			statementCreateVisit.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			statementCreateVisit.executeUpdate();

			try (ResultSet rs = conn.prepareStatement(selectSql).executeQuery()) {
				out.print("Last 10 visits:\n");
				while (rs.next()) {
					String savedIp = rs.getString("user_ip");
					String timeStamp = rs.getString("timestamp");
					out.print("Time: " + timeStamp + " Addr: " + savedIp + "\n");
				}
			}
		} catch (SQLException e) {
			throw new ServletException("SQL error", e);
		}
	}
}