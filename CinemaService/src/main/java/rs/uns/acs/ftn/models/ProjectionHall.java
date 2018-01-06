package rs.uns.acs.ftn.models;

import org.springframework.data.annotation.Id;

public class ProjectionHall {

	@Id
	private String id;
	private String label;
	private int capacity;
	private int rowCount;
	private int colCount;
	private HallType type;
	
	public ProjectionHall() {
		
	}

	public ProjectionHall(String label, int capacity, int rowCount, int colCount, HallType type) {
		super();
		this.label = label;
		this.capacity = capacity;
		this.rowCount = rowCount;
		this.colCount = colCount;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	public HallType getType() {
		return type;
	}

	public void setType(HallType type) {
		this.type = type;
	}
	
	
}
