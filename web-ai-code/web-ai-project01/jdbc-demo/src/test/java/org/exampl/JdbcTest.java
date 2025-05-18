package org.exampl;

import org.exampl.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "050516";
        Connection connection = DriverManager.getConnection(url, username, password);

        //获取SQL语句执行对象
        Statement statement = connection.createStatement();


        //执行SQL
        int i = statement.executeUpdate("update user Set age = 25 where id = 1");
        System.out.println("SQl执行完毕影响的记录数为：" + i);

        //释放资源
        statement.close();
        connection.close();


        }

        @Test
        public void testSelect(){
            String URL = "jdbc:mysql://localhost:3306/web01"; // 替换为你的数据库URL
            String USER = "root"; // 替换为你的数据库用户名
            String PASSWORD = "050516"; // 替换为你的数据库密码

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;//封装查询返回的结果集对象

            try {
                // 1. 注册 JDBC 驱动
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 2. 打开链接
                conn = DriverManager.getConnection(URL, USER, PASSWORD);

                // 3. 执行查询
                String sql = "SELECT id, username, password, name, age FROM user WHERE username = ? AND password = ?";//预编译SQL语句
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "daqiao");
                stmt.setString(2, "123456");

                rs = stmt.executeQuery();

                // 4. 处理结果集
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getInt("age")
                    );
                    System.out.println(user); // 使用 Lombok 的 @Data 自动生成的 toString 方法
                }
            } catch (SQLException se) {
                // Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                // Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                // 5. 关闭资源
                try {
                    if (rs != null) rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
}
