package deportes.beisbol;

import java.util.Locale;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Component
public class CustomServerBean implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		/* container.setPort(8090);
		container.setSessionTimeout(120);
		container.setContextPath("/baseball"); */
	}
	
    /*@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.forLanguageTag("es"));
        return slr;
    }
 
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
 
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }*/
 

	/**
    *
    * @return
    *
    * Permite habilitar la salida de los WebService en formato JSon.
    */
   /*@Bean
   public HttpMessageConverters customConverters() {
       StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
       stringConverter.setWriteAcceptCharset(false);

       return new HttpMessageConverters(
               new ByteArrayHttpMessageConverter(),
               stringConverter,
               new ResourceHttpMessageConverter(),
               new SourceHttpMessageConverter<Source>(),
               new AllEncompassingFormHttpMessageConverter(),
               jackson2Converter()
       );
   }*/

   /**
    *
    * @return
    *
    * Permite habilitar la salida de los WebService en formato JSon.
    */
   /*@Bean
   public MappingJackson2HttpMessageConverter jackson2Converter() {
       MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

       List<MediaType> mediaTypes = new ArrayList<>();
       MediaType mediaType = new MediaType("text", "plain", Charset.forName("UTF-8"));
       mediaTypes.add(mediaType);
       converter.setObjectMapper(objectMapper());
       converter.setSupportedMediaTypes(mediaTypes);
       return converter;
   }
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}*/

}
