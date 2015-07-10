package com.technoirarts.autumn.eval;

import com.technoirarts.autumn.exception.PropertyEvaluationException;

/**
 * @author Filinger
 * @author Filinger (current maintainer)
 * @version 7/9/2015
 * @since 1.0
 */
public abstract class BasicPropertyEvaluator implements PropertyEvaluator {

    protected final EvalPropertyMaker maker;

    public BasicPropertyEvaluator(EvalPropertyMaker maker) {
        this.maker = maker;
    }

    @Override
    public Object evaluate(Object property) throws PropertyEvaluationException {
        if (canEvaluate(property)) {
            return checkedEvaluate(property);
        }
        throw new PropertyEvaluationException(this, "this evaluator is not intended for property: " + property);
    }

    protected abstract Object checkedEvaluate(Object property) throws PropertyEvaluationException;

    @Override
    public <T> T evaluate(Object property, Class<T> typeAdvice) throws PropertyEvaluationException {
        if (canEvaluate(property, typeAdvice)) {
            return checkedEvaluate(property, typeAdvice);
        }
        throw new PropertyEvaluationException(this, "this evaluator is not intended for property: " + property + " and type: " + typeAdvice);
    }

    protected abstract <T> T checkedEvaluate(Object property, Class<T> typeAdvice) throws PropertyEvaluationException;
}
