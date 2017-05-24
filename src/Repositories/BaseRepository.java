package Repositories;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import Entities.*;
import Tools.*;

public abstract class BaseRepository<T extends BaseEntityWithId> {

	protected String fileName;
	
	public BaseRepository(String fileName) throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
		this.fileName = fileName;
		
		File file = new File(fileName);
		if (!file.exists()) {
			
			file.createNewFile();
			InitializeDatabase();
		}
	}

	protected void InitializeDatabase() throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
	}
	protected abstract void PopulateEntityFromStream(BufferedReader bufferedReader, T item) throws IOException, ParseException;
	protected abstract void WriteEntityToStream(PrintWriter printWriter, T item);
	
	private int GetNextId() throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
		int nextId = 0;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
		
			String value = "";
			while((value = bufferedReader.readLine()) != null) {

				T item = ReflectionHelper.<T>CreateInstance(this.getClass(), 1);
				item.setId(Integer.parseInt(value));
				
				PopulateEntityFromStream(bufferedReader, item);
				
				if (nextId < item.getId())
					nextId = item.getId();
			}
		}
		
		return nextId + 1;
	}
	
	public T  GetById(int id) throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ParseException {
		
		T result = null;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
		
			String value = "";
			while((value = bufferedReader.readLine()) != null) {
				
				T item = ReflectionHelper.<T>CreateInstance(this.getClass(), 1);
				item.setId(Integer.parseInt(value));
				
				PopulateEntityFromStream(bufferedReader, item);
				
				if (item.getId() == id) {
					result = item;
					break;
				}
			}
		}

		return result;
	}
	
	public ArrayList<T> GetAll() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ParseException {
		
		ArrayList<T> result = new ArrayList<T>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			
			String value = "";
			while((value = bufferedReader.readLine()) != null) {
				
				T item = ReflectionHelper.<T>CreateInstance(this.getClass(), 1);
				item.setId(Integer.parseInt(value));
				
				PopulateEntityFromStream(bufferedReader, item);
				
				result.add(item);
			}		
		}
		
		return result;
	}
	
	public void Add(T item) throws IOException, InstantiationException, IllegalAccessException, ParseException {
		
		item.setId(GetNextId());
		
		try (PrintWriter printWriter  = new PrintWriter(new FileWriter(fileName, true))) {
			
			printWriter.println(item.getId());
			
			WriteEntityToStream(printWriter, item);
		}
	}
	
	public void Edit(T item) throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ParseException {
		
		String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
				 PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))) {
			
			String value = "";
			while((value = bufferedReader.readLine()) != null) {
				
				T tempEntity = ReflectionHelper.<T>CreateInstance(this.getClass(), 1);
				tempEntity.setId(Integer.parseInt(value));
				
				PopulateEntityFromStream(bufferedReader, tempEntity);
				
				if (tempEntity.getId() != item.getId()) {
				
					printWriter.println(tempEntity.getId());
					
					WriteEntityToStream(printWriter, tempEntity);
				}
				else {
					
					printWriter.println(item.getId());
					
					WriteEntityToStream(printWriter, item);
				}
			}
		}
		
		File original = new File(fileName);
		File tempFile = new File(tempFileName);
		
		original.delete();
		tempFile.renameTo(original);
		tempFile.delete();
	}
	
	public void Delete(T item) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ParseException {

		String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
				 PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))) {
			
			String value = "";
			while((value = bufferedReader.readLine()) != null) {
				
				T tempEntity = ReflectionHelper.<T>CreateInstance(this.getClass(), 1);
				tempEntity.setId(Integer.parseInt(value));
				
				PopulateEntityFromStream(bufferedReader, tempEntity);
				
				if (tempEntity.getId() != item.getId()) {
				
					printWriter.println(tempEntity.getId());
					
					WriteEntityToStream(printWriter, tempEntity);
				}
			}
		}
		
		File original = new File(fileName);
		File tempFile = new File(tempFileName);
		
		original.delete();
		tempFile.renameTo(original);
		tempFile.delete();
	}
}
