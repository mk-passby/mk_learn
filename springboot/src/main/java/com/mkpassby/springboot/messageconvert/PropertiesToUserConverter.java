package com.mkpassby.springboot.messageconvert;

import com.mkpassby.springboot.importdemo.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-06-25 22:19
 **/
public class PropertiesToUserConverter extends AbstractHttpMessageConverter<User> {

  public PropertiesToUserConverter() {
    super(MediaType.valueOf("application/properties+person"));
    setDefaultCharset(Charset.forName("UTF-8"));
  }

  @Override
  protected boolean supports(Class<?> aClass) {
    return aClass.isAssignableFrom(User.class);
  }

  //转换入参
  @Override
  protected User readInternal(Class<? extends User> aClass, HttpInputMessage httpInputMessage)
      throws IOException, HttpMessageNotReadableException {
    InputStream inputStream=httpInputMessage.getBody();
    Properties properties=new Properties();
    //请求内容转换为properties
    properties.load(inputStream);
    User user=new User();
    user.setName(properties.getProperty("user.name"));
    return user;
  }

  /***
   * @param user
   * @param httpOutputMessage
   * @throws IOException
   * @throws HttpMessageNotWritableException
   */
  //用properties格式写出去
  @Override
  protected void writeInternal(User user, HttpOutputMessage httpOutputMessage)
      throws IOException, HttpMessageNotWritableException {
    OutputStream outputStream=httpOutputMessage.getBody();
    Properties properties=new Properties();
    properties.setProperty("user.name",user.getName());
    properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"from web server");
  }
}
