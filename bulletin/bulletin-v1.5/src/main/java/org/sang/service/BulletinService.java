package org.sang.service;

import org.sang.mapper.BulletinMapper;
import org.sang.mapper.UserAndBulletinMapper;
import org.sang.model.Bulletin;
import org.sang.model.UserAndBulletin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletinService {
    @Autowired
    BulletinMapper bulletinMapper;

    @Autowired
    UserAndBulletinMapper userAndBulletinMapper;

    //添加公告
   public boolean insertBulletin(Bulletin bulletin){
        if (bulletinMapper.insert(bulletin) > 0)
            return true;
        return false;
    }


    //删除公告
   public boolean deleteBulletinByPrimaryKey(Integer id){
        if (bulletinMapper.deleteByPrimaryKey(id) > 0){
            if (userAndBulletinMapper.deleteWithBulletin(id) > 0)
                return true;
        }
        return false;
    }

    //修改公告
   public boolean updateBulletinByPrimaryKey(Bulletin bulletin){
        if (bulletinMapper.updateByPrimaryKey(bulletin) > 0)
            return true;
        return false;
    }

    //查询单个公告
   public Bulletin getBulletinByPrimaryKey(Integer id){
        return bulletinMapper.selectByPrimaryKey(id);
    }

    public List<Bulletin> getBulletinByLikePrimaryKey(Integer id){
       return bulletinMapper.selectByLikePrimaryKey(id);
    }

    //查询所有公告
   public List<Bulletin> getAllBulletins(){
        return bulletinMapper.selectAllBulletins();
    }
}
