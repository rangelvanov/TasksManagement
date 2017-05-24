package Views;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.User;
import Repositories.UsersRepository;

public class UserManagementView {
	
	public void Run() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		while(true) {
			EnumMenu choice = RenderMenu();
			
			switch(choice){
				
				case List: {
					List();
					break;
				}
				case Add: {
					Add();
					break;
				}
				case Edit: {
					Edit();
					break;
				}
				case Delete: {
					Delete();
					break;
				}
				case View: {
					View();
					break;
				}
				case Exit: {
					return;
				}
			}
		}
		
	}
	
	private EnumMenu RenderMenu() throws IOException{
		
		while(true){
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			System.out.println("[L]ist Users");
			System.out.println("[A]dd user");
			System.out.println("[E]dit User");
			System.out.println("[D]elete User");
			System.out.println("[V]iew User");
			System.out.println("E[x]it");
			

			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			
			switch(choice.toUpperCase()){
				case "L":{
					return EnumMenu.List;
				}
				case "A":{
					return EnumMenu.Add;
				}
				case "E":{
					return EnumMenu.Edit;
				}
				case "D":{
					return EnumMenu.Delete;
				}
				case "V":{
					return EnumMenu.View;
				}
				case "X":{
					return EnumMenu.Exit;
				}
				default: {
					continue;
				}
			}
		}

	}
	
	public void Add() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####Add User####");
		
		Scanner scanner = new Scanner(System.in);
		
		User item = new User();
		
		System.out.print("Username:");
		item.setUserName(scanner.nextLine());
		
		System.out.print("Password:");
		item.setPassWord(scanner.nextLine());
		
		System.out.print("Firstname:");
		item.setFirstName(scanner.nextLine());
		
		System.out.print("Lastname:");
		item.setLastName(scanner.nextLine());
		
		UsersRepository UserRepo = new UsersRepository("users.txt");
		UserRepo.Add(item);
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Item added successfully");
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
		
		
		
		
	}
	
	public void Delete() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####Delete User####");
		
		UsersRepository userRepo = new UsersRepository("users.txt");
		ArrayList<User> users = userRepo.GetAll();
		for(int i=0;i<users.size();i++) {
			
			User user = users.get(i);
			
			System.out.print(user.getUserName() + " ( " + user.getId() + " )\t");
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter ID of user: ");
		int id = Integer.parseInt(scanner.nextLine());
		
		User user = userRepo.GetById(id);
		userRepo.Delete(user);
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Item deleted successfully");
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
	}
	
	public void Edit() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####Edit User####");
		
		UsersRepository userRepo = new UsersRepository("users.txt");
		ArrayList<User> users = userRepo.GetAll();
		
		for(int i=0;i<users.size();i++) {
			
			User user = users.get(i);
			
			System.out.print(user.getUserName() + " ( " + user.getId() + " )\t");
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter ID of user: ");
		int id = Integer.parseInt(scanner.nextLine());
		
		User user = userRepo.GetById(id);
		
		System.out.print("Username (" + user.getUserName() + "): ");
		user.setUserName(scanner.nextLine());
		
		System.out.print("Password ( " + user.getPassWord() + ") : ");
		user.setPassWord(scanner.nextLine());
		
		System.out.print("First Name ( " + user.getFirstName() + " ): ");
		user.setFirstName(scanner.nextLine());
		
		System.out.print("Last Name: (" + user.getLastName() + " ): ");
		user.setLastName(scanner.nextLine());
		
		System.out.print("Is Admin: (" + user.getIsAdmin() + " ): ");
		user.setIsAdmin(Boolean.parseBoolean(scanner.nextLine()));
		
		userRepo.Edit(user);
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Item updated successfully");
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
	}
	
	public void List() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####List Users####");
		
		UsersRepository userRepo = new UsersRepository("users.txt");
		ArrayList<User> users = userRepo.GetAll();
		
		for(int i=0;i<users.size();i++) {
			
			User user = users.get(i);
			
			System.out.println(user.getId());
			System.out.println(user.getUserName());
			System.out.println(user.getPassWord());
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
			System.out.println("---------------------------------");
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
	}
		
	
	public void View() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####View User####");
		
		UsersRepository userRepo = new UsersRepository("users.txt");
		ArrayList<User> users = userRepo.GetAll();
		
		for(int i=0;i<users.size();i++) {
			
			User user = users.get(i);
			
			System.out.print(user.getUserName() + " ( " + user.getId() + " )\t");
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter ID of user: ");
		int id = Integer.parseInt(scanner.nextLine());
		
		User user = userRepo.GetById(id);
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(user.getId());
		System.out.println(user.getUserName());
		System.out.println(user.getPassWord());
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
	}	
	
		public void View2() throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
		UsersRepository userRepo = new UsersRepository("users.txt");
		ArrayList<User> users = userRepo.GetAll();
		
		for(int i=0;i<users.size();i++) {
			
			User user = users.get(i);
			
			System.out.print(user.getUserName() + " ( " + user.getId() + " )\t");
		}
}
		
	
	public void Exit(){
		
	}
	
	
	
	
	
	

}
