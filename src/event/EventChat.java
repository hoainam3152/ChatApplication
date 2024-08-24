package event;

import java.sql.SQLException;

import javax.swing.ImageIcon;

import pojo.ChatPOJO;

public interface EventChat {

    public void sendMessage(ChatPOJO message);
    
    public void receiveMessage(ChatPOJO message) throws SQLException;
    
    public void sendImage(ImageIcon imageIcon);
    
    public void sendFile(String fileName, String mb);
}
