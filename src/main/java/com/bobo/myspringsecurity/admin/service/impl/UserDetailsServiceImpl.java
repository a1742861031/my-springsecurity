package com.bobo.myspringsecurity.admin.service.impl;

import com.bobo.myspringsecurity.admin.dao.MenuDao;
import com.bobo.myspringsecurity.admin.dao.UserDao;
import com.bobo.myspringsecurity.admin.dto.JwtUserDto;
import com.bobo.myspringsecurity.admin.dto.MenuIndexDto;
import com.bobo.myspringsecurity.admin.entity.MyUser;
import com.bobo.myspringsecurity.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 用户登录逻辑
 * @Date 2021/10/11 15:13
 * @Created by bobo
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDao menuDao;
    @Override
    public JwtUserDto loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser user = userDao.getUser(userName);//根据用户名获取用户
        if (user == null ){
            throw new UsernameNotFoundException("用户名不存在");//这个异常一定要抛
        }else if (user.getStatus().equals(MyUser.Status.LOCKED)) {
            throw new LockedException("用户被锁定,请联系管理员");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<MenuIndexDto> list = menuDao.listByUserId(user.getUserId());
        List<String> collect = list.stream().map(MenuIndexDto::getPermission).collect(Collectors.toList());
        for (String authority : collect){
            if (!("").equals(authority) & authority !=null){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
                grantedAuthorities.add(grantedAuthority);
            }
        }//将用户所拥有的权限加入GrantedAuthority集合中
        JwtUserDto loginUser =new JwtUserDto(user,grantedAuthorities);
        return loginUser;
    }
}


