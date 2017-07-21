package example.spring.boot.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import example.spring.boot.common.BusinessException;
import example.spring.boot.dao.mapper.HelloMapper;
import example.spring.boot.dao.model.Hello;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by liuluming on 2017/2/17.
 */
@Service
public class HelloService extends ServiceImpl<HelloMapper, Hello> {

    @Autowired
    private HelloMapper helloMapper;

    public int updateName(String name, Long id) throws BusinessException {

        if (StringUtils.isEmpty(name) || id == null) {
            throw new IllegalArgumentException("参数不允许为空");
        }
        return helloMapper.updateName(name, id);
    }

    public List<Hello> selectByName(String name) throws BusinessException {

        EntityWrapper<Hello> wrapper = new EntityWrapper<>();
        wrapper.like("name", name);
        System.out.println(wrapper.getSqlSegment());
        return helloMapper.selectList(wrapper);
    }

    public List<Hello> selectByName2(String name) {
        return helloMapper.findByName(name);
    }

    public String getPulicIp() throws Exception {
        OkHttpClient client = new OkHttpClient();
        String url="http://www.ip168.com/json.do?view=myipaddress";
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String content=response.body().string();
            int startIndex=content.indexOf("[");
            int endIndex=content.indexOf("]");
            String ip = content.subSequence(startIndex+1,endIndex).toString();
            return ip;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


}
