package Entities;

public class Comment extends BaseEntityWithId {

	
	private int TaskId;
	private String UserName;
	private String Content;
	
	public int getTaskId() {
		return TaskId;
	}
	
	public void setTaskId(int taskId) {
		TaskId = taskId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
}
