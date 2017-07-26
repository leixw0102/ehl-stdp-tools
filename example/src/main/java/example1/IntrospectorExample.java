package example1;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by 雷晓武 on 2017/5/27.
 */
public class IntrospectorExample {
    public void setPropertyName(User user,String name) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        BeanInfo bi = Introspector.getBeanInfo(user.getClass());
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds){
            switch (pd.getName()){
                case "name":
                    pd.getWriteMethod().invoke(user,name);
                    break;
                default:
                    break;
            }
        }

    }

    public String getPropertyName(User user) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        BeanInfo bi = Introspector.getBeanInfo(user.getClass());
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds){
            switch (pd.getName()){
                case "name":
                    return pd.getReadMethod().invoke(user).toString();
                default:
                    break;
            }
        }
        return "";
    }

    public static void main(String []args) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        IntrospectorExample introspectorExample = new IntrospectorExample();
        User user = new User();
       introspectorExample.setPropertyName(user,"sdfsd");
        System.out.println(introspectorExample.getPropertyName(user));
    }
}
