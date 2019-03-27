package model;

public class Category {

    private String name;
	private String description;
	
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}

    public String getName() {
		return name;
	}
	public void setName(String naam) {
		this.name = naam;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object anderObject) {
		boolean isEqual = false;

		if (anderObject instanceof Category) {
			Category andereCategory = (Category) anderObject;

			if (this.name.equals(andereCategory.getName()) &&
					this.description.equals(andereCategory.description)) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}
