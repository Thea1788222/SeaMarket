package com.example.seamarket.dao;

import com.example.seamarket.dao.ItemDao;
import com.example.seamarket.db.ConnectionManager;
import com.example.seamarket.db.DbExecutor;
import com.example.seamarket.db.RowMapper;
import com.example.seamarket.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {

    private final DbExecutor dbExecutor = new DbExecutor();

    private final RowMapper<Item> itemMapper = rs -> {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setSellerId(rs.getInt("seller_id"));
        item.setTitle(rs.getString("title"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setStatus(rs.getString("status"));
        item.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return item;
    };

    @Override
    public int insert(Item item) throws SQLException {
        String sql = "INSERT INTO items(seller_id, title, description, price, status, created_at) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.executeUpdate(conn, sql,
                    item.getSellerId(),
                    item.getTitle(),
                    item.getDescription(),
                    item.getPrice(),
                    item.getStatus(),
                    item.getCreatedAt());
        }
    }

    @Override
    public int update(Item item) throws SQLException {
        String sql = "UPDATE items SET title=?, description=?, price=?, status=? WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.executeUpdate(conn, sql,
                    item.getTitle(),
                    item.getDescription(),
                    item.getPrice(),
                    item.getStatus(),
                    item.getId());
        }
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.executeUpdate(conn, sql, id);
        }
    }

    @Override
    public Item findById(int id) throws SQLException {
        String sql = "SELECT * FROM items WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.queryForObject(conn, sql, itemMapper, id);
        }
    }

    @Override
    public List<Item> findByTitle(String keyword) throws SQLException {
        String sql = "SELECT * FROM items WHERE title LIKE ?";
        try (Connection conn = ConnectionManager.getConnection()) {
            return dbExecutor.queryForList(conn, sql, itemMapper, "%" + keyword + "%");
        }
    }
}
