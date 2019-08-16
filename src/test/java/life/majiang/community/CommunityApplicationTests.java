package life.majiang.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Test
    public void contextLoads() {
        List<String> tests = new ArrayList<>();
        tests.set(1,"第1个");
        tests.set(2,"第2个");
        tests.set(3,"第3个");
        tests.set(4,"第4个");
        tests.set(5,"第5个");
        tests.set(6,"第6个");
        System.out.println(tests);
        List<String> tests1 = new ArrayList<>();

        tests1.set(1,tests.get(1));
        tests1.set(2,tests.get(2));
        tests1.set(3,tests.get(3));
        System.out.println(tests1);
        List<String> tests2 = new ArrayList<>();
        BeanUtils.copyProperties(tests2,tests);
        System.out.println(tests2);
    }

}
