package cn.edu.scau.config;

import cn.edu.scau.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class VerificationCodeFilter extends GenericFilter {
    @Autowired
    private CompanyConfig companyConfig;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if ("POST".equals(req.getMethod()) && "/doLogin".equals((req.getServletPath()))) {
        //if ("/doLogin".equals((req.getServletPath()))) {
            String code = req.getParameter("code");
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            String verify_code2 = companyConfig.getVerifCode();
            if(code.equals(verify_code2) ||  verify_code2.toLowerCase().equals(code.toLowerCase())){
                filterChain.doFilter(req, resp);
            }
            else if (code == null || verify_code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())) {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(Response.error("验证码填写错误")));
                out.flush();
                out.close();
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
