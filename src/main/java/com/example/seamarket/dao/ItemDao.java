package com.example.seamarket.dao;

import com.example.seamarket.model.Item;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

    int insert(Item item) throws SQLException;           // 插入商品
    int update(Item item) throws SQLException;           // 更新商品
    int deleteById(int id) throws SQLException;          // 根据 ID 删除商品
    Item findById(int id) throws SQLException;           // 根据 ID 查询商品
    List<Item> findByTitle(String keyword) throws SQLException; // 模糊匹配标题
    List<Item> findAll() throws SQLException;

}
