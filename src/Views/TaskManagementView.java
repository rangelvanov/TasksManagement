package Views;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.Task;
import Entities.User;
import Repositories.TasksRepository;
import Repositories.UsersRepository;
import Services.AuthenticationServices;

public class TaskManagementView {
	
	public void Run() throws Exception {
		
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
	
	private void View() throws Exception {
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####View Task####");
		
		
		TasksRepository taskRepo = new TasksRepository("task.txt");
		ArrayList<Task> tasks = taskRepo.GetAll(AuthenticationServices.getInstance().getLoggedUser().getId());
		
		for(int i=0; i<tasks.size();i++){
			
			Task task = tasks.get(i);
			
			System.out.print(task.getTitle() + " ( " + task.getId() + " )\t " );
		}
		Scanner scanner = new Scanner(System.in);
			
		System.out.print("Enter Id of Task: ");
		int id = Integer.parseInt(scanner.nextLine());
		Task task = taskRepo.GetById(id);
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		System.out.println(task.getTitle());
		System.out.println(task.getDescription());
		System.out.println(task.getAssessment());
		System.out.println(task.getCreateUserId());
		System.out.println(task.getResponsibleUserId());
		System.out.println(task.getDateCreate());
		System.out.println(task.getDateLastChange());
		System.out.println(task.getStatus());
		
		System.out.println("\n\n");
		System.out.println("Press [Enter] to Log Time");
		scanner.nextLine();
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		LogWorkManagementView logWork = new LogWorkManagementView(task);
		logWork.Run();
		
	}

	private void Delete() throws IOException, ParseException, InstantiationException, IllegalAccessException {
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####Delete Task####");
		
		TasksRepository taskRepo = new TasksRepository("task.txt");
		ArrayList<Task> tasks = taskRepo.GetAll(AuthenticationServices.getInstance().getLoggedUser().getId());
		
		for(int i=0; i<tasks.size();i++){
			
			Task task = tasks.get(i);
			
			System.out.print(task.getTitle() + " ( " + task.getId() + " )\t " );
		}
		Scanner scanner = new Scanner(System.in);
			
		System.out.print("Enter Id of Task: ");
		int id = Integer.parseInt(scanner.nextLine());
		Task task = taskRepo.GetById(id);
		taskRepo.Delete(task);
			
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Item deleted successfully");
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
		
	}

	private void Edit() throws Exception {
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####Edit Task####");
		
		TasksRepository taskRepo = new TasksRepository("task.txt");
		ArrayList<Task> tasks = taskRepo.GetAll(AuthenticationServices.getInstance().getLoggedUser().getId());
		
		for(int i=0; i<tasks.size();i++){
			
			Task task = tasks.get(i);
			
			System.out.print(task.getTitle() + " ( " + task.getId() + " )\t " );
		}
		Scanner scanner = new Scanner(System.in);
			
		System.out.print("Enter Id of Task: ");
		int id = Integer.parseInt(scanner.nextLine());
		Task item = taskRepo.GetById(id);
		
		System.out.print("Title ( " + item.getTitle() + " ): ");
		item.setTitle(scanner.nextLine());
		
		System.out.print("Description ("+ item.getDescription() + "): ");
		item.setDescription(scanner.nextLine());
		
		System.out.print("Assesment ("+ item.getAssessment() + "):");
		item.setAssessment(Integer.parseInt(scanner.nextLine()));
		
		System.out.println("Choose Responsible User By Id :");
		View2();
		System.out.println("Responsible User Id (" + item.getResponsibleUserId() + "):");
		item.setResponsibleUserId(Integer.parseInt(scanner.nextLine()));
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	    Date startDate = df.getCalendar().getTime();
	    item.setDateLastChange(startDate); 
	    StatusMenu(item);
		
		
		taskRepo.Edit(item);
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Item edited successfully");
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
		
	}

	private void Add() throws IOException, ParseException, InstantiationException, IllegalAccessException {
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####Add Task####");
		Scanner scanner = new Scanner(System.in);
		
		Task item = new Task();
		item.setCreateUserId(AuthenticationServices.getInstance().getLoggedUser().getId());
		
		System.out.print("Title:");
		item.setTitle(scanner.nextLine());
		
		System.out.print("Description:");
		item.setDescription(scanner.nextLine());;
		
		System.out.print("Assessment:");
		item.setAssessment(Integer.parseInt(scanner.nextLine()));
		
		System.out.println("Choose Responsible User:");
		View2();
		System.out.print("Responsible User ID:");
		item.setResponsibleUserId(Integer.parseInt(scanner.nextLine()));
	
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	    
		item.setDateCreate(df.getCalendar().getTime());
		item.setDateLastChange(df.getCalendar().getTime()); 
		item.setStatus(EnumStatus.Pending);
		
		TasksRepository taskRepo = new TasksRepository("task.txt");
		taskRepo.Add(item);
			
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Item added successfully");
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
	}

	private void List() throws IOException, ParseException, InstantiationException, IllegalAccessException {
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("####List Tasks####");
		
		TasksRepository taskRepo = new TasksRepository("task.txt");
		ArrayList<Task> tasks = taskRepo.GetAll(AuthenticationServices.getInstance().getLoggedUser().getId());
		
		for(int i=0; i<tasks.size(); i++){
			
			Task task = tasks.get(i);
			
			System.out.println(task.getId());
			System.out.println(task.getTitle());
			System.out.println(task.getDescription());
			System.out.println(task.getAssessment());
			System.out.println(task.getDateCreate());
			System.out.println(task.getDateLastChange());
			System.out.println(task.getStatus());
			System.out.println("---------------------------------");
		}
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Press [Enter] to continue");
		scanner.nextLine();
	}

	private EnumMenu RenderMenu() throws IOException {
		
		while(true){
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			System.out.println("[L]ist Task");
			System.out.println("[A]dd Task");
			System.out.println("[E]dit Task");
			System.out.println("[D]elete Task");
			System.out.println("[V]iew Task");
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

	public void View2() throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
		UsersRepository userRepo = new UsersRepository("users.txt");
		ArrayList<User> users = userRepo.GetAll();
		
		for(int i=0;i<users.size();i++) {
			
			User user = users.get(i);
			
			System.out.println(user.getUserName() + " ( " + user.getId() + " )\t");
		}
}

	public void StatusMenu(Task item) throws Exception{
		
			Scanner scanner = new Scanner(System.in);
			System.out.print("Select task status [P]ending, " + " [F]inished, " + " [I]nProgress : ");
		
			String choice = scanner.nextLine();
			switch (choice.toUpperCase()){
		
				case "P": {
					item.setStatus(EnumStatus.Pending);
					break;
				}
				case "F" : {
					item.setStatus(EnumStatus.Finished);
					break;
				}
				case "I" : {
					item.setStatus(EnumStatus.InProgress);
					break;
				}
				default: throw new Exception("Invalid choice!");	
			}
	}
}

