
import java.util.Scanner;
import Services.AuthenticationServices;
import Views.AuthenticationView;
import Views.TaskManagementView;
import Views.UserManagementView;

public class Programa {
	

	public static void main(String[] args) throws Exception {
		
		AuthenticationView authView = new AuthenticationView();
		authView.Run();
		
		if(AuthenticationServices.getInstance().getLoggedUser() != null){
			
			if(AuthenticationServices.getInstance().getLoggedUser().getIsAdmin()){
				isAdminMenu();
			}
			else{
				
				TaskManagementView taskView = new TaskManagementView();
				taskView.Run();
			}
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Good bye");
		}
	} 
		
	static void isAdminMenu() throws Exception{
	System.out.print("Choose [U]sers or [T]asks : ");
	Scanner scanner = new Scanner(System.in);
	String choise = scanner.nextLine();
	
		switch(choise.toUpperCase()){
			case "U": {	
				UserManagementView userView = new UserManagementView();
				userView.Run(); 
				break;
		
			}
			case "T" : {
				TaskManagementView taskView = new TaskManagementView();
				taskView.Run();
			}
		}
		scanner.close();
	}
}
