package com.wage.dao;

import com.wage.model.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void adminTest() {
        Admin admin = adminRepository.findDistinctByUname("admin");
        System.out.println(admin);
    }
}