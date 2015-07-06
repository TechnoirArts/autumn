package com.technoirarts.autumn.bean;

import java.util.ArrayList;

/**
 * @author Filinger
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 1.0
 */
public interface BeanRegistry {
    void register(Bean bean);

    Bean findById(String id);

    Bean findByType(String className);

    <T> Bean<T> findByType(Class<T> beanType);

    <T> ArrayList<Bean<T>> findOfType(Class<T> beanType);
}
