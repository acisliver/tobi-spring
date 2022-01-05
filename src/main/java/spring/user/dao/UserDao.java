package spring.user.dao;

import spring.user.User;
import java.sql.*;

public class UserDao {

    // User 정보 DB에 추가
    public void add(User user) throws ClassNotFoundException, SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/tobi-spring?serverTimezone=UTC", "root", "1234"
        );

        // SQL 생성
        PreparedStatement ps = c.prepareStatement(
                "insert into userinfo(id, name, password) value(?,?,?)"
        );
        ps.setLong(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        // 만들어진 Statement 실행
        ps.executeUpdate();

        // 자원 반환
        ps.close();
        c.close();
    }

    // userinfo 테이블에서 id로 User 정보 찾기
    public User get(Long id) throws ClassNotFoundException, SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/tobi-spring?serverTimezone=UTC", "root", "1234"
        );

        // SQL 생성
        PreparedStatement ps = c.prepareStatement(
                "select * from userinfo where id = ?"
        );
        ps.setLong(1, id);

        // 만들어진 Statement 실행
        // 실행 결과를 ResultSet에 저장
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // 자원 반환
        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
