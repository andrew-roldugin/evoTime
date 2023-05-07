package ru.vsu.csf.g7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
//        AuthenticationManagerBuilder authenticationManagerBuilder = null;
//        authenticationManagerBuilder.jdbcAuthentication()
//                .authoritiesByUsernameQuery(
//                        "select login, authority from my_user " +
//                                "where login=?")
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select login, password, 'true' from my_user where login=?")
//                .groupAuthoritiesByUsername("select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga " +
//                        "where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id");

//        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        SpringApplication.run(ServerApplication.class, args);
    }

}
