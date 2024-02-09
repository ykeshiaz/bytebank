package com.techelevator.model;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;
import com.techelevator.security.PasswordHasher;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}
	
	@Override
	public void saveUser(String userName, String password) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		
		jdbcTemplate.update("INSERT INTO app_user(id, user_name, password, salt) VALUES (?, ?, ?, ?)",
				getNextId(), userName, hashedPassword, saltString);
	}

	private Long getNextId() {
		SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT nextval('app_user_id_seq') AS id;");
		if(row.next()) {
			return row.getLong("id");
		}
		return 0L;
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(user_name) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		jdbcTemplate.update("UPDATE app_user SET password = ? WHERE user_name = ?", password, userName);
	}

	@Override
	public User getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM app_user "+
		"WHERE UPPER(user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase()); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setUserId(user.getLong("id"));
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
		}

		return thisUser;
	}

	@Override
	public List<User> getAllUsers(){
		List<User> allUsers = new ArrayList<>();
		String select = "SELECT * FROM app_user;";
		SqlRowSet user = jdbcTemplate.queryForRowSet(select);

		User users = null;
		while(user.next()) {
			users = new User();
			users.setUserId(user.getLong("id"));
			users.setUserName(user.getString("user_name"));
			users.setPassword(user.getString("password"));
			allUsers.add(users);
		}

		return  allUsers;
	}

}
