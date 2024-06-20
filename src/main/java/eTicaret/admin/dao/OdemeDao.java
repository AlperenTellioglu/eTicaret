package eTicaret.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eTicaret.admin.model.Odeme;

public class OdemeDao {
	private Connection connection;

	public OdemeDao(Connection connection) {
		this.connection = connection;
	}

	public List<Odeme> list() {
		List<Odeme> odemeler = new ArrayList<>();
		String sql = "SELECT * FROM odemeler";
		try (PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("odemeId");
				String odemeTuru = resultSet.getString("odemeTuru");
				Odeme odeme = new Odeme(id, odemeTuru);
				odemeler.add(odeme);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return odemeler;
	}

	public void create(Odeme odeme) throws SQLException {
		String sql = "INSERT INTO odemeler (odemeTuru) VALUES (?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, odeme.getOdemeTuru());
			statement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void update(Odeme odeme) throws SQLException {
		String sql = "UPDATE odemeler SET odemeTuru = ? WHERE odemeId = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, odeme.getOdemeTuru());
			statement.setInt(2, odeme.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void delete(int odemeId) throws SQLException {
		String sql = "DELETE FROM odemeler WHERE odemeId = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, odemeId);
			statement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
