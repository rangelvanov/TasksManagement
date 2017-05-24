package Repositories;
import java.io.*;
import java.text.ParseException;

import Entities.*;


public class UsersRepository extends BaseRepository<User> {

	public UsersRepository(String fileName) throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
		 super(fileName);
	}

	protected void InitializeDatabase() throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
		User user = new User();
		user.setUserName("admin");
		user.setPassWord("adminpass");
		user.setFirstName("Administrator");
		user.setLastName("Administrator");
		user.setIsAdmin(true);
		
		Add(user);
	}
	
	protected void PopulateEntityFromStream(BufferedReader bufferedReader,User item) throws IOException {
			
		item.setUserName(bufferedReader.readLine());
		item.setPassWord(bufferedReader.readLine());
		item.setFirstName(bufferedReader.readLine());
		item.setLastName(bufferedReader.readLine());
		item.setIsAdmin(Boolean.parseBoolean(bufferedReader.readLine()));
	}

	protected void WriteEntityToStream(PrintWriter printWriter, User item) {
		
		printWriter.println(item.getUserName());
		printWriter.println(item.getPassWord());
		printWriter.println(item.getFirstName());
		printWriter.println(item.getLastName());
		printWriter.println(item.getIsAdmin());
	}

	public User GetByUsernameAndPassword(String username, String password) throws FileNotFoundException, IOException {
		
		User result = null;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
		
			String value = "";
			while((value = bufferedReader.readLine()) != null) {
				
				User user = new User();
				user.setId(Integer.parseInt(value));
				PopulateEntityFromStream(bufferedReader, user);
				
				if (user.getUserName().compareTo(username) == 0 && user.getPassWord().compareTo(password) == 0) {
					result = user;
					break;
				}
			}		
		}
		return result;
	}
}
