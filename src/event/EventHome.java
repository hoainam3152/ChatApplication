package event;

import java.sql.SQLException;

import pojo.UserAccountPOJO;

public interface EventHome {
	public void selectUser(UserAccountPOJO user) throws SQLException;

    public void updateUser(UserAccountPOJO user) throws SQLException;
}
