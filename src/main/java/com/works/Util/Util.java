package com.works.Util;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class Util {

    public List<Map<String , String >> errors (BindingResult bResult){
        List<Map<String,String>> ls = new LinkedList<>();

        bResult.getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String fieldMessage = error.getDefaultMessage();

            Map<String,String> erhm = new HashMap<>();
            erhm.put("fieldName", fieldName);
            erhm.put("fieldMessag",fieldMessage);
            ls.add(erhm);
        });


        return ls;

    }
}
