package com.technoirarts.autumn.eval;

import com.technoirarts.autumn.bean.PackageRegistry;
import com.technoirarts.autumn.exception.PropertyEvaluationException;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Filinger
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 1.0
 */
public class ClassPropertyEvaluator extends DescriptorPropertyEvaluator {

    private final PackageRegistry packages;

    public ClassPropertyEvaluator(EvalPropertyMaker maker, PackageRegistry packages) {
        super(maker);
        this.packages = packages;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T evaluateDescriptor(Object descriptor, Map<String, Object> rest, Class<T> typeAdvice) throws PropertyEvaluationException {
        try {
            return (T) packages.findClass((String) descriptor);
        } catch (ClassNotFoundException e) {
            throw new PropertyEvaluationException(this, "cannot find class", e);
        }
    }

    @Override
    protected String getDescriptor() {
        return "$class";
    }

    @Override
    protected Set<Class<?>> getDescriptorTypes() {
        return Collections.<Class<?>>singleton(String.class);
    }

    @Override
    protected Set<Class<?>> getReturnTypes() {
        return Collections.<Class<?>>singleton(Class.class);
    }
}
