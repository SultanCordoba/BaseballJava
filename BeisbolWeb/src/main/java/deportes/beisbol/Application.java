package deportes.beisbol;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@ComponentScan(basePackages={"deportes.beisbol"})
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(Application.class, args);
		
		/*ApplicationContext ctx = SpringApplication.run(Application.class, args);

        /* Desde aqui es opcional, se pone solo con fines de debuggeo y ver
           que se generen los Beans de manera adecuada. */
        /* System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/		
	}

	
	@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale.Builder().setLanguage("es").setRegion("MX").build());
        return slr;
    }
 
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    /* @Bean    
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("locale");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    } */
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      //String myExternalFilePath = "file:///C://Users//juanfriverap//Google Drive//Beisbol//";
      String myExternalFilePath = "file://home//pelotero//logos//";

      registry.addResourceHandler("/imagenes/**").addResourceLocations(myExternalFilePath);

      super.addResourceHandlers(registry);
    }
}
