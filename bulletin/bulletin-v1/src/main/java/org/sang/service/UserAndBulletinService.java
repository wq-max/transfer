package org.sang.service;

import org.sang.mapper.UserAndBulletinMapper;
import org.sang.model.UserAndBulletin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAndBulletinService {

    @Autowired
    UserAndBulletinMapper userAndBulletinMapper;


    //添加一条记录，表示有一个用户收到一条公告
    public boolean insertUserAndBulletin(Integer uid, Integer bid){
        if (userAndBulletinMapper.insert(uid, bid) > 0)
            return true;
        return false;
    }

    /*删除n条记录，因为有一条公告被删除(放在了公告被删之后直接执行)
    public boolean deleteUserAndBulletin(Integer bid){
        if (userAndBulletinMapper.deleteWithBulletin(bid) > 0)
            return true;
        return false;
    }*/

    //查询n条记录，某个用户收到的所有公告
    public List<UserAndBulletin> selectBulletinByUserId(Integer uid){
        return userAndBulletinMapper.selectBulletinByUserId(uid);
    }

    public List<UserAndBulletin> selectBulletinByUserIdLikeBid(Integer uid, Integer bid){
        return userAndBulletinMapper.selectBulletinByUserIdLikeBid(uid, bid);
    }

    //更新用户查看公告的状态
    public boolean updateUBState(Integer uid, Integer bid, Integer state){
        if (userAndBulletinMapper.updateUBState(uid, bid, state) > 0)
            return true;
        return false;
    }

    //查看该用户是否有未读公告
    public boolean hasMessageNoRead(Integer uid){
        boolean flag = false;
        for (UserAndBulletin userAndBulletin : userAndBulletinMapper.selectBulletinByUserId(uid)){
            if (userAndBulletin.getState() == 0){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
