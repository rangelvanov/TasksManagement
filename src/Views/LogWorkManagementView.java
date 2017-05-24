package Views;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.Comment;
import Entities.LogWork;
import Entities.Task;
import Repositories.CommentsRepository;
import Repositories.LogWorkRepository;
import Repositories.TasksRepository;
import Services.AuthenticationServices;

public class LogWorkManagementView {

	public Task parent;
	
	public LogWorkManagementView(Task parent){
		
		this.parent = parent;
		
	}

	public void Run() throws Exception{
		while(true){
			EnumLogWork choice = RenderMenu();
			
			switch (choice){
				case LogWork : Add(); break;
				case ViewLogWork : ListLogs(); break;
				case Comment : Comment(); break;
				case AllComments : ListComments(); break;
				case StatusChange : StatusChange(parent); break;
				case Exit: return;
			}
		}
	}

	private EnumLogWork RenderMenu(){
		
		while(true){
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			System.out.println("[L]og work ");
			System.out.println("[V]iew work logs ");
			System.out.println("[C]omment ");
			System.out.println("[A]ll comments ");
			System.out.println("[S]tatus resolve ");
			System.out.println("e[X]it ");
			
			System.out.println();
			System.out.println("Choice: ");
			
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			
			switch(choice.toUpperCase()){
				case "L": return EnumLogWork.LogWork;
				case "V": return EnumLogWork.ViewLogWork;
				case "C": return EnumLogWork.Comment;
				case "A": return EnumLogWork.AllComments;
				case "S": return EnumLogWork.StatusChange;
				case "X": return EnumLogWork.Exit;
				default: continue;
			}
		}
		
		
	}

	private void Add() throws IOException, ParseException, InstantiationException, IllegalAccessException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#### Log Work ####");
		
		Scanner scanner = new Scanner(System.in);
		
		LogWork item = new LogWork();
		
		item.setTaskId(parent.getId());
		item.setUserId(AuthenticationServices.getInstance().getLoggedUser().getId());
		
		System.out.print("Log Time : ");
		item.setLogTime(Integer.parseInt(scanner.nextLine()));
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		item.setLogDate(df.getCalendar().getInstance().getTime());
		
		LogWorkRepository logRepo = new LogWorkRepository("Task"+parent.getId() +"LogWork.txt");
		logRepo.Add(item);
		
		System.out.println("\n\n\n");
		System.out.println("#### Added Successfully ####");
		System.out.println("#### Press [Enter] to continue \n\n\n\n\n");
		scanner.nextLine();
		
	}
	
	private void ListLogs() throws IOException, ParseException, InstantiationException, IllegalAccessException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#### View Logged Work ####");
		
		LogWorkRepository logRepo = new LogWorkRepository("Task"+parent.getId() +"LogWork.txt");
		ArrayList<LogWork> logArray = logRepo.GetAll(parent.getId());
		
		for(int i=0; i<logArray.size(); i++){
			LogWork logItem = logArray.get(i);
			System.out.println(logItem.getId());
			System.out.println("Task : " + logItem.getTaskId());
			System.out.println("Log Time : " + logItem.getLogTime());
			System.out.println(logItem.getLogDate());
			System.out.println("###############################");
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("Press [Enter] to continue");
		scan.nextLine();
		
	}

	public void StatusChange(Task item) throws Exception{
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#### New Status ####");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Current Status : ("+ item.getStatus() + ")");
		System.out.print("Chooce New Status : [P]ending, " + " [F]inished, " + " [I]nProgress : ");
	
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
		TasksRepository taskRepo = new TasksRepository("Task.txt");
		taskRepo.Edit(parent);
		
		System.out.println("\n\n\n");
		
		System.out.println("Status was changed Successfully to : (" + item.getStatus() + "):" );
		
		Comment comment = new Comment();
		System.out.print("Please write a comment... : ");
		comment.setContent("New Status ("+ item.getStatus()+") :"+ scanner.nextLine());
		comment.setTaskId(parent.getId());
		comment.setUserName(AuthenticationServices.getInstance().getLoggedUser().getFirstName());
		
		CommentsRepository commentsRepo = new CommentsRepository("Task" + parent.getId() + "Comments.txt");
		commentsRepo.Add(comment);
		 
		System.out.println("#### Press [Enter] to continue");
		scanner.nextLine();
		
		
	}
	
	private void Comment() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		Scanner scanner = new Scanner(System.in);
		Comment comment = new Comment();
		
		System.out.print("Please write a comment... : ");
		comment.setContent(scanner.nextLine());
		comment.setTaskId(parent.getId());
		comment.setUserName(AuthenticationServices.getInstance().getLoggedUser().getFirstName());
		
		CommentsRepository commentsRepo = new CommentsRepository("Task" + parent.getId() + "Comments.txt");
		commentsRepo.Add(comment);
	}

	private void ListComments() throws IOException, InstantiationException, IllegalAccessException, ParseException{
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("#### All Comments ####");
		System.out.println();
		
		CommentsRepository commentsRepo = new CommentsRepository("Task" + parent.getId() + "Comments.txt");
		ArrayList<Comment> comments = commentsRepo.GetAll(parent.getId());
		
		for(int i=0; i<comments.size();i++){
			
			Comment comment = comments.get(i);
			
			System.out.println("Comment " + comment.getId());
			System.out.println("Task " + comment.getTaskId());
			System.out.print(comment.getUserName() + ":" + comment.getContent());
			System.out.println("\n ____________________________________________________\n");
			
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Press [Enter] to continue");
		scan.nextLine();
		
	}

	
}
