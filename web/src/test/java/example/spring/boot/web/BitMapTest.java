package example.spring.boot.web; /**
 * Created by liuluming on 2017/5/1.
 */

import org.apache.commons.lang.StringUtils;

import java.util.BitSet;

/**
 * ip黑/白名单工具接口, 请为本interface实现一个基于内存的ip黑/白名单具体实现类<br/>
 * 要求’isInList’操作为常数级时间复杂度<br/>
 * 要求’isInList’内部操作完全基于内存，不得有网络或文件读取; 对象初始化部分如构造函数则不受此限制(如初始化时可从文件中load ip名单列表)<br/>
 * 程序设计上，请在满足上述条件的前提下，让此工具所能支持的ip列表数量尽可能大(甚至能否覆盖整个ipv4地址空间?), 内存占用尽可能小；
 * 此工具可能在多线程环境被使用
 */
public class BitMapTest {


    private String[] bitArray;

    private BitSet bitSet;


    public BitMapTest() {

        bitArray = new String[]{"172.168.1.1","172.168.1.2","172.168.1.3","172.168.1.4"};

        bitSet = new BitSet(4);

        //将数组内容组bitmap
        for (int i = 0; i < bitArray.length; i++) {
            bitSet.set(bitArray[i].hashCode());
        }

    }

    boolean isInList(String ip) throws Exception {
        if (StringUtils.isEmpty(ip)) {
            throw new Exception("参数不允许为null");
        }
       return bitSet.get(ip.hashCode());

    }

    public static void main(String[] args) {
        BitMapTest test=new BitMapTest();
        try {
            boolean isInList=test.isInList("172.168.1.5");
            System.out.println("是否存在："+isInList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
