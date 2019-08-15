package com.king.domain;

import java.util.List;

/**
 * @ProjectName: day01_eesy_01mybatis
 * @Package: com.king.domain
 * @ClassName: QueryVo
 * @Author: 王团结
 * @Description:
 * @Date: 2019/8/14 21:42
 * @Version: 1.0
 */
public class QueryVo {

    private User user;

    private List<Integer> ids;


    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
