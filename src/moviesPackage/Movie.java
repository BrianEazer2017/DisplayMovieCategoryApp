package moviesPackage;

import java.util.ArrayList;
import java.util.List;

public class Movie {

	private String title;
	private List<String> category = new ArrayList<>();
	
	public Movie() {
		
	}
	public Movie(String title, String category) {
		this.title = title;
		this.category.add(category);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category.add(category);
	}

}
