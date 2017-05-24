package Tools;

import java.lang.reflect.*;

public class ReflectionHelper {

	@SuppressWarnings("unchecked")
	public static <T> T CreateInstance(Class<?> genericClass, int genericParamIndex) throws InstantiationException, IllegalAccessException {
		
		return (T) ((Class<?>) ((ParameterizedType)genericClass.getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
	}
}
