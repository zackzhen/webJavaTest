package com.example.demo.service;

import com.example.demo.entity.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface UserService extends JpaRepository<UserDo, Long> {


    @Query(value = "select * from t_system_user  where name = ?1",nativeQuery = true)
    List<UserDo> queryByName(String name);

//    @Query("select u.id,u.name , u.account from t_system_user u where u.name like %?1")
//    List<UserDo> findByFirstnameEndsWith(String name);

//    @Query(nativeQuery = true,value = "select * from t_system_user where name like 66")
//    List<UserDo> queryByName(@Param("name")String name);


//    @Override
//    public Object findPage(PageQuery pageQuery) {
//        return findAll(PageRequest.of(pageQuery.getPage(), pageQuery.getSize()));
//    }

}
