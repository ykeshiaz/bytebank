package com.techelevator.model;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.security.PasswordHasher;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JDBCUserDAOTest extends DAOIntegrationTest {


    private JDBCUserDAO dao;
    private PasswordHasher hashMaster;



    @Before
    public void setup() {

        dao = new JDBCUserDAO(getDataSource(), hashMaster);

    }

    @Test
    public void saveUserTest() throws NullPointerException {
//        byte[] salt = hashMaster.generateRandomSalt();
//        String password = null;
//        String hashedPassword = hashMaster.computeHash(password, salt);
//        String saltString = new String(Base64.encode(salt));
        User user = createUser((long) 5,"sel", "sc", "sc" );

        dao.saveUser(user.getUserName(), user.getPassword());
        List<User>  allUsers =  dao.getAllUsers();

        Assert.assertEquals(user, allUsers.get(5));
    }

    private User createUser(Long userId, String userName, String password, String confirmPassword){
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        return user;
    }

}

