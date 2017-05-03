package io.github.god99me.chapter3.bean;

import io.github.god99me.chapter2.util.CastUtil;

import java.util.Map;

/**
 * Created by L.M.Y on 2017/5/3.
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getMap() {
        return paramMap;
    }

    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }
}
