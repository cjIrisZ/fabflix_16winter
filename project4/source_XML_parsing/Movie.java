import java.util.ArrayList;

public class Movie {
	private String id;
	private String title;
	private String year;
	private String director;
	private ArrayList<String> genres = new ArrayList<String>();


	public Movie(){
		
	}
	
	public Movie(String id, String title, String year, String director, ArrayList<String> genres) {
		this.id  = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.genres = genres;		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getDirector(){
		return director;
	}
	public void setDirector(String director){
		this.director = director;
	}
	
	public ArrayList<String> getGenres(){
		return genres;
	}
	public void updateGenres(String genre){
		this.genres.add(genre);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Movie Details - ");
		sb.append("Id:" + getId());
		sb.append(", Title:" + getTitle());
		sb.append(", Year:" + getYear());
		sb.append(", Director:" + getDirector());
		sb.append(", Genres: " + getGenres());
		sb.append(".");
		
		return sb.toString();
	}
}
