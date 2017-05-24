package Repositories;

import java.io.*;
import java.text.*;
import java.util.*;
import Entities.*;

public class LogWorkRepository extends BaseRepository<LogWork> {
	
	public LogWorkRepository(String fileName) throws IOException, InstantiationException, IllegalAccessException, ParseException {
		super(fileName);
		
	}

	@Override
	protected void PopulateEntityFromStream(BufferedReader bufferedReader, LogWork item) throws IOException, ParseException {
		
		item.setTaskId(Integer.parseInt(bufferedReader.readLine()));
		item.setUserId(Integer.parseInt(bufferedReader.readLine()));
		item.setLogTime(Integer.parseInt(bufferedReader.readLine()));
		
		String DateString = bufferedReader.readLine();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date startDate = df.parse(DateString);
		item.setLogDate(startDate);	
	}

	@Override
	protected void WriteEntityToStream(PrintWriter printWriter, LogWork item) {
	
		printWriter.println(item.getTaskId());
		printWriter.println(item.getUserId());
		printWriter.println(item.getLogTime());
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String dateCreated = df.format(item.getLogDate());
		printWriter.println(dateCreated);
	}
	
	public ArrayList<LogWork> GetAll(int parentId) throws FileNotFoundException, IOException, ParseException {
		
		ArrayList<LogWork> result = new ArrayList<LogWork>();
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
		
			String value = "";
			while ((value = bufferedReader.readLine()) != null){
				
				LogWork item = new LogWork();
				item.setId(Integer.parseInt(value));
				PopulateEntityFromStream(bufferedReader, item);
			
				if(item.getTaskId() == parentId){
					result.add(item);
				}
			}
		
			return result;
		}
	}	
}