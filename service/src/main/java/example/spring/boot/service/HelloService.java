package example.spring.boot.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import example.spring.boot.common.BusinessException;
import example.spring.boot.dao.mapper.HelloMapper;
import example.spring.boot.dao.model.Hello;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuluming on 2017/2/17.
 */
@Service
public class HelloService extends ServiceImpl<HelloMapper, Hello> {

    @Autowired
    private HelloMapper helloMapper;


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int delete(Hello hello) throws BusinessException {
//        int i= helloMapper.insert(name);
//        System.out.println("数量="+i);
        //baomidou
        return helloMapper.deleteById(hello.getId());
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int update(Hello hello) throws BusinessException {
//        int i= helloMapper.insert(name);
//        System.out.println("数量="+i);
        //baomidou
        return helloMapper.updateById(hello);
    }

    public Hello selectById(Hello hello) throws BusinessException {
//        int i= helloMapper.insert(name);
//        System.out.println("数量="+i);
        //baomidou
        return helloMapper.selectById(hello.getId());
    }

    public List<Hello> selectList(String name) throws BusinessException {
//        int i= helloMapper.insert(name);
//        System.out.println("数量="+i);
        //baomidou
        EntityWrapper<Hello> wrapper = new EntityWrapper<>();
        wrapper.like("name",name);
        System.out.println(wrapper.getSqlSegment());
        return helloMapper.selectList(wrapper);
    }


//    public int update(String name,Long id){
//        return helloMapper.update(name,id);
//    }

    public List<Hello> findByName(String name) {
        return helloMapper.findByName(name);
    }

    public int addHello(Hello hello) {
        return helloMapper.addHello(hello);
    }


}
