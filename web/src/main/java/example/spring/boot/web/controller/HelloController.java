package example.spring.boot.web.controller;

import example.spring.boot.common.BusinessException;
import example.spring.boot.common.GeneralResponse;
import example.spring.boot.dao.model.Hello;
import example.spring.boot.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liuluming on 2017/2/10.
 */
@Controller
@RequestMapping("/hello")
@Api(value = "代码示例",description = "描述")
public class HelloController {

    @Autowired
    private HelloService helloService;


    @ApiOperation(value = "【示例】返回JSON")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<List<Hello>> list(@ApiParam(value = "姓名",required = false) @RequestParam(required = false) String name) {
        GeneralResponse resp=new GeneralResponse();
        resp.setData(helloService.findByName(name));
        return resp;
    }

    @ApiOperation(value = "【示例】返回异常")
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    @ResponseBody
    public int testException() throws Exception {
        int i = 1;
        int j = 0;
        int k;
        k = 1 / j;
        return k;
    }

    @ApiOperation(value = "【示例】返回页面")
    @RequestMapping(value = {"/index",""}, method = RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("host", "http://liuluming.com");
        return "index";
    }

    @ApiOperation(value = "【示例】insert")
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<Integer> insert(@ApiParam(value = "Hello",required = true) @ModelAttribute Hello hello) throws BusinessException {
        GeneralResponse resp=new GeneralResponse();
        resp.setData(helloService.insert(hello));
        return resp;
    }

    @ApiOperation(value = "【示例】delete")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<Integer> delete(@ApiParam(value = "Hello",required = true) @ModelAttribute Hello hello) throws BusinessException {
        GeneralResponse resp=new GeneralResponse();
        resp.setData(helloService.delete(hello));
        return resp;
    }

    @ApiOperation(value = "【示例】update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<Integer> update(@ApiParam(value = "Hello",required = true) @ModelAttribute Hello hello) throws BusinessException {
        GeneralResponse resp=new GeneralResponse();
        resp.setData(helloService.update(hello));
        return resp;
    }

    @ApiOperation(value = "【示例】select one")
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<Hello> selectById(@ApiParam(value = "Hello",required = true) @ModelAttribute Hello hello) throws BusinessException {
        GeneralResponse resp=new GeneralResponse();
        resp.setData(helloService.selectById(hello));
        return resp;
    }


    @ApiOperation(value = "【示例】select List")
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<List<Hello>> selectList(@ApiParam(value = "Hello-name",required = true) @RequestParam String name) throws BusinessException {
        GeneralResponse resp=new GeneralResponse();
        resp.setData(helloService.selectList(name));
        return resp;
    }
}
