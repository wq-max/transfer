package org.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.model.Bulletin;

@Mapper
public interface BulletinMapper {

    int insert(Bulletin bulletin);                 //增

    int deleteByPrimaryKey(Integer id);         //删

    int updateByPrimaryKey(Bulletin record);    //改

    Bulletin selectByPrimaryKey(Integer id);    //查
    List<Bulletin> selectAllBulletins();
    List<Bulletin> selectByLikePrimaryKey(Integer id);

}