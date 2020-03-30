package com.lianlian.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class BeansUtils {
    /**
     * @param goin
     * @param gout
     */
    public static void copyPropertiesIgnoreNull(Object goin, Object gout) {

        final BeanWrapper src = new BeanWrapperImpl(goin);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || srcValue.equals("")) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        BeanUtils.copyProperties(goin, gout, emptyNames.toArray(result));

    }
}
