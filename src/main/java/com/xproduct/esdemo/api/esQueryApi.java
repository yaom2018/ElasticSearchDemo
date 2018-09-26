package com.xproduct.esdemo.api;

import com.xproduct.esdemo.query.QueryById;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="api")
public class esQueryApi {

    public static final String BOOK_INDEX = "book";
    public static final String BOOK_TYPE_NOVEL = "novel";
    public final static String HOST = "192.168.32.140";
    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300"

    @ApiOperation(value="ES查询使用author",notes="es查询使用author")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "index",value ="Index输入值",
                    required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "author",value ="Type输入值",
            required = true,dataType = "String")
    })
    @RequestMapping(value="/getQuery", method = RequestMethod.GET)
    public List<Map<String,Object>> getQuery(@RequestParam(name = "index", defaultValue = "") String index,
                                             @RequestParam(name = "author", defaultValue = "") String author) {

        QueryById queryById = new QueryById();
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        try {
            result = queryById.getBookById(author,index);
        } catch (Exception e) {
            System.out.println("error at esQuery getquery");
        }
        return result;
    }

}
