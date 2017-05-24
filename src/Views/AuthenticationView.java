package Views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import Services.AuthenticationServices;

public class AuthenticationView {

public void Run() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ParseException {
		
		while(AuthenticationServices.getInstance().getLoggedUser() == null) {
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Username: ");
			String username = scanner.nextLine();
			
			System.out.print("Password: ");
			String password = scanner.nextLine();
			
			AuthenticationServices.getInstance().AuthenticateUser(username, password);
		}
		
	}
}
