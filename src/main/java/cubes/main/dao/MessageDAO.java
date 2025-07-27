package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Message;


public interface MessageDAO {

	public List<Message> getAllMessages();
	
	public void saveMessage(Message message);
	
	  // ne treba nam update poruke jer stize od korisnika
	  //  i samo treba da pogledamo i oznacimo da li je
		// vidljiva ili nevidljiva. necemo ni da brisemo.
	
	public void markAsSeen(int id); // id poruke koju hocu da oznacim
	
	public long getUnreadMessagesCount(); // broj neprocitanih poruka
	
	public Message getMessageById(int id);
	
}
