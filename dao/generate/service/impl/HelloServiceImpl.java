package service.impl;

import entity.Hello;
import mapper.HelloMapper;
import service.HelloService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuluming
 * @since 2017-07-20
 */
@Service
public class HelloServiceImpl extends ServiceImpl<HelloMapper, Hello> implements HelloService {
	
}
