package ru.vsu.csf.g7;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.vsu.csf.g7.entity.FlexValue;
import ru.vsu.csf.g7.entity.FlexValuesSet;
import ru.vsu.csf.g7.entity.Role;
import ru.vsu.csf.g7.entity.User;
import ru.vsu.csf.g7.repos.FlexValueRepository;
import ru.vsu.csf.g7.repos.FlexValuesSetsRepository;
import ru.vsu.csf.g7.repos.RoleRepository;
import ru.vsu.csf.g7.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    FlexValuesSetsRepository flexValuesSetsRepository;

    @Autowired
    FlexValueRepository flexValueRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleRepository roleRepository;

//    @BeforeEach
//    public void init() {
//        User user = new User();
//        user.setLogin("test");
//        user.setPassword("test1234");
//        user.setEmail("1");
//        userRepository.save(user);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }

    @Test
    void test() {
        var fvs = new FlexValuesSet();
        fvs.setName("FlexValuesSet-2");
        flexValuesSetsRepository.save(fvs);

        var list = new ArrayList<FlexValue>(5);
        for (int i = 0; i < 5; i++) {
            var fv = new FlexValue();
            fv.setName("first-" + i);
            fv.setFlexValue(i + "");
            fv.setFlexValuesSet(fvs);
            list.add(fv);
        }
        flexValueRepository.saveAll(list);
    }

    @Test
    void testDeleteFV() {
        val flexValuesSet = flexValuesSetsRepository.findById(52);
        List<FlexValue> values = flexValuesSet.get().getValues();
//        System.out.println(values.size());
        flexValueRepository.deleteById(5L);
        System.out.println("\n\nremove\n\n");
//        System.out.println(values.size());
    }

    @Test
    void testRoles() {

        var list = new ArrayList<Role>(10);

        for (int i = 0; i < 10; i++) {
            var r = new Role();
            r.setRoleName("role " + i);
            list.add(r);
        }
        roleRepository.saveAll(list);

        val random = new Random();

        for (int i = 0; i < 5; i++) {
            var u = new User();
            u.setEmail(String.format("email%s@mail.io", i));
            u.setLogin(String.format("user%s", i));
            u.setPassword(i + "pass");
            u.addRole(list.get(random.nextInt(0, 10)));
            userRepository.save(u);
        }
    }

    @Test
    void deleteRole() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> roleRepository.deleteById(308));
    }

    @Test
    void contextLoads() {

    }
}
