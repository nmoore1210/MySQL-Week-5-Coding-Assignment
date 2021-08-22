import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {
	
	private static Connection conn;
	
	public static void main(String[] args) throws SQLException {
		
		final String connectionStr = "jdbc:mysql://localhost:3306/music";
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Attempting to connect to DB...");
		
			conn = DriverManager.getConnection(connectionStr, "root", "Hugooreo1210!");
		
			System.out.println("Successfully connected to DB\n");
			
			System.out.print("Enter artist id: ");
			String id = scanner.nextLine();
			selectAllArtists(id);
			
			
			
			System.out.println("What artist released a new album?");
			String artist_name_update = scanner.nextLine();
			
			System.out.println("What is the new album total?");
			int total_albums = scanner.nextInt();
			
			updateArtist(total_albums, artist_name_update);
			System.out.println("Now let's enter a new artist");
			System.out.println("What is the artist's name?");
			String Artist_Name = scanner.nextLine();
			
			scanner.nextLine();
			
			System.out.println("What is the artist's year of origin?");
			int origin_year = scanner.nextInt();
			
			System.out.println("How many albums does the artist have?");
			int albums = scanner.nextInt();
			
			createNewArtist(Artist_Name, origin_year, albums); 
			
			System.out.println("What is the earliest origin date you would like for a music artist?");
			
			int earliest_origin_date = scanner.nextInt();
			
			deleteArtist(earliest_origin_date);
			
		
		} catch (SQLException e) {
			System.out.println("Unable to connect to DB");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				System.out.println("\nClosing DB connection");
				
				conn.close();
				
				System.out.println("Successfully disconnected from DB");
			}
		}
		
	}
	
	public static void selectAllArtists(String id) {
		final String SELECT_Query = "SELECT * FROM music_artists WHERE id = ";
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_Query + id);
			
			while(rs.next()) {
				System.out.println("Artist: " + rs.getString(2) + " Origin: " + rs.getInt(3));
				
			}
			
		
			} catch (SQLException e) {
				System.out.println("Error occured in selectAllArtists()");
				e.printStackTrace();
			}
	}
	
	
	public static void createNewArtist(String a, int b, int c) { 
		
		final String query = " insert into music_artists (Artist_Name, Origin_Year, Total_Albums)"
				+ " values (?,?,?)";
		
		try { 
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, a);
		preparedStmt.setInt(2,  b);
		preparedStmt.setInt(3, c);
		
		preparedStmt.execute();
		} catch (Exception e) {
			System.out.println("Error occured in createNewArtist()");
			e.printStackTrace();
		}
	}
	
	public static void updateArtist(int a, String b) {
		final String query = "update music_artists set Total_Albums = ? where Artist_Name = ?";
		
		try {
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(2, b);
		preparedStmt.setInt(1, a);
		preparedStmt.execute();
		} catch (Exception e) {
			System.out.println("Error occured in updateArtist()");
			e.printStackTrace();
		}
	}
	
	public static void deleteArtist(int a) {
		final String sql = "DELETE FROM music_artists WHERE Origin_Year < ?";
		
		try {
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, a);
		
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Entries were deleted successfully!");
		}
		} catch (Exception e) {
			System.out.println("Error occured in deleteArtist()");
			e.printStackTrace();
		}
	}

}	


