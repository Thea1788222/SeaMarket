package com.example.seamarket.service;

import com.example.seamarket.model.Item;

import java.util.List;

public interface ItemService {

    int publishItem(Item item); // 发布商品

    int updateItem(Item item);  // 修改商品

    int deleteItem(int id);     // 删除商品

    Item getItemById(int id);   // 通过主键查商品

    List<Item> searchItems(String keyword); // 模糊搜索
}
