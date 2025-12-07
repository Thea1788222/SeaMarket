package com.example.seamarket.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbExecutor {

    public int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            setParams(pst, params);
            return pst.executeUpdate();
        }
    }

    public <T> T queryForObject(Connection conn, String sql, RowMapper<T> mapper, Object... params) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            setParams(pst, params);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                } else {
                    return null;
                }
            }
        }
    }

    public <T> List<T> queryForList(Connection conn, String sql, RowMapper<T> mapper, Object... params) throws SQLException {
        List<T> list = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            setParams(pst, params);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    list.add(mapper.mapRow(rs));
                }
            }
        }
        return list;
    }

    private void setParams(PreparedStatement pst, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
        }
    }
}
