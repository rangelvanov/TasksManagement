package Entities;




import java.util.Date;

import Views.EnumStatus;

public class Task extends BaseEntityWithId {

	private String title;
	private String description;
	private int assessment;
	private int responsibleUserId;
	private int createUserId;	
	private Date dateCreate;
	private Date dateLastChange;
	private EnumStatus status;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getAssessment() {
		return assessment;
	}
	
	public void setAssessment(int assessment) {
		this.assessment = assessment;
	}

	public int getResponsibleUserId() {
		return responsibleUserId;
	}

	public void setResponsibleUserId(int responsibleUserId) {
		this.responsibleUserId = responsibleUserId;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	

	

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	

	

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateLastChange() {
		return dateLastChange;
	}

	public void setDateLastChange(Date dateLastChange) {
		this.dateLastChange = dateLastChange;
	}

	

	



	
	
	

	
	
}
