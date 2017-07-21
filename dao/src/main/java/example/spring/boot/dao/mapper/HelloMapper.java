package example.spring.boot.dao.mapper;

import example.spring.boot.dao.model.Hello;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by liuluming on 2017/2/14.
 */
@Mapper
//@CacheConfig(cacheNames="hello")
public interface HelloMapper extends BaseMapper<Hello> {

    @Select("select * from hello where name like #{name}")
    List<Hello> findByName(@Param("name") String name);


    @Update("update user set name=#{name}where id=#{id}")
    int updateName(@Param("name") String name, @Param("id") Long id);
}

