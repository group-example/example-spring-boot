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
@Api(value = "代码示例", description = "描述")
public class HelloController {

    @Autowired
    private HelloService helloService;

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
    @RequestMapping(value = {"/index", ""}, method = RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("host", "http://liuluming.com");
        return "index";
    }


    @ApiOperation(value = "【示例】查询列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<List<Hello>> selectList(@ApiParam(value = "姓名", required = false) @RequestParam(required = false) String name) throws Exception {
        GeneralResponse resp = new GeneralResponse();
        resp.setData(helloService.selectByName(name));
        return resp;
    }

    @ApiOperation(value = "【示例】查询单个")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<Hello> selectById(@ApiParam(value = "ID", required = true) @PathVariable("id") Long id) throws BusinessException {
        GeneralResponse resp = new GeneralResponse();
        resp.setData(helloService.selectById(id));
        return resp;
    }


    @ApiOperation(value = "【示例】新增")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResponse<Integer> insert(@ApiParam(value = "姓名", required = true) @RequestParam String name,
                                           @ApiParam(value = "年龄", required = true) @RequestParam Integer age) throws BusinessException {
        GeneralResponse resp = new GeneralResponse();
        Hello hello = new Hello();
        hello.setAge(age);
        hello.setName(name);
        boolean result=helloService.insert(hello);
        if (!result) {
            resp.setMessage("操作失败");
            resp.setSuccessful(false);
        }
        return resp;
    }

    @ApiOperation(value = "【示例】删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public GeneralResponse delete(@ApiParam(value = "主键ID", required = true) @RequestParam Long id) throws BusinessException {
        GeneralResponse resp = new GeneralResponse();
        boolean result = helloService.deleteById(id);
        if (!result) {
            resp.setMessage("操作失败:该记录不存在！");
            resp.setSuccessful(false);
        }
        return resp;
    }

    @ApiOperation(value = "【示例】更新")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse<Integer> updateName(@ApiParam(value = "姓名", required = true) @RequestParam String name,
                                               @ApiParam(value = "ID", required = true) @RequestParam Long id) throws BusinessException {
        GeneralResponse resp = new GeneralResponse();
        int result = helloService.updateName(name, id);
        if (result == 0) {
            resp.setMessage("操作失败:该记录不存在！");
            resp.setSuccessful(false);
        }
        return resp;
    }

}
