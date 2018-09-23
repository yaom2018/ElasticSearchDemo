package com.xproduct.esdemo.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api")
public class esQueryApi {

    public static final String BOOK_INDEX = "book";
    public static final String BOOK_TYPE_NOVEL = "novel";

    @Autowired
    private TransportClient client;


    @ApiOperation(value="es查询",notes="es查询")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "id",value ="输入值",
            required = true,dataType = "String")})
    @RequestMapping(value="/getQuery", method = RequestMethod.GET)
    public ResponseEntity getQuery(@RequestParam(name = "id", defaultValue = "") String id) {
        GetResponse response = client.prepareGet(BOOK_INDEX, BOOK_TYPE_NOVEL, id).get();
        if (!response.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response.getSource(), HttpStatus.OK);
    }

    @ApiOperation(value="测试hello",notes="测试hello")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "getName",value ="输入值",
            required = true,dataType = "String")})
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(name = "getName") String getName){
        System.out.println("hello this is eatApi input :"+getName);
        return "hello this is eatApi input :"+getName;
    }

}
