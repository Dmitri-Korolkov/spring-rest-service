package task.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import task.entity.UserDataEntity;
import task.service.UserData;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserDataImpl.class)
@DirtiesContext
public class UserDataImplTest {

    @Autowired
    UserData userData;

    @Test
    public void testSaveUserData() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();

        UserDataEntity entity = new UserDataEntity("test", "unit", "123456789");
        userData.saveUserData(request, entity);

        assertEquals("unit test", true, userData.saveUserData(request, entity));
    }
}