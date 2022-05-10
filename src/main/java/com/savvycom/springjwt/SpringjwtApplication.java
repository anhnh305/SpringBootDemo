package com.savvycom.springjwt;

import com.savvycom.springjwt.entity.User;
import com.savvycom.springjwt.entity.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringjwtApplication.class, args);
    }}

//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        User user = new User();
//        user.setUsername("haianh");
//        user.setPassword(passwordEncoder.encode("3005"));
//        userRepository.save(user);
//        System.out.println(user);
//    }
//}

