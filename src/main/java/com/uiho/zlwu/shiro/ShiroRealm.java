package com.uiho.zlwu.shiro;

import com.uiho.zlwu.model.User;
import com.uiho.zlwu.service.IPermissionService;
import com.uiho.zlwu.service.IRoleService;
import com.uiho.zlwu.service.IUserService;
import com.uiho.zlwu.utils.ShiroUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Author wangyong
 * @Date 2019/11/26 19:20
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 授权权限
     * 用户进行权限验证时候Shiro会去缓存中找，如果查不到数据，会执行这个方法查权限，并放入缓存中
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String account = (String) principalCollection.getPrimaryPrincipal();

        //查询角色和权限(这里根据业务自行查询)
        Set<String> rolesSet = roleService.selectRoles(account);
        Set<String> permsSet = permissionService.selectPermissions(account);

        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setRoles(rolesSet);
        authorizationInfo.setStringPermissions(permsSet);
        return authorizationInfo;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号
        String account = (String) authenticationToken.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到进行验证
        User user = userService.selectUserByAccount(account);
        //判断账号是否存在
        if (user == null){
            throw new AuthenticationException();
        }
        //判断账号是否被冻结
        if (user.getStatus() == null || user.getStatus().equals(1)){
            throw new LockedAccountException();
        }
        //清除缓存Session
        ShiroUtil.deleteCache(account);
        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                account,
                //密码
                user.getPassword(),
                //设置盐值
//                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
        return authenticationInfo;
    }
}
