package example.spring.boot.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.spring.boot.dao.model.Hello;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by liuluming on 2017/2/14.
 */
@Mapper
//@CacheConfig(cacheNames="hello")
public interface HelloMapper extends BaseMapper<Hello> {

    //    @Select("select * from hello where name like #{name}")
    List<Hello> findByName(@Param("name") String name);

    int addHello(@Param("hello") Hello hello);


    @Update("update user set name=#{name}, date_modify=now() where id=#{id}")
    int update(@Param("name") String name, @Param("id") Long id);
}

