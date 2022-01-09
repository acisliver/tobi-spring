import org.junit.Test;
import spring.user.User;
import spring.user.dao.NUserDao;
import spring.user.dao.UserDao;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    @Test
    public void addTest() throws SQLException, ClassNotFoundException {
        // given
        Long id = 1L;
        String name = "testName";
        String password = "1234";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        // when
        // DB에 user 저장
        UserDao userDao = new NUserDao();
        userDao.add(user);

        // DB에서 user id로 user get
        User addedUser = userDao.get(user.getId());

        // then
        // 검증
        assertThat(addedUser.getId()).isEqualTo(user.getId());
        assertThat(addedUser.getName()).isEqualTo(name);
        assertThat(addedUser.getPassword()).isEqualTo(password);
    }
}
