import com.example.seamarket.dao.UserDao;
import com.example.seamarket.dao.ItemDao;
import com.example.seamarket.dao.UserDaoImpl;
import com.example.seamarket.dao.ItemDaoImpl;
import com.example.seamarket.model.User;
import com.example.seamarket.model.Item;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DaoTest {

    public static void main(String[] args) {
        try {
            // ===== 测试用户 DAO =====
            UserDao userDao = new UserDaoImpl();

            User user = new User();
            user.setUsername("thea");
            user.setPasswordHash("123456-hash"); // 这里用你加密后的密码
            user.setEmail("thea@example.com");
            user.setCreatedAt(LocalDateTime.now());

            // 插入用户
            int rows = userDao.insert(user);
            System.out.println("用户插入行数: " + rows);

            // 查询用户
            User fetchedUser = userDao.findByUsername("thea");
            System.out.println("查询到用户: " + fetchedUser);

            // ===== 测试商品 DAO =====
            ItemDao itemDao = new ItemDaoImpl();

            Item item = new Item();
            item.setSellerId(fetchedUser.getId()); // 使用刚插入用户的 id
            item.setTitle("二手书");
            item.setDescription("Java 编程书籍，九成新");
            item.setPrice(BigDecimal.valueOf(50.0));
            item.setStatus("ACTIVE");
            item.setCreatedAt(LocalDateTime.now());

            // 插入商品
            int itemRows = itemDao.insert(item);
            System.out.println("商品插入行数: " + itemRows);

            // 查询商品
            List<Item> items = itemDao.findByTitle("书");
            System.out.println("模糊查询商品: " + items);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
