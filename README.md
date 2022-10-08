# 楔子
BMS初始版本是在初学Java时使用Java图形开发工具之一的swing及原生Java语言开发。介于当时技术有限，整个系统有很多写得非常糟糕的地方。

在时隔一年半之后，突然又对Java图形化界面心血来潮，便萌生了在此前的基础上，开发BMS2.0。

# 简介
BMS2.0采用SpringBoot + JavaFX + MybatisPlus开发。

# 工具版本
- Spring Boot：2.7.3
- Mybatis Plus：3.5.2
- MySQL：8.0.30

# 核心组件
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>de.roskenet</groupId>
        <artifactId>springboot-javafx-support</artifactId>
        <version>2.1.6</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.5.2</version>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-generator</artifactId>
        <version>3.5.3</version>
    </dependency>
</dependencies>
```

# 启动
运行BmsApplication（与常规Spring Boot应用启动方式一样）