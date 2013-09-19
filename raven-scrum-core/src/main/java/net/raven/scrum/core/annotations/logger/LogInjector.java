package net.raven.scrum.core.annotations.logger;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class LogInjector implements BeanPostProcessor
{

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException
	{
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(final Object bean,
			String beanName) throws BeansException
	{
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback()
		{

			@Override
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException
			{
				ReflectionUtils.makeAccessible(field);
				if (field.getAnnotation(Log.class) != null)
				{
					Logger log = LoggerFactory.getLogger(bean.getClass());
					field.set(bean, log);
				}
			}
		});
		return bean;
	}
}
