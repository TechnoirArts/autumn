package com.technoirarts.autumn.eval;

import com.technoirarts.autumn.bean.BeanValueResolver;

import java.util.Map;

/**
 * @author Filinger
 * @author Filinger (current maintainer)
 * @version 7/9/2015
 * @since 1.0
 */
public class InjectPropertyEvaluator extends DescriptorPropertyEvaluator {

    private BeanValueResolver resolver;

    public InjectPropertyEvaluator(PropertyMaker maker, BeanValueResolver resolver) {
        super(maker);
        this.resolver = resolver;
    }

    @Override
    protected String getDescriptor() {
        return "$inject";
    }

    @Override
    protected Object evaluateDescriptor(Object descriptor, Map<String, Object> rest) {
        String idOrType = (String) descriptor;
        Object resolved = resolver.getValueById(idOrType);
        if (resolved != null) {
            return resolved;
        }
        return resolver.getValueByType(idOrType);
        // TODO: throw error?
    }
}