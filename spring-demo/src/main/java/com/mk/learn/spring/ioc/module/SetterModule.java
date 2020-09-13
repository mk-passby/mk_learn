package com.mk.learn.spring.ioc.module;
import java.util.*;
/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-13 22:02
 **/
public class SetterModule {
    private List<String> list;
    private Map<String,Integer> map;
    private Set<Integer> integerSet;
    private Properties properties;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public Set<Integer> getIntegerSet() {
        return integerSet;
    }

    public void setIntegerSet(Set<Integer> integerSet) {
        this.integerSet = integerSet;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
