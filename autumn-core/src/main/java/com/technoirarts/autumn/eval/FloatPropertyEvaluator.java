package com.technoirarts.autumn.eval;

import com.technoirarts.autumn.exception.PropertyEvaluationException;

import java.util.Map;

/**
 * @author Filinger
 * @author Filinger (current maintainer)
 * @version 7/9/2015
 * @since 1.0
 */
public class FloatPropertyEvaluator extends DescriptorPropertyEvaluator {

    public FloatPropertyEvaluator(EvalPropertyMaker maker) {
        super(maker);
    }

    @Override
    protected String getDescriptor() {
        return "$float";
    }

    @Override
    protected Object evaluateDescriptor(Object descriptor, Map<String, Object> rest) throws PropertyEvaluationException {
        try {
            return Float.parseFloat((String) descriptor);
        } catch (NumberFormatException e) {
            throw new PropertyEvaluationException(this, "cannot parse specified float: " + descriptor);
        }
    }

    @Override
    public boolean canEvaluate(Object property, Class<?> typeAdvice) {
        return canEvaluate(property) && (typeAdvice.isAssignableFrom(Float.class) || typeAdvice.isAssignableFrom(float.class));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T evaluateDescriptor(Object descriptor, Map<String, Object> rest, Class<T> typeAdvice) throws PropertyEvaluationException {
        return (T) evaluateDescriptor(descriptor, rest);
    }
}
