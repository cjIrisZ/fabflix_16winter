import java.util.ArrayList;

public class Stars_in_Movie {
	private String movieid;
	private ArrayList<String> starList = new ArrayList<String>();
	
	public Stars_in_Movie(){}
	
	public Stars_in_Movie(String movieid, ArrayList<String> starList)
	{
		this.movieid = movieid;
		this.starList = starList;
	}
	
	public String getMovieid() {
		return movieid;
	}
	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}
	
	public ArrayList<String> getStarList(){
		return starList;
	}
	public void setStarList(ArrayList<String> starList)
	{
		this.starList = starList;
	}
	public void updateStarList(String star){
		this.starList.add(star);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Relationship Details - ");
		sb.append("MovieId:" + getMovieid());
		sb.append(", StarList:" + getStarList());
		sb.append(".");
		
		return sb.toString();
	}
}
