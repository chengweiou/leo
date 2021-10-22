package chengweiou.universe.leob.interceptor;

import chengweiou.universe.blackhole.exception.UnauthException;
import chengweiou.universe.blackhole.model.BasicRestCode;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.util.GsonUtil;
import chengweiou.universe.leob.base.converter.Account;
import chengweiou.universe.leob.model.PersonType;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptorMe implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accountJson = request.getHeader("loginAccount");
        if (accountJson == null) return unauth(response);
        Account loginAccount = GsonUtil.create().fromJson(accountJson, Account.class);
        PersonType personType = PersonType.valueOf(loginAccount.getExtra());
        if (personType != null) return true;
        return unauth(response);
    }

    private boolean unauth(HttpServletResponse response) throws IOException, UnauthException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(GsonUtil.create().toJson(Rest.fail(BasicRestCode.UNAUTH)));
        return false;
    }
}
