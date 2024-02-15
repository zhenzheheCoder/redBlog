package com.redblog.service.impl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.base.service.impl.BaseServiceImpl;
import com.redBlog.service.IFriendService;
import com.redblog.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.redblog.mapper.IFriendMapper;
import java.util.List;

@Service
public class FriendServiceImpl extends BaseServiceImpl<Friend> implements IFriendService {
    @Autowired
    private IFriendMapper friendMapper;

    @Override
    public BaseMapper<Friend> getMapper() {
        return friendMapper;
    }

    @Override
    public void addFriend(Integer tid, Integer fid, int i) {

        Friend friend1 = new Friend();
        friend1.setUid(tid);
        friend1.setFid(fid);
        friend1.setStatus(1);

        if (isFriend(friend1)) {
            friendMapper.insert(friend1);
        }

        Friend friend2 = new Friend();
        friend2.setUid(fid);
        friend2.setFid(tid);
        friend2.setStatus(1);

        if (isFriend(friend2)) {
            friendMapper.insert(friend2);
        }
    }

    @Override
    public List<Friend> getFriendListByUid(Integer uid) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("uid",uid);
        return friendMapper.selectList(wrapper);
    }

    public boolean isFriend(Friend friend) {
        return friendMapper.selectOne(friend) == null;
    }
}
