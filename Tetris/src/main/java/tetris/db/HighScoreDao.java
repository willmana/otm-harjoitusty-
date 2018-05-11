package tetris.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Our playerdatabase, which saves name, score and id.
 * 
 */
public class HighScoreDao implements Dao<HighScore, Integer> {

    private Database database;

    public HighScoreDao(Database database) {
        this.database = database;
    }

    /**
     * Deletes HighScore from database with matching PRIMARY KEY.
     *
     * @param key
     * @throws SQLException
     */
    public void delete(Integer key) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM highscore WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * Saves HighScore to the database.
     *
     * @param highscore
     * @return highscore
     * @throws SQLException
     */
    private HighScore save(HighScore highscore) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO highscore"
                + " (NAME, SCORE)"
                + " VALUES (?, ?)");
        stmt.setString(1, highscore.getPlayer());
        stmt.setInt(2, highscore.getPoints());

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM highscore"
                + " WHERE NAME = ?");
        stmt.setString(1, highscore.getPlayer());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        HighScore p = new HighScore(rs.getString("NAME"), rs.getInt("SCORE"));

        stmt.close();
        rs.close();

        conn.close();

        return p;
    }

    /**
     * Updates HighScore in database with matching name and PRIMARY KEY.
     *
     * @param highscore
     * @return
     * @throws SQLException
     */
    private HighScore update(HighScore highscore) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE highscore SET"
                + " NAME = ? WHERE id = ?");
        stmt.setInt(1, highscore.getId());
        stmt.setString(2, highscore.getPlayer());

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return highscore;
    }

    /**
     * Uses private methods save() and update().
     *
     * @param highscore
     * @return highscore
     * @throws SQLException
     */
    public HighScore saveOrUpdate(HighScore highscore) throws SQLException {
        if ((Integer) highscore.getId() == null) {
            return save(highscore);
        } else {
            return update(highscore);
        }

    }

    /**
     * Returns list of highscores in database.
     *
     * @return List of highscores.
     * @throws SQLException
     */
    public List<HighScore> findAll() throws SQLException {
        List<HighScore> scores = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM highscore");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            HighScore highscore = new HighScore(rs.getString("NAME"), rs.getInt("SCORE"));

            scores.add(highscore);
        }

        stmt.close();
        rs.close();

        connection.close();
        return scores;
    }

    /**
     * Finds our lucky and skillful top 10 players.
     *
     * @throws SQLException
     * @return List<HighScore>
     */
    public List<HighScore> findTop() throws SQLException {
        List<HighScore> scores = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM highscore "
                + "ORDER BY SCORE DESC limit 10");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            HighScore highscore = new HighScore(rs.getString("NAME"), rs.getInt("SCORE"));

            scores.add(highscore);
        }

        stmt.close();
        rs.close();

        connection.close();
        return scores;

    }

    /**
     * Sometimes we drop the table down.
     *
     * @throws SQLException
     * @return HighScore
     */
    public HighScore dropTable() throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DROP TABLE highscore");

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return new HighScore("DROPING", 13);
    }

    /**
     * Finds and returns highscore with specific PRIMARY KEY.
     *
     * @param key
     * @return highscore
     * @throws SQLException
     */
    @Override
    public HighScore findOne(Integer key) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM highscore WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        HighScore highscore = new HighScore(rs.getString("NAME"), rs.getInt("SCORE"));

        stmt.close();
        rs.close();

        conn.close();

        return highscore;

    }

}
