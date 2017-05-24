package Services;

import java.io.IOException;
import java.text.ParseException;

import Entities.User;
import Repositories.UsersRepository;


public class AuthenticationServices {

	private static AuthenticationServices instance = null;
	
	private AuthenticationServices(){
		
	}
	
	public static AuthenticationServices getInstance(){
		
		if(AuthenticationServices.instance == null)
			AuthenticationServices.instance = new AuthenticationServices();
			
			
			
		return AuthenticationServices.instance;
	}
	
	private User authenticatedUser =  null;
	
	public User getLoggedUser() {
		return authenticatedUser;
	}
	
	public void AuthenticateUser(String username,String password) throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		UsersRepository usersRepo = new UsersRepository("users.txt");
		this.authenticatedUser = usersRepo.GetByUsernameAndPassword(username, password);
	}
}
