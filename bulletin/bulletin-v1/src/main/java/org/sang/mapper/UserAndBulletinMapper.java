package org.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.UserAndBulletin;

@Mapper
public interface UserAndBulletinMapper {

    int insert(Integer uid, Integer bid);          //添加一条记录，某个公告发送给了某个用户

    int deleteWithBulletin(Integer bid);           //删除n条记录，某个公告被删除

    List<UserAndBulletin> selectBulletinByUserId(Integer uid);       //查询某个用户有哪些公告

    Integer updateUBState(Integer uid, Integer bid, Integer state);        //更新用户查看公告的状态

    List<UserAndBulletin> selectBulletinByUserIdLikeBid(Integer uid, Integer bid);   //在用户收到公告的基础上进行查询


}