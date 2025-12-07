package com.example.seamarket.dao;

import com.example.seamarket.dao.UserDao;
import com.example.seamarket.db.ConnectionManager;
import com.example.seamarket.db.DbExecutor;
import com.example.seamarket.db.RowMapper;
import com.example.seamarket.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class UserDaoImpl implements UserDao {

    private final DbExecutor dbExecutor = new DbExecutor();

    private final RowMapper<User> userMapper = rs -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setEmail(rs.getString("email"));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return user;
    };

    @Override
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password_hash, email, created_at) VALUES(?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.executeUpdate(conn, sql,
                    user.getUsername(),
                    user.getPasswordHash(),
                    user.getEmail(),
                    user.getCreatedAt());
        }
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = "UPDATE users SET username=?, password_hash=?, email=? WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.executeUpdate(conn, sql,
                    user.getUsername(),
                    user.getPasswordHash(),
                    user.getEmail(),
                    user.getId());
        }
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.executeUpdate(conn, sql, id);
        }
    }

    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.queryForObject(conn, sql, userMapper, id);
        }
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.queryForObject(conn, sql, userMapper, username);
        }
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.queryForObject(conn, sql, userMapper, email);
        }
    }

}
