package shop.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shop.domain.Category;
import shop.service.CategoryService;

@Component
public class GlobalAttributeInterceptor implements HandlerInterceptor {
	@Autowired
	HttpSession session;
	@Autowired
	CategoryService categoryService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		Boolean isLogin = session.getAttribute("username") != null ;
		request.setAttribute("isLogin", isLogin);
		List<Category> listCategories = categoryService.findAll();
		request.setAttribute("listCategories", listCategories);
	}
}
