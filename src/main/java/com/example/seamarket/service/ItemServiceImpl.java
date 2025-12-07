package com.example.seamarket.service;

import com.example.seamarket.dao.ItemDao;
import com.example.seamarket.dao.ItemDaoImpl;
import com.example.seamarket.model.Item;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao = new ItemDaoImpl();

    @Override
    public int publishItem(Item item) {
        try {
            // 简单数据校验
            if (item.getTitle() == null || item.getTitle().trim().isEmpty()) {
                return 0;
            }
            if (item.getSellerId() <= 0) {
                return 0;
            }
            if (item.getPrice() == null || item.getPrice().doubleValue() < 0) {
                return 0;
            }

            item.setCreatedAt(LocalDateTime.now());
            item.setStatus("ACTIVE");

            return itemDao.insert(item);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateItem(Item item) {
        try {
            // id 必须有效
            if (item.getId() <= 0) return 0;

            return itemDao.update(item);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteItem(int id) {
        try {
            return itemDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Item getItemById(int id) {
        try {
            return itemDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Item> searchItems(String keyword) {
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                // 关键字为空，返回全部商品
                return itemDao.findAll();
            }
            return itemDao.findByTitle(keyword.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
