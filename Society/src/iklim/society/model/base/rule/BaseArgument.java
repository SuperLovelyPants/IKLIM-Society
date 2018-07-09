package iklim.society.model.base.rule;

public class BaseArgument {
	private String			name;
	private String			type;

	public BaseArgument(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
