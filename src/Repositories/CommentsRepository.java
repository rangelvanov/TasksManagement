package Repositories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import Entities.Comment;

public class CommentsRepository extends BaseRepository<Comment>{
	
	
	
	public CommentsRepository(String fileName) throws IOException, InstantiationException, IllegalAccessException, ParseException {
		super(fileName);
	}

	@Override
	protected void PopulateEntityFromStream(BufferedReader bufferedReader, Comment item) throws IOException, ParseException {
		
		item.setTaskId(Integer.parseInt(bufferedReader.readLine()));
		item.setUserName(bufferedReader.readLine());
		item.setContent(bufferedReader.readLine());	
	}

	@Override
	protected void WriteEntityToStream(PrintWriter printWriter, Comment item) {
		
		printWriter.println(item.getTaskId());
		printWriter.println(item.getUserName());
		printWriter.println(item.getContent());
	}
	
	public ArrayList<Comment> GetAll(int parentId) throws FileNotFoundException, IOException, ParseException{
		
		ArrayList<Comment> result = new ArrayList<Comment>();
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
			
			String value = "";
			while ((value = bufferedReader.readLine()) != null){
				
				Comment item = new Comment();
				item.setId(Integer.parseInt(value));
				
				PopulateEntityFromStream(bufferedReader, item);
				
				if(item.getTaskId() == parentId)
					result.add(item);
			}		
		}
		return result;
	}

	
}
