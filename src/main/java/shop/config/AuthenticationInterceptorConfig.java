package shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shop.interceptor.AdminAuthenticationInterceptor;
import shop.interceptor.GlobalAttributeInterceptor;
import shop.interceptor.SiteAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
	
	@Autowired
	private SiteAuthenticationInterceptor siteAuthenticationInterceptor;
	
	@Autowired
	GlobalAttributeInterceptor attributeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/admin/**");
		registry.addInterceptor(siteAuthenticationInterceptor).addPathPatterns("/site/**").excludePathPatterns("/site/accounts/registration","/site/alogina","/site/alogouta");
		registry.addInterceptor(attributeInterceptor);
	}

}
