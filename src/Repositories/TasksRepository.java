package Repositories;

import java.io.*;
import java.text.*;
import java.util.*;

import Views.EnumStatus;
import Entities.*;

public class TasksRepository extends BaseRepository<Task> {
	
	public TasksRepository(String fileName) throws IOException, InstantiationException, IllegalAccessException, ParseException {
		super(fileName);
		
	}

	@Override
	protected void PopulateEntityFromStream(BufferedReader bufferedReader, Task item) throws IOException, ParseException {
		
		item.setTitle(bufferedReader.readLine());
		item.setDescription(bufferedReader.readLine());
		item.setAssessment(Integer.parseInt(bufferedReader.readLine()));
		item.setResponsibleUserId(Integer.parseInt(bufferedReader.readLine()));
		item.setCreateUserId(Integer.parseInt(bufferedReader.readLine()));
	
		String dateString = bufferedReader.readLine();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	    Date startDate = df.parse(dateString);
	    item.setDateCreate(startDate);
		
		dateString = bufferedReader.readLine();
		Date lastChanged = df.parse(dateString);
		item.setDateLastChange(lastChanged);
		item.setStatus(EnumStatus.valueOf(bufferedReader.readLine()));
	}

	@Override
	protected void WriteEntityToStream(PrintWriter printWriter, Task item) {
		
		printWriter.println(item.getTitle());
		printWriter.println(item.getDescription());
		printWriter.println(item.getAssessment());
		printWriter.println(item.getResponsibleUserId());
		printWriter.println(item.getCreateUserId());
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String dateCreated = df.format(item.getDateCreate());
		printWriter.println(dateCreated);
		
		String dateLastChanged = df.format(item.getDateLastChange());
		printWriter.println(dateLastChanged);
		printWriter.println(item.getStatus());
	}
	
	public ArrayList<Task> GetAll(int parentId) throws FileNotFoundException, IOException, ParseException{
		
		
		ArrayList<Task> result = new ArrayList<Task>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			
			String value = "";
			while((value = bufferedReader.readLine()) != null){
				
				Task task = new Task();
				task.setId(Integer.parseInt(value));
				PopulateEntityFromStream(bufferedReader, task); 
				
				if(task.getCreateUserId() == parentId || task.getResponsibleUserId() == parentId  ){
					result.add(task);
				}
			}
		return result;
		}
	}
}
