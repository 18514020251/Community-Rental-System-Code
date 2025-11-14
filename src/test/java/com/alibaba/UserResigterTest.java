package com.alibaba;


import com.alibaba.dto.UsersRegisterDto;
import com.alibaba.mapper.UsersMapper;
import com.alibaba.pojo.Users;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserResigterTest {

    @Autowired
    private UsersMapper usersMapper;


    @Test
    public  void  RegisterTest()  {
        UsersRegisterDto usersRegisterDTO = new UsersRegisterDto();
        usersRegisterDTO.setPhone("12345678901");
        LambdaQueryWrapper<Users> lambda = new LambdaQueryWrapper<Users>().eq(Users::getPhone, usersRegisterDTO.getPhone());
        System.out.println(usersMapper.selectOne(lambda));


    }

}
