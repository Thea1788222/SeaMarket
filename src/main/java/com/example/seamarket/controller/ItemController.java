package com.example.seamarket.controller;

import com.example.seamarket.model.Item;
import com.example.seamarket.model.User;
import com.example.seamarket.service.ItemService;
import com.example.seamarket.service.ItemServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/item")
public class ItemController extends HttpServlet {

    private final ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("search".equals(action)) {
            handleSearch(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            handleDelete(req, resp);
            return;
        }

        // 默认跳到发布页
        req.getRequestDispatcher("/item/publish.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("publish".equals(action)) {
            handlePublish(req, resp);
        }else {
            // 默认回到发布页
            req.getRequestDispatcher("/item/publish.jsp").forward(req, resp);
        }
    }

    private void handlePublish(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        User seller = (User) req.getSession().getAttribute("user");
        if (seller == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.jsp");
            return;
        }

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String priceStr = req.getParameter("price");

        Item item = new Item();
        item.setSellerId(seller.getId());
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(new BigDecimal(priceStr));

        int rows = itemService.publishItem(item);

        if (rows > 0) {
            resp.sendRedirect(req.getContextPath() + "/item?action=search");
        } else {
            req.setAttribute("error", "发布失败，检查输入内容");
            req.getRequestDispatcher("/item/publish.jsp").forward(req, resp);
        }
    }

    private void handleSearch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String keyword = req.getParameter("keyword");
        List<Item> items;

        if (keyword == null || keyword.trim().isEmpty()) {
            // 关键字为空时，显示所有商品
            items = itemService.searchItems("");
        } else {
            items = itemService.searchItems(keyword.trim());
        }

        req.setAttribute("items", items);
        req.getRequestDispatcher("/item/list.jsp").forward(req, resp);
    }


    private void handleDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        itemService.deleteItem(id);

        resp.sendRedirect(req.getContextPath() + "//item?action=search&deleted=1");
    }
}
