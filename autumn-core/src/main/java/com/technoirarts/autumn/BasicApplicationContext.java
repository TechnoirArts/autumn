package com.technoirarts.autumn;

import com.technoirarts.autumn.bean.BeanRegistry;
import com.technoirarts.autumn.bean.MapBeanRegistry;
import com.technoirarts.autumn.exception.ContextLoadException;

import java.util.List;

/**
 * @author Filinger
 * @author Filinger (current maintainer)
 * @version 7/7/2015
 * @since 1.0
 */
public abstract class BasicApplicationContext implements ApplicationContext {

    protected BeanRegistry registry;
    protected NodeReader reader;

    public BasicApplicationContext() {
        this.registry = new MapBeanRegistry();
    }

    protected abstract void processNode(Object node);

    @Override
    public void loadAll() {
        try {
            Object node = reader.next();
            while (node != null) {
                processNode(node);
                node = reader.next();
            }
        } catch (Exception e) {
            throw new ContextLoadException("Context load failed", e);
        }
    }

    @Override
    public void loadNext() {
        try {
            Object node = reader.next();
            if (node != null) {
                processNode(node);
            }
        } catch (Exception e) {
            throw new ContextLoadException("Context load failed", e);
        }
    }

    @Override
    public Object getBean(String id) {
        return registry.findById(id);
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return registry.findByType(type);
    }

    @Override
    public <T> List<T> getBeans(Class<T> type) {
        return registry.findOfType(type);
    }
}
