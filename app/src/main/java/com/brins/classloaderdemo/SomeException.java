package com.brins.classloaderdemo;

/**
 * @author lipeilin
 * @date 2020/5/28
 */
public class SomeException implements IDoSomething {
    @Override
    public String doSomethingResult() {
        return "Some Exceptions occur";
    }
}
