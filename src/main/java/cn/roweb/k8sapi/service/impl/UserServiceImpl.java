package cn.roweb.k8sapi.service.impl;

import cn.roweb.k8sapi.dao.UserMapper;
import cn.roweb.k8sapi.model.User;
import cn.roweb.k8sapi.service.UserService;
import cn.roweb.k8sapi.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/08/21.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
