package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Util {
  
  private static boolean verificarCamposNulos(Object myObjeto) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    Method[] methods = myObjeto.getClass().getDeclaredMethods();
    int atributosComValorInvalido = 0;
    for (Method method : methods) {
        if(method.getName().startsWith("get") || method.getName().startsWith("is")) {
            Object obj = method.invoke(myObjeto);
            if(null == obj || "".equals(obj)  || obj.equals(0)  || obj.equals("SELECIONE")) {
              atributosComValorInvalido++;
            }
            
            if(atributosComValorInvalido == methods.length) {
              return true;
            } 
        }
    }
    return false;
}

}
