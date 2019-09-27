package com.king;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName: springBoot_quick
 * @Package: com
 * @ClassName: kingMySpringBootApplication
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/26 20:12
 * @Version: 1.0
 */
//声明该类是一个springboot的引导类
@SpringBootApplication
public class MySpringBootApplication {

    public static void main(String[] args) {
        //run方法 表示运行springboot的引导类  run参数就是springboot引导类的字节码对象
        SpringApplication.run(MySpringBootApplication.class);
    }
}
